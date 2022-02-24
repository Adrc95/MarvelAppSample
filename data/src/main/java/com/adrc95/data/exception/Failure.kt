package com.adrc95.data.exception

sealed class Failure(val exception: Exception = Exception("Failure")) {
    object NetworkConnection : Failure()
    object ServerError : Failure()
    object UnknownRemoteError : Failure()
    object LocalError : Failure()
}