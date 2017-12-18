/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointeligentes;

import java.io.Serializable;

/**
 *
 * @author JORGE_ALEJANDRO
 */
public class CajaMarcador extends Cuadro implements Serializable{
    
    
    public CajaMarcador(int i, int j) {
        super(i, j);
        setRutaImagen("img/cajamarcador.png");
    }
    
}
