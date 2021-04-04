package com.thanht.data.util

import com.thanht.data.net.ApiConnection

inline fun <reified T : Any> ApiConnection.provideService(): T = retrofit.create(T::class.java)