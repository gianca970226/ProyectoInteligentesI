/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointeligentes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author JORGE_ALEJANDRO
 */
public class AEstrella {

    private Nodo inicio;
    private Nodo fin;
    private LinkedList<Nodo>abierta;
    private LinkedList<Nodo>cerrada;

    public AEstrella() {
        inicio = null;
        fin = null;
        abierta=new LinkedList<>();
        cerrada=new LinkedList<>();

    }

    public void cargarInicioFin(Mapa mapa) {
        for (int i = 0; i < mapa.getMapaM().length && inicio != null && fin != null; i++) {
            for (int j = 0; j < mapa.getMapaM().length && inicio != null && fin != null; j++) {

                if (mapa.getMapaM()[i][j] instanceof Agente && inicio!=null) {
                    Agente agente = (Agente) mapa.getMapaM()[i][j];
                    if (!agente.isAsignado()) {
                        agente.setAsignado(true);
                        mapa.getMapaM()[i][j] = agente;
                        inicio = (Nodo) mapa.getMapaM()[i][j];
                    }

                }
                if (mapa.getMapaM()[i][j] instanceof Caja && fin !=null) {
                    Caja caja = (Caja) mapa.getMapaM()[i][j];
                    if (!caja.isAsignado()) {
                        caja.setAsignado(true);
                        mapa.getMapaM()[i][j] = caja;
                        fin = (Nodo) mapa.getMapaM()[i][j];
                    }
                }
            }
        }
        cerrada.add(inicio);
    }
    
    public void vecinos(Nodo posicion, Mapa mapa)
    {
        LinkedList<Nodo>vecinos=new LinkedList<>();
        if (!(mapa.getMapaM()[posicion.getI()+1][posicion.getJ()] instanceof Muro))
        {
            Nodo vecino=(Nodo) mapa.getMapaM()[posicion.getI()+1][posicion.getJ()];
            vecino.setPadre(posicion);
            vecino.distancias(fin);
            vecinos.add(vecino);
        }
        if (!(mapa.getMapaM()[posicion.getI()-1][posicion.getJ()] instanceof Muro))
        {
            Nodo vecino=(Nodo) mapa.getMapaM()[posicion.getI()-1][posicion.getJ()];
            vecino.setPadre(posicion);
            vecino.distancias(fin);
            vecinos.add(vecino);
        }
        if (!(mapa.getMapaM()[posicion.getI()][posicion.getJ()+1] instanceof Muro))
        {
            Nodo vecino=(Nodo) mapa.getMapaM()[posicion.getI()][posicion.getJ()+1];
            vecino.setPadre(posicion);
            vecino.distancias(fin);
            vecinos.add(vecino);
        }
        if (!(mapa.getMapaM()[posicion.getI()][posicion.getJ()-1] instanceof Muro))
        {
            Nodo vecino=(Nodo) mapa.getMapaM()[posicion.getI()][posicion.getJ()-1];
            vecino.setPadre(posicion);
            vecino.distancias(fin);
            vecinos.add(vecino);
        }
        
    }
    
    public boolean objetivo()
    {
        for (int i=0; i<abierta.size(); i++)
        {
            if (fin.getI()==abierta.get(i).getI() && fin.getJ()==abierta.get(i).getJ())
            {
                return false;
            }
        }
        return true;
    }
    
    public void buscar()
    {
        
    }
    
    public void fMenor()
    {
        Nodo actual=abierta.get(0);
        int n=0;
        for (int i=1; i<abierta.size(); i++)
        {
            if (actual.getF()>abierta.get(i).getF())
            {
                actual=abierta.get(i);
                n=i;
            }
        }
    }
}
