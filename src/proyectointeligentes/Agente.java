/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointeligentes;

import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author JORGE_ALEJANDRO
 */
public class Agente extends Cuadro implements Runnable {

    private boolean asignado;
    private Panel panel;
    private Mapa mapa;
    private LinkedList<Nodo> camino;
    private boolean colision;

    public Agente(int i, int j) {
        super(i, j);
        setRutaImagen("img/agente.png");
        asignado = false;
        panel = null;
        mapa = null;
        colision=false;

    }

    public void start() {
        Thread hilo = new Thread(this);
        hilo.start();

    }

    public boolean isAsignado() {
        return asignado;
    }

    public void setAsignado(boolean asignado) {
        this.asignado = asignado;
    }

    public LinkedList<Nodo> getCamino() {
        return camino;
    }

    public void setCamino(LinkedList<Nodo> camino1) {
        this.camino = camino1;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public Panel getPanel() {
        return panel;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    public boolean isColision() {
        return colision;
    }

    public void setColision(boolean colision) {
        this.colision = colision;
    }
    

    public void tipoMovimiento(Nodo caja, Nodo agente) {
        if (agente.getJ() + 1 < mapa.getN() && agente.getI() == caja.getI() && agente.getJ() + 1 == caja.getJ()) {
            if (mapa.getMapaM()[caja.getI()][caja.getJ() + 1] instanceof Agente || mapa.getMapaM()[caja.getI()][caja.getJ() + 1] instanceof Caja) {
                colision = true;
            } else {
                mapa.getMapaM()[caja.getI()][caja.getJ() + 1] = mapa.getMapaM()[caja.getI()][caja.getJ()];
                mapa.getMapaM()[caja.getI()][caja.getJ() + 1].setI(caja.getI());
                mapa.getMapaM()[caja.getI()][caja.getJ() + 1].setJ(caja.getJ() + 1);
            }

        } else if (agente.getJ() - 1 >= 0 && agente.getI() == caja.getI() && agente.getJ() - 1 == caja.getJ()) {
            if (mapa.getMapaM()[caja.getI()][caja.getJ() - 1] instanceof Agente || mapa.getMapaM()[caja.getI()][caja.getJ() - 1] instanceof Caja) {
                colision = true;
            } else {
                mapa.getMapaM()[caja.getI()][caja.getJ() - 1] = mapa.getMapaM()[caja.getI()][caja.getJ()];
                mapa.getMapaM()[caja.getI()][caja.getJ() - 1].setI(caja.getI());
                mapa.getMapaM()[caja.getI()][caja.getJ() - 1].setJ(caja.getJ() - 1);
            }

        } else if (agente.getI() - 1 >= 0 && agente.getJ() == caja.getJ() && agente.getI() - 1 == caja.getI()) {
            if (mapa.getMapaM()[caja.getI() - 1][caja.getJ()] instanceof Agente || mapa.getMapaM()[caja.getI() - 1][caja.getJ()] instanceof Caja) {
                colision = true;
            } else {
                mapa.getMapaM()[caja.getI() - 1][caja.getJ()] = mapa.getMapaM()[caja.getI()][caja.getJ()];
                mapa.getMapaM()[caja.getI() - 1][caja.getJ()].setI(caja.getI() - 1);
                mapa.getMapaM()[caja.getI() - 1][caja.getJ()].setJ(caja.getJ());
            }

        } else if (agente.getI() + 1 < mapa.getN() && agente.getJ() == caja.getJ() && agente.getI() + 1 == caja.getI()) {
            if (mapa.getMapaM()[caja.getI() + 1][caja.getJ()] instanceof Agente || mapa.getMapaM()[caja.getI() + 1][caja.getJ()] instanceof Caja) {
                colision = true;
            } else {
                mapa.getMapaM()[caja.getI() + 1][caja.getJ()] = mapa.getMapaM()[caja.getI()][caja.getJ()];
                mapa.getMapaM()[caja.getI() + 1][caja.getJ()].setI(caja.getI() + 1);
                mapa.getMapaM()[caja.getI() + 1][caja.getJ()].setJ(caja.getJ());
            }

        }
    }

    @Override
    public void run() {
        colision = false;
        for (int i = 0; i < camino.size() && !colision; i++) {
            if (mapa.getMapaM()[camino.get(i).getI()][camino.get(i).getJ()] instanceof Caja) {
                tipoMovimiento(camino.get(i), new Nodo(this));
            }
            if (!colision) {
                mapa.getMapaM()[camino.get(i).getI()][camino.get(i).getJ()] = this;
                Rectangle area = getArea();
                mapa.getMapaM()[getI()][getJ()] = new Cuadro(getI(), getJ()); //Hago un cuadro blanco en esa posicion
                mapa.getMapaM()[getI()][getJ()].setArea(area);
                setI(camino.get(i).getI());
                setJ(camino.get(i).getJ());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    System.out.println("Error en el hilo");
                }
            }
            panel.repaint();
        }
        camino=new LinkedList<>();
    }

}
