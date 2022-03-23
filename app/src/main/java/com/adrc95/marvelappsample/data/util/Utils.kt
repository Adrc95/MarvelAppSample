package com.adrc95.marvelappsample.data.util

import android.util.Log
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object Utils {
    private const val MD5 = "MD5"

    /**
     * Method to create the MD5
     *
     * @param text Current text
     */
    @Suppress("MagicNumber")
    fun md5(text: String): String {
        try {
            // Create MD5 Hash
            val digest: MessageDigest = MessageDigest.getInstance(MD5)
            digest.update(text.toByteArray())
            val messageDigest: ByteArray = digest.digest()

            // Create Hex String
            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2) {
                    h = "0$h"
                    }
                    hexString.append(h)
                }
                return hexString.toString()
            } catch (e: NoSuchAlgorithmException) {
            Log.e(e.cause.toString(), e.message.toString())
            }
        return ""
    }
}
