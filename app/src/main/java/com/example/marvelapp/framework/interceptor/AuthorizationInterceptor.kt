package com.example.marvelapp.framework.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.util.Calendar

class AuthorizationInterceptor(

    private val calendar: Calendar
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val requestUrl = chain.request().url

        val ts = (calendar.timeInMillis / 1000L).toString()
        val hash = "$ts$PRIVETE_KEY$PUBLIC_KEY".md5()

        val newUrl = requestUrl.newBuilder()
            .addQueryParameter(QUERY_PARAMETER_TIMESTAMP,ts)
            .addQueryParameter(QUERY_PARAMETER_API_KEY, PUBLIC_KEY)
            .addQueryParameter(QUERY_PARAMETER_HASH, hash)
            .build()


        return chain.proceed(request.newBuilder().url(newUrl).build())
    }

    private fun String.md5(): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    }
    companion object{
        const val PUBLIC_KEY = "bb6894dced9cb987056a1f7c410e6d38"
        const val PRIVETE_KEY = "525c6e06faa06762a6f821a4d45a55bc21923390"
        const val QUERY_PARAMETER_TIMESTAMP = "ts"
        const val QUERY_PARAMETER_API_KEY = "apikey"
        const val QUERY_PARAMETER_HASH = "hash"
    }
}