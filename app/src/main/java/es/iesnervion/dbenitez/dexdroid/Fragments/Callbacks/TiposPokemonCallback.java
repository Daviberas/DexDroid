package es.iesnervion.dbenitez.dexdroid.Fragments.Callbacks;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Fragments.Detalles.DetallePokemonFragment;
import es.iesnervion.dbenitez.dexdroid.Models.TiposPokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TiposPokemonCallback implements Callback<List<TiposPokemon>>
{
    List<TiposPokemon> tiposPokes;
    DetallePokemonFragment detallePokemon;

    public TiposPokemonCallback(DetallePokemonFragment detallePokemonFragment)
    {
        this.detallePokemon = detallePokemonFragment;
    }

    public List<TiposPokemon> getTiposPokes()
    {
        return tiposPokes;
    }

    public void setTiposPokes(List<TiposPokemon> pokes)
    {
        this.tiposPokes = pokes;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResponse(Call<List<TiposPokemon>> call, Response<List<TiposPokemon>> response)
    {
        tiposPokes = response.body();

        detallePokemon.tiposPokemonResponse(tiposPokes);
    }

    @Override
    public void onFailure(Call<List<TiposPokemon>> call, Throwable t)
    {

    }
}
