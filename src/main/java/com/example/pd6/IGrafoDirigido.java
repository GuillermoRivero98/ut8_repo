package com.example.pd6;

import java.util.Collection;
import java.util.Map;

public interface IGrafoDirigido extends IGrafo {

    Collection<TVertice> bpf();

    Collection<TVertice> bea();

    Collection<TVertice> bpf(TVertice vertice);

    Collection<TVertice> bpf(Comparable etiquetaOrigen);

    Comparable centroDelGrafo();

    boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino);

    boolean eliminarVertice(Comparable nombreVertice);

    boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino);

    boolean existeVertice(Comparable unaEtiqueta);

    Double[][] floyd();

    boolean insertarArista(TArista arista);

    boolean insertarVertice(TVertice vertice);

    Comparable obtenerExcentricidad(Comparable etiquetaVertice);

    boolean[][] warshall();
}
