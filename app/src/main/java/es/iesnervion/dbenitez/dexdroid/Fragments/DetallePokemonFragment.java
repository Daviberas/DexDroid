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
import es.iesnervion.dbenitez.dexdroid.Models.PokemonResponse;
import es.iesnervion.dbenitez.dexdroid.R;
import es.iesnervion.dbenitez.dexdroid.RetrofitInterfaces.PokemonInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetallePokemonFragment extends Fragment implements Callback<List<Pokemon>>,PokemonResponse
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

            pi.getPokemon(mCurrentPosition).enqueue(this);
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

        pi.getPokemon(position).enqueue(this);

        mCurrentPosition = position;
    }

    public void pokemonResponsed(Pokemon poke)
    {
        if (poke != null)
        {

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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response)
    {
        pokemonResponsed(response.body().get(0));
    }

    @Override
    public void onFailure(Call<List<Pokemon>> call, Throwable t)
    {
        Toast.makeText(getActivity(), t.getMessage(),Toast.LENGTH_SHORT).show();
        Log.e(getClass().getSimpleName(),"Exception from Retrofit request to StackOverflow", t);
    }
}
