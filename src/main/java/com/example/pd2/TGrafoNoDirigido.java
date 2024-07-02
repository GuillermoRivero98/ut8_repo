package com.example.pd2;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;

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
        Set<Comparable> visitados = new HashSet<>();
        PriorityQueue<TArista> aristas = new PriorityQueue<>();
        LinkedList<TArista> aristasMST = new LinkedList<>();

        Comparable etiquetaInicial = this.getVertices().keySet().iterator().next();
        visitados.add(etiquetaInicial);

        for (TAdyacencia adyacencia : this.getVertices().get(etiquetaInicial).getAdyacentes()) {
            aristas.add(new TArista(etiquetaInicial, adyacencia.getDestino().getEtiqueta(), adyacencia.getCosto()));
        }

        while (!aristas.isEmpty() && visitados.size() < this.getVertices().size()) {
            TArista arista = aristas.poll();
            if (visitados.contains(arista.getEtiquetaDestino())) {
                continue;
            }
            visitados.add(arista.getEtiquetaDestino());
            aristasMST.add(arista);

            for (TAdyacencia adyacencia : this.getVertices().get(arista.getEtiquetaDestino()).getAdyacentes()) {
                if (!visitados.contains(adyacencia.getDestino().getEtiqueta())) {
                    aristas.add(new TArista(arista.getEtiquetaDestino(), adyacencia.getDestino().getEtiqueta(), adyacencia.getCosto()));
                }
            }
        }

        return new TGrafoNoDirigido(this.getVertices().values(), aristasMST);
    }

    @Override
    public TGrafoNoDirigido Kruskal() {
        UnionFind unionFind = new UnionFind(this.getVertices().size());
        PriorityQueue<TArista> aristas = new PriorityQueue<>(lasAristas);
        LinkedList<TArista> aristasMST = new LinkedList<>();

        while (!aristas.isEmpty() && aristasMST.size() < this.getVertices().size() - 1) {
            TArista arista = aristas.poll();
            int origen = unionFind.find((int) arista.getEtiquetaOrigen());
            int destino = unionFind.find((int) arista.getEtiquetaDestino());

            if (origen != destino) {
                aristasMST.add(arista);
                unionFind.union(origen, destino);
            }
        }

        return new TGrafoNoDirigido(this.getVertices().values(), aristasMST);
    }

    @Override
    public Collection<TVertice> bea(Comparable etiquetaOrigen) {
        LinkedList<TVertice> visitados = new LinkedList<>();
        LinkedList<TVertice> cola = new LinkedList<>();
        TVertice origen = this.getVertices().get(etiquetaOrigen);

        if (origen != null) {
            cola.add(origen);
            origen.setVisitado(true);
            while (!cola.isEmpty()) {
                TVertice actual = cola.poll();
                visitados.add(actual);
                for (TAdyacencia adyacencia : actual.getAdyacentes()) {
                    TVertice adyacente = adyacencia.getDestino();
                    if (!adyacente.getVisitado()) {
                        adyacente.setVisitado(true);
                        cola.add(adyacente);
                    }
                }
            }
        }
        desvisitarVertices();
        return visitados;
    }

    @Override
    public LinkedList<TVertice> puntosArticulacion(Comparable etOrigen) {
        LinkedList<TVertice> puntosArticulacion = new LinkedList<>();
        Set<TVertice> visitados = new HashSet<>();
        TVertice origen = this.getVertices().get(etOrigen);
        if (origen != null) {
            puntosArticulacionDFS(origen, null, visitados, puntosArticulacion);
        }
        return puntosArticulacion;
    }

    private void puntosArticulacionDFS(TVertice vertice, TVertice padre, Set<TVertice> visitados, LinkedList<TVertice> puntosArticulacion) {
        visitados.add(vertice);
        vertice.setVisitado(true);
        int hijos = 0;
        boolean esPuntoArticulacion = false;

        for (TAdyacencia adyacencia : vertice.getAdyacentes()) {
            TVertice adyacente = adyacencia.getDestino();
            if (!adyacente.getVisitado()) {
                hijos++;
                puntosArticulacionDFS(adyacente, vertice, visitados, puntosArticulacion);
                if (adyacente.getDatos() >= vertice.getDatos()) {
                    esPuntoArticulacion = true;
                }
            } else if (adyacente != padre) {
                vertice.setDatos(Math.min(vertice.getDatos(), adyacente.getDatos()));
            }
        }

        if ((padre == null && hijos > 1) || (padre != null && esPuntoArticulacion)) {
            puntosArticulacion.add(vertice);
        }
    }

    @Override
    public boolean esConexo() {
        Collection<TVertice> vertices = this.bea(this.getVertices().keySet().iterator().next());
        return vertices.size() == this.getVertices().size();
    }

    private class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int i) {
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        public void union(int i, int j) {
            int ri = find(i);
            int rj = find(j);
            if (ri != rj) {
                if (rank[ri] < rank[rj]) {
                    parent[ri] = rj;
                } else if (rank[ri] > rank[rj]) {
                    parent[rj] = ri;
                } else {
                    parent[rj] = ri;
                    rank[ri]++;
                }
            }
        }
    }
}
