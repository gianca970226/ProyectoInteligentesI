/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointeligentes;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author JORGE_ALEJANDRO
 */
public class AreaItems {
    private ArrayList<Cuadro>items;    
    private int anchoItemsX1;
    private int anchoItemsX2;

    public AreaItems(int anchoItemsX1, int anchoItemsX2) {
        this.items = new ArrayList<>();
        this.anchoItemsX1 = anchoItemsX1;
        this.anchoItemsX2 = anchoItemsX2;
    }

    public int getAnchoItemsX1() {
        return anchoItemsX1;
    }

    public int getAnchoItemsX2() {
        return anchoItemsX2;
    }

    public ArrayList<Cuadro> getItems() {
        return items;
    }
    
    public void cargarItems()
    {
        int auxAltura = 100;
        Cuadro caja=new Caja(-1,-1);
        caja.setArea(new Rectangle(anchoItemsX1 + 20, 0 * auxAltura, 70, 100));
        items.add(caja);
        Cuadro muro=new Muro(-1,-1);
        muro.setArea(new Rectangle(anchoItemsX1 + 20, 1 * auxAltura+10, 70, 100));
        items.add(muro);
        Cuadro cuadro=new Cuadro(-1,-1);
        cuadro.setArea(new Rectangle(anchoItemsX1 + 20, 2 * auxAltura+20, 70, 100));
        items.add(cuadro);
        Cuadro marcador=new Marcador(-1,-1);
        marcador.setArea(new Rectangle(anchoItemsX1 + 20, 3 * auxAltura+30, 70, 100));
        items.add(marcador);
        Cuadro agente=new Agente(-1,-1);
        agente.setArea(new Rectangle(anchoItemsX1 + 20, 4 * auxAltura+40, 70, 100));
        items.add(agente);
    }
}
