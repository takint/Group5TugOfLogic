package specialtopic.groupfive.tugoflogic.utilities

import android.content.Context
import java.io.BufferedReader

class FileHelper {
    companion object {
        fun getConfigFromAssets(context: Context, fileName: String): String {
            return context.assets.open(fileName).use {
                it.bufferedReader().use { bf: BufferedReader ->
                    bf.readText()
                }
            }
        }
    }
}