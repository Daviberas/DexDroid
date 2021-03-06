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

import es.iesnervion.dbenitez.dexdroid.Fragments.Callbacks.HabilidadCallback;
import es.iesnervion.dbenitez.dexdroid.Fragments.Callbacks.PokemonCallback;
import es.iesnervion.dbenitez.dexdroid.Fragments.Callbacks.PokemonHabilidadCallback;
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
import es.iesnervion.dbenitez.dexdroid.Interfaces.HabilidadInterface;
import es.iesnervion.dbenitez.dexdroid.Interfaces.HabilidadesPokemonInterface;
import es.iesnervion.dbenitez.dexdroid.Interfaces.PokemonInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetalleHabilidadFragment extends Fragment implements ApiResponse
{
    public final static String ARG_ID = "id";
    int mCurrentPosition = -1;

    List<Pokemon> listaPokes = new Vector<Pokemon>(0,1);
    int tamanio;
    int altura;

    public boolean terminado = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (savedInstanceState != null)
        {
            mCurrentPosition = savedInstanceState.getInt(ARG_ID);

            Retrofit retrofit= new Retrofit.Builder().baseUrl("http://dbenitez.ciclo.iesnervion.es").addConverterFactory(GsonConverterFactory.create()).build();
            HabilidadInterface hi= retrofit.create(HabilidadInterface.class);

            HabilidadCallback habilidadCallback = new HabilidadCallback(this,"");
            hi.getHabilidad(mCurrentPosition).enqueue(habilidadCallback);
        }
        altura = getResources().getDimensionPixelSize(R.dimen.row_height);

        return inflater.inflate(R.layout.detalle_habilidad, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        Bundle args = getArguments();
        if (args != null)
        {
            updateHabilidadView(args.getInt(ARG_ID));
        } else if (mCurrentPosition != -1)
        {
            updateHabilidadView(mCurrentPosition);
        }
    }

    public void updateHabilidadView(int position)
    {
        Retrofit retrofit= new Retrofit.Builder().baseUrl("http://dbenitez.ciclo.iesnervion.es").addConverterFactory(GsonConverterFactory.create()).build();
        HabilidadInterface hi= retrofit.create(HabilidadInterface.class);

        HabilidadCallback habilidadCallback = new HabilidadCallback(this,"");
        hi.getHabilidad(position).enqueue(habilidadCallback);

        mCurrentPosition = position;
    }

    @Override
    public void pokemonResponse(List<Pokemon> poke, boolean evolucion)
    {
        if(poke!=null)
        {
            listaPokes.add(poke.get(0));
            if(listaPokes.size()==tamanio)
            {
                GridView grid = (GridView) getActivity().findViewById(R.id.gridPokemonHabilidad);
                if(tamanio>0)
                {
                    ViewGroup.LayoutParams params = grid.getLayoutParams();

                    if(tamanio%3>0)
                        params.height = altura * ((tamanio/3)+1);
                    else
                        params.height = altura * (tamanio/3);

                    grid.setLayoutParams(params);
                }
                Pokemon[] arrayPokemon=new Pokemon[listaPokes.size()];
                grid.setAdapter(new AdapterIcono<Pokemon>(getActivity().getApplicationContext(), R.layout.elemento_grid, R.id.textoGrid,listaPokes.toArray(arrayPokemon)));
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
    public void habilidadesPokemonResponse(List<HabilidadesPokemon> habilidadesPoke)
    {
        if(habilidadesPoke!=null)
        {
            tamanio = habilidadesPoke.size();
            for(int i = 0; i<tamanio;i++)
            {
                Retrofit retrofit= new Retrofit.Builder().baseUrl("http://dbenitez.ciclo.iesnervion.es").addConverterFactory(GsonConverterFactory.create()).build();
                PokemonInterface pi= retrofit.create(PokemonInterface.class);

                PokemonCallback pokemonCallback = new PokemonCallback(this);
                pi.getPokemon(habilidadesPoke.get(i).getIdPokemon()).enqueue(pokemonCallback);
            }
        }
    }

    @Override
    public void habilidadResponse(List<Habilidad> habilidades, String categoria)
    {
        if(habilidades!=null)
        {
            if(habilidades!=null)
            {
                TextView tv = (TextView) getActivity().findViewById(R.id.nombreHabilidad);
                tv.setText(habilidades.get(0).getNombre()+":");

                TextView tv2 = (TextView) getActivity().findViewById(R.id.efectoHabilidad);
                tv2.setText(habilidades.get(0).getEfecto());

                Retrofit retrofit= new Retrofit.Builder().baseUrl("http://dbenitez.ciclo.iesnervion.es").addConverterFactory(GsonConverterFactory.create()).build();
                HabilidadesPokemonInterface hpi= retrofit.create(HabilidadesPokemonInterface.class);

                PokemonHabilidadCallback pokemonHabilidadCallback = new PokemonHabilidadCallback(this);
                hpi.getPokemonHabilidad(habilidades.get(0).getId()).enqueue(pokemonHabilidadCallback);
            }
        }
    }

    @Override
    public void evolucionResponse(List<Evolucion> evoluciones)
    {

    }

    @Override
    public void movimientosPokemonResponse(List<MovimientosPokemon> movimientosPokemon) {

    }

    @Override
    public void movimientoResponse(List<Movimiento> movimientos) {

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