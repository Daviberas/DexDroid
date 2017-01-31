package es.iesnervion.dbenitez.dexdroid.Activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id)
        {
            case R.id.nav_pokemon:
                getFragmentManager().beginTransaction().replace(R.id.content_main,new ListadoPokemonFragment()).commit();
                break;
            case R.id.nav_tipos:
                getFragmentManager().beginTransaction().replace(R.id.content_main,new ListadoTiposFragment()).commit();
                break;
            case R.id.nav_habilidades:
                getFragmentManager().beginTransaction().replace(R.id.content_main,new ListadoHabilidadesFragment()).commit();
                break;
            case R.id.nav_movimientos:
                getFragmentManager().beginTransaction().replace(R.id.content_main,new ListadoMovimientosFragment()).commit();
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

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTipoClicked(TipoClickedEvent event)
    {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHabilidadClicked(HabilidadClickedEvent event)
    {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMovimientoClicked(MovimientoClickedEvent event)
    {

    }
}
