package io.davidosemwota.storage

import com.google.common.truth.Truth.assertThat
import io.davidosemwota.storage.responses.FactsApiResponse
import io.davidosemwota.storage.utils.FactsResponse
import io.davidosemwota.storage.utils.getJson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

internal class FactsApiServiceTest  {

    object MockResponses {
        const val factApiResponse = "mock-responses/get-random-fact-status200.json"
        const val factApiErrorResponse = "mock-responses/get-random-fact-error.json"
    }

    private lateinit var service: FactsApiService
    private lateinit var mockWebServer: MockWebServer
    private val HTTP_GET_REQUEST = "GET"

    @BeforeEach
    fun initialise() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FactsApiService::class.java)
    }

    @AfterEach
    fun reset() {
        mockWebServer.shutdown()
    }

    @Test
    fun `request random Fact API confirm request path & method`() = runBlocking {
        val expectedRequestUrl = "/" + FactsApiService.RANDOM_FACT_ENDPOINT
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson(MockResponses.factApiResponse))
            }
        }

        val response = service.getRandomFact()
        val request = mockWebServer.takeRequest()
        assertThat(request.path).isEqualTo(expectedRequestUrl)
        assertThat(request.method).isEqualTo(HTTP_GET_REQUEST)
    }

    @Test
    fun `request random Fact API success confirm request response`() = runBlocking {
        val expectedRequestUrl = "/" + FactsApiService.RANDOM_FACT_ENDPOINT
        val id = FactsResponse.getId()
        val category = FactsResponse.getCategory()
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson(MockResponses.factApiResponse))
            }
        }

        val response: FactsApiResponse? = service.getRandomFact().body()
        mockWebServer.takeRequest()
        assertThat(response?.contents?.id).isEqualTo(id)
        assertThat(response?.contents?.category).isEqualTo(category)
    }

    @Test
    fun `request random Fact API error response`() = runBlocking {

        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson(MockResponses.factApiErrorResponse))
            }
        }

        val response: FactsApiResponse? = service.getRandomFact().body()
        mockWebServer.takeRequest()

        assertThat(response?.contents).isNull()
    }
}