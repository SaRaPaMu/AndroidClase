package com.example.comienzo.controladoresPaginas;

import android.content.Context;
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

    OkHttpClient client;
    Request request;
    private final List<String> nombre = new ArrayList<>();

    public Tio(){
        url = "https://tioanime.com/";

        client = new OkHttpClient();

    }

    @Override
    public List<Episodio> getFromDocument(Document doc) {

        List<Episodio> items = new ArrayList<>();

        Elements episodios = doc.getElementsByClass("col-6 col-sm-4 col-md-3");
        for (Element link : episodios) {

            String url ="https://tioanime.com"+ link.getElementsByTag("a").attr("href");
            String image ="https://tioanime.com" + link.getElementsByTag("img").attr("src");
            String serie = link.getElementsByClass("title").text();

            Log.v("CheckData", url);
            getNombreSerieTioAnime(url);

            //TioAsyncTask tAsT = new TioAsyncTask(url);

            //String name =  tAsT.doInBackground();


            Episodio item = new Episodio();
            item.image = image;
            item.serie = serie;
            //item.nombre = serie.substring(name.length());
            item.urls = new ArrayList();
            item.urls.add(url);
            items.add(item);
        }
        /*
        int i = 0;
        for(Episodio epi: items){
            epi.nombre = nombre.get(i);
            epi.serie = epi.serie.substring(nombre.get(i).length());
            i++;
        }
*/
        return items;
    }

    private void getNombreSerieTioAnime(String url){
        request = new Request.Builder()
                .url(url)
                .build();

        Log.v("CheckUrl", "Entro en getnombreserie");
        Log.v("CheckUrl", url);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String html = response.body().string();

                Document doc = Jsoup.parse(html);
                Elements elements = doc.getElementsByClass("btn-primary");
                    String url ="https://tioanime.com"+ elements.get(2).getElementsByTag("a").attr("href");

                    Log.v("CheckUrl", url);
                    aa(url);

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.v("CheckFailure1", "No ha funcionao");
                e.printStackTrace();
            }
        });
        Log.v("CheckUrl", "salgo de nombreserie");
    }

    private void aa(String url){
        request = new Request.Builder()
                .url(url)
                .build();

        Log.v("CheckUrl", "Entro en aa");

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String html = response.body().string();

                Document doc = Jsoup.parse(html);
                Elements elements = doc.getElementsByTag("aside");
                String name = elements.get(1).getElementsByClass("Title").text();
                Log.v("CheckName", name);
                nombre.add(name);

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.v("CheckFailure2", "No ha funcionao");
                e.printStackTrace();
            }
        });

        Log.v("CheckUrl", "salgo de aa");
    }

}
