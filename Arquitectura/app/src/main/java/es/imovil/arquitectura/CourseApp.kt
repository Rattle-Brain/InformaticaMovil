package es.imovil.arquitectura

import android.app.Application
import es.imovil.arquitectura.data.CourseRepository
import es.imovil.arquitectura.model.CourseDatabase

class CourseApp: Application() {

    val courseDatabase by lazy {
        CourseDatabase.getInstance(this)
    }

    val repository by lazy {
        CourseRepository(courseDatabase!!.courseDao())
    }
}