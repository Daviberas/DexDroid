package es.iesnervion.dbenitez.dexdroid.Fragments;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Models.Pokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonCallback implements Callback<List<Pokemon>>
{
    List<Pokemon> pokes;
    boolean evolucion;
    DetallePokemonFragment detallePokemon;
    DetalleTipoFragment detalleTipoFragment;
    ListadoPokemonFragment listadoPokemonFragment;
    DetalleHabilidadFragment detalleHabilidad;
    DetalleMovimientoFragment detalleMovimiento;

    public PokemonCallback(DetallePokemonFragment detallePokemonFragment, boolean evolucion)
    {
        this.detallePokemon = detallePokemonFragment;
        this.evolucion = evolucion;
    }

    public PokemonCallback(DetalleTipoFragment detalleTipoFragment)
    {
        this.detalleTipoFragment = detalleTipoFragment;
        this.evolucion = false;
    }

    public PokemonCallback(DetalleHabilidadFragment detalleHabilidad)
    {
        this.detalleHabilidad = detalleHabilidad;
        this.evolucion = false;
    }

    public PokemonCallback(DetalleMovimientoFragment detalleMovimiento)
    {
        this.detalleMovimiento = detalleMovimiento;
        this.evolucion = false;
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
            detallePokemon.pokemonResponse(pokes,evolucion);
        }
        else
            if(listadoPokemonFragment != null)
            {
                listadoPokemonFragment.pokemonResponse(pokes,evolucion);
            }
            else
                if(detalleTipoFragment != null)
                {
                    detalleTipoFragment.pokemonResponse(pokes,evolucion);
                }
                else
                    if(detalleHabilidad != null)
                    {
                        detalleHabilidad.pokemonResponse(pokes,evolucion);
                    }
                    else
                        if(detalleMovimiento!=null)
                            detalleMovimiento.pokemonResponse(pokes,evolucion);
    }

    @Override
    public void onFailure(Call<List<Pokemon>> call, Throwable t)
    {

    }
}
