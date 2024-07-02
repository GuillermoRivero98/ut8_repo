package com.example.pd1;

import java.util.Collection;
import java.util.LinkedList;


public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoNoDirigido {

    protected TAristas lasAristas = new TAristas();

    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
        lasAristas.insertarAmbosSentidos(aristas);
    }

    @Override
    public boolean insertarArista(TArista arista) {
        TArista aristaInversa = new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto());
        return super.insertarArista(arista) && super.insertarArista(aristaInversa);
    }

    @Override
    public TGrafoNoDirigido Prim() {
        // Implementación del algoritmo de Prim para obtener el Árbol de Abarcado Mínimo
        // Debes crear y retornar un nuevo objeto TGrafoNoDirigido que contenga el AAM
        // Puedes implementar esto usando PriorityQueue y HashSet para mantener seguimiento de los vértices visitados
        throw new UnsupportedOperationException("Método Prim no implementado.");
    }

    @Override
    public TGrafoNoDirigido Kruskal() {
        // Implementación del algoritmo de Kruskal para obtener el Árbol de Abarcado Mínimo
        // Debes crear y retornar un nuevo objeto TGrafoNoDirigido que contenga el AAM
        // Puedes implementar esto usando Union-Find (Disjoint Set) para detectar ciclos
        throw new UnsupportedOperationException("Método Kruskal no implementado.");
    }

    @Override
    public Collection<TVertice> bea(Comparable etiquetaOrigen) {
        // Implementación de Búsqueda en Amplitud (BFS) desde el vértice con la etiquetaOrigen
        // Debes retornar una colección de vértices alcanzados por BFS
        throw new UnsupportedOperationException("Método bea no implementado.");
    }

    @Override
    public LinkedList<TVertice> puntosArticulacion(Comparable etOrigen) {
        // Implementación del algoritmo para encontrar los puntos de articulación en el grafo
        throw new UnsupportedOperationException("Método puntosArticulacion no implementado.");
    }

    @Override
    public boolean esConexo() {
        throw new UnsupportedOperationException("Método esConexo no implementado.");
    }
}
