/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointeligentes;

import java.util.LinkedList;

/**
 *
 * @author JORGE_ALEJANDRO
 */
public class AEstrellaCM{

    private Nodo inicio;
    private Nodo fin;
    private LinkedList<Nodo> abierta;
    private LinkedList<Nodo> cerrada;

    public AEstrellaCM() {
        inicio = null;
        fin = null;
        abierta = new LinkedList<>();
        cerrada = new LinkedList<>();

    }

    public LinkedList<Nodo> ejecutar(Nodo inicio, Nodo fin, Mapa mapa) {
        this.inicio = inicio;
        this.fin = fin;
        abierta = new LinkedList<>();
        cerrada = new LinkedList<>();
        cerrada.add(inicio);
        LinkedList<Nodo> camino = ruta(mapa);
        return camino;
    }
    
    public Nodo nodoOpuesto(Nodo nodo1, Nodo nodo2, Mapa mapa) {
        Nodo nodo3 = null;
        if (nodo1.getI() - 1 >= 0 && nodo1.getI() + 1 == nodo2.getI() && nodo1.getJ() == nodo2.getJ()) { //Verificaciones para saber cual es el nodo fin para acomodar el agente
            nodo3 = new Nodo(mapa.getMapaM()[nodo1.getI() - 1][nodo1.getJ()]);
        } else if (nodo1.getI() + 1 < mapa.getN() && nodo1.getI() - 1 == nodo2.getI() && nodo1.getJ() == nodo2.getJ()) {
            nodo3 = new Nodo(mapa.getMapaM()[nodo1.getI() + 1][nodo1.getJ()]);
        } else if (nodo1.getJ() - 1 >= 0 && nodo1.getJ() + 1 == nodo2.getJ() && nodo1.getI() == nodo2.getI()) {
            nodo3 = new Nodo(mapa.getMapaM()[nodo1.getI()][nodo1.getJ() - 1]);
        } else if (nodo1.getJ() + 1 < mapa.getN() && nodo1.getJ() - 1 == nodo2.getJ() && nodo1.getI() == nodo2.getI()) {
            nodo3 = new Nodo(mapa.getMapaM()[nodo1.getI()][nodo1.getJ() + 1]);
        }
        return nodo3;
    }
    
    public LinkedList<Nodo> ruta(Mapa mapa) {
        abierta = vecinos(inicio, mapa);
        while (objetivo()) {
            buscar(mapa);
        }
        LinkedList<Nodo> camino = camino();
        return camino;
    }

    public LinkedList<Nodo> camino() {
        LinkedList<Nodo> camino = new LinkedList<>();
        Nodo objetivo = null;

        for (int i = 0; i < abierta.size(); i++) {
            if (fin.getI() == abierta.get(i).getI() && fin.getJ() == abierta.get(i).getJ()) {
                objetivo = abierta.get(i);
            }
        }
        while (objetivo.getPadre() != null) {
            camino.add(objetivo);
            objetivo = objetivo.getPadre();
        }

        camino = reversa(camino);
        return camino;
    }

    public LinkedList<Nodo> reversa(LinkedList<Nodo> camino) {
        LinkedList<Nodo> reversa = new LinkedList<>();
        for (int i = camino.size() - 1; i >= 0; i--) {
            reversa.add(camino.get(i));
        }
        return reversa;
    }
    
