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

//    public Caja getCajaAsignada() {
//        return cajaAsignada;
//    }
//
//    public void setCajaAsignada(Caja cajaAsignada) {
//        this.cajaAsignada = cajaAsignada;
//    }
    
    
    
    @Override
    public void run() {
        while (!camino1.isEmpty()) {
            Nodo nodo = camino1.getFirst();
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
        Nodo fin=camino2.getLast();
        mapa.getMapaM()[panel.getCajaMovimiento().getI()][panel.getCajaMovimiento().getJ()]= new Cuadro(panel.getCajaMovimiento().getI(), panel.getCajaMovimiento().getJ());
        while (!camino2.isEmpty()) {
            Nodo nodo = camino2.getFirst();
            Cuadro siguiente = mapa.getMapaM()[nodo.getI()][nodo.getJ()];
            getArea().setLocation((int) panel.getCajaMovimiento().getArea().getX(), (int) panel.getCajaMovimiento().getArea().getY());
            panel.getCajaMovimiento().getArea().setLocation((int)siguiente.getArea().getX(), (int)siguiente.getArea().getY());
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
            panel.repaint();
            camino2.removeFirst();
        }
        mapa.getMapaM()[fin.getI()][fin.getJ()] = new CajaMarcador(fin.getI(), fin.getJ());
        panel.setCajaMovimiento(null);
        panel.repaint();
    }
}
