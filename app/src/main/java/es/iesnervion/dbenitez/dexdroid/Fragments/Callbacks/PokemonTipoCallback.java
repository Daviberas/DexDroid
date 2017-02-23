package es.iesnervion.dbenitez.dexdroid.Fragments.Callbacks;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Fragments.Detalles.DetalleTipoFragment;
import es.iesnervion.dbenitez.dexdroid.Models.TiposPokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonTipoCallback implements Callback<List<TiposPokemon>>
{
    List<TiposPokemon> pokesTipo;
    DetalleTipoFragment detalleTipoFragment;

    public PokemonTipoCallback(DetalleTipoFragment detalleTipoFragment)
    {
        this.detalleTipoFragment = detalleTipoFragment;
    }

    public List<TiposPokemon> getPokesTipo() {
        return pokesTipo;
    }

    public void setPokesTipo(List<TiposPokemon> pokesTipo) {
        this.pokesTipo = pokesTipo;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResponse(Call<List<TiposPokemon>> call, Response<List<TiposPokemon>> response)
    {
        pokesTipo = response.body();

        detalleTipoFragment.tiposPokemonResponse(pokesTipo);
    }

    @Override
    public void onFailure(Call<List<TiposPokemon>> call, Throwable t)
    {

    }
}
