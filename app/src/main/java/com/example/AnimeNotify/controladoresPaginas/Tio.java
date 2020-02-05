package com.example.AnimeNotify.controladoresPaginas;

import com.example.AnimeNotify.model.Episodio;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public class Tio extends PaginaEpisodios {

    public Tio(){
        url = "https://tioanime.com/";
    }

    @Override
    public List<Episodio> getFromDocument(Document doc) {

        List<Episodio> items = new ArrayList<>();

        Elements episodios = doc.getElementsByClass("col-6 col-sm-4 col-md-3");
        for (Element link : episodios) {
            String url = link.attr("href");
            String image = link.getElementsByTag("img").attr("src");
            Elements nombres = link.getElementsByClass("title");
            String nombre = "";
            if (nombres !=null && nombres.size() > 0){
                nombre = nombres.get(0).text();
            }
            String serie = link.getElementsByClass("title").text();

            Episodio item = new Episodio();
            item.image = image;
            item.nombre = null;
            item.serie = serie;
            item.urls = new ArrayList(); // Solo si no lo  tuvimos nunca
            item.urls.add(url);
            items.add(item);
        }

        return items;
    }
}
