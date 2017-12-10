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
public class Agente extends Cuadro implements Runnable {

    private boolean asignado;
    private LinkedList<Nodo> camino;
    private Panel panel;
    private Mapa mapa;

    public Agente(int i, int j) {
        super(i, j);
        setRutaImagen("img/agente.png");
//        setTipo("agente");
        asignado = false;
        camino = null;
        panel = null;
        mapa = null;
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

    public void setCamino(LinkedList<Nodo> camino) {
        this.camino = camino;
    }

    public Panel getPanel() {
        return panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    @Override
    public void run() {
        while (!camino.isEmpty()) {
            Nodo siguiente = camino.getFirst();
            if ((int) getArea().getX() == (int) siguiente.getArea().getX()) {
                int origenY = (int) getArea().getY();
                int destinoY = (int) getArea().getY();
                if (origenY < destinoY) {
                    while (origenY < destinoY) {
                        try {
                            origenY = origenY + 10;
                            getArea().setLocation((int) getArea().getX(), origenY);
                            Thread.sleep((mapa.getN() * mapa.getN()) / 50 + 100);
                        } catch (InterruptedException ex) {
                            System.out.println(ex.getMessage());
                        }
                        panel.repaint();
                    }
//                    getArea().setLocation((int) getArea().getX(), auxY);
                } else {
                    while (origenY > destinoY) {
                        try {
                            origenY = origenY - 10;
                            getArea().setLocation((int) getArea().getX(), origenY);
                            Thread.sleep((mapa.getN() * mapa.getN()) / 50 + 100);
                        } catch (InterruptedException ex) {
                            System.out.println(ex.getMessage());
                        }
                        panel.repaint();
                    }
                }
            }
            if ((int) getArea().getY() == (int) siguiente.getArea().getY())
            {
                int origenX = (int) getArea().getX();
                int destinoX = (int) getArea().getX();
                if (origenX < destinoX) {
                    while (origenX < destinoX) {
                        try {
                            origenX = origenX + 10;
                            getArea().setLocation(origenX, (int) getArea().getY());
                            Thread.sleep((mapa.getN() * mapa.getN()) / 50 + 100);
                        } catch (InterruptedException ex) {
                            System.out.println(ex.getMessage());
                        }
                        panel.repaint();
                    }
//                    getArea().setLocation((int) getArea().getX(), auxY);
                } else {
                    while (origenX > destinoX) {
                        try {
                            origenX = origenX - 10;
                            getArea().setLocation(origenX, (int) getArea().getY());
                            Thread.sleep((mapa.getN() * mapa.getN()) / 50 + 100);
                        } catch (InterruptedException ex) {
                            System.out.println(ex.getMessage());
                        }
                        panel.repaint();
                    }
                }
            }
            mapa.getMapaM()[getI()][getJ()] = new Cuadro(getI(), getJ());
            mapa.getMapaM()[siguiente.getI()][siguiente.getJ()] = siguiente;
            panel.repaint();
            camino.removeFirst();
        }
    }
}
