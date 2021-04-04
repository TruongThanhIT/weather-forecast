package com.thanht.data.cache

import android.content.Context

object SharePrefFactory {

    private const val SP_NAME_CORE = "sp_core"

    fun create(context: Context) = createCoreSharedPref(context, SP_NAME_CORE)
}
