package es.imovil.fiestasasturias.data

import es.imovil.fiestasasturias.model.Fiesta
import es.imovil.fiestasasturias.model.FiestaDAO
import es.imovil.fiestasasturias.model.Fiestas
import es.imovil.fiestasasturias.network.RestApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class FiestaRepository(private val fiestaDAO: FiestaDAO){

    fun getFiestaByName(nombreFiesta: String) = fiestaDAO.getFiestaByName(nombreFiesta)
    suspend fun deleteFiesta(nombreFiesta: String) {
        CoroutineScope(Dispatchers.IO).launch {
            fiestaDAO.deleteFiesta(nombreFiesta)
        }
    }
    suspend fun insertFiesta(fiesta: Fiesta) {
        CoroutineScope(Dispatchers.IO).launch {
            fiestaDAO.insertFiesta(fiesta)
        }
    }
    fun updateFieStatusData(): Flow<ApiResult<Fiestas>> =
        // Se crea un flujo
        flow {
            // Se realiza la petición al servicio
            try {
                // Respuesta correcta
                emit(ApiResult.Loading(null))
                val busStatus = RestApi.retrofitService.getFiestasInfo()
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