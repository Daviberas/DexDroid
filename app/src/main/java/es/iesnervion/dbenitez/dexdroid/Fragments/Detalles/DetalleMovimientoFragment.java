package es.iesnervion.dbenitez.dexdroid.Fragments.Detalles;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;

import es.iesnervion.dbenitez.dexdroid.Fragments.Callbacks.MovimientoCallback;
import es.iesnervion.dbenitez.dexdroid.Fragments.Callbacks.PokemonCallback;
import es.iesnervion.dbenitez.dexdroid.Fragments.Callbacks.PokemonMovimientoCallback;
import es.iesnervion.dbenitez.dexdroid.ViewHolder;
import es.iesnervion.dbenitez.dexdroid.Interfaces.ApiResponse;
import es.iesnervion.dbenitez.dexdroid.Models.Evolucion;
import es.iesnervion.dbenitez.dexdroid.Models.Habilidad;
import es.iesnervion.dbenitez.dexdroid.Models.HabilidadesPokemon;
import es.iesnervion.dbenitez.dexdroid.Models.Movimiento;
import es.iesnervion.dbenitez.dexdroid.Models.MovimientosPokemon;
import es.iesnervion.dbenitez.dexdroid.Models.Pokemon;
import es.iesnervion.dbenitez.dexdroid.Models.Tipo;
import es.iesnervion.dbenitez.dexdroid.Models.TiposPokemon;
import es.iesnervion.dbenitez.dexdroid.R;
import es.iesnervion.dbenitez.dexdroid.Interfaces.MovimientoInterface;
import es.iesnervion.dbenitez.dexdroid.Interfaces.MovimientosPokemonInterface;
import es.iesnervion.dbenitez.dexdroid.Interfaces.PokemonInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetalleMovimientoFragment extends Fragment implements ApiResponse
{
    public final static String ARG_ID = "id";
    int mCurrentPosition = -1;

    List<Pokemon> listaPokes = new Vector<Pokemon>(0,1);
    int tamanio;

    public boolean terminado = false;

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
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void pokemonResponse(List<Pokemon> poke, boolean evolucion)
    {
        if(poke!=null)
        {
            listaPokes.add(poke.get(0));
            if(listaPokes.size()==tamanio)
            {
                GridView grid = (GridView) getActivity().findViewById(R.id.gridPokemonMovimiento);
                Pokemon[] arrayPokemon=new Pokemon[listaPokes.size()];
                grid.setAdapter(new AdapterIcono<Pokemon>(getContext(), R.layout.elemento_grid, R.id.textoGrid,listaPokes.toArray(arrayPokemon)));
                terminado = true;
            }
        }
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
        if(movimientosPokemon!=null)
        {
            tamanio = movimientosPokemon.size();
            for(int i = 0; i<tamanio;i++)
            {
                Retrofit retrofit= new Retrofit.Builder().baseUrl("http://dbenitez.ciclo.iesnervion.es").addConverterFactory(GsonConverterFactory.create()).build();
                PokemonInterface pi= retrofit.create(PokemonInterface.class);

                PokemonCallback pokemonCallback = new PokemonCallback(this);
                pi.getPokemon(movimientosPokemon.get(i).getIdPokemon()).enqueue(pokemonCallback);
            }
        }
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

            Retrofit retrofit= new Retrofit.Builder().baseUrl("http://dbenitez.ciclo.iesnervion.es").addConverterFactory(GsonConverterFactory.create()).build();
            MovimientosPokemonInterface mpi= retrofit.create(MovimientosPokemonInterface.class);

            PokemonMovimientoCallback pokemonMovimientoCallback = new PokemonMovimientoCallback(this);
            mpi.getPokemonMovimiento(mov.getId()).enqueue(pokemonMovimientoCallback);
        }
    }

    class AdapterIcono<T> extends ArrayAdapter<T>
    {
        AdapterIcono(Context c, int resourceId, int textId, T[] objects)
        {
            super(c, resourceId, textId, objects);
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            View row = convertView;
            ViewHolder holder;

            if (row==null){
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                row=inflater.inflate(R.layout.elemento_grid, parent, false);

                TextView tv = (TextView) row.findViewById(R.id.textoGrid);
                ImageView iv = (ImageView) row.findViewById(R.id.imgGrid);

                holder = new ViewHolder (tv,iv);
                row.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) row.getTag();
            }

            holder.getTv().setText(listaPokes.get(position).getNombre());
            Bitmap bmp = BitmapFactory.decodeByteArray(listaPokes.get(position).getImagen(), 0, listaPokes.get(position).getImagen().length);

            holder.getIv().setImageBitmap(bmp);


            return (row);
        }
    }
}