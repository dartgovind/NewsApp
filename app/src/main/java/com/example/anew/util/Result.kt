package com.example.anew.util

sealed class Result<out T> {
    object Idle : Result<Nothing>()
    object Loading :Result<Nothing>()
    data class Success<T>(val data:T):Result<T>()
    data class Error(val message: String):Result<Nothing>()
}