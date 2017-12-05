/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointeligentes;

import java.awt.Rectangle;

/**
 *
 * @author JORGE_ALEJANDRO
 */
public class Marcador extends Cuadro{
    private boolean asignado;
    public Marcador(int i, int j) {
        super(i, j);
        setRutaImagen("img/marcador.png");
//        setTipo("marcador");
        asignado=false;
    }

    public boolean isAsignado() {
        return asignado;
    }

    public void setAsignado(boolean asignado) {
        this.asignado = asignado;
    }
    
    
    
}
