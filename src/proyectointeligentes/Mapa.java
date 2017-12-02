/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointeligentes;

/**
 *
 * @author JORGE_ALEJANDRO
 */
public class Mapa {
    private Cuadro [][] mapaM;
    private int n;//indica la canidad de columnas 
    private int anchoCuadro;//indica el ancho del rectangulo de representacion de un componente
    private int altoCuadro;//indica el alto del rectangulo de representacion de un componente
    private int anchoMapa;//indica el ancho del area de la ciudad 
    private int altoMapa;//indica el alto del area de la ciudad

    public Mapa(Cuadro[][] mapaM, int n, int anchoCuadro, int altoCuadro, int anchoMapa, int altoMapa) {
        this.mapaM = mapaM;
        this.n = n;
        this.anchoCuadro = anchoCuadro;
        this.altoCuadro = altoCuadro;
        this.anchoMapa = anchoMapa;
        this.altoMapa = altoMapa;
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
    
    
    
}
