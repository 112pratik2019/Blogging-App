package com.scaler.microblogs.repository

import android.util.Log
import com.scaler.libconduit.data.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

open class BaseRepository {

    fun <T> getData(apiCall: suspend () -> T): Flow<DataState<T>> = flow {
        emit(DataState.Loading)
        try {
            val response = apiCall.invoke()
            emit(DataState.Success(response))
        } catch (e: Exception) {
            Log.v("exception----", e.message.toString())
            emit(DataState.Error("01", "Something went wrong!!!"))
        }
    }.flowOn(Dispatchers.IO)

}