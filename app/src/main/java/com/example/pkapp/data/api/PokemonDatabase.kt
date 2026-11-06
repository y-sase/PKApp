import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pkapp.data.api.PokemonMemoDao
import com.example.pkapp.model.PokemonMemo

/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [PokemonMemo::class], version = 1, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonMemoDao(): PokemonMemoDao

    companion object {
        @Volatile
        private var Instance: PokemonDatabase? = null

        fun getDatabase(context: Context): PokemonDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, PokemonDatabase::class.java, "pokemon_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}