package es.iesnervion.dbenitez.dexdroid.Fragments;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Models.Habilidad;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HabilidadCallback implements Callback<List<Habilidad>>
{
    List<Habilidad> habilidades;
    String categoria;
    DetallePokemonFragment detallePokemon;

    public HabilidadCallback(DetallePokemonFragment detallePokemonFragment, String categoria)
    {
        this.detallePokemon = detallePokemonFragment;
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResponse(Call<List<Habilidad>> call, Response<List<Habilidad>> response)
    {
        habilidades = response.body();

        detallePokemon.habilidadResponse(habilidades, categoria);
    }

    @Override
    public void onFailure(Call<List<Habilidad>> call, Throwable t)
    {

    }
}
