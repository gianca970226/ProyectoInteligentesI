/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointeligentes;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author gianc
 */
public class Util implements Serializable {
    
    public void GuardarMapa(Mapa mapa, String nombreArchivo) {
        String linea;
        FileWriter fichero = null;
        PrintWriter pw;
        try {
            fichero = new FileWriter(nombreArchivo + ".txt");
            pw = new PrintWriter(fichero);
            linea = String.valueOf(mapa.getMapaM().length);
            pw.println(linea);
            linea = "";
            for (int i = 0; i < mapa.getMapaM().length; i++) {
                for (int j = 0; j < mapa.getMapaM().length; j++) {
                    if (mapa.getMapaM()[i][j] == null) {
                        linea = linea + "-,";
                    } else {
                        if (mapa.getMapaM()[i][j] instanceof Agente)
                        {
                            linea = linea + "agente" + ",";
                        }
                        else if (mapa.getMapaM()[i][j] instanceof Muro)
                        {
                            linea = linea + "muro" + ",";
                        }
                        else if (mapa.getMapaM()[i][j] instanceof Marcador)
                        {
                            linea = linea + "marcador" + ",";
                        }
                        else if (mapa.getMapaM()[i][j] instanceof Caja)
                        {
                            linea = linea + "caja" + ",";
                        }
                        else
                        {
                            linea = linea + "cuadro" + ",";
                        }
                    }
                }
                linea = linea.substring(0, linea.length() - 1);
                pw.println(linea);
                linea = "";
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (IOException e2) {
                System.out.println(e2.getMessage());
            }
        }
    }
    public Mapa cargarMapa(int getWidth, int getHeigth, String nombreArchivo) {
        File archivo;
        FileReader fr = null;
        BufferedReader br;
        int i = 0;
        Mapa mapa=null;
        Cuadro mapaM[][]=null;
        try {
            archivo = new File(nombreArchivo + ".txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            int tamaño = Integer.parseInt(br.readLine());
            mapa=actualizarMapa(getWidth, getHeigth, tamaño);
            mapaM = new Cuadro[tamaño][tamaño];
            String linea;
            while ((linea = br.readLine()) != null) {
                String columnas[] = linea.split(",");
                for (int j = 0; j < columnas.length; j++) {
                    if ("agente".equals(columnas[j])) {
                        mapaM[i][j] = new Agente(i, j);
                        mapaM[i][j].setBloqueado(true);
                        Rectangle area = new Rectangle(mapa.getAnchoCuadro() * j, mapa.getAltoCuadro() * i, mapa.getAnchoCuadro(), mapa.getAltoCuadro());
                        mapaM[i][j].setArea(area);
                    }
                    else if("caja".equals(columnas[j]))
                    {
                        mapaM[i][j] = new Caja(i, j);
                        mapaM[i][j].setBloqueado(true);
                        Rectangle area = new Rectangle(mapa.getAnchoCuadro() * j, mapa.getAltoCuadro() * i, mapa.getAnchoCuadro(), mapa.getAltoCuadro());
                        mapaM[i][j].setArea(area);
                    }
                    else if("cuadro".equals(columnas[j]))
                    {
                        mapaM[i][j] = new Cuadro(i, j);
                        Rectangle area = new Rectangle(mapa.getAnchoCuadro() * j, mapa.getAltoCuadro() * i, mapa.getAnchoCuadro(), mapa.getAltoCuadro());
                        mapaM[i][j].setArea(area);
                    }
                    else if("marcador".equals(columnas[j]))
                    {
                        mapaM[i][j] = new Marcador(i, j);
                        Rectangle area = new Rectangle(mapa.getAnchoCuadro() * j, mapa.getAltoCuadro() * i, mapa.getAnchoCuadro(), mapa.getAltoCuadro());
                        mapaM[i][j].setArea(area);
                    }
                    else
                    {
                        mapaM[i][j] = new Muro(i, j);
                        Rectangle area = new Rectangle(mapa.getAnchoCuadro() * j, mapa.getAltoCuadro() * i, mapa.getAnchoCuadro(), mapa.getAltoCuadro());
                        mapaM[i][j].setArea(area);
                    }
                }
                i++;
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (IOException e2) {
                System.out.println(e2.getMessage());
            }
        }
        mapa.setMapaM(mapaM);
        return mapa;
    }
    
    private Mapa actualizarMapa(double getWidth, double getHeight, int tamaño)
    {
        //Obtengo el ancho para el cuadro de acuerdo al numero de columnas de la matriz del mapa.
        int anchoCuadro = (int) ((getWidth * 0.8) / tamaño);
        //obtengo el alto para el cuadro de acuerdo al numero de filas de la matriz mapa.
        int altoCuadro = (int) ((getHeight - 100) / tamaño);
        //El ancho del mapa es el numero de columnas por el ancho de un cuadro.
        int anchoMapa=tamaño * anchoCuadro;
        //El alto de la ciudad es el numero de filas por el alto del campo.
        int altoMapa=altoCuadro * tamaño;
        Mapa mapa=new Mapa(null, tamaño, anchoCuadro, altoCuadro, anchoMapa, altoMapa);
        return mapa;
    }
    
    public ArrayList<Agente> actualizarAgentes(Mapa mapa)
    {
        ArrayList<Agente>agentes=new ArrayList<>();
        for (int i=0; i<mapa.getMapaM().length;i++)
        {
            for (int j=0; j<mapa.getMapaM().length; j++)
            {
                if (mapa.getMapaM()[i][j] instanceof Agente)
                {
                    agentes.add((Agente) mapa.getMapaM()[i][j]);
                }
            }
        }
        return agentes;
    }
    
    public LinkedList<Nodo> ejecutarJuegoUnAgente(Cuadro agentep, Cuadro cajap, Cuadro marcadorp, Mapa mapap) {
        try {
        Agente agente = (Agente)agentep;
        AEstrellaCM aEstrellaCM = new AEstrellaCM(); //Hago objeto de algoritmo estrella, que mira las acomodaciones para el agente
        AEstrellaAM aEstrellaAM = new AEstrellaAM();
        Nodo nodoCaja = new Nodo(cajap);
        Nodo nodoMarcador = new Nodo(marcadorp);
        Nodo nodoAgente = new Nodo(agentep);
        LinkedList<Nodo> caminoCajaMarcador = aEstrellaCM.ejecutar(nodoCaja, nodoMarcador, mapap); //Obtengo ese camino
        Nodo cuadrar = caminoCajaMarcador.getFirst(); //Como hay que cambiar el nodo fin del agente a la caja entonces tomo el primer nodo camino de la caja
        Caja caja = (Caja) cajap; //Tomo la caja seleccion
        Nodo finAgente = aEstrellaAM.actualizarFinAgente(cuadrar, caja, mapap); //Nodo para cambiar el nodo fin al agente
        LinkedList<Nodo> caminoAgenteCaja = aEstrellaAM.ejecutar(nodoAgente, finAgente, mapap); //Obtengo el camino del agente
        caminoAgenteCaja.add(new Nodo(caja)); //Le agrego el nodo donde esta la caja al final del camino del agente a la caja
        agente.setCamino(caminoAgenteCaja); //Seteo el camino del agente a la caja
        Nodo auxNodoInicio = caminoAgenteCaja.getLast(); //Obtengo el ultimo nodo del camino agente caja que es la posicion de la caja
        LinkedList<Nodo> caminoNuevo = new LinkedList<>(); //Creo una lista nueva de camino
        Mapa auxMapa = mapap.clonarMapa(); //Clono mapa
        Agente auxAgente = (Agente) agente.clone(); //Clono el agente
        Caja auxCaja = (Caja) caja.clone(); //Clono la caja
        for (int i = 0; i < caminoCajaMarcador.size() - 1; i++) {//Cojo el caminod e la caja al marcador
            auxMapa.getMapaM()[caminoCajaMarcador.get(i).getI()][caminoCajaMarcador.get(i).getJ()] = auxCaja; //Muevo la caja a una posicion que da el camino
            auxMapa.getMapaM()[auxCaja.getI()][auxCaja.getJ()] = new Cuadro(auxCaja.getI(), auxCaja.getJ()); //Hago un cuadro donde antes estaba la caja
            auxCaja.setI(caminoCajaMarcador.get(i).getI()); //Seteo las posiciones de la caja
            auxCaja.setJ(caminoCajaMarcador.get(i).getJ());
            Nodo nodoOpuesto = aEstrellaAM.nodoOpuesto(caminoCajaMarcador.get(i), caminoCajaMarcador.get(i + 1), auxMapa);
            if (aEstrellaAM.diagonal(auxNodoInicio, nodoOpuesto)) {
                LinkedList<Nodo> auxCamino = aEstrellaAM.ejecutar(new Nodo(auxMapa.getMapaM()[auxNodoInicio.getI()][auxNodoInicio.getJ()]), new Nodo(auxMapa.getMapaM()[nodoOpuesto.getI()][nodoOpuesto.getJ()]), auxMapa);
                caminoNuevo.addAll(auxCamino);
                caminoNuevo.add(caminoCajaMarcador.get(i));
            } else {
                caminoNuevo.add(caminoCajaMarcador.get(i));
            }
            auxNodoInicio = caminoNuevo.getLast();
            auxMapa.getMapaM()[auxNodoInicio.getI()][auxNodoInicio.getJ()] = auxAgente;
            auxMapa.getMapaM()[auxAgente.getI()][auxAgente.getJ()] = new Cuadro(auxAgente.getI(), auxAgente.getJ());
            auxAgente.setI(auxNodoInicio.getI());
            auxAgente.setJ(auxNodoInicio.getJ());
        }
        agente.getCamino().addAll(caminoNuevo); //Luego el segundo camino
        return agente.getCamino();    
        }
        catch(Exception exception)
        {
            return new LinkedList<>();
        }
    }
    
}
