package es.imovil.arquitectura.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: Course)

    @Query("DELETE FROM course_table WHERE name = :course")
    suspend fun deleteCourse(course:String)

    @Query("SELECT * FROM course_table WHERE name LIKE :name")
    fun getCourseByName(name:String): Flow<Course>

    @Query("SELECT name FROM course_table")
    fun getNames(): Flow<List<String>>
}