package es.iesnervion.dbenitez.dexdroid.RetrofitInterfaces;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Models.Pokemon;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonInterface
{
    @GET("Pokemon")
    Call<List<Pokemon>> getPokemon();

    @GET("Pokemon/{id}")
    Call<List<Pokemon>> getPokemon(@Path("id") int id);
}
