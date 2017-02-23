package es.iesnervion.dbenitez.dexdroid.Interfaces;

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

    @GET("PokemonTipo")
    Call<List<TiposPokemon>> getPokemonTipo();

    @GET("PokemonTipo/{id}")
    Call<List<TiposPokemon>> getPokemonTipo(@Path("id") int id);
}
