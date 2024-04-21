package es.imovil.arquitectura.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import es.imovil.arquitectura.data.CourseRepository
import es.imovil.arquitectura.model.Course

class CourseDetailsViewModel(val repository: CourseRepository) : ViewModel()
{
    private val courseName = MutableLiveData<String>()

    val mCourse: LiveData<Course> = courseName.switchMap {
            name -> repository.getCourseByName(name).asLiveData()
    }

    fun setCourse(coursename: String) {
        courseName.value = coursename
    }

    class CourseDetailsViewModelFactory(private val repository: CourseRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CourseDetailsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CourseDetailsViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}