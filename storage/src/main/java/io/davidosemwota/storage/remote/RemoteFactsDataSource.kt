package io.davidosemwota.storage.remote

import io.davidosemwota.storage.BaseDataSource
import io.davidosemwota.storage.FactsApiService
import io.davidosemwota.storage.FactsDataSource
import io.davidosemwota.storage.Result
import io.davidosemwota.storage.responses.FactsApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RemoteFactsDataSource(
    private val factsApiService: FactsApiService
) : FactsDataSource, BaseDataSource() {

    override suspend fun getRandomFact(): Flow<Result<FactsApiResponse?>> = flowOf(
        safeApiCall(
            call = { factsApiService.getRandomFact() },
            errorMsg = "Error fetching data.."
        )
    )
}