package com.example.comienzo.controladoresPaginas;

import com.example.comienzo.model.Episodio;

import org.jsoup.nodes.Document;

import java.util.List;



public abstract class PaginaEpisodios {

    public String url;
    public abstract List<Episodio> getFromDocument(Document doc);
}
