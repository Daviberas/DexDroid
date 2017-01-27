package es.iesnervion.dbenitez.dexdroid.RetrofitInterfaces;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Models.HabilidadesPokemon;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HabilidadesPokemonInterface
{
    @GET("HabilidadesPokemon")
    Call<List<HabilidadesPokemon>> getHabilidadesPokemon();

    @GET("HabilidadesPokemon/{id}")
    Call<List<HabilidadesPokemon>> getHabilidadesPokemon(@Path("id") int id);
}
