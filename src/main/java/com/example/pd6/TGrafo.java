package com.example.pd6;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class TGrafo implements IGrafo {

    protected Map<Comparable, TVertice> vertices;

    public TGrafo() {
        vertices = new HashMap<>();
    }

    @Override
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

    @Override
    public void desvisitarVertices() {
        for (TVertice vertice : vertices.values()) {
            vertice.setVisitado(false);
        }
    }

    @Override
    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice v = vertices.get(etiquetaOrigen);
        if (v == null) {
            return null;
        }
        desvisitarVertices();
        return v.todosLosCaminos(etiquetaDestino, null, new TCaminos());
    }

    @Override
    public boolean tieneCiclo(TCamino camino) {
        desvisitarVertices();
        for (TVertice vertice : vertices.values()) {
            if (!vertice.getVisitado()) {
                if (tieneCiclo(new TCamino(vertice))) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean tieneCiclo(Comparable etiquetaOrigen) {
        desvisitarVertices();
        TVertice v = vertices.get(etiquetaOrigen);
        if (v == null) {
            return false;
        }
        return v.tieneCiclo(new LinkedList<>());
    }

    @Override
    public boolean tieneCiclo() {
        desvisitarVertices();
        for (TVertice vertice : vertices.values()) {
            if (!vertice.getVisitado()) {
                if (tieneCiclo(new TCamino(vertice))) {
                    return true;
                }
            }
        }
        return false;
    }
}

