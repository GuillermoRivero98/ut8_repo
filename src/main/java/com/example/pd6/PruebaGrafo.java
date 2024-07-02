package com.example.pd6;
import java.util.Collection;
import java.util.LinkedList;

public class PruebaGrafo {

    public static void main(String[] args) {
        TGrafoNoDirigido gnd = (TGrafoNoDirigido) UtilGrafos.cargarGrafo(
                "src/vert2.txt",
                "src/ari2.txt",
                false, TGrafoNoDirigido.class);

      
    
 
      TGrafoNoDirigido grafoPrim = gnd.Prim();
       System.out.println("cantidad de vertices del grafo por prim:"  + grafoPrim.getVertices().size());
        /*
        mostrar las aristas del AAM por Prim y el costo total
       */
     
        
  
         TGrafoNoDirigido grafoKruskal = gnd.Kruskal();
       System.out.println("cantidad de vertices del grafo por kruskal:"  + grafoKruskal.getVertices().size());
        /*
        mostrar las aristas del AAM por Kruskal y el costo total
       */
       
       
    }
}
