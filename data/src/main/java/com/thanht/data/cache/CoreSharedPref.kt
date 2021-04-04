package com.thanht.data.cache

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

fun createCoreSharedPref(context: Context, prefName: String): CoreSharedPref =
    CoreSharedPrefImpl(
        context.getSharedPreferences(
            prefName,
            Context.MODE_PRIVATE
        )
    )

interface CoreSharedPref {
    fun saveString(key: String, value: String?)
    fun getString(key: String, defaultValue: String? = null): String?

    fun saveInt(key: String, value: Int)
    fun getInt(key: String, defaultValue: Int = 0): Int

    fun saveLong(key: String, value: Long)
    fun getLong(key: String, defaultValue: Long = 0): Long

    fun saveBoolean(key: String, value: Boolean)
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean

    fun saveFloat(key: String, value: Float)
    fun getFloat(key: String, defaultValue: Float = 0f): Float

}

private class CoreSharedPrefImpl(private val pref: SharedPreferences) : CoreSharedPref {

    override fun saveString(key: String, value: String?) =
        pref.edit { putString(encrypt(key), value?.let(::encrypt)) }

    override fun getString(key: String, defaultValue: String?) =
        pref.getString(encrypt(key), defaultValue)?.let(::decrypt)

    override fun saveInt(key: String, value: Int) =
        pref.edit { putInt(encrypt(key), value) }

    override fun getInt(key: String, defaultValue: Int) =
        pref.getInt(encrypt(key), defaultValue)

    override fun saveLong(key: String, value: Long) =
        pref.edit { putLong(encrypt(key), value) }

    override fun getLong(key: String, defaultValue: Long) =
        pref.getLong(encrypt(key), defaultValue)

    override fun saveBoolean(key: String, value: Boolean) =
        pref.edit { putBoolean(encrypt(key), value)}

    override fun getBoolean(key: String, defaultValue: Boolean) =
        pref.getBoolean(encrypt(key), defaultValue)

    override fun saveFloat(key: String, value: Float) =
        pref.edit { putFloat(encrypt(key), value) }

    override fun getFloat(key: String, defaultValue: Float) =
        pref.getFloat(encrypt(key), defaultValue)
}
