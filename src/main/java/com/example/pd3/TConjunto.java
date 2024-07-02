package com.example.pd3;

import java.util.HashMap;
import java.util.Map;

public class TConjunto<T> {

    private Map<T, T> padre;
    private Map<T, Integer> rango;

    public TConjunto() {
        padre = new HashMap<>();
        rango = new HashMap<>();
    }

    public void makeSet(T x) {
        padre.put(x, x);
        rango.put(x, 0);
    }

    public T find(T x) {
        if (padre.get(x) != x) {
            padre.put(x, find(padre.get(x)));
        }
        return padre.get(x);
    }

    public void union(T x, T y) {
        T raizX = find(x);
        T raizY = find(y);

        if (!raizX.equals(raizY)) {
            if (rango.get(raizX) > rango.get(raizY)) {
                padre.put(raizY, raizX);
            } else {
                padre.put(raizX, raizY);
                if (rango.get(raizX).equals(rango.get(raizY))) {
                    rango.put(raizY, rango.get(raizY) + 1);
                }
            }
        }
    }
}
