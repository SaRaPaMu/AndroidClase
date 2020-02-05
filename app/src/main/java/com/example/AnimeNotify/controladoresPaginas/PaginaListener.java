package com.example.AnimeNotify.controladoresPaginas;

import com.example.AnimeNotify.model.Episodio;

import java.util.List;



public interface PaginaListener {
    void devolver(List<Episodio> items);
}