    public boolean condiciones(Cuadro posicion)
    {
        if (posicion.getI()==fin.getI() && posicion.getJ()==fin.getJ() || (posicion.getI()==inicio.getI() && posicion.getJ()==inicio.getJ()))
        {
            return true;
        }
        else if (!posicion.isBloqueado())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public LinkedList<Nodo> vecinos(Nodo posicion, Mapa mapa) {
        LinkedList<Nodo> vecinos = new LinkedList<>();
        if (posicion.getI() + 1 < mapa.getN() && !(mapa.getMapaM()[posicion.getI() + 1][posicion.getJ()] instanceof Muro) && condiciones(mapa.getMapaM()[posicion.getI() + 1][posicion.getJ()])) {
//        if (posicion.getI() + 1 < mapa.getN() && !(mapa.getMapaM()[posicion.getI() + 1][posicion.getJ()] instanceof Muro)) {
            Nodo vecino = new Nodo(mapa.getMapaM()[posicion.getI() + 1][posicion.getJ()]);
            Nodo nodoAux = nodoOpuesto(posicion, vecino, mapa);
            if (nodoAux != null && !(mapa.getMapaM()[nodoAux.getI()][nodoAux.getJ()] instanceof Muro)) {
//            if (nodoAux != null && !(mapa.getMapaM()[nodoAux.getI()][nodoAux.getJ()] instanceof Muro)) {
                vecino.setPadre(posicion);
                vecino.distancias(fin);
                vecinos.add(vecino);
            }
        }
        if (posicion.getI() - 1 >= 0 && !(mapa.getMapaM()[posicion.getI() - 1][posicion.getJ()] instanceof Muro) && condiciones(mapa.getMapaM()[posicion.getI() -1][posicion.getJ()])) {
//        if (posicion.getI() - 1 >= 0 && !(mapa.getMapaM()[posicion.getI() - 1][posicion.getJ()] instanceof Muro)) {
            Nodo vecino = new Nodo(mapa.getMapaM()[posicion.getI() - 1][posicion.getJ()]);
            Nodo nodoAux = nodoOpuesto(posicion, vecino, mapa);
//            if (nodoAux != null && !(mapa.getMapaM()[nodoAux.getI()][nodoAux.getJ()] instanceof Muro) && !mapa.getMapaM()[posicion.getI() -1][posicion.getJ()].isBloqueado()) {
            if (nodoAux != null && !(mapa.getMapaM()[nodoAux.getI()][nodoAux.getJ()] instanceof Muro)) {
                vecino.setPadre(posicion);
                vecino.distancias(fin);
                vecinos.add(vecino);
            }
        }
        if (posicion.getJ() + 1 < mapa.getN() && !(mapa.getMapaM()[posicion.getI()][posicion.getJ() + 1] instanceof Muro) && condiciones(mapa.getMapaM()[posicion.getI()][posicion.getJ()+1])) {
//        if (posicion.getJ() + 1 < mapa.getN() && !(mapa.getMapaM()[posicion.getI()][posicion.getJ() + 1] instanceof Muro)) {
            Nodo vecino = new Nodo(mapa.getMapaM()[posicion.getI()][posicion.getJ() + 1]);
            Nodo nodoAux = nodoOpuesto(posicion, vecino, mapa);
//            if (nodoAux != null && !(mapa.getMapaM()[nodoAux.getI()][nodoAux.getJ()] instanceof Muro) && !mapa.getMapaM()[posicion.getI()][posicion.getJ()+1].isBloqueado()) {
            if (nodoAux != null && !(mapa.getMapaM()[nodoAux.getI()][nodoAux.getJ()] instanceof Muro)) {
                vecino.setPadre(posicion);
                vecino.distancias(fin);
                vecinos.add(vecino);
            }
        }
        if (posicion.getJ() - 1 >= 0 && !(mapa.getMapaM()[posicion.getI()][posicion.getJ() - 1] instanceof Muro) && condiciones(mapa.getMapaM()[posicion.getI()][posicion.getJ()-1])) {
//        if (posicion.getJ() - 1 >= 0 && !(mapa.getMapaM()[posicion.getI()][posicion.getJ() - 1] instanceof Muro)) {
            Nodo vecino = new Nodo(mapa.getMapaM()[posicion.getI()][posicion.getJ() - 1]);
            Nodo nodoAux = nodoOpuesto(posicion, vecino, mapa);
//            if (nodoAux != null && !(mapa.getMapaM()[nodoAux.getI()][nodoAux.getJ()] instanceof Muro) && !mapa.getMapaM()[posicion.getI()][posicion.getJ()-1].isBloqueado()) {
            if (nodoAux != null && !(mapa.getMapaM()[nodoAux.getI()][nodoAux.getJ()] instanceof Muro)) {
                vecino.setPadre(posicion);
                vecino.distancias(fin);
                vecinos.add(vecino);
            }
        }

        return vecinos;

    }

    public boolean objetivo() {
        for (int i = 0; i < abierta.size(); i++) {
            if (fin.getI() == abierta.get(i).getI() && fin.getJ() == abierta.get(i).getJ()) {
                return false;
            }
        }
        return true;
    }

    public void buscar(Mapa mapa) {
        fMenor();
        Nodo seleccionado = cerrada.getLast();
        LinkedList<Nodo> vecinos = vecinos(seleccionado, mapa);
        evaluacionVecinos(vecinos, seleccionado);
    }

    private void fMenor() {
        Nodo actual = abierta.get(0);
        int n = 0;
        for (int i = 1; i < abierta.size(); i++) {
            if (actual.getF() > abierta.get(i).getF()) {
                actual = abierta.get(i);
                n = i;
            }
        }
        cerrada.add(abierta.get(n));
        abierta.remove(n);

    }

    public void evaluacionVecinos(LinkedList<Nodo> vecinos, Nodo seleccionado) {
        for (int i = 0; i < vecinos.size(); i++) {
            if (existe(vecinos.get(i), cerrada)) {
                continue;
            } else if (!existe(vecinos.get(i), abierta)) {
                abierta.add(vecinos.get(i));
            } else {
                if (seleccionado.getG() + 1 < vecinos.get(i).getG()) {
                    for (int j = 0; j < abierta.size(); j++) {
                        if (vecinos.get(i).getI() == abierta.get(j).getI() && vecinos.get(i).getJ() == abierta.get(j).getJ()) {
                            abierta.remove(j);
                            abierta.add(vecinos.get(i));
                            break;
                        }
                    }
                }
            }
        }
    }

    public boolean existe(Nodo nodo, LinkedList<Nodo> nodos) {
        for (int i = 0; i < nodos.size(); i++) {
            if (nodos.get(i).getI() == nodo.getI() && nodos.get(i).getJ() == nodo.getJ()) {
                return true;
            }
        }
        return false;
    }

    public Nodo getInicio() {
        return inicio;
    }

    public Nodo getFin() {
        return fin;
    }

}
