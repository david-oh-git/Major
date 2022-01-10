package io.davidosemwota.domain

import io.davidosemwota.storage.Graph

object Graph {

    fun getFactUseCase(): FactUseCase = FactUseCase(Graph.ioDispatcher, Graph.repository)
}