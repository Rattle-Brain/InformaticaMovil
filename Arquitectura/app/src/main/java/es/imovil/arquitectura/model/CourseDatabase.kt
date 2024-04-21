package es.imovil.arquitectura.model

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [Course::class], version = 1)
abstract class CourseDatabase: RoomDatabase() {
    abstract fun courseDao(): CourseDAO

    companion object {
        private var INSTANCE: CourseDatabase? = null

        fun getInstance(context: Context): CourseDatabase? {
            if (INSTANCE == null) {
                synchronized(CourseDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CourseDatabase::class.java, "course.db"
                    )
                        .addCallback(CALLBACK)
                        .build()
                }
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }

        private val CALLBACK = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                CoroutineScope(Dispatchers.IO).launch {
                    INSTANCE!!.courseDao().insertCourse(
                        Course("Android", "Javier", "Curso de Android")
                    )
                    INSTANCE!!.courseDao().insertCourse(
                        Course("iOS", "Javier", "Curso de iOS")
                    )
                }
            }
        }
    }
}