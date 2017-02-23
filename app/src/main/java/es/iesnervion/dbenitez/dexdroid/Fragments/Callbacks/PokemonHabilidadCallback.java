package es.iesnervion.dbenitez.dexdroid.Fragments.Callbacks;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Fragments.Detalles.DetalleHabilidadFragment;
import es.iesnervion.dbenitez.dexdroid.Models.HabilidadesPokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonHabilidadCallback implements Callback<List<HabilidadesPokemon>>
{
    List<HabilidadesPokemon> pokesHabilidad;
    DetalleHabilidadFragment detalleHabilidad;

    public PokemonHabilidadCallback(DetalleHabilidadFragment detalleHabilidad)
    {
        this.detalleHabilidad = detalleHabilidad;
    }

    public List<HabilidadesPokemon> getPokesTipo() {
        return pokesHabilidad;
    }

    public void setPokesTipo(List<HabilidadesPokemon> pokesHabilidad) {
        this.pokesHabilidad = pokesHabilidad;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResponse(Call<List<HabilidadesPokemon>> call, Response<List<HabilidadesPokemon>> response)
    {
        pokesHabilidad = response.body();

        detalleHabilidad.habilidadesPokemonResponse(pokesHabilidad);
    }

    @Override
    public void onFailure(Call<List<HabilidadesPokemon>> call, Throwable t)
    {

    }
}