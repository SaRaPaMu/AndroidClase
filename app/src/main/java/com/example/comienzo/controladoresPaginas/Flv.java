package com.example.comienzo.controladoresPaginas;

import com.example.comienzo.model.Episodio;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;



public class Flv extends PaginaEpisodios {

    public Flv(){
        url = "https://m.animeflv.net/";
    }

    @Override
    public List<Episodio> getFromDocument(Document doc) {

        List<Episodio> items = new ArrayList<>();

        Elements episodios = doc.getElementsByClass("Episode");
        for (Element link : episodios) {

            if(link.getElementsByClass("OUTBRAIN").size() > 0){
                continue;
            }

            String url = "https://m.animeflv.net" +  link.getElementsByTag("a").attr("href");
            String image = "https://m.animeflv.net" + link.getElementsByTag("img").attr("src");
            //Element span = link.getElementsByTag("a").tagName("p").get(0);
            String nombre = "";
            Elements a = link.getElementsByTag("a");
            Elements p = a.tagName("p");
            Elements span = p.get(0).getElementsByTag("span");
            nombre = span.text();


            String serie = link.getElementsByClass("Title").text();


            Episodio item = new Episodio();
            item.image = image;
            //item.nombre = String.valueOf(nombre.length());
            item.nombre = nombre;
            item.serie = serie;
            item.urls = new ArrayList();
            item.urls.add(url);
            items.add(item);

        }

        return items;
    }
}
