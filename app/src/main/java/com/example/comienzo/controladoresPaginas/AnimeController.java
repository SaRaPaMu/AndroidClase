package com.example.comienzo.controladoresPaginas;

import android.content.Context;
import android.content.res.Resources;

import com.example.comienzo.R;
import com.example.comienzo.listener.PaginaListener;
import com.example.comienzo.model.Episodio;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AnimeController {

    private Context ctx;
    private String[] paginas;
    private ArrayList<PaginaEpisodios> items;


    OkHttpClient client;
    Request request;


    public AnimeController(Context ctx){
        this.ctx = ctx;
        client = new OkHttpClient();

        Resources res = ctx.getResources();
        paginas = res.getStringArray(R.array.paginas);
    }

    public void getEpisodies(PaginaListener listener ) throws IOException {

        items = new ArrayList<>();
        items.add(new Tio(ctx));
        items.add(new Flv());
        items.add(new Fenix());


        for (PaginaEpisodios pagina: items) {
            getPaginaEpisodios(pagina, listener);
        }
    }

    private void getPaginaEpisodios(final PaginaEpisodios pagina, final PaginaListener listener) {
        request = new Request.Builder()
                .url(pagina.url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String html = response.body().string();

                Document doc = Jsoup.parse(html);
                List<Episodio> items = pagina.getFromDocument(doc);

                if (listener !=null){
                    listener.devolver(items);
                }

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

                e.printStackTrace();
            }
        });
    }


}
