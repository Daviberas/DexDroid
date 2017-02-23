package es.iesnervion.dbenitez.dexdroid.Interfaces;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Models.MovimientosPokemon;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovimientosPokemonInterface
{
    @GET("MovimientosPokemon")
    Call<List<MovimientosPokemon>> getMovimientosPokemon();

    @GET("MovimientosPokemon/{id}")
    Call<List<MovimientosPokemon>> getMovimientosPokemon(@Path("id") int id);

    @GET("PokemonMovimiento")
    Call<List<MovimientosPokemon>> getPokemonMovimiento();

    @GET("PokemonMovimiento/{id}")
    Call<List<MovimientosPokemon>> getPokemonMovimiento(@Path("id") int id);
}
