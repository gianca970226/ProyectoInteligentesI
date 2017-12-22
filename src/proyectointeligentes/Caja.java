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
public class Caja extends Cuadro{
    
    private boolean asignado;
    public Caja(int i, int j) {
        super(i, j);
        setRutaImagen("img/caja.png");
        asignado=false;
    }

    public boolean isAsignado() {
        return asignado;
    }

    public void setAsignado(boolean asignado) {
        this.asignado = asignado;
    }
    
    

    
    
    
    
    
   
    
}
