package es.iesnervion.dbenitez.dexdroid.Fragments;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Models.Evolucion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EvolucionCallback implements Callback<List<Evolucion>>
{
    List<Evolucion> evoluciones;
    DetallePokemonFragment detallePokemon;

    public EvolucionCallback(DetallePokemonFragment detallePokemonFragment)
    {
        this.detallePokemon = detallePokemonFragment;
    }

    public List<Evolucion> getEvoluciones()
    {
        return evoluciones;
    }

    public void setEvoluciones(List<Evolucion> evoluciones)
    {
        this.evoluciones = evoluciones;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResponse(Call<List<Evolucion>> call, Response<List<Evolucion>> response)
    {
        evoluciones = response.body();

        detallePokemon.evolucionResponse(evoluciones);
    }

    @Override
    public void onFailure(Call<List<Evolucion>> call, Throwable t)
    {

    }
}
