package com.example.pd6;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DisjointSet {

    private Map<Comparable, Comparable> padre; // Mapa para almacenar el padre de cada nodo
    private Map<Comparable, Integer> rango; // Mapa para almacenar el rango de cada conjunto

    public DisjointSet(Collection<TVertice> vertices) {
        padre = new HashMap<>();
        rango = new HashMap<>();
        
        for (TVertice vertice : vertices) {
            Comparable etiqueta = vertice.getEtiqueta();
            padre.put(etiqueta, etiqueta); // Cada vértice es su propio padre al inicio
            rango.put(etiqueta, 0); // Inicialmente, el rango es 0
        }
    }

    // Método para encontrar el representante (padre) del conjunto de un elemento
    private Comparable encontrar(Comparable elemento) {
        if (!padre.containsKey(elemento)) {
            padre.put(elemento, elemento); // Si no está en el mapa, es su propio padre
            rango.put(elemento, 0); // Inicialmente, el rango es 0
        }

        // Si no es su propio padre, buscar recursivamente al padre y comprimir el camino
        if (!padre.get(elemento).equals(elemento)) {
            padre.put(elemento, encontrar(padre.get(elemento))); // Compresión de camino
        }
        return padre.get(elemento);
    }

    // Método para unir dos conjuntos en los que se encuentran los elementos 'origen' y 'destino'
    public void union(Comparable origen, Comparable destino) {
        Comparable raizOrigen = encontrar(origen);
        Comparable raizDestino = encontrar(destino);

        // Si están en el mismo conjunto, no hacer nada
        if (raizOrigen.equals(raizDestino)) {
            return;
        }

        // Unir por rango (técnica de unión por rango)
        if (rango.get(raizOrigen) < rango.get(raizDestino)) {
            padre.put(raizOrigen, raizDestino);
        } else if (rango.get(raizOrigen) > rango.get(raizDestino)) {
            padre.put(raizDestino, raizOrigen);
        } else {
            padre.put(raizDestino, raizOrigen);
            rango.put(raizOrigen, rango.get(raizOrigen) + 1);
        }
    }

    // Método para verificar si dos elementos están en el mismo conjunto
    public boolean estanConectados(Comparable origen, Comparable destino) {
        return encontrar(origen).equals(encontrar(destino));
    }
}
