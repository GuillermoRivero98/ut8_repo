package com.example.pd6;

import java.util.Collection;
import java.util.LinkedList;

public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoNoDirigido {

    private TAristas lasAristas;

    public TGrafoNoDirigido() {
        super();
        this.lasAristas = new TAristas();
    }

    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
        this.lasAristas = new TAristas();
        this.lasAristas.insertarAmbosSentidos(aristas);
    }

    @Override
    public boolean insertarArista(TArista arista) {
        TArista aristaInversa = new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto());
        return super.insertarArista(arista) && super.insertarArista(aristaInversa);
    }

    @Override
    public TGrafoNoDirigido Kruskal() {
        TGrafoNoDirigido arbolAbarcadorMinimo = new TGrafoNoDirigido(new LinkedList<>(this.vertices.values()), new LinkedList<>(this.lasAristas));
        arbolAbarcadorMinimo.getLasAristas().ordenarPorCosto();

        DisjointSet disjointSet = new DisjointSet(vertices.values());

        for (TArista arista : arbolAbarcadorMinimo.getLasAristas()) {
            Comparable origen = arista.getEtiquetaOrigen();
            Comparable destino = arista.getEtiquetaDestino();

            if (!disjointSet.estanConectados(origen, destino)) {
                arbolAbarcadorMinimo.insertarArista(arista);
                disjointSet.union(origen, destino);
            }
        }

        return arbolAbarcadorMinimo;
    }

    @Override
    public Collection<TVertice> bea() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<TVertice> bea(Comparable etiquetaOrigen) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TAristas getLasAristas() {
        return lasAristas;
    }

    @Override
    public TGrafoNoDirigido Prim() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Prim'");
    }
}
