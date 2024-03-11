import platform.Foundation.*
import kotlinx.cinterop.*

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
    }
