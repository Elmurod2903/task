package uz.elmurod.newsapi.checkinternet

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo

class Connect {
    companion object {
        fun isConnectToInternet(context: Context): Boolean {
            val connectManager: ConnectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var networkInfo = connectManager.activeNetworkInfo

            return (networkInfo == null || !networkInfo.isConnected || !networkInfo.isAvailable)
        }

    }
}