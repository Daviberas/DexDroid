package es.iesnervion.dbenitez.dexdroid.Fragments.Callbacks;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Fragments.Detalles.DetalleMovimientoFragment;
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
