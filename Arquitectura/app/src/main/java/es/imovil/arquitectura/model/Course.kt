package es.imovil.arquitectura.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course_table")
data class Course(@PrimaryKey val name: String, val teacher:String, val description:String) {
}