package com.example.comienzo.controladoresPaginas;

import com.example.comienzo.model.Episodio;

import java.util.List;



public interface PaginaListener {
    void devolver(List<Episodio> items);
}
