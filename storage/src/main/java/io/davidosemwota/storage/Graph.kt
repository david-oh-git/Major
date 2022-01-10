package io.davidosemwota.storage

import io.davidosemwota.storage.remote.RemoteFactsDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object Graph {

    val ioDispatcher: CoroutineDispatcher
        get() = Dispatchers.IO

    val repository: MajorRepository by lazy {
        DefaultRepository(
            ioDispatcher,
            RemoteFactsDataSource(RetrofitApiFactory.provideFactApiService())
        )
    }
}