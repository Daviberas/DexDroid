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

import es.iesnervion.dbenitez.dexdroid.Models.Movimiento;
import es.iesnervion.dbenitez.dexdroid.R;
import es.iesnervion.dbenitez.dexdroid.RetrofitInterfaces.MovimientoInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListadoMovimientosFragment extends ListFragment implements Callback<List<Movimiento>>
{
    private List<Movimiento> movimientos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View result= super.onCreateView(inflater, container, savedInstanceState);

        setRetainInstance(true);

        Retrofit retrofit= new Retrofit.Builder().baseUrl("http://dbenitez.ciclo.iesnervion.es").addConverterFactory(GsonConverterFactory.create()).build();
        MovimientoInterface ti= retrofit.create(MovimientoInterface.class);

        ti.getMovimiento().enqueue(this);

        return(result);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        Movimiento Movimiento= ((ListadoMovimientosFragment.AdapterIcono<Movimiento>)getListAdapter()).getItem(position);

        EventBus.getDefault().post(new MovimientoClickedEvent(Movimiento));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResponse(Call<List<Movimiento>> call, Response<List<Movimiento>> response)
    {
        movimientos = response.body();
        Movimiento[] arraymovimientos =new Movimiento[movimientos.size()];
        setListAdapter(new ListadoMovimientosFragment.AdapterIcono<Movimiento>(this.getContext(), R.layout.row, R.id.texto,movimientos.toArray(arraymovimientos)));
    }

    @Override
    public void onFailure(Call<List<Movimiento>> call, Throwable t)
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

            holder.getTv().setText(movimientos.get(position).getNombre());

            return (row);
        }
    }
}
