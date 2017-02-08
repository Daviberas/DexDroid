package es.iesnervion.dbenitez.dexdroid.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Models.Evolucion;
import es.iesnervion.dbenitez.dexdroid.Models.Habilidad;
import es.iesnervion.dbenitez.dexdroid.Models.HabilidadesPokemon;
import es.iesnervion.dbenitez.dexdroid.Models.Movimiento;
import es.iesnervion.dbenitez.dexdroid.Models.MovimientosPokemon;
import es.iesnervion.dbenitez.dexdroid.Models.Pokemon;
import es.iesnervion.dbenitez.dexdroid.Models.Tipo;
import es.iesnervion.dbenitez.dexdroid.Models.TiposPokemon;
import es.iesnervion.dbenitez.dexdroid.R;
import es.iesnervion.dbenitez.dexdroid.RetrofitInterfaces.TipoInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetalleTipoFragment extends Fragment implements ApiResponse
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
            TipoInterface ti= retrofit.create(TipoInterface.class);

            TipoCallback tipoCallback = new TipoCallback(this);
            ti.getTipo(mCurrentPosition).enqueue(tipoCallback);
        }

        return inflater.inflate(R.layout.detalle_tipo, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        Bundle args = getArguments();
        if (args != null)
        {
            updateTipoView(args.getInt(ARG_ID));
        } else if (mCurrentPosition != -1)
        {
            updateTipoView(mCurrentPosition);
        }
    }

    public void updateTipoView(int position)
    {
        Retrofit retrofit= new Retrofit.Builder().baseUrl("http://dbenitez.ciclo.iesnervion.es").addConverterFactory(GsonConverterFactory.create()).build();
        TipoInterface ti= retrofit.create(TipoInterface.class);

        TipoCallback tipoCallback = new TipoCallback(this);
        ti.getTipo(position).enqueue(tipoCallback);

        mCurrentPosition = position;
    }
    @Override
    public void pokemonResponse(List<Pokemon> poke, boolean evolucion) {

    }

    @Override
    public void tiposPokemonResponse(List<TiposPokemon> tiposPoke) {

    }

    @Override
    public void tipoResponse(List<Tipo> tipos)
    {
        //TODO implementar el adapter para el gridview de tipo y rellenarlo con los pokemon de ese tipo
    }

    @Override
    public void habilidadesPokemonResponse(List<HabilidadesPokemon> habilidadesPoke) {

    }

    @Override
    public void habilidadResponse(List<Habilidad> habilidades, String categoria) {

    }

    @Override
    public void evolucionResponse(List<Evolucion> evoluciones) {

    }

    @Override
    public void movimientosPokemonResponse(List<MovimientosPokemon> movimientosPokemon) {

    }

    @Override
    public void movimientoResponse(List<Movimiento> movimientos) {

    }
}
