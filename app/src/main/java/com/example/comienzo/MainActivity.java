package com.example.comienzo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.comienzo.adapter.ListAdapter;
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
        initData();
    }

    void initData(){

        AnimeController ctrl = new AnimeController(ctx);
        try {
            ctrl.getEpisodies(new PaginaListener() {
                @Override
                public void devolver(List<Episodio> items) {
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Episodio> listado = new ArrayList<>();
/*
        for (Episodio episodio : ctrl.getItems()) {

        }

        ListAdapterListener listener = new ListAdapterListener() {
            @Override
            public void click(Episodio item) {

                Intent intent = new Intent(ctx, DetailsActivity.class);
                intent.putExtra(DetailsActivity.ITEM_CLICKADO, item);
                intent.putExtra("episodio","");
                startActivity(intent);
            }
        };

        ListAdapter adapter = new ListAdapter(this, listado, listener);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);

        RecyclerView lista = findViewById(R.id.act_main_lista_rec);
        lista.setLayoutManager(mLayoutManager);
        lista.setAdapter(adapter);
        */
    }

}
