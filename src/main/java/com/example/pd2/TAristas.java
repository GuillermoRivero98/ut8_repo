package com.example.pd2;

import java.util.Collection;
import java.util.LinkedList;

public class TAristas extends LinkedList<TArista> {

    private final static String SEPARADOR_ELEMENTOS_IMPRESOS = "-";

    /**
     * Busca dentro de la lista de aristas una arista que conecte a etOrigen con
     * etDestino
     *
     * @param etOrigen
     * @param etDestino
     * @return
     */
    public TArista buscar(Comparable etOrigen, Comparable etDestino) {
        for (TArista arista : this) {
            if (arista.getEtiquetaOrigen().equals(etOrigen) && arista.getEtiquetaDestino().equals(etDestino)) {
                return arista;
            }
        }
        return null;
    }

    /**
     * Busca la arista de menor costo que conecte a cualquier nodo de VerticesU
     * con cualquier otro de VerticesV y cuyo costo sea el minimo
     *
     * @param VerticesU - Lista de vertices U
     * @param VerticesV - Lista de vertices V
     * @return
     */
    public TArista buscarMin(Collection<Comparable> VerticesU, Collection<Comparable> VerticesV) {
        TArista aristaMinima = null;
        double costoMinimo = Double.MAX_VALUE;

        for (Comparable u : VerticesU) {
            for (Comparable v : VerticesV) {
                TArista arista = buscar(u, v);
                if (arista != null && arista.getCosto() < costoMinimo) {
                    aristaMinima = arista;
                    costoMinimo = arista.getCosto();
                }
            }
        }

        return aristaMinima;
    }

    public String imprimirEtiquetas() {
        if (this.isEmpty()) {
            return null;
        }
        StringBuilder salida = new StringBuilder();
        for (TArista arista : this) {
            salida.append(arista.getEtiquetaOrigen())
                  .append(SEPARADOR_ELEMENTOS_IMPRESOS)
                  .append(arista.getEtiquetaDestino())
                  .append(SEPARADOR_ELEMENTOS_IMPRESOS)
                  .append(arista.getCosto())
                  .append("\n");
        }
        return salida.toString();
    }

    void insertarAmbosSentidos(Collection<TArista> aristas) {
        if (aristas == null) return;
        for (TArista ta : aristas) {
            this.add(ta);
            this.add(ta.aristaInversa());
        }
    }
}
