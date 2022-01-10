package io.davidosemwota.storage

import io.davidosemwota.storage.responses.FactsApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface FactsApiService {

    @GET(RANDOM_FACT_ENDPOINT)
    suspend fun getRandomFact(): Response<FactsApiResponse>

    companion object {
        const val RANDOM_FACT_ENDPOINT = "fact/random"
    }
}