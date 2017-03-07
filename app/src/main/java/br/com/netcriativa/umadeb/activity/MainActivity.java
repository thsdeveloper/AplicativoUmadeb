package br.com.netcriativa.umadeb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.ShareActionProvider;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import br.com.netcriativa.umadeb.R;
import br.com.netcriativa.umadeb.fragment.AgendaGeralFragment;
import br.com.netcriativa.umadeb.fragment.MainFragment;
import br.com.netcriativa.umadeb.fragment.IntegrantesFragment;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Seta a toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.app_name);


        //Inicia o fragment1 como padrão
        if (savedInstanceState == null) {
            Fragment f = MainFragment.newInstance("fragment1");
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
        }


        //Botão de Compartilhamento com Social Media
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "UMADEB Play, O aplicativo do maior congresso de Jovens Pentecostais do Brasil agora na sua mão e por onde for! https://play.google.com/store/apps/details?id=com.adeb&hl=pt_BR";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Umadeb Play, interatividade e informação.");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Compartilhar APP"));
            }
        });

        //Navigation Drawer Ativo nos fragments
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.btn_quem_somos) {

            AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
            alerta.setTitle("UMADEB Play");
            alerta.setIcon(R.mipmap.ic_umadeb_app_brasilia);
            alerta.setMessage("UNIÃO DE MOCIDADES DA ASSEMBLEIA DEUS DE BRASÍLIA");
            alerta.setCancelable(true);

            AlertDialog alertDialog = alerta.create();
            alertDialog.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.umadeb_2018) {

            setTitle(R.string.app_name);
            Fragment f = MainFragment.newInstance("fragment1");
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();

        } else if (id == R.id.integrantes) {

            setTitle("Integrantes");
            Fragment f = IntegrantesFragment.newInstance("fragment2");
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();

        } else if (id == R.id.agenda_geral) {

            setTitle("Agenda Geral");
            Fragment f = AgendaGeralFragment.newInstance("AgendaGeralFragment");
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();

            // Intent intent = new Intent(this, AgendaGeralActivity.class);
            //startActivity(intent);

        } else if (id == R.id.btn_album) {
            Intent intent = new Intent(this, AlbumActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}