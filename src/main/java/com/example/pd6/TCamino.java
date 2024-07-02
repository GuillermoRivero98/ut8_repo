package com.example.pd6;

import java.util.Collection;
import java.util.LinkedList;

public class TCamino {

    private final TVertice origen;
    private final Collection<Comparable> otrosVertices;
    private Double costoTotal;

    public TCamino(TVertice v) {
        this.costoTotal = 0.0d;
        this.origen = v;
        this.otrosVertices = new LinkedList<>();
    }

    public boolean agregarAdyacencia(TAdyacencia adyacenciaActual) {
        if (adyacenciaActual.getDestino() != null) {
            costoTotal += adyacenciaActual.getCosto();
            return otrosVertices.add(adyacenciaActual.getDestino().getEtiqueta());
        }
        return false;
    }

    public boolean eliminarAdyacencia(TAdyacencia adyacenciaActual) {
        if (otrosVertices.contains(adyacenciaActual.getDestino().getEtiqueta())) {
            costoTotal -= adyacenciaActual.getCosto();
            return otrosVertices.remove(adyacenciaActual.getDestino().getEtiqueta());
        }
        return false;
    }

    public TVertice getOrigen() {
        return origen;
    }

    public Collection<Comparable> getOtrosVertices() {
        return otrosVertices;
    }

    public Double getCostoTotal() {
        return costoTotal;
    }

    public void imprimirEtiquetasConsola() {
        System.out.println(imprimirEtiquetas());
    }

    public String imprimirEtiquetas() {
        StringBuilder sb = new StringBuilder();
        sb.append("Origen: ").append(origen.getEtiqueta());
        for (Comparable adyacente : otrosVertices) {
            sb.append(" -> ").append(adyacente);
        }
        return sb.toString();
    }

    public TCamino copiar() {
        TVertice origenCopia = new TVertice(this.getOrigen().getEtiqueta());
        TCamino copia = new TCamino(origenCopia);
        copia.setCostoTotal(this.getCostoTotal());
        copia.getOtrosVertices().addAll(this.getOtrosVertices());
        return copia;
    }

    private void setCostoTotal(Double costoTotal2) {
        throw new UnsupportedOperationException("Unimplemented method 'setCostoTotal'");
    }

    public String imprimirDesdeClave(Comparable clave) {
        StringBuilder sb = new StringBuilder();
        boolean start = false;
        Collection<Comparable> listaDefinitiva = new LinkedList<>();
        listaDefinitiva.add(this.getOrigen().getEtiqueta());
        listaDefinitiva.addAll(this.getOtrosVertices());

        for (Comparable next : listaDefinitiva) {
            if (next.compareTo(clave) == 0) {
                start = true;
            }
            if (start) {
                sb.append(" ").append(next).append(" ");
            }
        }
        return sb.toString();
    }
}
