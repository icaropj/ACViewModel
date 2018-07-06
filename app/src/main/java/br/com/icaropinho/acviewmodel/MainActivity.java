package br.com.icaropinho.acviewmodel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.icaropinho.acviewmodel.home.RepoListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.screen_container, new RepoListFragment())
                    .commit();
        }
    }
}
