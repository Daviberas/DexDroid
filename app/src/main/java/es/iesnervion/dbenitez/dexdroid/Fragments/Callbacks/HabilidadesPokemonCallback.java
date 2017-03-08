package es.iesnervion.dbenitez.dexdroid.Fragments.Callbacks;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Fragments.Detalles.DetalleHabilidadFragment;
import es.iesnervion.dbenitez.dexdroid.Fragments.Detalles.DetallePokemonFragment;
import es.iesnervion.dbenitez.dexdroid.Models.HabilidadesPokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HabilidadesPokemonCallback implements Callback<List<HabilidadesPokemon>>
{
    List<HabilidadesPokemon> habilidadesPoke;
    DetallePokemonFragment detallePokemon;
    DetalleHabilidadFragment detalleHabilidad;

    public HabilidadesPokemonCallback(DetallePokemonFragment detallePokemonFragment)
    {
        this.detallePokemon = detallePokemonFragment;
    }

    public HabilidadesPokemonCallback(DetalleHabilidadFragment detalleHabilidad)
    {
        this.detalleHabilidad = detalleHabilidad;
    }

    public List<HabilidadesPokemon> getHabilidadesPokePokes()
    {
        return habilidadesPoke;
    }

    public void setHabilidadesPokePokes(List<HabilidadesPokemon> habilidadesPoke)
    {
        this.habilidadesPoke = habilidadesPoke;
    }

    @Override
    public void onResponse(Call<List<HabilidadesPokemon>> call, Response<List<HabilidadesPokemon>> response)
    {
        habilidadesPoke = response.body();

        if(detallePokemon!=null)
            detallePokemon.habilidadesPokemonResponse(habilidadesPoke);
        else
            if(detalleHabilidad!=null)
                detalleHabilidad.habilidadesPokemonResponse(habilidadesPoke);
    }

    @Override
    public void onFailure(Call<List<HabilidadesPokemon>> call, Throwable t)
    {

    }
}
