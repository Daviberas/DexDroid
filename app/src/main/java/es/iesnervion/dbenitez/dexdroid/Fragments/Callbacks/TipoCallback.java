package es.iesnervion.dbenitez.dexdroid.Fragments.Callbacks;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Fragments.Detalles.DetalleMovimientoFragment;
import es.iesnervion.dbenitez.dexdroid.Fragments.Detalles.DetallePokemonFragment;
import es.iesnervion.dbenitez.dexdroid.Fragments.Detalles.DetalleTipoFragment;
import es.iesnervion.dbenitez.dexdroid.Fragments.Listados.ListadoTiposFragment;
import es.iesnervion.dbenitez.dexdroid.Models.Tipo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TipoCallback implements Callback<List<Tipo>>
{
    List<Tipo> tipos;
    DetallePokemonFragment detallePokemon;
    DetalleMovimientoFragment detalleMovimiento;
    DetalleTipoFragment detalleTipo;
    ListadoTiposFragment listadoTipos;

    public TipoCallback(DetallePokemonFragment detallePokemonFragment)
    {
        this.detallePokemon = detallePokemonFragment;
    }

    public TipoCallback(DetalleMovimientoFragment detalleMovimiento)
    {
        this.detalleMovimiento = detalleMovimiento;
    }

    public TipoCallback(DetalleTipoFragment detalleTipo)
    {
        this.detalleTipo = detalleTipo;
    }

    public TipoCallback(ListadoTiposFragment listadoTipos)
    {
        this.listadoTipos = listadoTipos;
    }

    public List<Tipo> getTiposPokes()
    {
        return tipos;
    }

    public void setTiposPokes(List<Tipo> tipos)
    {
        this.tipos = tipos;
    }

    @Override
    public void onResponse(Call<List<Tipo>> call, Response<List<Tipo>> response)
    {
        tipos = response.body();

        if(detallePokemon!=null)
            detallePokemon.tipoResponse(tipos);
        else
            if(listadoTipos!=null)
                listadoTipos.tipoResponse(tipos);
            else
                if(detalleTipo!=null)
                    detalleTipo.tipoResponse(tipos);
                else
                    if(detalleMovimiento!=null)
                        detalleMovimiento.tipoResponse(tipos);

    }

    @Override
    public void onFailure(Call<List<Tipo>> call, Throwable t)
    {

    }
}