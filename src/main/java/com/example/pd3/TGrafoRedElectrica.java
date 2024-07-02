package com.example.pd3;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class TGrafoRedElectrica extends TGrafoDirigido implements IGrafoRedElectrica {

    public TGrafoRedElectrica(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
    }

    @Override
    public TAristas mejorRedElectrica() {
        TAristas mst = new TAristas();

        // Obtener todas las aristas del grafo
        PriorityQueue<TArista> pq = new PriorityQueue<>(Comparator.comparingDouble(TArista::getCosto));
        for (TVertice vertice : getVertices().values()) {
            for (TAdyacencia ady : vertice.getAdyacentes()) {
                TVertice destino = ady.getDestino();
                TArista arista = new TArista(vertice.getEtiqueta(), destino.getEtiqueta(), ady.getCosto());
                pq.offer(arista);
            }
        }

        // Estructura para Union-Find
        TConjunto<Comparable> conjunto = new TConjunto<>();

        // Inicializar conjuntos disjuntos
        for (Comparable vertice : getVertices().keySet()) {
            conjunto.makeSet(vertice);
        }

        // Aplicar algoritmo de Kruskal
        while (!pq.isEmpty()) {
            TArista arista = pq.poll();
            Comparable etiquetaU = arista.getEtiquetaOrigen();
            Comparable etiquetaV = arista.getEtiquetaDestino();

            if (!conjunto.find(etiquetaU).equals(conjunto.find(etiquetaV))) {
                mst.insertarArista(arista);
                conjunto.union(etiquetaU, etiquetaV);
            }
        }

        return mst;
    }

    public static void main(String[] args) {
        // Lectura de archivos de entrada
        try {
            // Lectura de "barrio.txt"
            List<TVertice> vertices = new ArrayList<>();
            BufferedReader brVertices = new BufferedReader(new FileReader("barrio.txt"));
            String linea;
            while ((linea = brVertices.readLine()) != null) {
                TVertice vertice = new TVertice(linea.trim());
                vertices.add(vertice);
            }
            brVertices.close();

            // Lectura de "distancias.txt"
            List<TArista> aristas = new ArrayList<>();
            BufferedReader brAristas = new BufferedReader(new FileReader("distancias.txt"));
            while ((linea = brAristas.readLine()) != null) {
                String[] partes = linea.trim().split(" ");
                if (partes.length == 3) {
                    Comparable etiquetaOrigen = partes[0];
                    Comparable etiquetaDestino = partes[1];
                    double costo = Double.parseDouble(partes[2]);
                    TArista arista = new TArista(etiquetaOrigen, etiquetaDestino, costo);
                    aristas.add(arista);
                }
            }
            brAristas.close();

            // Creación del grafo y cálculo del MST
            TGrafoRedElectrica grafo = new TGrafoRedElectrica(vertices, aristas);
            TAristas mst = grafo.mejorRedElectrica();

            // Escritura del archivo de salida "redelectrica.txt"
            PrintWriter pw = new PrintWriter(new FileWriter("redelectrica.txt"));
            pw.println("Algoritmo utilizado: Kruskal");
            double costoTotal = 0;
            for (TArista arista : mst.getAristas()) {
                pw.println(arista.getEtiquetaOrigen() + " " + arista.getEtiquetaDestino() + " " + arista.getCosto());
                costoTotal += arista.getCosto();
            }
            pw.println("Cantidad total del cableado: " + costoTotal);
            pw.close();

            System.out.println("Archivo 'redelectrica.txt' generado correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
