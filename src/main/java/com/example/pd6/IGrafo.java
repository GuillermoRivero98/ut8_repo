package com.example.pd6;

import java.util.Map;

public interface IGrafo {

    Map<Comparable, TVertice> getVertices();

    void desvisitarVertices();

    TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino);

    boolean tieneCiclo(TCamino camino);

    boolean tieneCiclo(Comparable etiquetaOrigen);

    boolean tieneCiclo();
}

