package es.imovil.fiestasasturias.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * DAO de la fiesta con las consultas pertinentes a la base de datos.
 */
@Dao
interface FiestaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFiesta(fiesta: Fiesta)

    @Query("DELETE FROM fiesta_table WHERE nombre = :name")
    suspend fun deleteFiesta(name: String)

    @Query("SELECT * FROM fiesta_table")
    fun getFiestas(): Flow<List<Fiesta>>
    @Query("SELECT * FROM fiesta_table WHERE nombre LIKE :name")
    fun getFiestaByName(name: String):Flow<Fiesta>

    @Query("SELECT * FROM fiesta_table WHERE " +
            "Nombre LIKE '%' || :name || '%' OR " +
            "Localidad LIKE '%' || :name || '%' OR " +
            "Municipio LIKE '%' || :name || '%'" )
    fun getFiestasListByName(name: String):Flow<List<Fiesta>>

    @Query("SELECT nombre  FROM fiesta_table")
    fun getNames(): Flow<List<String>>

    @Query("DELETE FROM fiesta_table where 1=1")
    suspend fun deleteAllFiestas()
}