package es.iesnervion.dbenitez.dexdroid.Fragments;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Models.Pokemon;
import es.iesnervion.dbenitez.dexdroid.R;
import es.iesnervion.dbenitez.dexdroid.RetrofitInterfaces.PokemonInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetallePokemon extends Fragment implements ApiResponseDetallePokemon
{
    public final static String ARG_ID = "id";
    int mCurrentPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (savedInstanceState != null)
        {
            mCurrentPosition = savedInstanceState.getInt(ARG_ID);

            Retrofit retrofit= new Retrofit.Builder().baseUrl("http://dbenitez.ciclo.iesnervion.es").addConverterFactory(GsonConverterFactory.create()).build();
            PokemonInterface pi= retrofit.create(PokemonInterface.class);

            PokemonCallback pokemonCallback = new PokemonCallback(this);
            pi.getPokemon().enqueue(pokemonCallback);
        }

        return inflater.inflate(R.layout.detalle_pokemon, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        Bundle args = getArguments();
        if (args != null)
        {
            updatePokemonView(args.getInt(ARG_ID));
        } else if (mCurrentPosition != -1)
        {
            updatePokemonView(mCurrentPosition);
        }
    }

    public void updatePokemonView(int position)
    {
        Retrofit retrofit= new Retrofit.Builder().baseUrl("http://dbenitez.ciclo.iesnervion.es").addConverterFactory(GsonConverterFactory.create()).build();
        PokemonInterface pi= retrofit.create(PokemonInterface.class);

        PokemonCallback pokemonCallback = new PokemonCallback(this);
        pi.getPokemon().enqueue(pokemonCallback);

        mCurrentPosition = position;
    }

    public void pokemonResponsed(List<Pokemon> pokes)
    {
        if (pokes != null)
        {
            Pokemon poke = pokes.get(0);

            TextView num = (TextView) getActivity().findViewById(R.id.numPokemon);
            num.setText(""+poke.getNumPokedex());

            TextView nombre = (TextView) getActivity().findViewById(R.id.nombrePokemon);
            nombre.setText(poke.getNombre());

            TextView porcentajeHembra = (TextView) getActivity().findViewById(R.id.porcentajeHembraPokemon);
            porcentajeHembra.setText("" + poke.getPorcentajeHembra());

            TextView porcentajeMacho = (TextView) getActivity().findViewById(R.id.porcentajeMachoPokemon);
            porcentajeMacho.setText("" + poke.getPorcentajeMacho());

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(ARG_ID, mCurrentPosition);
    }
}
