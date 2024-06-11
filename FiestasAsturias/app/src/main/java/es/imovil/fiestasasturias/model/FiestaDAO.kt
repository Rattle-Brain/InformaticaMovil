package es.imovil.fiestasasturias.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface FiestaDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFiesta(fiesta: Fiesta)

    @Query("DELETE FROM fiesta_table WHERE nombre = :name")
    suspend fun deleteFiesta(name: String)

    @Query("SELECT * FROM fiesta_table WHERE nombre LIKE :name")
    fun getFiestaByName(name: String):Flow<Fiesta>

    @Query("SELECT nombre  FROM fiesta_table")
    fun getNames(): Flow<List<String>>
}