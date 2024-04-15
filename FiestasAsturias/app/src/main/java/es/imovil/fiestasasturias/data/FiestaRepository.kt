package es.imovil.fiestasasturias.data

import es.imovil.fiestasasturias.model.FieStatus
import es.imovil.fiestasasturias.network.RestApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
class FiestaRepository{

    fun updateFieStatusData(): Flow<ApiResult<FieStatus>> =
        // Se crea un flujo
        flow {
            // Se realiza la petición al servicio
            try {
                // Respuesta correcta
                emit(ApiResult.Loading(null))
                val busStatus = RestApi.retrofitService.getStatusInfo()
                // Se emite el estado Succes y se incluyen los datos recibidos
                emit(ApiResult.Success(busStatus))
            } catch (e: Exception) {
                // Error en la red
                // Se emite el estado de Error con el mensaje que lo explica
                emit(ApiResult.Error(e.toString()))
            }
            // El flujo se ejecuta en el hilo I/O
        }.flowOn(Dispatchers.IO)
}