package es.iesnervion.dbenitez.dexdroid.Fragments.Callbacks;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Fragments.Detalles.DetalleHabilidadFragment;
import es.iesnervion.dbenitez.dexdroid.Fragments.Detalles.DetallePokemonFragment;
import es.iesnervion.dbenitez.dexdroid.Fragments.Listados.ListadoHabilidadesFragment;
import es.iesnervion.dbenitez.dexdroid.Models.Habilidad;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HabilidadCallback implements Callback<List<Habilidad>>
{
    List<Habilidad> habilidades;
    String categoria;
    DetallePokemonFragment detallePokemon;
    DetalleHabilidadFragment detalleHabilidad;
    ListadoHabilidadesFragment listadoHabilidad;

    public HabilidadCallback(DetallePokemonFragment detallePokemonFragment, String categoria)
    {
        this.detallePokemon = detallePokemonFragment;
        this.categoria = categoria;
    }

    public HabilidadCallback(DetalleHabilidadFragment detalleHabilidad, String categoria)
    {
        this.detalleHabilidad = detalleHabilidad;
        this.categoria = categoria;
    }

    public HabilidadCallback(ListadoHabilidadesFragment listadoHabilidad, String categoria)
    {
        this.listadoHabilidad = listadoHabilidad;
        this.categoria = categoria;
    }

    public List<Habilidad> getHabilidadesPoke()
    {
        return habilidades;
    }

    public void setHabilidadesPoke(List<Habilidad> habilidades)
    {
        this.habilidades = habilidades;
    }

    @Override
    public void onResponse(Call<List<Habilidad>> call, Response<List<Habilidad>> response)
    {
        habilidades = response.body();

        if(detallePokemon!=null)
            detallePokemon.habilidadResponse(habilidades, categoria);
        else
            if(listadoHabilidad!=null)
                listadoHabilidad.habilidadResponse(habilidades,categoria);
            else
                if(detalleHabilidad!=null)
                    detalleHabilidad.habilidadResponse(habilidades,categoria);
    }

    @Override
    public void onFailure(Call<List<Habilidad>> call, Throwable t)
    {

    }
}
