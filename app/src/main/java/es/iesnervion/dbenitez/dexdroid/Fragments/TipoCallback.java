package es.iesnervion.dbenitez.dexdroid.Fragments;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Models.Tipo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TipoCallback implements Callback<List<Tipo>>
{
    List<Tipo> tipos;
    DetallePokemonFragment detallePokemon;

    public TipoCallback(DetallePokemonFragment detallePokemonFragment)
    {
        this.detallePokemon = detallePokemonFragment;
    }

    public List<Tipo> getTiposPokes()
    {
        return tipos;
    }

    public void setTiposPokes(List<Tipo> tipos)
    {
        this.tipos = tipos;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResponse(Call<List<Tipo>> call, Response<List<Tipo>> response)
    {
        tipos = response.body();

        detallePokemon.tipoResponse(tipos);
    }

    @Override
    public void onFailure(Call<List<Tipo>> call, Throwable t)
    {

    }
}