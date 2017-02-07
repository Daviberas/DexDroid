package es.iesnervion.dbenitez.dexdroid.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Models.Pokemon;
import es.iesnervion.dbenitez.dexdroid.Models.Tipo;
import es.iesnervion.dbenitez.dexdroid.Models.TiposPokemon;
import es.iesnervion.dbenitez.dexdroid.R;
import es.iesnervion.dbenitez.dexdroid.RetrofitInterfaces.PokemonInterface;
import es.iesnervion.dbenitez.dexdroid.RetrofitInterfaces.TipoInterface;
import es.iesnervion.dbenitez.dexdroid.RetrofitInterfaces.TiposPokemonInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetallePokemonFragment extends Fragment implements ApiResponse
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
            pi.getPokemon(mCurrentPosition).enqueue(pokemonCallback);
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
        pi.getPokemon(position).enqueue(pokemonCallback);

        mCurrentPosition = position;
    }

    public void pokemonResponse(List<Pokemon> pokes)
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

            Retrofit retrofit= new Retrofit.Builder().baseUrl("http://dbenitez.ciclo.iesnervion.es").addConverterFactory(GsonConverterFactory.create()).build();
            TiposPokemonInterface tpi= retrofit.create(TiposPokemonInterface.class);

            TiposPokemonCallback tiposPokemonCallback = new TiposPokemonCallback(this);
            tpi.getTiposPokemon(poke.getNumPokedex()).enqueue(tiposPokemonCallback);
        }
    }

    @Override
    public void tiposPokemonResponse(List<TiposPokemon> tiposPoke)
    {
        if (tiposPoke != null)
        {
            for(int i = 0; i<tiposPoke.size();i++)
            {
                Retrofit retrofit= new Retrofit.Builder().baseUrl("http://dbenitez.ciclo.iesnervion.es").addConverterFactory(GsonConverterFactory.create()).build();
                TipoInterface ti= retrofit.create(TipoInterface.class);

                TipoCallback tipoCallback = new TipoCallback(this);
                ti.getTipo(tiposPoke.get(i).getIdTipo()).enqueue(tipoCallback);
            }
        }
    }

    @Override
    public void tipoResponse(List<Tipo> tipos)
    {
        if (tipos != null)
        {
            Tipo tipo = tipos.get(0);

            TextView txtTipo = (TextView) getActivity().findViewById(R.id.txtTipoPokemon);
            String txtPrevio = (String) txtTipo.getText();
            if(txtPrevio == "")
                txtTipo.setText(tipo.getNombre());
            else
                txtTipo.setText(txtPrevio+" / "+tipo.getNombre());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(ARG_ID, mCurrentPosition);
    }
}
