package es.imovil.fiestasasturias.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import es.imovil.fiestasasturias.data.ApiResult
import es.imovil.fiestasasturias.data.FiestaRepository
import es.imovil.fiestasasturias.model.Fiesta
import es.imovil.fiestasasturias.ui.FiestasUIState
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * Viewmodelde la lista de fiestas
 */
class FiestasViewModel(val repository: FiestaRepository): ViewModel()
{
    // observable para cambiar el estado
    private val _fiestasUIStateObservable = MutableLiveData<FiestasUIState>()
    val fiestasUIStateObservable: LiveData<FiestasUIState> get() = _fiestasUIStateObservable

    // Query para la búsqueda por nombre, municipio o localidad
    val query = MutableLiveData<String>()
    val fiestasFiltradas: MediatorLiveData<List<Fiesta>> = MediatorLiveData()

    // Limitamos las fiestas
    private val fiestasNames: LiveData<List<Fiesta>> = query.switchMap {query ->
        repository.getFiestasListByName(query).asLiveData()
    }


    init {
        // Al inicio la query es empty
        query.value = ""

        // Filtramos fiestas si procede
        fiestasFiltradas.addSource(fiestasNames) { list ->
            fiestasFiltradas.value = list
        }

        // Actualizamos la lista de fiestas acorde y emitimos el resultado
        getFiestasList()
    }

    fun getFiestasList() {
        viewModelScope.launch {
            repository.updateFiestasInfo().map {
                result ->
                when (result) {

                    is ApiResult.Success -> FiestasUIState.Success(result.data!!)
                    is ApiResult.Error -> FiestasUIState.Error(result.message!!)
                    is ApiResult.Loading -> FiestasUIState.Loading()
                }
            }.collect {
                _fiestasUIStateObservable.value = it
            }
        }
    }


}

/**
 * Factoría de viewmodels
 */
class FiestasViewModelFactory(private val repository: FiestaRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(FiestasViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FiestasViewModel(repository) as T
            } else if (modelClass.isAssignableFrom(FiestasDetailsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FiestasDetailsViewModel(repository) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class")
        }
}