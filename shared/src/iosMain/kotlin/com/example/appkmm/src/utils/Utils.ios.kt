import platform.Foundation.*
import kotlinx.cinterop.*
import kotlin.native.concurrent.*
actual class Utils actual constructor(context: Any?) {
    @OptIn(ExperimentalForeignApi::class)
    actual fun isConnected() : Boolean {
        val reachability = try {
        SCNetworkReachabilityCreateWithName(null, "www.apple.com")
    } catch (e: Throwable) {
        null
    }
    if (reachability == null) return false

    val flags = memScoped {
        val ptr = alloc<SCNetworkReachabilityFlagsVar>()
        if (!SCNetworkReachabilityGetFlags(reachability, ptr.ptr)) {
            return false
        }
        ptr.value
    }
    return flags.contains(kSCNetworkReachabilityFlagsReachable) &&
    !flags.contains(kSCNetworkReachabilityFlagsConnectionRequired)
}

    actual suspend fun stringToMD5(s: String): String {
        val data = s.dataUsingEncoding(NSUTF8StringEncoding) ?: return ""
        val hash = data.withUnsafeBytes { bytes ->
            val buffer = ByteArray(CC_MD5_DIGEST_LENGTH.toUInt())
            CC_MD5(bytes, data.length.toUInt(), buffer.refTo(0))
            buffer.joinToString("") { "%02x".format(it) }
        }
        println("***********************************$hash")
        return hash
    }
}
