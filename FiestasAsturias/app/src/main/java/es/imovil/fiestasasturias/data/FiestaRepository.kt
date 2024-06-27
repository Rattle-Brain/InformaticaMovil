package es.imovil.fiestasasturias.data

import es.imovil.fiestasasturias.model.Fiesta
import es.imovil.fiestasasturias.model.FiestaDAO
import es.imovil.fiestasasturias.network.RestApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

/**
 * Repositorio de las fiestas
 */
class FiestaRepository(private val fiestaDAO: FiestaDAO){

    fun getFiestaByName(nombreFiesta: String) = fiestaDAO.getFiestaByName(nombreFiesta)
    fun getFiestas() = fiestaDAO.getFiestas()

    fun getFiestasListByName(input: String) = fiestaDAO.getFiestasListByName(input)

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

        flow {
            try {
                // Obtenemos la lista de fiestas del servicio red
                val items = RestApi.retrofitService.getFiestasFromLink()

                items.fiestas.map { insertFiesta(it) }

                emit(ApiResult.Success(items))

            } catch (e: Exception) {
                e.printStackTrace()
                emit(ApiResult.Error("Error en el acceso a la API"))
            }
        }.flowOn(Dispatchers.IO)
}