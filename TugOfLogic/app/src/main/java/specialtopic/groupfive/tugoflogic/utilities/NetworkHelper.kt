package specialtopic.groupfive.tugoflogic.utilities

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

class NetworkHelper {
    companion object {
        @RequiresApi(Build.VERSION_CODES.M)
        fun isNetworkConnected(app: Application): Boolean {
            //1
            val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            //2
            val activeNetwork = connectivityManager.activeNetwork
            //3

            val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
            //4
            return networkCapabilities != null &&
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        }
    }
}