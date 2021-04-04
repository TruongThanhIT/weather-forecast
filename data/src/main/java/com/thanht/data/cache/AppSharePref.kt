package com.thanht.data.cache

interface AppSharePref {
    var appId: String
}
private const val APP_ID = "SP_APP_ID"

class AppSharePrefImpl(
    private val pref: CoreSharedPref
) : AppSharePref {

    override var appId: String
        get() = pref.getString(APP_ID) ?: "60c6fbeb4b93ac653c492ba806fc346d"
        set(value) = pref.saveString(APP_ID, value)
}
