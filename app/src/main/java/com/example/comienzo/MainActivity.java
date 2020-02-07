package com.example.comienzo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.comienzo.adapter.ListAdapter;
import com.example.comienzo.controladoresPaginas.AnimeController;
import com.example.comienzo.listener.PaginaListener;
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
        setTitle("AnimeNotify");
        ctx = this;
        initData();
    }

    void initData(){

        final RecyclerView lista = findViewById(R.id.main_list);
        final List<Episodio> listado = new ArrayList<>();
        final ListAdapterListener listener = new ListAdapterListener() {
            @Override
            public void click(Episodio item) {

                if (item.urls.isEmpty()){
                    return;
                }

                Intent intent = new Intent(ctx, DetailsActivity.class);
                intent.putExtra(DetailsActivity.ITEM_CLICKADO, item);
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


                    for (Episodio item : items) {
                        boolean isIn = false;
                        String cSerie = ""+item.serie;
                        
                        for (Episodio episodio : listado) {
                            String serie = ""+episodio.serie;
                            if (cSerie.contains(serie)){
                                isIn = true;
                                item.urls.addAll(episodio.urls);
                                break;
                            }
                        }
                        if (!isIn){
                            listado.add(item);
                        }
                    }

                    //listado.addAll(items);



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
