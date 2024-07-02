package com.example.pd6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AlgoritmoKruskal {

    private List<TAdyacencia> aristas;

    public AlgoritmoKruskal() {
        aristas = new ArrayList<>();
    }

    public void agregarArista(TAdyacencia arista) {
        aristas.add(arista);
    }

    public TCaminos ejecutarKruskal(Collection<TVertice> vertices) {
        TCaminos arbolAbarcadorMinimo = new TCaminos();

        // Ordenar las aristas por peso (costo)
        Collections.sort(aristas);

        // Inicializar la estructura para conjuntos disjuntos
        DisjointSet disjointSet = new DisjointSet(vertices);

        for (TAdyacencia arista : aristas) {
            TVertice u = arista.getOrigen();
            TVertice v = arista.getDestino();

            // Verificar si u y v están en diferentes componentes conexas
            if (!disjointSet.estanConectados(u.getEtiqueta(), v.getEtiqueta())) {
                // Agregar arista al árbol abarcador mínimo
                arbolAbarcadorMinimo.agregarAdyacencia(arista);

                // Unir los conjuntos de u y v en la estructura Disjoint Set
                disjointSet.union(u.getEtiqueta(), v.getEtiqueta());
            }
        }

        return arbolAbarcadorMinimo;
    }
}
