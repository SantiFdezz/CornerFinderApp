    package com.example.cornerfinder;

    import android.app.Activity;
    import android.content.Context;
    import android.graphics.Color;
    import android.graphics.PorterDuff;
    import android.graphics.PorterDuffColorFilter;
    import android.graphics.drawable.Drawable;
    import android.os.Bundle;
    import android.text.SpannableString;
    import android.text.style.ForegroundColorSpan;
    import android.view.MenuItem;
    import android.view.View;
    import android.view.Menu;
    import android.widget.Toast;

    import com.android.volley.Request;
    import com.android.volley.RequestQueue;
    import com.android.volley.Response;
    import com.android.volley.VolleyError;
    import com.android.volley.toolbox.JsonArrayRequest;
    import com.android.volley.toolbox.Volley;
    import com.example.cornerfinder.savedplaces.SavedPlacesFragment;
    import com.example.cornerfinder.summermode.SummerModeAdapter;
    import com.example.cornerfinder.summermode.SummerModeData;
    import com.google.android.material.snackbar.Snackbar;
    import com.google.android.material.navigation.NavigationView;

    import androidx.activity.OnBackPressedCallback;
    import androidx.annotation.NonNull;
    import androidx.appcompat.app.ActionBarDrawerToggle;
    import androidx.appcompat.widget.Toolbar;
    import androidx.core.view.GravityCompat;
    import androidx.fragment.app.Fragment;
    import androidx.navigation.NavController;
    import androidx.navigation.Navigation;
    import androidx.navigation.ui.AppBarConfiguration;
    import androidx.navigation.ui.NavigationUI;
    import androidx.drawerlayout.widget.DrawerLayout;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import com.example.cornerfinder.databinding.ActivityMainBinding;

    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    import java.util.ArrayList;
    import java.util.List;

    public class MainActivity extends AppCompatActivity {
        private Context context = this;
        private DrawerLayout drawerLayout;
        private Toolbar toolbar;
        private Activity activity=this;
        private RecyclerView recyclerView;


        @Override
        protected void onCreate(Bundle savedInstanceState) {//inicializamos los atributos
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            drawerLayout = findViewById(R.id.drawer_layout);
            toolbar = findViewById(R.id.toolbar);


            //creamos metodo que cierre el menu y no la app
            getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
                //metodo que cierra el menu si se pulsa atrás.
                @Override
                public void handleOnBackPressed() {
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                        drawerLayout.closeDrawer(GravityCompat.START);
                    }else{
                        if (isEnabled()){
                            setEnabled(false);
                            MainActivity.super.onBackPressed();
                        }
                    }
                }
            });

            setSupportActionBar(toolbar);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
            NavigationView navigationView = findViewById(R.id.nav_view);
         //   creamos el elemento que escuchara en cual boton clickamos de nuestro menú
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    // menu que lleva a sus actividades
                    Fragment fragment = null;
                    if (item.getItemId() == R.id.nav_hotspots) {
                        //fragment = new ();
                    }else if(item.getItemId() == R.id.nav_recommended){
                        fragment = new RecommendedFragment();
                    }else if(item.getItemId() == R.id.nav_lugaresguardados){
                        fragment = new AddlocationFragment();
                    }else if(item.getItemId() == R.id.nav_summermode){
                        fragment = new AddlocationFragment();
                    }else if(item.getItemId() == R.id.nav_lugaresguardados){
                        fragment = new SavedPlacesFragment();
                    }else if(item.getItemId() == R.id.nav_generalmap){
                        //fragment = new MapsFR();
                    }
                    //si no llega ningun fragment
                    if (fragment != null){
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    }
                    // Cierra el Navigation Drawer después de la selección
                    return false;
                }
            });


            // A partir de aquí es código del recycler view:

            recyclerView = findViewById(R.id.recycler_view_summermode); // Aquí obtenemos una referencia al RecyclerView desde el diseño

            JsonArrayRequest request = new JsonArrayRequest( // Creamos una solicitud GET usando Volley para obtener un JSONArray de nuestra URL.
                    Request.Method.GET,
                    "https://raw.githubusercontent.com/Bl4nc018/Proyectos-2-trimestre/main/beaches.json",
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {  // Procedemos a procesar la respuesta JSON y crear una lista de objetos GameData
                            // donde estarán los datos de la url.
                            List<SummerModeData> allTheBeaches = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                try { // Convertimos cada objeto JSON en un objeto GameData.
                                    JSONObject game = response.getJSONObject(i);
                                    SummerModeData data = new SummerModeData(game);
                                    allTheBeaches.add(data);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            // Creamos un adaptador con la lista de datos y la actividad asociada.
                            SummerModeAdapter adapter = new SummerModeAdapter(allTheBeaches, activity);

                            // Configuramos el RecyclerView con el adaptador y un LinearLayoutManager.
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // En el siguiente método y la línea del mismo, manejamos los errores de la solicitud y mostramos un Toast con este.
                            Toast.makeText(activity, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
            );
            // Una vez finalizado lo anterior, agregamos la solicitud a la cola de Volley para su procesamiento.
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(request);
        }

    }