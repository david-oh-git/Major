package io.davidosemwota.presentation.ui.fact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.davidosemwota.domain.FactItem
import io.davidosemwota.domain.FactUseCase
import io.davidosemwota.domain.Graph
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

data class FactViewState(
    val isLoading: Boolean = false,
    val onError: Boolean = false,
    val factItem: FactItem? = null
)

class FactViewModel(
    private val factUseCase: FactUseCase = Graph.getFactUseCase()
) : ViewModel() {

    private val _viewState = MutableStateFlow(FactViewState())

    val viewState: StateFlow<FactViewState>
        get() = _viewState

    init {
        fetchFact()
    }

    fun fetchFact() {
        viewModelScope.launch {
            _viewState.value = FactViewState(isLoading = true)

            factUseCase.execute().collect { result ->

                when(result) {
                    is FactItem -> {
                        _viewState.value = FactViewState(factItem = result)
                    }
                    is Exception -> {
                        _viewState.value = _viewState.value.copy(onError = true, isLoading = false)
                    }
                }
            }

        }
    }
}