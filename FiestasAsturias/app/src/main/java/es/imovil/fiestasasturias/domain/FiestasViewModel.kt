package es.imovil.fiestasasturias.domain

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

class FiestasViewModel(val repository: FiestaRepository): ViewModel()
{
    private val _fiestasUIStateObservable = MutableLiveData<FiestasUIState>()
    val fiestasUIStateObservable: LiveData<FiestasUIState> get() = _fiestasUIStateObservable

    val query = MutableLiveData<String>()
    val fiestasFiltradas: MediatorLiveData<List<Fiesta>> = MediatorLiveData()

    private val fiestasNames: LiveData<List<Fiesta>> = query.switchMap { query ->
        val temp: List<String> = query.chunked(1)
        val exclude: List<String> = listOf("a","e","i","o","u","á","é","í","ó","ú")
        var name = ""
        for (c in temp) {
            name += if(exclude.contains(c)) "_"  else c
        }
        repository.getFiestasListByName(name).asLiveData()
    }



    // Inicializacion del viewmodel
    init {

       query.value = ""

        fiestasFiltradas.addSource(fiestasNames) { list ->
            fiestasFiltradas.value = list
        }
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