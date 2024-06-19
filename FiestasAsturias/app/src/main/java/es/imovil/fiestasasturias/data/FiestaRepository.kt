package es.imovil.fiestasasturias.data

import es.imovil.fiestasasturias.model.Fiesta
import es.imovil.fiestasasturias.model.FiestaDAO
import es.imovil.fiestasasturias.network.RestApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

    suspend fun deleteFiestas() {
        CoroutineScope(Dispatchers.IO).launch {
            fiestaDAO.deleteAllFiestas()
        }
    }

    fun updateFiestasInfo() =
        // Se crea un flujo
        flow {
            // Se realiza la petición al servicio
            try {
                val fiestas = RestApi.retrofitService.getFiestasFromLink()

                deleteFiestas()

                fiestas.fiestas.map { insertFiesta(it) }

                emit(ApiResult.Success(fiestas))

            } catch (e: Exception) {
                emit(ApiResult.Error("Error en el acceso a la API"))
            }

            // El flujo se ejecuta en el hilo I/O
        }.flowOn(Dispatchers.IO)

}