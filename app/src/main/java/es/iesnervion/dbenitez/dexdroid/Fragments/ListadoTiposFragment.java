package es.iesnervion.dbenitez.dexdroid.Fragments;

import android.app.ListFragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Models.Tipo;
import es.iesnervion.dbenitez.dexdroid.R;
import es.iesnervion.dbenitez.dexdroid.RetrofitInterfaces.PokemonInterface;
import es.iesnervion.dbenitez.dexdroid.RetrofitInterfaces.TipoInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListadoTiposFragment extends ListFragment implements Callback<List<Tipo>>
{
    private List<Tipo> tipos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View result= super.onCreateView(inflater, container, savedInstanceState);

        setRetainInstance(true);

        Retrofit retrofit= new Retrofit.Builder().baseUrl("http://dbenitez.ciclo.iesnervion.es").addConverterFactory(GsonConverterFactory.create()).build();
        TipoInterface ti= retrofit.create(TipoInterface.class);

        ti.getTipo().enqueue(this);

        return(result);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        Tipo tipo= ((ListadoTiposFragment.AdapterIcono<Tipo>)getListAdapter()).getItem(position);

        EventBus.getDefault().post(new TipoClickedEvent(tipo));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResponse(Call<List<Tipo>> call, Response<List<Tipo>> response)
    {
        tipos = response.body();
        Tipo[] arrayTipos =new Tipo[tipos.size()];
        setListAdapter(new ListadoTiposFragment.AdapterIcono<Tipo>(this.getContext(), R.layout.row, R.id.texto,tipos.toArray(arrayTipos)));
    }

    @Override
    public void onFailure(Call<List<Tipo>> call, Throwable t)
    {
        Toast.makeText(getActivity(), t.getMessage(),Toast.LENGTH_SHORT).show();
        Log.e(getClass().getSimpleName(),"Exception from Retrofit request to StackOverflow", t);
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

            holder.getTv().setText(tipos.get(position).getNombre());

            return (row);
        }
    }
}