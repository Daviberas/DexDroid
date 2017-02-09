package es.iesnervion.dbenitez.dexdroid.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import es.iesnervion.dbenitez.dexdroid.RetrofitInterfaces.MovimientoInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetalleMovimientoFragment extends Fragment implements ApiResponse
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
            MovimientoInterface mi= retrofit.create(MovimientoInterface.class);

            MovimientoCallback movimientoCallback = new MovimientoCallback(this);
            mi.getMovimiento(mCurrentPosition).enqueue(movimientoCallback);
        }

        return inflater.inflate(R.layout.detalle_movimiento, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        Bundle args = getArguments();
        if (args != null)
        {
            updateMovimientoView(args.getInt(ARG_ID));
        } else if (mCurrentPosition != -1)
        {
            updateMovimientoView(mCurrentPosition);
        }
    }

    public void updateMovimientoView(int position)
    {
        Retrofit retrofit= new Retrofit.Builder().baseUrl("http://dbenitez.ciclo.iesnervion.es").addConverterFactory(GsonConverterFactory.create()).build();
        MovimientoInterface mi= retrofit.create(MovimientoInterface.class);

        MovimientoCallback movimientoCallback = new MovimientoCallback(this);
        mi.getMovimiento(position).enqueue(movimientoCallback);

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

    }

    @Override
    public void habilidadesPokemonResponse(List<HabilidadesPokemon> habilidadesPoke) {

    }

    @Override
    public void habilidadResponse(List<Habilidad> habilidades, String categoria)
    {

    }

    @Override
    public void evolucionResponse(List<Evolucion> evoluciones) {

    }

    @Override
    public void movimientosPokemonResponse(List<MovimientosPokemon> movimientosPokemon)
    {

    }

    @Override
    public void movimientoResponse(List<Movimiento> movimientos)
    {
        if(movimientos!=null)
        {
            Movimiento mov = movimientos.get(0);

            if(mov.getNombre()!=null)
            {
                TextView tv = (TextView) getActivity().findViewById(R.id.nombreMovimiento);
                tv.setText(mov.getNombre());
            }

            if(mov.getEfecto()!=null)
            {
                TextView tv = (TextView) getActivity().findViewById(R.id.efectoMovimiento);
                tv.setText(mov.getEfecto());
            }

            if(mov.getVia()!=null)
            {
                TextView tv = (TextView) getActivity().findViewById(R.id.txtVia);
                tv.setText(mov.getVia());
            }

            TextView txtPotencia = (TextView) getActivity().findViewById(R.id.potencia);
            txtPotencia.setText(""+mov.getPotencia());

            TextView txtPorcentaje = (TextView) getActivity().findViewById(R.id.porcentajeAcierto);
            txtPorcentaje.setText(""+mov.getPorcentajeAcierto());

            TextView txtPP = (TextView) getActivity().findViewById(R.id.pp);
            txtPP.setText(""+mov.getPp());

            if(mov.getObjetivo()!=null)
            {
                TextView tv = (TextView) getActivity().findViewById(R.id.objetivo);
                tv.setText(mov.getObjetivo());
            }

            if(mov.getContacto()!=null)
            {
                TextView tv = (TextView) getActivity().findViewById(R.id.contacto);
                tv.setText(mov.getContacto());
            }

        }
    }
}