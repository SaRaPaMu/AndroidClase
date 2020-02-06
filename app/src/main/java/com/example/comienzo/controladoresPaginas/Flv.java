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
            String url = link.attr("href");
            String image = link.getElementsByTag("img").attr("src");
            //Element span = link.getElementsByTag("a").tagName("p").get(0);
            String nombre = "";
            nombre = link.getElementsByTag("a").tagName("p").text();

            String serie = link.getElementsByClass("Title").text();

            Episodio item = new Episodio();
            item.image = image;
            item.nombre = nombre;
            item.serie = serie;
            item.urls = new ArrayList(); // Solo si no lo  tuvimos nunca
            if (url == null){
                item.urls.add("Faloooo");
            }
            item.urls.add(url);
            items.add(item);
        }

        return items;
    }
}
