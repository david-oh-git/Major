package io.davidosemwota.storage

import io.davidosemwota.storage.responses.FactsApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DefaultRepository(
    private val dispatcher: CoroutineDispatcher,
    private val factsDataSource: FactsDataSource
) : MajorRepository {

    override suspend fun getRandomFact(): Flow<Result<FactsApiResponse?>> = withContext(
        dispatcher
    ) {
        return@withContext factsDataSource.getRandomFact()
    }
}