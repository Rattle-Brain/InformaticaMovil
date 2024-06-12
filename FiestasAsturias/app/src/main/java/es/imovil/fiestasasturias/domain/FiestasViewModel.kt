package es.imovil.fiestasasturias.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import es.imovil.fiestasasturias.data.ApiResult
import es.imovil.fiestasasturias.data.FiestaRepository
import es.imovil.fiestasasturias.ui.FiestasUIState
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FiestasViewModel(val repository: FiestaRepository): ViewModel()
{
    private val _fiestasUIStateObservable = MutableLiveData<FiestasUIState>()
    val fiestasUIStateObservable: LiveData<FiestasUIState> get() = _fiestasUIStateObservable

    init {
        getFiestasList()
    }

    fun getFiestasList() {
        viewModelScope.launch {
            repository.updateFieStatusData().map {
                result ->
                when (result) {
                    is ApiResult.Success -> FiestasUIState.Success(result.data!!)
                    is ApiResult.Error -> FiestasUIState.Error(result.message!!)
                    is ApiResult.Loading -> FiestasUIState.Loading()
                    else -> FiestasUIState.Error("Unknown error")
                }
            }.collect {
                _fiestasUIStateObservable.value = it
            }
        }
    }

    class FiestasViewModelFactory(private val repository: FiestaRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FiestasViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FiestasViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}