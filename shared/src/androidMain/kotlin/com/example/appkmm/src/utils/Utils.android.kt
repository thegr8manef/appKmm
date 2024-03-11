
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

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
}