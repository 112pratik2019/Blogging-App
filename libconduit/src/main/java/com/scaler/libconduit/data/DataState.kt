package com.scaler.libconduit.data

sealed class DataState<out R> {

    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val statusCode:String,val statusDescription:String) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}