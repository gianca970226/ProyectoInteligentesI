/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointeligentes;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author JORGE_ALEJANDRO
 */
public class Mapa implements Serializable {

    private Cuadro[][] mapaM;
    private int n;//indica la canidad de columnas 
    private int anchoCuadro;//indica el ancho del rectangulo de representacion de un componente
    private int altoCuadro;//indica el alto del rectangulo de representacion de un componente
    private int anchoMapa;//indica el ancho del area de la ciudad 
    private int altoMapa;//indica el alto del area de la ciudad
    private LinkedList<Agente> agentes;
    private LinkedList<Marcador> marcadores;
    private LinkedList<Caja> cajas;

    public Mapa(Cuadro[][] mapaM, int n, int anchoCuadro, int altoCuadro, int anchoMapa, int altoMapa) {
        this.mapaM = mapaM;
        this.n = n;
        this.anchoCuadro = anchoCuadro;
        this.altoCuadro = altoCuadro;
        this.anchoMapa = anchoMapa;
        this.altoMapa = altoMapa;
        this.agentes = new LinkedList<>();
        this.marcadores = new LinkedList<>();
        this.cajas = new LinkedList<>();
    }

//    public void EncontrarACM(Panel panel) {
//        LinkedList<Caja> cajasTemp = new LinkedList<>();
//        LinkedList<Marcador> marcadoresTemp = new LinkedList<>();
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (mapaM[i][j] instanceof Agente) {
//                    Agente agente = (Agente) mapaM[i][j];
//                    agente.setMapa(this);
//                    agente.setPanel(panel);
//                    agente.isAsignado();
//                    agentes.add((Agente) mapaM[i][j]);
//                }
//                if (this.mapaM[i][j] instanceof Caja) {
//                    cajasTemp.add((Caja) mapaM[i][j]);
////                    cajas.add((Caja) mapaM[i][j]);
//                }
//                if (this.mapaM[i][j] instanceof Marcador) {
////                    marcadores.add((Marcador) mapaM[i][j]);
//                    marcadoresTemp.add((Marcador) mapaM[i][j]);
//                }
//            }
//        }
//        int distancia = 100000;
//        Marcador seleccionM = null;
//        for (int i = 0; i < agentes.size(); i++) {
//            distancia=100000;
//            for (int j = 0; j < marcadoresTemp.size(); j++) {
//                if (!marcadoresTemp.get(j).isAsignado()) {
//                    int distanciaC = Math.abs(agentes.get(i).getI() - marcadoresTemp.get(j).getI()) + Math.abs(agentes.get(i).getJ() - marcadoresTemp.get(j).getJ());
//                    if (distanciaC < distancia) {
//                        seleccionM = marcadoresTemp.get(j);
//                        distancia = distanciaC;
//                    }
//                }
//            }
//            seleccionM.setAsignado(true);
//            marcadores.add(seleccionM);
//        }
//        
//        distancia = 100000;
//        Caja seleccionC = null;
//        for (int i = 0; i < agentes.size(); i++) {
//            distancia=100000;
//            for (int j = 0; j < cajasTemp.size(); j++) {
//                if (!cajasTemp.get(j).isAsignado()) {
//                    int distanciaC = Math.abs(agentes.get(i).getI() - cajasTemp.get(j).getI()) + Math.abs(agentes.get(i).getJ() - cajasTemp.get(j).getJ());
//                    if (distanciaC < distancia) {
//                        seleccionC = cajasTemp.get(j);
//                        distancia = distanciaC;
//                    }
//                }
//            }
//            seleccionC.setAsignado(true);
//            cajas.add(seleccionC);
//        }
//
//    }

    public void EncontrarACM(Panel panel) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mapaM[i][j] instanceof Agente) {
                    Agente agente=(Agente) mapaM[i][j];
                    agente.setMapa(this);
                    agente.setPanel(panel);
                    agentes.add((Agente) mapaM[i][j]);
                }
                if (this.mapaM[i][j] instanceof Caja) {
                    cajas.add((Caja) mapaM[i][j]);
                }
                if (this.mapaM[i][j] instanceof Marcador) {
                    marcadores.add((Marcador) mapaM[i][j]);
                }
            }
        }
    }
    public Mapa clonarMapa() {
        Cuadro[][] auxMapaM = new Cuadro[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                auxMapaM[i][j] = (Cuadro) mapaM[i][j].clone();
            }
        }
        Mapa auxMapa = new Mapa(auxMapaM, n, anchoCuadro, altoCuadro, anchoMapa, altoMapa);
        return auxMapa;
    }

    //El agente que se le manda como parametro es el que colisiono y debe volver a moverse
    public Mapa eliminarPanelMapaM(Agente agente) {
        Cuadro mapaMNuevo[][] = new Cuadro[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.mapaM[i][j].getI() == agente.getI() && this.mapaM[i][j].getJ() == agente.getJ()) {
                    mapaMNuevo[i][j] = new Agente(i, j);
                } else if (this.mapaM[i][j] instanceof Agente) {
                    mapaMNuevo[i][j] = new Agente(i, j);
                    mapaMNuevo[i][j].setBloqueado(this.mapaM[i][j].isBloqueado());
                } else {
                    mapaMNuevo[i][j] = this.mapaM[i][j];
                }

            }
        }
        Mapa mapaNuevo = new Mapa(mapaMNuevo, n, anchoCuadro, altoCuadro, anchoMapa, altoMapa);
        return mapaNuevo;
    }

    /**
     * @return the mapaM
     */
    public Cuadro[][] getMapaM() {
        return mapaM;
    }

    /**
     * @param mapaM the mapaM to set
     */
    public void setMapaM(Cuadro[][] mapaM) {
        this.mapaM = mapaM;
    }

    /**
     * @return the n
     */
    public int getN() {
        return n;
    }

    /**
     * @param n the n to set
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * @return the anchoCuadro
     */
    public int getAnchoCuadro() {
        return anchoCuadro;
    }

    /**
     * @param anchoCuadro the anchoCuadro to set
     */
    public void setAnchoCuadro(int anchoCuadro) {
        this.anchoCuadro = anchoCuadro;
    }

    /**
     * @return the altoCuadro
     */
    public int getAltoCuadro() {
        return altoCuadro;
    }

    /**
     * @param altoCuadro the altoCuadro to set
     */
    public void setAltoCuadro(int altoCuadro) {
        this.altoCuadro = altoCuadro;
    }

    /**
     * @return the anchoMapa
     */
    public int getAnchoMapa() {
        return anchoMapa;
    }

    /**
     * @param anchoMapa the anchoMapa to set
     */
    public void setAnchoMapa(int anchoMapa) {
        this.anchoMapa = anchoMapa;
    }

    /**
     * @return the altoMapa
     */
    public int getAltoMapa() {
        return altoMapa;
    }

    /**
     * @param altoMapa the altoMapa to set
     */
    public void setAltoMapa(int altoMapa) {
        this.altoMapa = altoMapa;
    }

    public LinkedList<Agente> getAgentes() {
        return agentes;
    }

    public LinkedList<Caja> getCajas() {
        return cajas;
    }

    public LinkedList<Marcador> getMarcadores() {
        return marcadores;
    }

}
