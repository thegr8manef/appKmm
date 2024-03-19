
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

actual class Utils actual constructor(private val context: Any?) {
    val androidContext = context as? Context


    actual fun isConnected(): Boolean {
        val connectivityManager =
            androidContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
    actual suspend fun stringToMD5(s: String): String {
        try {
            val digest = MessageDigest.getInstance("MD5")
            digest.update(s.toByteArray())
            val messageDigest = digest.digest()
            val hexString = StringBuilder()
            for (i in messageDigest.indices) {
                var h = Integer.toHexString(0xFF and messageDigest[i].toInt())
                while (h.length < 2) {
                    h = "0$h"
                }
                hexString.append(h)
            }
            println("***********************************${hexString.toString().toUpperCase()}")
            return hexString.toString().toUpperCase()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }
}