package com.example.pd6;

import java.util.Collection;

public interface IGrafoNoDirigido extends IGrafo {

    Collection<TVertice> bea();

    Collection<TVertice> bea(Comparable etiquetaOrigen);

    TGrafoNoDirigido Prim();

    TGrafoNoDirigido Kruskal();

    TAristas getLasAristas();
}
