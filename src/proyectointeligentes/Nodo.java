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
public class Nodo extends Cuadro {
    
    private int f;
    private int g;
    private int h;
    private Nodo padre;
    private boolean visitado;
    
    public Nodo(Cuadro nodo) {
        super(nodo.getI(), nodo.getJ());
        f=-1;
        g=-1;
        h=-1;
        padre=null;
        visitado=false;
    }
    
    public void distancias(Nodo fin)
    {
        h=Math.abs(getI() - fin.getI()) + Math.abs(getJ() - fin.getJ());
        if (padre==null)
        {
            g=0;
        }
        else
        {
            g=padre.getG()+1;
        }
        f=g+h;
    }
    
    public int getF() {
        return f;
    }

    public int getG() {
        return g;
    }

    public int getH() {
        return h;
    }

    public void setF(int f) {
        this.f = f;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setH(int h) {
        this.h = h;
    }

    public boolean isVisitado() {
        return visitado;
    }
    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }  
    
}
