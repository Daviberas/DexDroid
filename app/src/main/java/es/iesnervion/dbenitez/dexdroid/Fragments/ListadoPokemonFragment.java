package es.iesnervion.dbenitez.dexdroid.Fragments;

import android.app.ListFragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import org.greenrobot.eventbus.EventBus;
import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Models.Pokemon;
import es.iesnervion.dbenitez.dexdroid.R;
import es.iesnervion.dbenitez.dexdroid.RetrofitInterfaces.PokemonInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListadoPokemonFragment extends ListFragment implements ApiResponseDetallePokemon
{
    PokemonCallback pokemonCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View result= super.onCreateView(inflater, container, savedInstanceState);

        setRetainInstance(true);

        Retrofit retrofit= new Retrofit.Builder().baseUrl("http://dbenitez.ciclo.iesnervion.es").addConverterFactory(GsonConverterFactory.create()).build();
        PokemonInterface pi= retrofit.create(PokemonInterface.class);

        pokemonCallback = new PokemonCallback(this);
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
    public void pokemonResponsed(List<Pokemon> pokes)
    {
        Pokemon[] arrayPokemon=new Pokemon[pokes.size()];
        setListAdapter(new AdapterIcono<Pokemon>(getContext(), R.layout.row, R.id.texto,pokes.toArray(arrayPokemon)));
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

            holder.getTv().setText(pokemonCallback.getPokes().get(position).getNombre());

            return (row);
        }
    }
}
