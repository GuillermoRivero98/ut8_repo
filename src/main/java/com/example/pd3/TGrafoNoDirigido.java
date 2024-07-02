package com.example.pd3;

import java.util.Collection;

public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoRedElectrica {

    protected TAristas lasAristas = new TAristas();

    /**
     * Constructor que inicializa el grafo no dirigido con vértices y aristas.
     *
     * @param vertices colección de vértices
     * @param aristas  colección de aristas
     */
    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
        lasAristas.insertarAmbosSentidos(aristas);
    }

    /**
     * Inserta una arista en el grafo no dirigido, asegurándose de insertar también
     * la arista inversa.
     *
     * @param arista la arista a insertar
     * @return true si la arista se inserta correctamente, false en caso contrario
     */
    @Override
    public boolean insertarArista(TArista arista) {
        boolean exito = super.insertarArista(arista); // Inserta la arista directa
        TArista aristaInversa = new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto());
        exito = exito && super.insertarArista(aristaInversa); // Inserta la arista inversa
        if (exito) {
            lasAristas.insertarArista(arista);
            lasAristas.insertarArista(aristaInversa);
        }
        return exito;
    }

    /**
     * Devuelve todas las aristas del grafo no dirigido.
     *
     * @return las aristas del grafo
     */
    public TAristas getLasAristas() {
        return lasAristas;
    }

    /**
     * Método no implementado para encontrar la mejor red eléctrica. Debe ser
     * implementado en la clase concreta que lo extienda.
     *
     * @return las aristas del MST (árbol de expansión mínima) que representa la red
     *         eléctrica óptima
     */
    @Override
    public TAristas mejorRedElectrica() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
