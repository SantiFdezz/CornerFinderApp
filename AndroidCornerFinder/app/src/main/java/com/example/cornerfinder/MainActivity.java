    package com.example.cornerfinder;

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

    import com.example.cornerfinder.databinding.ActivityMainBinding;

    public class MainActivity extends AppCompatActivity {
        private Context context = this;
        private DrawerLayout drawerLayout;
        private Toolbar toolbar;


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
            //creamos el elemento que escuchara en cual boton clickamos de nuestro menú
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    // menu que lleva a sus actividades
                    Fragment fragment = null;
                    if (item.getItemId() == R.id.nav_home) {
                        // Navega a al fragment1 .. Carlos ponme un 70
                        fragment = new RecommendedFragment();
                    }else if(item.getItemId() == R.id.nav_gallery){
                        fragment = new AddlocationFragment();
                    }else if(item.getItemId() == R.id.nav_lugaresguardados){
                        fragment = new AddlocationFragment();
                    }else if(item.getItemId() == R.id.nav_slideshow){
                        fragment = new AddlocationFragment();
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
        }
    }