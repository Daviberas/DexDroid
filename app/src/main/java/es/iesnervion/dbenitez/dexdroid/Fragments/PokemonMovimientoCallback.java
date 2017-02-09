package es.iesnervion.dbenitez.dexdroid.Fragments;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Models.HabilidadesPokemon;
import es.iesnervion.dbenitez.dexdroid.Models.MovimientosPokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonMovimientoCallback implements Callback<List<MovimientosPokemon>>
{
    List<MovimientosPokemon> pokesMovimiento;
    DetalleMovimientoFragment detalleMovimiento;

    public PokemonMovimientoCallback(DetalleMovimientoFragment detalleMovimiento)
    {
        this.detalleMovimiento = detalleMovimiento;
    }

    public List<MovimientosPokemon> getPokesTipo() {
        return pokesMovimiento;
    }

    public void setPokesTipo(List<MovimientosPokemon> pokesMovimiento) {
        this.pokesMovimiento = pokesMovimiento;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResponse(Call<List<MovimientosPokemon>> call, Response<List<MovimientosPokemon>> response)
    {
        pokesMovimiento = response.body();

        detalleMovimiento.movimientosPokemonResponse(pokesMovimiento);
    }

    @Override
    public void onFailure(Call<List<MovimientosPokemon>> call, Throwable t)
    {

    }
}
