package es.iesnervion.dbenitez.dexdroid.Fragments.Listados;

import android.app.ListFragment;
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
import org.greenrobot.eventbus.EventBus;
import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Fragments.Callbacks.PokemonCallback;
import es.iesnervion.dbenitez.dexdroid.Fragments.ClickedEvents.PokemonClickedEvent;
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
import es.iesnervion.dbenitez.dexdroid.Interfaces.PokemonInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListadoPokemonFragment extends ListFragment implements ApiResponse
{
    Pokemon[] arrayPokemon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View result= super.onCreateView(inflater, container, savedInstanceState);

        setRetainInstance(true);

        Retrofit retrofit= new Retrofit.Builder().baseUrl("http://dbenitez.ciclo.iesnervion.es").addConverterFactory(GsonConverterFactory.create()).build();
        PokemonInterface pi= retrofit.create(PokemonInterface.class);

        PokemonCallback pokemonCallback = new PokemonCallback(this);
        pi.getPokemon().enqueue(pokemonCallback);

        return(result);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        Pokemon poke= ((AdapterIcono<Pokemon>)getListAdapter()).getItem(position);

        EventBus.getDefault().post(new PokemonClickedEvent(poke));
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void pokemonResponse(List<Pokemon> pokes,boolean evolucion)
    {
        arrayPokemon=new Pokemon[pokes.size()];
        setListAdapter(new AdapterIcono<Pokemon>(getContext(), R.layout.row_imagen, R.id.texto,pokes.toArray(arrayPokemon)));
    }

    @Override
    public void tiposPokemonResponse(List<TiposPokemon> tiposPoke)
    {

    }

    @Override
    public void tipoResponse(List<Tipo> tipos)
    {

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
                row=inflater.inflate(R.layout.row_imagen, parent, false);

                TextView tv = (TextView) row.findViewById(R.id.texto);
                ImageView iv = (ImageView) row.findViewById(R.id.imagen);

                holder = new ViewHolder (tv,iv);
                row.setTag(holder);

                getListView().setDivider(null);
                getListView().setDividerHeight(0);
            }
            else
            {
                holder = (ViewHolder) row.getTag();
            }

            holder.getTv().setText(arrayPokemon[position].getNombre());
            Bitmap bmp = BitmapFactory.decodeByteArray(arrayPokemon[position].getImagen(), 0, arrayPokemon[position].getImagen().length);

            holder.getIv().setImageBitmap(Bitmap.createScaledBitmap(bmp, holder.getIv().getWidth(), holder.getIv().getHeight(), false));


            return (row);
        }
    }
}
