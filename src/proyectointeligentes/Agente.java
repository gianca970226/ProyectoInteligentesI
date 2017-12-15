/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointeligentes;

import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JORGE_ALEJANDRO
 */
public class Agente extends Cuadro implements Runnable {

    private boolean asignado;
    private Panel panel;
    private Mapa mapa;
    private LinkedList<Nodo> camino;

//    private Caja cajaAsignada;
    private Thread hilo;

    public Agente(int i, int j) {
        super(i, j);
        setRutaImagen("img/agente.png");
//        setTipo("agente");
        asignado = false;
        panel = null;
        mapa = null;
//        cajaAsignada=null;

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

//    public Caja getCajaAsignada() {
//        return cajaAsignada;
//    }
//
//    public void setCajaAsignada(Caja cajaAsignada) {
//        this.cajaAsignada = cajaAsignada;
//    }
    public void tipoMovimiento(Nodo caja, Nodo agente) {
        if (agente.getJ() + 1 < mapa.getN() && agente.getI() == caja.getI() && agente.getJ() + 1 == caja.getJ()) {
            this.panel.getMapa().getMapaM()[caja.getI()][caja.getJ() + 1] = this.panel.getMapa().getMapaM()[caja.getI()][caja.getJ()];
        } else if (agente.getJ() - 1 >= 0 && agente.getI() == caja.getI() && agente.getJ() - 1 == caja.getJ()) {
            this.panel.getMapa().getMapaM()[caja.getI()][caja.getJ() - 1] = this.panel.getMapa().getMapaM()[caja.getI()][caja.getJ()];
        } else if (agente.getI() - 1 >= 0 && agente.getJ() == caja.getJ() && agente.getI() - 1 == caja.getI()) {
            this.panel.getMapa().getMapaM()[caja.getI() - 1][caja.getJ()] = this.panel.getMapa().getMapaM()[caja.getI()][caja.getJ()];
        } else if (agente.getI() + 1 < mapa.getN() && agente.getJ() == caja.getJ() && agente.getI() + 1 == caja.getI()) {
            this.panel.getMapa().getMapaM()[caja.getI() + 1][caja.getJ()] = this.panel.getMapa().getMapaM()[caja.getI()][caja.getJ()];
        }
    }

    public void inicarAnimaccion() {
        for (int i = 0; i < this.camino.size(); i++) {
            Agente auxAgente = this;
            if (this.panel.getMapa().getMapaM()[this.camino.get(i).getI()][this.camino.get(i).getJ()] instanceof Caja) {

                tipoMovimiento(camino.get(i), new Nodo(this));

                // this.panel.getMapa().getMapaM()[this.camino.get(i).getI()][this.camino.get(i).getJ() - 1] = this.panel.getMapa().getMapaM()[this.camino.get(i).getI()][this.camino.get(i).getJ()];
            }
            this.panel.getMapa().getMapaM()[this.camino.get(i).getI()][this.camino.get(i).getJ()] = auxAgente;
            Rectangle area = mapa.getMapaM()[auxAgente.getI()][auxAgente.getJ()].getArea();
            mapa.getMapaM()[auxAgente.getI()][auxAgente.getJ()] = new Cuadro(auxAgente.getI(), auxAgente.getJ()); //Hago un cuadro blanco en esa posicion
            mapa.getMapaM()[auxAgente.getI()][auxAgente.getJ()].setArea(area);
            auxAgente.setI(camino.get(i).getI());
            auxAgente.setJ(camino.get(i).getJ());

            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Agente.class.getName()).log(Level.SEVERE, null, ex);
            }
            panel.repaint();
        }
    }

    @Override
    public void run() {

        inicarAnimaccion();
    }

}
