package es.iesnervion.dbenitez.dexdroid.Fragments;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Models.HabilidadesPokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HabilidadesPokemonCallback implements Callback<List<HabilidadesPokemon>>
{
    List<HabilidadesPokemon> habilidadesPoke;
    DetallePokemonFragment detallePokemon;

    public HabilidadesPokemonCallback(DetallePokemonFragment detallePokemonFragment)
    {
        this.detallePokemon = detallePokemonFragment;
    }

    public List<HabilidadesPokemon> getHabilidadesPokePokes()
    {
        return habilidadesPoke;
    }

    public void setHabilidadesPokePokes(List<HabilidadesPokemon> habilidadesPoke)
    {
        this.habilidadesPoke = habilidadesPoke;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResponse(Call<List<HabilidadesPokemon>> call, Response<List<HabilidadesPokemon>> response)
    {
        habilidadesPoke = response.body();

        detallePokemon.habilidadesPokemonResponse(habilidadesPoke);
    }

    @Override
    public void onFailure(Call<List<HabilidadesPokemon>> call, Throwable t)
    {

    }
}
