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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;
import java.util.Vector;

import es.iesnervion.dbenitez.dexdroid.Fragments.Callbacks.EvolucionCallback;
import es.iesnervion.dbenitez.dexdroid.Fragments.Callbacks.HabilidadCallback;
import es.iesnervion.dbenitez.dexdroid.Fragments.Callbacks.HabilidadesPokemonCallback;
import es.iesnervion.dbenitez.dexdroid.Fragments.Callbacks.MovimientoCallback;
import es.iesnervion.dbenitez.dexdroid.Fragments.Callbacks.MovimientosPokemonCallback;
import es.iesnervion.dbenitez.dexdroid.Fragments.Callbacks.PokemonCallback;
import es.iesnervion.dbenitez.dexdroid.Fragments.Callbacks.TipoCallback;
import es.iesnervion.dbenitez.dexdroid.Fragments.Callbacks.TiposPokemonCallback;
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
import es.iesnervion.dbenitez.dexdroid.Interfaces.EvolucionInterface;
import es.iesnervion.dbenitez.dexdroid.Interfaces.HabilidadInterface;
import es.iesnervion.dbenitez.dexdroid.Interfaces.HabilidadesPokemonInterface;
import es.iesnervion.dbenitez.dexdroid.Interfaces.MovimientoInterface;
import es.iesnervion.dbenitez.dexdroid.Interfaces.MovimientosPokemonInterface;
import es.iesnervion.dbenitez.dexdroid.Interfaces.PokemonInterface;
import es.iesnervion.dbenitez.dexdroid.Interfaces.TipoInterface;
import es.iesnervion.dbenitez.dexdroid.Interfaces.TiposPokemonInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetallePokemonFragment extends Fragment implements ApiResponse
{
    public final static String ARG_ID = "id";
    int mCurrentPosition = -1;

    List<Pokemon> listaPokes = new Vector<Pokemon>(0,1);
    List<Movimiento> listaMov = new Vector<Movimiento>(0,1);
    int tamanio;
    int tamanioMov;
    int altura;
    int alturaMov;
    public boolean terminado = false;
    List<String> metodosEvolucion = new Vector<String>(0,1);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (savedInstanceState != null)
        {
            mCurrentPosition = savedInstanceState.getInt(ARG_ID);

            Retrofit retrofit= new Retrofit.Builder().baseUrl("http://dbenitez.ciclo.iesnervion.es").addConverterFactory(GsonConverterFactory.create()).build();
            PokemonInterface pi= retrofit.create(PokemonInterface.class);

            PokemonCallback pokemonCallback = new PokemonCallback(this,false);
            pi.getPokemon(mCurrentPosition).enqueue(pokemonCallback);
        }
        altura = getResources().getDimensionPixelSize(R.dimen.row_height2);

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

        PokemonCallback pokemonCallback = new PokemonCallback(this,false);
        pi.getPokemon(position).enqueue(pokemonCallback);

        mCurrentPosition = position;
    }

    public void pokemonResponse(List<Pokemon> poke, boolean evolucion)
    {
        if (poke != null)
        {
            if(evolucion)
            {
                listaPokes.add(poke.get(0));
                if(listaPokes.size()==tamanio)
                {
                    TextView tv = (TextView) getActivity().findViewById(R.id.txtEvolucion);
                    tv.setVisibility(View.VISIBLE);
                    ListView lista = (ListView) getActivity().findViewById(R.id.evolucionPokemon);
                    Pokemon[] arrayPokemon=new Pokemon[listaPokes.size()];
                    lista.setAdapter(new AdapterIcono<Pokemon>(getActivity().getApplicationContext(), R.layout.row, R.id.texto,listaPokes.toArray(arrayPokemon)));
                    lista.setVisibility(View.VISIBLE);
                }
            }
            else
            {
                Pokemon pokemon = poke.get(0);

                ImageView fotaso = (ImageView) getActivity().findViewById(R.id.imgPokemon);

                Bitmap bmp = BitmapFactory.decodeByteArray(pokemon.getImagen(), 0, pokemon.getImagen().length);

                fotaso.setImageBitmap(bmp);

                TextView pokimon = (TextView) getActivity().findViewById(R.id.pokemon);
                int numPokedex = pokemon.getNumPokedex();
                String cabecera = "";
                if(numPokedex<100)
                    cabecera += "0";
                if(numPokedex<10)
                    cabecera += "0";

                cabecera+=numPokedex;

                cabecera += " " + pokemon.getNombre();

                pokimon.setText(cabecera);

                TextView porcentajeHembra = (TextView) getActivity().findViewById(R.id.porcentajeHembraPokemon);
                porcentajeHembra.setText("" + pokemon.getPorcentajeHembra());

                TextView porcentajeMacho = (TextView) getActivity().findViewById(R.id.porcentajeMachoPokemon);
                porcentajeMacho.setText("" + pokemon.getPorcentajeMacho());

                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://dbenitez.ciclo.iesnervion.es").addConverterFactory(GsonConverterFactory.create()).build();
                TiposPokemonInterface tpi = retrofit.create(TiposPokemonInterface.class);

                TiposPokemonCallback tiposPokemonCallback = new TiposPokemonCallback(this);
                tpi.getTiposPokemon(pokemon.getNumPokedex()).enqueue(tiposPokemonCallback);

                HabilidadesPokemonInterface hpi = retrofit.create(HabilidadesPokemonInterface.class);

                HabilidadesPokemonCallback habilidadesPokemonCallback = new HabilidadesPokemonCallback(this);
                hpi.getHabilidadesPokemon(pokemon.getNumPokedex()).enqueue(habilidadesPokemonCallback);

                EvolucionInterface ei = retrofit.create(EvolucionInterface.class);

                EvolucionCallback evolucionCallback = new EvolucionCallback(this);
                ei.getEvolucion(pokemon.getNumPokedex()).enqueue(evolucionCallback);

                MovimientosPokemonInterface mpi = retrofit.create(MovimientosPokemonInterface.class);

                MovimientosPokemonCallback movimientosPokemonCallback = new MovimientosPokemonCallback(this);
                mpi.getMovimientosPokemon(pokemon.getNumPokedex()).enqueue(movimientosPokemonCallback);
            }
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
            if(!txtPrevio.contains(tipo.getNombre()))
            {
                if (txtPrevio == "")
                    txtTipo.setText(tipo.getNombre());
                else
                    txtTipo.setText(txtPrevio + " / " + tipo.getNombre());
            }
        }
    }

    @Override
    public void habilidadesPokemonResponse(List<HabilidadesPokemon> habilidadesPoke)
    {
        if (habilidadesPoke != null)
        {
            for(int i = 0; i<habilidadesPoke.size();i++)
            {
                Retrofit retrofit= new Retrofit.Builder().baseUrl("http://dbenitez.ciclo.iesnervion.es").addConverterFactory(GsonConverterFactory.create()).build();
                HabilidadInterface hi= retrofit.create(HabilidadInterface.class);

                HabilidadCallback habilidadCallback = new HabilidadCallback(this,habilidadesPoke.get(i).getCategoria());
                hi.getHabilidad(habilidadesPoke.get(i).getIdHabilidad()).enqueue(habilidadCallback);
            }
        }
    }

    @Override
    public void habilidadResponse(List<Habilidad> habilidades, String categoria)
    {
        if(habilidades != null)
        {
            Habilidad habilidad = habilidades.get(0);

            TextView txtHabilidad = null;

            if(categoria.equals("Primaria"))
                txtHabilidad = (TextView) getActivity().findViewById(R.id.habilidadPokemon);
            else
                if(categoria.equals("Secundaria"))
                {
                    txtHabilidad = (TextView) getActivity().findViewById(R.id.habilidadSecundariaPokemon);
                    txtHabilidad.setVisibility(View.VISIBLE);
                }
            else
                if(categoria.equals("Oculta"))
                {
                    TextView tv = (TextView) getActivity().findViewById(R.id.txtHO);
                    tv.setVisibility(View.VISIBLE);
                    txtHabilidad = (TextView) getActivity().findViewById(R.id.habilidadOcultaPokemon);
                    txtHabilidad.setVisibility(View.VISIBLE);
                }
            else
                if(categoria.equals("Oculta Secundaria"))
                {
                    TextView tv = (TextView) getActivity().findViewById(R.id.txtHO);
                    tv.setVisibility(View.VISIBLE);
                    txtHabilidad = (TextView) getActivity().findViewById(R.id.habilidadOcultaSecundariaPokemon);
                    txtHabilidad.setVisibility(View.VISIBLE);
                }

            if(txtHabilidad!=null)
            {
                txtHabilidad.setText(habilidad.getNombre());
            }

        }
    }

    @Override
    public void evolucionResponse(List<Evolucion> evoluciones)
    {
        if (evoluciones != null)
        {
            tamanio = evoluciones.size();

            ListView lista = (ListView) getActivity().findViewById(R.id.evolucionPokemon);

            ViewGroup.LayoutParams params = lista.getLayoutParams();

            params.height = altura*tamanio;

            lista.setLayoutParams(params);

            for(int i = 0; i<evoluciones.size();i++)
            {
                metodosEvolucion.add(evoluciones.get(i).getMetodo());
                Retrofit retrofit= new Retrofit.Builder().baseUrl("http://dbenitez.ciclo.iesnervion.es").addConverterFactory(GsonConverterFactory.create()).build();
                PokemonInterface pi= retrofit.create(PokemonInterface.class);

                PokemonCallback pokemonCallback = new PokemonCallback(this,true);
                pi.getPokemon(evoluciones.get(i).getidEv()).enqueue(pokemonCallback);
            }
        }
    }

    @Override
    public void movimientosPokemonResponse(List<MovimientosPokemon> movimientosPokemon)
    {
        if (movimientosPokemon != null)
        {
            tamanioMov = movimientosPokemon.size();

            ListView lista = (ListView) getActivity().findViewById(R.id.listaMov);

            ViewGroup.LayoutParams params = lista.getLayoutParams();

            params.height = altura*tamanioMov;

            lista.setLayoutParams(params);

            for(int i = 0; i<movimientosPokemon.size();i++)
            {
                Retrofit retrofit= new Retrofit.Builder().baseUrl("http://dbenitez.ciclo.iesnervion.es").addConverterFactory(GsonConverterFactory.create()).build();
                MovimientoInterface mi= retrofit.create(MovimientoInterface.class);

                MovimientoCallback movimientoCallback = new MovimientoCallback(this);
                mi.getMovimiento(movimientosPokemon.get(i).getIdMovimiento()).enqueue(movimientoCallback);
            }
        }
    }

    @Override
    public void movimientoResponse(List<Movimiento> movimientos)
    {
        if (movimientos != null)
        {
            listaMov.add(movimientos.get(0));
            if(listaMov.size()==tamanioMov)
            {
                TextView tv = (TextView) getActivity().findViewById(R.id.txtListaMov);
                tv.setVisibility(View.VISIBLE);
                ListView lista = (ListView) getActivity().findViewById(R.id.listaMov);
                lista.setVisibility(View.VISIBLE);
                Movimiento[] arrayMovimientos=new Movimiento[listaMov.size()];
                listaMov.toArray(arrayMovimientos);
                lista.setAdapter(new AdapterIcono2<Movimiento>(getActivity().getApplicationContext(), R.layout.row, R.id.texto,arrayMovimientos));
                terminado = true;
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);

        outState.putInt(ARG_ID, mCurrentPosition);
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
                row=inflater.inflate(R.layout.row, parent, false);

                TextView tv = (TextView) row.findViewById(R.id.texto);

                holder = new ViewHolder (tv);
                row.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) row.getTag();
            }

            holder.getTv().setText(listaPokes.get(position).getNombre()+": "+metodosEvolucion.get(position));

            return (row);
        }
    }

    class AdapterIcono2<T> extends ArrayAdapter<T>
    {
        private Movimiento[] miLista;
        AdapterIcono2(Context c, int resourceId, int textId, T[] objects)
        {
            super(c, resourceId, textId, objects);
            this.miLista=(Movimiento[]) objects;
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            View row = convertView;
            ViewHolder holder;

            if (row==null)
            {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                row=inflater.inflate(R.layout.row, parent, false);

                TextView tv = (TextView) row.findViewById(R.id.texto);

                holder = new ViewHolder (tv);
                row.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) row.getTag();
            }

            holder.getTv().setText(miLista[position].getNombre());

            return (row);
        }
    }


}
