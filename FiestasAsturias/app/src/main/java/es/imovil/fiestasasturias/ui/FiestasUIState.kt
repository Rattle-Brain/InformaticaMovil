package es.imovil.fiestasasturias.ui

import es.imovil.fiestasasturias.model.Fiestas
import es.imovil.fiestasasturias.status.AppStatus

sealed class FiestasUIState (val state: AppStatus) {
    data class Success(val fiestas: Fiestas): FiestasUIState(AppStatus.SUCCESS)
    data class Error (val message: String): FiestasUIState(AppStatus.ERROR)
    data class Loading (val loading: Boolean = true): FiestasUIState(AppStatus.LOADING)
}