package es.iesnervion.dbenitez.dexdroid.Fragments.Callbacks;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Fragments.Detalles.DetallePokemonFragment;
import es.iesnervion.dbenitez.dexdroid.Models.MovimientosPokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovimientosPokemonCallback implements Callback<List<MovimientosPokemon>>
{
    List<MovimientosPokemon> movimientosPokemon;
    DetallePokemonFragment detallePokemon;

    public MovimientosPokemonCallback(DetallePokemonFragment detallePokemonFragment)
    {
        this.detallePokemon = detallePokemonFragment;
    }

    public List<MovimientosPokemon> getMovimientosPokemon()
    {
        return movimientosPokemon;
    }

    public void setMovimientosPokemon(List<MovimientosPokemon> evoluciones)
    {
        this.movimientosPokemon = movimientosPokemon;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResponse(Call<List<MovimientosPokemon>> call, Response<List<MovimientosPokemon>> response)
    {
        movimientosPokemon = response.body();

        detallePokemon.movimientosPokemonResponse(movimientosPokemon);
    }

    @Override
    public void onFailure(Call<List<MovimientosPokemon>> call, Throwable t)
    {

    }
}
