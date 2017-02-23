package es.iesnervion.dbenitez.dexdroid.Activities;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import es.iesnervion.dbenitez.dexdroid.Fragments.DetalleHabilidadFragment;
import es.iesnervion.dbenitez.dexdroid.Fragments.DetalleMovimientoFragment;
import es.iesnervion.dbenitez.dexdroid.Fragments.DetallePokemonFragment;
import es.iesnervion.dbenitez.dexdroid.Fragments.DetalleTipoFragment;
import es.iesnervion.dbenitez.dexdroid.Fragments.HabilidadClickedEvent;
import es.iesnervion.dbenitez.dexdroid.Fragments.ListadoHabilidadesFragment;
import es.iesnervion.dbenitez.dexdroid.Fragments.ListadoMovimientosFragment;
import es.iesnervion.dbenitez.dexdroid.Fragments.ListadoPokemonFragment;
import es.iesnervion.dbenitez.dexdroid.Fragments.ListadoTiposFragment;
import es.iesnervion.dbenitez.dexdroid.Fragments.MovimientoClickedEvent;
import es.iesnervion.dbenitez.dexdroid.Fragments.PokemonClickedEvent;
import es.iesnervion.dbenitez.dexdroid.Fragments.TipoClickedEvent;
import es.iesnervion.dbenitez.dexdroid.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (getFragmentManager().findFragmentById(R.id.content_main) == null)
        {
            getFragmentManager().beginTransaction().add(R.id.content_main,new ListadoPokemonFragment()).commit();
        }
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else
        {
            if (noHayFragmentCargando())
                super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(noHayFragmentCargando())
        switch (id)
        {
            case R.id.nav_pokemon:
                ListadoPokemonFragment fragmentPokes = new ListadoPokemonFragment();
                FragmentTransaction transactionPokes = getFragmentManager().beginTransaction();

                transactionPokes.replace(R.id.content_main,fragmentPokes);
                transactionPokes.addToBackStack(null);

                transactionPokes.commit();
                break;
            case R.id.nav_tipos:
                ListadoTiposFragment fragmentTipos = new ListadoTiposFragment();
                FragmentTransaction transactionTipos = getFragmentManager().beginTransaction();

                transactionTipos.replace(R.id.content_main,fragmentTipos);
                transactionTipos.addToBackStack(null);

                transactionTipos.commit();
                break;
            case R.id.nav_habilidades:
                ListadoHabilidadesFragment fragmentHabilidades = new ListadoHabilidadesFragment();
                FragmentTransaction transactionHabilidades = getFragmentManager().beginTransaction();

                transactionHabilidades.replace(R.id.content_main,fragmentHabilidades);
                transactionHabilidades.addToBackStack(null);

                transactionHabilidades.commit();
                break;
            case R.id.nav_movimientos:
                ListadoMovimientosFragment fragmentMovimientos = new ListadoMovimientosFragment();
                FragmentTransaction transactionMovimientos = getFragmentManager().beginTransaction();

                transactionMovimientos.replace(R.id.content_main,fragmentMovimientos);
                transactionMovimientos.addToBackStack(null);

                transactionMovimientos.commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause()
    {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPokemonClicked(PokemonClickedEvent event)
    {
        DetallePokemonFragment fragment = new DetallePokemonFragment();
        Bundle args = new Bundle();
        args.putInt(DetallePokemonFragment.ARG_ID, event.getPokemon().getNumPokedex());
        fragment.setArguments(args);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.content_main,fragment,"pokemonFragment");
        transaction.addToBackStack(null);

        transaction.commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTipoClicked(TipoClickedEvent event)
    {
        DetalleTipoFragment fragment = new DetalleTipoFragment();
        Bundle args = new Bundle();
        args.putInt(DetalleTipoFragment.ARG_ID, event.getTipo().getId());
        fragment.setArguments(args);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.content_main,fragment,"tipoFragment");
        transaction.addToBackStack(null);

        transaction.commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHabilidadClicked(HabilidadClickedEvent event)
    {
        DetalleHabilidadFragment fragment = new DetalleHabilidadFragment();
        Bundle args = new Bundle();
        args.putInt(DetalleHabilidadFragment.ARG_ID, event.getHabilidad().getId());
        fragment.setArguments(args);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.content_main,fragment,"habilidadFragment");
        transaction.addToBackStack(null);

        transaction.commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMovimientoClicked(MovimientoClickedEvent event)
    {
        DetalleMovimientoFragment fragment = new DetalleMovimientoFragment();
        Bundle args = new Bundle();
        args.putInt(DetalleMovimientoFragment.ARG_ID, event.getMovimiento().getId());
        fragment.setArguments(args);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.content_main,fragment,"movimientoFragment");
        transaction.addToBackStack(null);

        transaction.commit();
    }

    public boolean noHayFragmentCargando()
    {
        boolean puedeEjecutar = false;

        DetallePokemonFragment pokeFragment = (DetallePokemonFragment) getFragmentManager().findFragmentByTag("pokemonFragment");
        DetalleTipoFragment tipoFragment = (DetalleTipoFragment) getFragmentManager().findFragmentByTag("tipoFragment");
        DetalleHabilidadFragment habilidadFragment = (DetalleHabilidadFragment) getFragmentManager().findFragmentByTag("habilidadFragment");
        DetalleMovimientoFragment movimientoFragment = (DetalleMovimientoFragment) getFragmentManager().findFragmentByTag("movimientoFragment");
        if(pokeFragment!=null)
        {
            if(pokeFragment.terminado==true)
                puedeEjecutar = true;
        }
        else
            if(tipoFragment!=null)
            {
                if(tipoFragment.terminado==true)
                    puedeEjecutar = true;
            }
            else
                if(habilidadFragment!=null)
                {
                    if(habilidadFragment.terminado==true)
                        puedeEjecutar = true;
                }
                else
                    if(movimientoFragment!=null)
                    {
                        if(movimientoFragment.terminado==true)
                            puedeEjecutar = true;
                    }
                    else
                        if(pokeFragment==null && tipoFragment==null && habilidadFragment == null && movimientoFragment==null)
                            puedeEjecutar = true;

        return puedeEjecutar;
    }
}
