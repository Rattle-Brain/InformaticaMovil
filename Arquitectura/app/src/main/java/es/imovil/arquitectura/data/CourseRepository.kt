package es.imovil.arquitectura.data

import es.imovil.arquitectura.model.Course
import es.imovil.arquitectura.model.CourseDAO
import kotlinx.coroutines.flow.Flow

class CourseRepository(private val courseDAO: CourseDAO) {


    fun getCourseNames() = courseDAO.getNames()

    fun getCourseByName(cname: String): Flow<Course> {
        return courseDAO.getCourseByName(cname)
    }

    suspend fun insertCourse(course: Course) = courseDAO.insertCourse(course)

    suspend fun deleteCourseByName(cname: String) = courseDAO.deleteCourse(cname)
}
