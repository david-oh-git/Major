package io.davidosemwota.domain

import io.davidosemwota.storage.MajorRepository
import io.davidosemwota.storage.Result
import io.davidosemwota.storage.responses.FactsApiResponse
import io.davidosemwota.storage.succeeded
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext


data class FactItem(
    val fact: String,
    val id: String,
    val category: String,
    val subcategory: String
)

class FactUseCase(
    private val dispatcher: CoroutineDispatcher,
    private val repository: MajorRepository
) : UseCase<Unit, Flow<Result<FactItem>>> {

    suspend fun execute(): Flow<Any> = withContext(dispatcher) {
        return@withContext repository.getRandomFact().map { result ->
            when {
                result.succeeded -> {
                    val response = ((result as Result.Success).data as FactsApiResponse).contents
                    FactItem(
                        response.fact,
                        response.id,
                        response.category,
                        response.subcategory
                    )
                }
                result is Result.Error -> {
                    result.exception
                }
                else -> {
                    Exception()
                }
            }

        }
    }

}