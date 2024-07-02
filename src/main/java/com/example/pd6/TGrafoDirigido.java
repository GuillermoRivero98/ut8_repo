package com.example.pd6;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class TGrafoDirigido extends TGrafo implements IGrafoDirigido {

    public TGrafoDirigido() {
        super();
    }

    public TGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        this();
        for (TVertice vertice : vertices) {
            insertarVertice(vertice);
        }
        for (TArista arista : aristas) {
            insertarArista(arista);
        }
    }

    @Override
    public Collection<TVertice> bpf() {
        desvisitarVertices();
        Collection<TVertice> visitados = new LinkedList<>();
        for (TVertice vertice : vertices.values()) {
            if (!vertice.getVisitado()) {
                vertice.bpf(visitados);
            }
        }
        return visitados;
    }

    @Override
    public Collection<TVertice> bea() {
        desvisitarVertices();
        Collection<TVertice> visitados = new LinkedList<>();
        for (TVertice vertice : vertices.values()) {
            if (!vertice.getVisitado()) {
                vertice.bea(visitados);
            }
        }
        return visitados;
    }

    @Override
    public Collection<TVertice> bpf(TVertice vertice) {
        desvisitarVertices();
        Collection<TVertice> visitados = new LinkedList<>();
        vertice.bpf(visitados);
        return visitados;
    }

    @Override
    public Collection<TVertice> bpf(Comparable etiquetaOrigen) {
        desvisitarVertices();
        TVertice v = vertices.get(etiquetaOrigen);
        if (v == null) {
            return null;
        }
        return bpf(v);
    }

    @Override
    public Comparable centroDelGrafo() {
        desvisitarVertices();
        Map<Comparable, Double> excentricidades = new HashMap<>();
        for (TVertice v : vertices.values()) {
            Double excentricidad = (Double) obtenerExcentricidad(v.getEtiqueta());
            excentricidades.put(v.getEtiqueta(), excentricidad);
        }
        return Collections.min(excentricidades.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    @Override
    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        TVertice origen = vertices.get(nomVerticeOrigen);
        if (origen == null) {
            return false;
        }
        return origen.eliminarAdyacencia(nomVerticeDestino);
    }

    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
        TVertice vertice = vertices.remove(nombreVertice);
        if (vertice == null) {
            return false;
        }
        for (TVertice v : vertices.values()) {
            v.eliminarAdyacencia(nombreVertice);
        }
        return true;
    }

    @Override
    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice origen = vertices.get(etiquetaOrigen);
        if (origen == null) {
            return false;
        }
        return origen.buscarAdyacencia(etiquetaDestino) != null;
    }

    @Override
    public boolean existeVertice(Comparable unaEtiqueta) {
        return vertices.containsKey(unaEtiqueta);
    }

    @Override
    public Double[][] floyd() {
        int n = vertices.size();
        Double[][] matrizCostos = new Double[n][n];
        Map<Comparable, Integer> indices = new HashMap<>();
        int idx = 0;
        for (TVertice v : vertices.values()) {
            indices.put(v.getEtiqueta(), idx);
            idx++;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    matrizCostos[i][j] = 0.0;
                } else {
                    matrizCostos[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
        for (TVertice v : vertices.values()) {
            int i = indices.get(v.getEtiqueta());
            for (TAdyacencia ady : v.getAdyacentes()) {
                int j = indices.get(ady.getDestino().getEtiqueta());
                matrizCostos[i][j] = ady.getCosto();
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrizCostos[i][j] > matrizCostos[i][k] + matrizCostos[k][j]) {
                        matrizCostos[i][j] = matrizCostos[i][k] + matrizCostos[k][j];
                    }
                }
            }
        }
        return matrizCostos;
    }

    @Override
    public boolean insertarArista(TArista arista) {
        TVertice origen = vertices.get(arista.getEtiquetaOrigen());
        if (origen == null) {
            return false;
        }
        TVertice destino = vertices.get(arista.getEtiquetaDestino());
        if (destino == null) {
            return false;
        }
        return origen.insertarAdyacencia(arista.getCosto(), destino);
    }

    @Override
    public boolean insertarVertice(TVertice vertice) {
        if (vertices.containsKey(vertice.getEtiqueta())) {
            return false;
        }
        vertices.put(vertice.getEtiqueta(), vertice);
        return true;
    }

    @Override
    public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {
        desvisitarVertices();
        TVertice v = vertices.get(etiquetaVertice);
        if (v == null) {
            return Double.POSITIVE_INFINITY;
        }
        Double maxDistancia = 0.0;
        Double[][] matrizCostos = floyd();
        int indice = 0;
        for (TVertice vertice : vertices.values()) {
            Double distancia = matrizCostos[indice][vertices.size() - 1];
            if (distancia > maxDistancia) {
                maxDistancia = distancia;
            }
            indice++;
        }
        return maxDistancia;
    }

    @Override
    public boolean[][] warshall() {
        int n = vertices.size();
        boolean[][] matrizWarshall = new boolean[n][n];
        Map<Comparable, Integer> indices = new HashMap<>();
        int idx = 0;
        for (TVertice v : vertices.values()) {
            indices.put(v.getEtiqueta(), idx);
            idx++;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    matrizWarshall[i][j] = true;
                } else {
                    matrizWarshall[i][j] = false;
                }
            }
        }
        for (TVertice v : vertices.values()) {
            int i = indices.get(v.getEtiqueta());
            for (TAdyacencia ady : v.getAdyacentes()) {
                int j = indices.get(ady.getDestino().getEtiqueta());
                matrizWarshall[i][j] = true;
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrizWarshall[i][j] = matrizWarshall[i][j] || (matrizWarshall[i][k] && matrizWarshall[k][j]);
                }
            }
        }
        return matrizWarshall;
    }
}
