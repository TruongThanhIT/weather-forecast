package com.thanht.data.cache

import android.annotation.SuppressLint
import android.util.Base64
import java.security.Key
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

private const val ALGORITHM = "AES"
private const val ENCRYPT_KEY = "thanh-truong"

@SuppressLint("GetInstance")
internal fun encrypt(value: String): String {
    return try {
        Cipher.getInstance(ALGORITHM)
            .apply { init(Cipher.ENCRYPT_MODE, generateKey) }
            .doFinal(value.toByteArray(charset("utf-8")))
            .let { Base64.encodeToString(it, Base64.DEFAULT) }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }?.let { removeCRLF(it) } ?: value
}

@SuppressLint("GetInstance")
internal fun decrypt(value: String): String {
    return try {
        Cipher.getInstance(ALGORITHM)
            .apply { init(Cipher.DECRYPT_MODE, generateKey) }
            .doFinal(Base64.decode(value, Base64.DEFAULT))
            .let { String(it, charset("utf-8")) }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }?.let { removeCRLF(it) } ?: value
}

private val generateKey: Key by lazy {
    SecretKeySpec(ENCRYPT_KEY.toByteArray(), ALGORITHM)
}

/**
 * replace default \r\n (CRLF)
 * link: https://commons.apache.org/proper/commons-codec/archives/1.10/apidocs/org/apache/commons/codec/binary/Base64.html
 */
private fun removeCRLF(str: String) = str.replace("[\\r\\n]".toRegex(), "")
