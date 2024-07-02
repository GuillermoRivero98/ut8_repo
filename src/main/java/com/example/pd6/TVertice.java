package com.example.pd6;

import java.util.Collection;
import java.util.LinkedList;

public class TVertice implements IVertice {

    private Comparable etiqueta;
    private Collection<TAdyacencia> adyacentes;
    private boolean visitado;
    private TVertice padre; // Nuevo atributo para Kruskal

    public TVertice(Comparable etiqueta) {
        this.etiqueta = etiqueta;
        this.adyacentes = new LinkedList<>();
        this.visitado = false;
        this.padre = this; // Cada vértice comienza como su propio padre
    }

    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    @Override
    public TAdyacencia buscarAdyacencia(TVertice verticeDestino) {
        for (TAdyacencia ady : adyacentes) {
            if (ady.getDestino().equals(verticeDestino)) {
                return ady;
            }
        }
        return null;
    }

    @Override
    public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        for (TAdyacencia ady : adyacentes) {
            if (ady.getDestino().getEtiqueta().equals(etiquetaDestino)) {
                return ady;
            }
        }
        return null;
    }

    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        TAdyacencia ady = buscarAdyacencia(nomVerticeDestino);
        if (ady != null) {
            adyacentes.remove(ady);
            return true;
        }
        return false;
    }

    @Override
    public boolean insertarAdyacencia(Double costo, TVertice verticeDestino) {
        if (buscarAdyacencia(verticeDestino) == null) {
            TAdyacencia nuevaAdyacencia = new TAdyacencia(costo, verticeDestino);
            adyacentes.add(nuevaAdyacencia);
            return true;
        }
        return false;
    }

    @Override
    public Double obtenerCostoAdyacencia(TVertice verticeDestino) {
        TAdyacencia ady = buscarAdyacencia(verticeDestino);
        return (ady != null) ? ady.getCosto() : null;
    }

    @Override
    public TVertice primerAdyacente() {
        if (!adyacentes.isEmpty()) {
            return adyacentes.iterator().next().getDestino();
        }
        return null;
    }

    @Override
    public TVertice siguienteAdyacente(TVertice w) {
        for (TAdyacencia ady : adyacentes) {
            if (ady.getDestino().equals(w)) {
                return ady.getDestino();
            }
        }
        return null;
    }

    @Override
    public void bpf(Collection<TVertice> visitados) {
        visitado = true;
        visitados.add(this);
        for (TAdyacencia ady : adyacentes) {
            if (!ady.getDestino().getVisitado()) {
                ady.getDestino().bpf(visitados);
            }
        }
    }

    @Override
    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos) {
        visitado = true;
        if (caminoPrevio == null) {
            caminoPrevio = new TCamino(this);
        } else {
            caminoPrevio = caminoPrevio.copiar();
            caminoPrevio.agregarAdyacencia(buscarAdyacencia((TVertice) etVertDest));
        }
        if (this.getEtiqueta().compareTo(etVertDest) == 0) {
            todosLosCaminos.getCaminos().add(caminoPrevio);
        } else {
            for (TAdyacencia ady : adyacentes) {
                if (!ady.getDestino().getVisitado()) {
                    ady.getDestino().todosLosCaminos(etVertDest, caminoPrevio, todosLosCaminos);
                }
            }
        }
        return todosLosCaminos;
    }

    @Override
    public boolean tieneCiclo(LinkedList<Comparable> camino) {
        visitado = true;
        camino.add(this.etiqueta);
        for (TAdyacencia ady : adyacentes) {
            if (!ady.getDestino().getVisitado()) {
                if (ady.getDestino().tieneCiclo(camino)) {
                    return true;
                }
            } else {
                if (camino.contains(ady.getDestino().getEtiqueta())) {
                    camino.add(ady.getDestino().getEtiqueta());
                    return true;
                }
            }
        }
        camino.remove(this.etiqueta);
        return false;
    }

    @Override
    public void bea(Collection<TVertice> visitados) {
        LinkedList<TVertice> pendientes = new LinkedList<>();
        visitado = true;
        visitados.add(this);
        pendientes.add(this);
        while (!pendientes.isEmpty()) {
            TVertice vertice = pendientes.poll();
            for (TAdyacencia ady : vertice.getAdyacentes()) {
                TVertice destino = ady.getDestino();
                if (!destino.getVisitado()) {
                    destino.setVisitado(true);
                    visitados.add(destino);
                    pendientes.add(destino);
                }
            }
        }
    }

    @Override
    public boolean mismoComponenteConexo(TVertice otroVertice) {
        return encontrarPadre() == otroVertice.encontrarPadre();
    }

    private TVertice encontrarPadre() {
        if (padre != this) {
            padre = padre.encontrarPadre(); // Compresión de camino
        }
        return padre;
    }

    public Collection<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    public boolean getVisitado() {
        return visitado;
    }

    public void setVisitado(boolean valor) {
        visitado = valor;
    }

    public void setPadre(TVertice padre) {
        this.padre = padre;
    }
}
