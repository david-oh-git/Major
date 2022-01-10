package io.davidosemwota.storage

import io.davidosemwota.storage.responses.FactsApiResponse
import kotlinx.coroutines.flow.Flow

interface MajorRepository {

    suspend fun getRandomFact(): Flow<Result<FactsApiResponse?>>

}