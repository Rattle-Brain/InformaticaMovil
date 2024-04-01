package es.imovil.arquitectura.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import es.imovil.arquitectura.data.CourseRepository

class CourseViewModelFactory(private val repository: CourseRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CourseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CourseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class CourseViewModel(private val repository: CourseRepository): ViewModel() {
    val courseNames: LiveData<List<String>> by lazy {
        repository.getCourseNames().asLiveData()
    }




}
