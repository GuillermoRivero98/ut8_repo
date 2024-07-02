package com.example.pd6;

public class TAdyacencia implements IAdyacencia, Comparable<TAdyacencia> {

    private Comparable etiqueta;
    private double costo;
    private TVertice destino;

    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    @Override
    public double getCosto() {
        return costo;
    }

    @Override
    public TVertice getDestino() {
        return destino;
    }

    public TAdyacencia(double costo, TVertice destino) {
        this.etiqueta = destino.getEtiqueta();
        this.costo = costo;
        this.destino = destino;
    }

    @Override
    public int compareTo(TAdyacencia otra) {
        // Comparar por el costo de la arista
        return Double.compare(this.costo, otra.costo);
    }

    public TVertice getOrigen() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrigen'");
    }
}
