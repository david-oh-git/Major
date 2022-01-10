package io.davidosemwota.storage

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApiFactory {

    private const val factsApi = "https://api.fungenerators.com/"

    /**
     *  Provider method for [HttpLoggingInterceptor] for HTTP client
     *
     *  @return Instance of http interceptor
     */
    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    /**
     *  Provides instance of [OkHttpClient]
     *
     *  @return Instance of http client
     */
    private fun provideHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(interceptor)
        }
        return clientBuilder.build()
    }

    /**
     * Provider method for Here Maps [Retrofit]
     * @return Instance of retrofit
     */
    private fun provideRetrofitBuilder() =
        Retrofit.Builder()
            .baseUrl(factsApi)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideHttpClient(provideHttpLoggingInterceptor()))
            .build()

    fun provideFactApiService(): FactsApiService =
        provideRetrofitBuilder().create(FactsApiService::class.java)
}