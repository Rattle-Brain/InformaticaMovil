package es.imovil.fiestasasturias.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import es.imovil.fiestasasturias.data.FiestaRepository
import es.imovil.fiestasasturias.model.Fiesta


class FiestasDetailsViewModel(fiestaRepo: FiestaRepository): ViewModel() {

    private val nombreFiesta = MutableLiveData<String>()
    // Usamos el nombre como trigger para
    val location: LiveData<Fiesta> = nombreFiesta.switchMap {
            name -> fiestaRepo.getFiestaByName(name).asLiveData()
    }

    // MÉTODOS
    fun setFiesta(name: String) {
        nombreFiesta.value = name
    }
}