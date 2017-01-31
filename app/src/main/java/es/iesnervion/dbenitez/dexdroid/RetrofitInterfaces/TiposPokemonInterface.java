package es.iesnervion.dbenitez.dexdroid.RetrofitInterfaces;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Models.TiposPokemon;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TiposPokemonInterface
{
    @GET("TiposPokemon")
    Call<List<TiposPokemon>> getTiposPokemon();

    @GET("TiposPokemon/{id}")
    Call<List<TiposPokemon>> getTiposPokemon(@Path("id") int id);
}
