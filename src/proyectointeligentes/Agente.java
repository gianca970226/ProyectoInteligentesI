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
public class Agente extends Cuadro implements Runnable{

    private boolean asignado;
    private Panel panel;
    private Mapa mapa;
    private LinkedList<Nodo> camino1;
    private LinkedList<Nodo> camino2;
    private Caja cajaAsignada;
    private Thread hilo;

    public Agente(int i, int j) {
        super(i, j);
        setRutaImagen("img/agente.png");
//        setTipo("agente");
        asignado = false;
        panel = null;
        mapa = null;
        cajaAsignada=null;
        
    }
    
    public void start() {
        this.hilo = new Thread(this);
        this.hilo.start();
    }

    public boolean isAsignado() {
        return asignado;
    }

    public void setAsignado(boolean asignado) {
        this.asignado = asignado;
    }
    
    public LinkedList<Nodo> getCamino1() {
        return camino1;
    }

    public void setCamino1(LinkedList<Nodo> camino1) {
        this.camino1 = camino1;
    }

    public LinkedList<Nodo> getCamino2() {
        return camino2;
    }

    public void setCamino2(LinkedList<Nodo> camino2) {
        this.camino2 = camino2;
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

    public Caja getCajaAsignada() {
        return cajaAsignada;
    }

    public void setCajaAsignada(Caja cajaAsignada) {
        this.cajaAsignada = cajaAsignada;
    }
    
    
    
    @Override
    public void run() {
        Nodo fin = getCamino1().getLast();
        while (!getCamino1().isEmpty()) {
            Nodo nodo = getCamino1().getFirst();
            Cuadro siguiente = mapa.getMapaM()[nodo.getI()][nodo.getJ()];
            getArea().setLocation((int) siguiente.getArea().getX(), (int) siguiente.getArea().getY());
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
            panel.repaint();
            camino1.removeFirst();
        }
        int finI = fin.getI();
        int finJ = fin.getJ();
        mapa.getMapaM()[finI][finJ] = new Caja(finI, finJ);
//        panel.getCajasMovimiento().removeLast();
        int inicioI = cajaAsignada.getI();
        int inicioJ = cajaAsignada.getJ();
        mapa.getMapaM()[inicioI][inicioJ] = new Cuadro(inicioI, inicioJ);
        panel.repaint();
    }
}
