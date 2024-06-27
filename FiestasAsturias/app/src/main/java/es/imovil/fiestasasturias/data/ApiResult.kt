package es.imovil.fiestasasturias.data

import es.imovil.fiestasasturias.status.AppStatus

/**
 * Resultados de la Api de red. Marcamos segun sea el resultado
 */
sealed class ApiResult <out T> (val status: AppStatus, val data: T?, val message: String?) {
    data class Success<out R>(val _data: R): ApiResult<R> (
        status = AppStatus.SUCCESS,
        data = _data,
        message = null
    )

    data class Error(val exception: String): ApiResult<Nothing>(
        status = AppStatus.ERROR,
        data = null,
        message = exception
    )

    data class Loading<out R>(val _data: R?): ApiResult<R>(
        status = AppStatus.LOADING,
        data = _data,
        message = "Loading..."
    )
}
