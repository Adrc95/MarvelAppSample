package com.adrc95.marvelappsample.data.server

import arrow.core.Either
import com.adrc95.data.exception.Failure
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okio.IOException
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

open class APIService<T> constructor(
    serviceClass: Class<T>,
    private val baseURL: String,
    private val converterFactory: Converter.Factory,
    private val interceptors: Array<Interceptor>
) {
    var service: T

    companion object {
        private const val READ_TIMEOUT = 30L
        private const val WRITE_TIMEOUT = 30L
        private const val CONNECT_TIMEOUT = 30L
    }

    init {
        service = initApiService().create(serviceClass)
    }

    private fun initApiService(): Retrofit {
        val client = OkHttpClient.Builder()
        client.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        client.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        client.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)

        interceptors.map { client.addInterceptor(it) }

        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(converterFactory)
            .client(client.build())
            .build()
    }

    suspend fun <R> execute(apiCall: suspend () -> R): Either<Failure, R> =
        try {
            Either.Right(apiCall.invoke())
        } catch (throwable: Throwable) {
            manageAPIError(throwable)
        }

    private fun <R> manageAPIError(throwable: Throwable): Either<Failure, R> =
        when (throwable) {
            is IOException -> Either.Left(Failure.NetworkConnection)
            is HttpException -> Either.Left(Failure.ServerError)
            else -> Either.Left(Failure.UnknownRemoteError)
        }
}
