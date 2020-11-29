package specialtopic.groupfive.tugoflogic.utilities

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket

class NetworkHelper {
    companion object {
        var API_ENDPOINT_URL = "http://127.0.0.1:5000"
        lateinit var mSocket: Socket;
        private var config_file = "config.txt"

        @RequiresApi(Build.VERSION_CODES.M)
        fun isNetworkConnected(app: Application): Boolean {
            //1
            val connectivityManager =
                app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            //2
            val activeNetwork = connectivityManager.activeNetwork
            //3
            val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
            //4
            return networkCapabilities != null &&
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        }

        fun getConfiguration(context: Context) {
            try {
                val configText = FileHelper.getConfigFromAssets(context, config_file)
                if (configText.isNotBlank()) {
                    API_ENDPOINT_URL = configText.trim()
                }
            } catch (e: Exception) {
                Log.d("error", e.message.toString())
            }
        }

        fun initSocket() {
            try {
                mSocket = IO.socket(API_ENDPOINT_URL)
            } catch (e: Exception) {
                Log.d("error", e.message.toString())
            }
        }

        fun openConnect() {
            try {
                mSocket.connect()
            } catch (e: Exception) {
                Log.d("error", e.message.toString())
            }
        }

        fun closeConnect() {
            try {
                mSocket.close()
            } catch (e: Exception) {
                Log.d("error", e.message.toString())
            }
        }
    }
}