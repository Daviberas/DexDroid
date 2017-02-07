package es.iesnervion.dbenitez.dexdroid.Fragments;

import android.app.Fragment;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Models.Pokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class PokemonCallback implements Callback<List<Pokemon>>
{
    List<Pokemon> pokes;
    DetallePokemon detallePokemon;
    ListadoPokemonFragment listadoPokemonFragment;

    public PokemonCallback(DetallePokemon detallePokemon)
    {
        this.detallePokemon = detallePokemon;
    }

    public PokemonCallback(ListadoPokemonFragment listadoPokemonFragment)
    {
        this.listadoPokemonFragment = listadoPokemonFragment;
    }

    public List<Pokemon> getPokes()
    {
        return pokes;
    }

    public void setPokes(List<Pokemon> pokes)
    {
        this.pokes = pokes;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response)
    {
        pokes = response.body();

        if(detallePokemon != null)
        {
            detallePokemon.pokemonResponsed(pokes);
        }
        else
            if(listadoPokemonFragment != null)
            {
                listadoPokemonFragment.pokemonResponsed(pokes);
            }
    }

    @Override
    public void onFailure(Call<List<Pokemon>> call, Throwable t)
    {

    }
}
