package io.davidosemwota.storage

import io.davidosemwota.storage.responses.FactsApiResponse
import kotlinx.coroutines.flow.Flow
import io.davidosemwota.storage.Result

interface FactsDataSource {

    suspend fun getRandomFact(): Flow<Result<FactsApiResponse?>>
}