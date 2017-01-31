package es.iesnervion.dbenitez.dexdroid.RetrofitInterfaces;

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
}
