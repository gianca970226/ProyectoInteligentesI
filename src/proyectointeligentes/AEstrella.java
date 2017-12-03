/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointeligentes;

import java.awt.Point;
import java.util.LinkedList;

/**
 *
 * @author JORGE_ALEJANDRO
 */
public class AEstrella {
    private LinkedList<Point>puntos;

    public AEstrella() {
        this.puntos = new LinkedList<>();
    }
    
    public void cargarPIniFin(Point inicio, Point fin)
    {
        puntos.addFirst(inicio);
        puntos.addLast(fin);
    }
    
    
    
    
}
