package es.iesnervion.dbenitez.dexdroid.Fragments.Listados;

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

import es.iesnervion.dbenitez.dexdroid.Fragments.Callbacks.HabilidadCallback;
import es.iesnervion.dbenitez.dexdroid.Fragments.ClickedEvents.HabilidadClickedEvent;
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
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListadoHabilidadesFragment extends ListFragment implements ApiResponse
{
    Habilidad[] arrayHabilidades;
    int altura;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View result= super.onCreateView(inflater, container, savedInstanceState);

        setRetainInstance(true);

        Retrofit retrofit= new Retrofit.Builder().baseUrl("http://dbenitez.ciclo.iesnervion.es").addConverterFactory(GsonConverterFactory.create()).build();
        HabilidadInterface ti= retrofit.create(HabilidadInterface.class);

        HabilidadCallback habilidadCallback = new HabilidadCallback(this,"");

        ti.getHabilidad().enqueue(habilidadCallback);
        altura = getResources().getDimensionPixelSize(R.dimen.row_height);

        return(result);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        Habilidad Habilidad= ((ListadoHabilidadesFragment.AdapterIcono<Habilidad>)getListAdapter()).getItem(position);

        EventBus.getDefault().post(new HabilidadClickedEvent(Habilidad));
    }

    @Override
    public void pokemonResponse(List<Pokemon> poke, boolean evolucion)
    {

    }

    @Override
    public void tiposPokemonResponse(List<TiposPokemon> tiposPoke) {

    }

    @Override
    public void tipoResponse(List<Tipo> tipos) {

    }

    @Override
    public void habilidadesPokemonResponse(List<HabilidadesPokemon> habilidadesPoke) {

    }

    @Override
    public void habilidadResponse(List<Habilidad> habilidades, String categoria)
    {
        arrayHabilidades =new Habilidad[habilidades.size()];
        ViewGroup.LayoutParams params = getListView().getLayoutParams();

        params.height = altura*habilidades.size();

        getListView().setLayoutParams(params);
        setListAdapter(new ListadoHabilidadesFragment.AdapterIcono<Habilidad>(getActivity().getApplicationContext(), R.layout.row, R.id.texto,habilidades.toArray(arrayHabilidades)));
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
                row=inflater.inflate(R.layout.row, parent, false);

                TextView tv = (TextView) row.findViewById(R.id.texto);


                holder = new ViewHolder (tv);
                row.setTag(holder);
                getListView().setDivider(null);
                getListView().setDividerHeight(0);
            }
            else
            {
                holder = (ViewHolder) row.getTag();
            }

            holder.getTv().setText(arrayHabilidades[position].getNombre());

            return (row);
        }
    }
}