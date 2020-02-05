package com.example.comienzo.controladoresPaginas;

import com.example.comienzo.model.Episodio;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;



public class Flv extends PaginaEpisodios {

    public Flv(){
        url = "https://animeflv.net/";
    }

    @Override
    public List<Episodio> getFromDocument(Document doc) {

        List<Episodio> items = new ArrayList<>();

        Elements episodios = doc.getElementsByClass("fa-play");
        for (Element link : episodios) {
            String url = link.attr("href");
            String image = link.getElementsByTag("img").attr("src");
            Elements nombres = link.getElementsByClass("Capi");
            String nombre = "";
            if (nombres !=null && nombres.size() > 0){
                nombre = nombres.get(0).text();
            }
            String serie = link.getElementsByTag("strong").text();

            Episodio item = new Episodio();
            item.image = image;
            item.nombre = nombre;
            item.serie = serie;
            item.urls = new ArrayList(); // Solo si no lo  tuvimos nunca
            item.urls.add(url);
            items.add(item);
        }

        return items;
    }
}
