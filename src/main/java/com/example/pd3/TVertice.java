package com.example.pd3;

import java.util.LinkedList;

public class TVertice implements IVertice {

    private String etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private Object datos;
    private TVertice predecesor;

    public String getEtiqueta() {
        return etiqueta;
    }

    public LinkedList<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    public Object getDatos() {
        return datos;
    }

    /**
     * @return the predecesor
     */
    public TVertice getPredecesor() {
        return predecesor;
    }

    /**
     * @param predecesor the predecesor to set
     */
    public void setPredecesor(TVertice predecesor) {
        this.predecesor = predecesor;
    }

    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = (String) unaEtiqueta;
        adyacentes = new LinkedList<>();
        visitado = false;
    }

    public void setVisitado(boolean valor) {
        this.visitado = valor;
    }

    public boolean getVisitado() {
        return this.visitado;
    }

    @Override
    public TAdyacencia buscarAdyacencia(TVertice verticeDestino) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarAdyacencia'");
    }

    @Override
    public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarAdyacencia'");
    }

    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarAdyacencia'");
    }

    @Override
    public boolean insertarAdyacencia(Double costo, TVertice verticeDestino) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertarAdyacencia'");
    }

    @Override
    public Double obtenerCostoAdyacencia(TVertice verticeDestino) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerCostoAdyacencia'");
    }

    @Override
    public TVertice primerAdyacente() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'primerAdyacente'");
    }

    @Override
    public TVertice siguienteAdyacente(TVertice w) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'siguienteAdyacente'");
    }

    // Métodos adicionales omitidos para brevedad
}
