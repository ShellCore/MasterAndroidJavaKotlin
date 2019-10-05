package com.shell.android.offlinemovies.data.remote

import com.shell.android.offlinemovies.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieDBApiModule {

    companion object {
        const val BASE_URL = BuildConfig.BASE_URL
        const val API_KEY = BuildConfig.API_KEY

        fun provideApiService(): MovieDBService {
            return provideRetrofit(BASE_URL, getClient())
                .create(MovieDBService::class.java)
        }

        private fun provideRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private fun getClient(): OkHttpClient {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = when (BuildConfig.BUILD_TYPE) {
                "debug" -> HttpLoggingInterceptor.Level.BODY
                else -> HttpLoggingInterceptor.Level.NONE
            }
            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(object : Interceptor {
                    override fun intercept(chain: Interceptor.Chain): Response {
                        var request = chain.request()
                        val url = request.url
                            .newBuilder()
                            .addQueryParameter(
                                "api_key",
                                API_KEY
                            )
                            .build()
                        request = request.newBuilder()
                            .url(url)
                            .build()
                        return chain.proceed(request)
                    }
                })
                .build()
        }
    }
}