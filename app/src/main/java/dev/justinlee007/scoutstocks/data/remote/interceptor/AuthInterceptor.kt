package dev.justinlee007.scoutstocks.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $POLYGON_API_KEY")
            .build()
        return chain.proceed(newRequest)
    }

    companion object {
        private const val POLYGON_API_KEY = "hkAashRfwpV8plrC6f4gb2tpP4U6BHce"
    }
}