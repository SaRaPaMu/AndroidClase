package com.example.comienzo.controladoresPaginas;

import android.os.AsyncTask;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TioAsyncTask extends AsyncTask<String,Void,String> {

    OkHttpClient client;
    Request request;
    String url;

    public TioAsyncTask(String url){
        this.url = url;
        client = new OkHttpClient();
    }

    @Override
    protected String doInBackground(String... strings) {
        final List<String> nombre = new ArrayList<>();
        final List<Boolean> finalizado = new ArrayList<>();

        finalizado.add(0,false);
        request = new Request.Builder()
                    .url(url)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                    String html = response.body().string();

                    Document doc = Jsoup.parse(html);
                    Elements elements = doc.getElementsByClass("btn-primary");
                    String url2 ="https://tioanime.com"+ elements.get(2).getElementsByTag("a").attr("href");

                    request = new Request.Builder()
                            .url(url2)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                            String html = response.body().string();

                            Document doc = Jsoup.parse(html);
                            Elements elements = doc.getElementsByTag("aside");
                            String name = elements.get(1).getElementsByClass("Title").text();

                            nombre.add(name);

                            finalizado.set(0,true);
                        }

                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                            e.printStackTrace();
                        }
                    });

                }

                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                }
            });

            do{

            }while(finalizado.get(0)==false);

        return nombre.get(0);
    }
}
