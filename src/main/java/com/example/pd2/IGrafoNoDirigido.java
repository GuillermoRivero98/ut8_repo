package com.example.pd2;

import java.util.Collection;
import java.util.LinkedList;

public interface IGrafoNoDirigido {

    public Collection<TVertice> bea(Comparable etiquetaOrigen);
    
    public TGrafoNoDirigido Prim();

    public TGrafoNoDirigido Kruskal();
    
    public LinkedList<TVertice> puntosArticulacion(Comparable etOrigen);
    
    boolean esConexo();
}
