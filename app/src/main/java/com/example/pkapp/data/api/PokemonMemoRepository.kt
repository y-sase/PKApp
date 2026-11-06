import com.example.pkapp.model.PokemonMemo
import kotlinx.coroutines.flow.Flow

interface PokemonMemoRepository {
    suspend fun saveMemo(
        memo: PokemonMemo
    )
    suspend fun getMemo(
        pokemonId: Int
    ): PokemonMemo?
}