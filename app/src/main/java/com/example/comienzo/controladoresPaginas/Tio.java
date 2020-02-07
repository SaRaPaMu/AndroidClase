package com.example.comienzo.controladoresPaginas;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.example.comienzo.model.Episodio;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Tio extends PaginaEpisodios {

    private Context ctx;

    OkHttpClient client,client1,client2;
    Request request,request1,request2;


    public Tio(Context ctx){
        this.ctx = ctx;
        url = "https://tioanime.com/";

        client = new OkHttpClient();

        Resources res = ctx.getResources();
    }

    @Override
    public List<Episodio> getFromDocument(Document doc) {

        List<Episodio> items = new ArrayList<>();

        Elements episodios = doc.getElementsByClass("col-6 col-sm-4 col-md-3");
        for (Element link : episodios) {

            String url ="https://tioanime.com"+ link.getElementsByTag("a").attr("href");
            String image ="https://tioanime.com" + link.getElementsByTag("img").attr("src");
            String serie = link.getElementsByClass("title").text();

            Episodio item = new Episodio();
            item.image = image;
            Log.v("CheckData", url);
            item.serie = getNombreSerieTioAnime(url);
            item.nombre = serie.substring(item.serie.length());
            item.urls = new ArrayList();
            item.urls.add(url);
            items.add(item);
        }

        return items;
    }

    private String getNombreSerieTioAnime(String url){
        final List<String> nombre = new ArrayList<>();

        request1 = new Request.Builder()
                .url(url)
                .build();

        client1.newCall(request1).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String html = response.body().string();

                Document doc = Jsoup.parse(html);
                Elements elements = doc.getElementsByClass("btn-primary");
                    String url ="https://tioanime.com"+ elements.get(2).getElementsByTag("a").attr("href");
                    nombre.add(aa(url));

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }
        });

        return nombre.get(0);
    }

    private String aa(String url){
        final List<String> nombre = new ArrayList<>();

        request2 = new Request.Builder()
                .url(url)
                .build();

        client2.newCall(request2).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String html = response.body().string();

                Document doc = Jsoup.parse(html);
                Elements elements = doc.getElementsByTag("aside");
                String name = elements.get(1).getElementsByClass("Title").text();
                nombre.add(name);

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

                e.printStackTrace();
            }
        });



        return nombre.get(0);
    }

}
