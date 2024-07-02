package com.example.pd2;

import java.util.Collection;
import java.util.LinkedList;

public class TVertice implements IVertice {

    private Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;

    public TVertice(Comparable etiqueta) {
        this.etiqueta = etiqueta;
        this.adyacentes = new LinkedList<>();
        this.visitado = false;
    }

    @Override
    public TAdyacencia buscarAdyacencia(TVertice verticeDestino) {
        for (TAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().equals(verticeDestino)) {
                return adyacencia;
            }
        }
        return null;
    }

    @Override
    public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        for (TAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().getEtiqueta().equals(etiquetaDestino)) {
                return adyacencia;
            }
        }
        return null;
    }

    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        TAdyacencia adyacencia = buscarAdyacencia(nomVerticeDestino);
        if (adyacencia != null) {
            adyacentes.remove(adyacencia);
            return true;
        }
        return false;
    }

    @Override
    public boolean insertarAdyacencia(Double costo, TVertice verticeDestino) {
        if (buscarAdyacencia(verticeDestino) == null) {
            TAdyacencia adyacencia = new TAdyacencia(costo, verticeDestino);
            adyacentes.add(adyacencia);
            return true;
        }
        return false;
    }

    @Override
    public Double obtenerCostoAdyacencia(TVertice verticeDestino) {
        TAdyacencia adyacencia = buscarAdyacencia(verticeDestino);
        if (adyacencia != null) {
            return adyacencia.getCosto();
        }
        return Double.MAX_VALUE;
    }

    @Override
    public TVertice primerAdyacente() {
        if (!adyacentes.isEmpty()) {
            return adyacentes.getFirst().getDestino();
        }
        return null;
    }

    @Override
    public TVertice siguienteAdyacente(TVertice w) {
        boolean returnNext = false;
        for (TAdyacencia adyacencia : adyacentes) {
            if (returnNext) {
                return adyacencia.getDestino();
            }
            if (adyacencia.getDestino().equals(w)) {
                returnNext = true;
            }
        }
        return null;
    }

    @Override
    public void bpf(Collection<TVertice> visitados) {
        if (!visitado) {
            visitado = true;
            visitados.add(this);
            for (TAdyacencia adyacencia : adyacentes) {
                TVertice verticeDestino = adyacencia.getDestino();
                verticeDestino.bpf(visitados);
            }
        }
    }

    @Override
    public boolean conectadoCon(TVertice destino) {
        this.desvisitarVertices();
        LinkedList<TVertice> visitados = new LinkedList<>();
        this.bpf(visitados);
        return visitados.contains(destino);
    }

    @Override
    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos) {
        this.setVisitado(true);
        for (TAdyacencia adyacencia : adyacentes) {
            TVertice destino = adyacencia.getDestino();
            if (!destino.getVisitado()) {
                TCamino copiaCamino = caminoPrevio.copiar();
                copiaCamino.agregarAdyacencia(adyacencia);
                if (destino.getEtiqueta().equals(etVertDest)) {
                    todosLosCaminos.getCaminos().add(copiaCamino);
                } else {
                    destino.todosLosCaminos(etVertDest, copiaCamino, todosLosCaminos);
                }
            }
        }
        this.setVisitado(false);
        return todosLosCaminos;
    }

    @Override
    public boolean tieneCiclo(LinkedList<Comparable> camino) {
        setVisitado(true);
        for (TAdyacencia adyacencia : adyacentes) {
            TVertice destino = adyacencia.getDestino();
            if (!destino.getVisitado()) {
                camino.add(destino.getEtiqueta());
                if (destino.tieneCiclo(camino)) {
                    return true;
                }
                camino.removeLast();
            } else {
                return true;
            }
        }
        setVisitado(false);
        return false;
    }

    @Override
    public void bea(Collection<TVertice> visitados) {
        LinkedList<TVertice> cola = new LinkedList<>();
        this.setVisitado(true);
        visitados.add(this);
        cola.add(this);
        while (!cola.isEmpty()) {
            TVertice actual = cola.removeFirst();
            for (TAdyacencia ady : actual.getAdyacentes()) {
                TVertice destino = ady.getDestino();
                if (!destino.getVisitado()) {
                    destino.setVisitado(true);
                    visitados.add(destino);
                    cola.add(destino);
                }
            }
        }
    }

    public Collection<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    public Comparable getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(Comparable etiqueta) {
        this.etiqueta = etiqueta;
    }

    public boolean getVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public void desvisitarVertices() {
        this.visitado = false;
        for (TAdyacencia adyacencia : adyacentes) {
            adyacencia.getDestino().setVisitado(false);
        }
    }

    public int getDatos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDatos'");
    }

    public void setDatos(int min) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDatos'");
    }
}
