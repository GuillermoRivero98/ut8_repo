package com.example.pd3;

public class TArista implements IArista {

    protected String etiquetaOrigen;
    protected String etiquetaDestino;
    protected double costo;

    public TArista(Comparable etiquetaOrigen2, Comparable etiquetaDestino2, double costo) {
        this.etiquetaOrigen = (String) etiquetaOrigen2;
        this.etiquetaDestino = (String) etiquetaDestino2;
        this.costo = costo;
    }

    // Getters y Setters
    public String getEtiquetaOrigen() {
        return etiquetaOrigen;
    }

    public void setEtiquetaOrigen(String etiquetaOrigen) {
        this.etiquetaOrigen = etiquetaOrigen;
    }

    public String getEtiquetaDestino() {
        return etiquetaDestino;
    }

    public void setEtiquetaDestino(String etiquetaDestino) {
        this.etiquetaDestino = etiquetaDestino;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public TArista aristaInversa() {
        return new TArista(this.etiquetaDestino, this.etiquetaOrigen, this.costo);
    }

    @Override
    public void setEtiquetaDestino(Comparable etiquetaDestino) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setEtiquetaDestino'");
    }

    @Override
    public void setEtiquetaOrigen(Comparable etiquetaOrigen) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setEtiquetaOrigen'");
    }
}
