package es.iesnervion.dbenitez.dexdroid.Fragments.Callbacks;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Fragments.Detalles.DetalleMovimientoFragment;
import es.iesnervion.dbenitez.dexdroid.Fragments.Detalles.DetallePokemonFragment;
import es.iesnervion.dbenitez.dexdroid.Fragments.Listados.ListadoMovimientosFragment;
import es.iesnervion.dbenitez.dexdroid.Models.Movimiento;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovimientoCallback implements Callback<List<Movimiento>>
{
    List<Movimiento> movimientos;
    DetallePokemonFragment detallePokemon;
    DetalleMovimientoFragment detalleMovimiento;
    ListadoMovimientosFragment listadoMovimientos;

    public MovimientoCallback(DetallePokemonFragment detallePokemonFragment)
    {
        this.detallePokemon = detallePokemonFragment;
    }

    public MovimientoCallback(DetalleMovimientoFragment detalleMovimiento)
    {
        this.detalleMovimiento = detalleMovimiento;
    }

    public MovimientoCallback(ListadoMovimientosFragment listadoMovimientos)
    {
        this.listadoMovimientos = listadoMovimientos;
    }

    public List<Movimiento> getMovimientos()
    {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos)
    {
        this.movimientos = movimientos;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResponse(Call<List<Movimiento>> call, Response<List<Movimiento>> response)
    {
        movimientos = response.body();

        if(detallePokemon!=null)
            detallePokemon.movimientoResponse(movimientos);
        else
        if(listadoMovimientos!=null)
            listadoMovimientos.movimientoResponse(movimientos);
        else
        if(detalleMovimiento!=null)
            detalleMovimiento.movimientoResponse(movimientos);
    }

    @Override
    public void onFailure(Call<List<Movimiento>> call, Throwable t)
    {

    }
}