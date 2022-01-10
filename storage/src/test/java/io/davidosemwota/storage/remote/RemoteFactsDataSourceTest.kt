package io.davidosemwota.storage.remote

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import io.davidosemwota.storage.FactsApiService
import io.davidosemwota.storage.FactsDataSource
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

internal class RemoteFactsDataSourceTest {

    @Mock
    lateinit var service: FactsApiService
    private lateinit var dataSource: FactsDataSource

    @BeforeEach
    fun initialise() = runBlocking {
        MockitoAnnotations.initMocks(this)
        service = mock()
        dataSource = RemoteFactsDataSource(service)
    }

    @AfterEach
    fun reset() {
    }

    @Test
    @DisplayName(
        "datasource.getRandomFact is called confirm  when service.getRandomFact"
    )
    fun getRandomFact(): Unit = runBlocking {

        dataSource.getRandomFact()

        verify(service).getRandomFact()
    }

    @Test
    @DisplayName(
        "when datasource.getRandomFact is not called, confirm service.getRandomFact isnt called"
    )
    fun getRandomFactII(): Unit = runBlocking {
        verify(service, never()).getRandomFact()
    }
}