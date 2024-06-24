package es.imovil.fiestasasturias.data

import androidx.annotation.OptIn
import androidx.datastore.core.DataStore
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import es.imovil.fiestasasturias.model.Fiesta
import es.imovil.fiestasasturias.model.FiestaDAO
import es.imovil.fiestasasturias.network.RestApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.util.prefs.Preferences

private const val TAG = "REPOSITORY-DEBUG"

class FiestaRepository(private val fiestaDAO: FiestaDAO){

    fun getFiestaByName(nombreFiesta: String) = fiestaDAO.getFiestaByName(nombreFiesta)
    fun getFiestas() = fiestaDAO.getFiestas()

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
                val fiestas = RestApi.retrofitService.getFiestasFromLink()

                fiestas.fiestas.map { insertFiesta(it) }

                emit(ApiResult.Success(fiestas))

            } catch (e: Exception) {
                emit(ApiResult.Error("Error en el acceso a la API"))
            }
        }.flowOn(Dispatchers.IO)

}