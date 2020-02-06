package com.example.comienzo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.comienzo.adapter.ListAdapter;
import com.example.comienzo.controladoresPaginas.AnimeController;
import com.example.comienzo.controladoresPaginas.PaginaListener;
import com.example.comienzo.listener.ListAdapterListener;
import com.example.comienzo.model.Episodio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = this;
        initData();
    }

    void initData(){

        final RecyclerView lista = findViewById(R.id.main_list);
        final List<Episodio> listado = new ArrayList<>();
        final ListAdapterListener listener = new ListAdapterListener() {
            @Override
            public void click(Episodio item) {

                Intent intent = new Intent(ctx, DetailsActivity.class);
                intent.putExtra(DetailsActivity.ITEM_CLICKADO, item.nombre);
                intent.putExtra("episodio","");
                startActivity(intent);
            }
        };
        final ListAdapter adapter = new ListAdapter(this, listado, listener);
        lista.setAdapter(adapter);


        AnimeController ctrl = new AnimeController(ctx);
        try {
            ctrl.getEpisodies(new PaginaListener() {
                @Override
                public void devolver(List<Episodio> items) {
                    listado.addAll(items);


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        lista.setLayoutManager(mLayoutManager);

    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onStart() {
        super.onStart();

        ctx = this;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();

        ctx = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}
