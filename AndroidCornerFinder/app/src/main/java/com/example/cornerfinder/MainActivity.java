    package com.example.cornerfinder;

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

    import androidx.navigation.NavController;
    import androidx.navigation.Navigation;
    import androidx.navigation.ui.AppBarConfiguration;
    import androidx.navigation.ui.NavigationUI;
    import androidx.drawerlayout.widget.DrawerLayout;
    import androidx.appcompat.app.AppCompatActivity;

    import com.example.cornerfinder.databinding.ActivityMainBinding;

    public class MainActivity extends AppCompatActivity {

        private AppBarConfiguration mAppBarConfiguration;
        private ActivityMainBinding binding;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            setSupportActionBar(binding.appBarMain.toolbar);
            binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            DrawerLayout drawer = binding.drawerLayout;

            // Obtenemos el navigation view y lo guardamos en una variable.
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            // Guardamos el menu del navigation view en una variable.
            Menu menu = navigationView.getMenu();
            // Guardamos el item del cual queremos cambiar el color.
            MenuItem menuItem = menu.findItem(R.id.nav_closesession);
            // Guardamos el texto del item en un spannable, que permite estilizarlo.
            SpannableString s = new SpannableString(menuItem.getTitle());
            s.setSpan(new ForegroundColorSpan(Color.RED), 0, s.length(), 0);
            menuItem.setTitle(s);

            // Guardamos el icono del item en una variable drawable.
            Drawable icon = menuItem.getIcon();
            if (icon != null) {
                // Mientras que el icono exista, se cambiará su filtro de color a rojo.
                icon.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SRC_IN));
                menuItem.setIcon(icon);
            }

            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                    .setOpenableLayout(drawer)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

        @Override
        public boolean onSupportNavigateUp() {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
            return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                    || super.onSupportNavigateUp();
        }
    }