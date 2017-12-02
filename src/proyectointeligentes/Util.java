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
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author gianc
 */
public class Util {
    
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
                        linea = linea + mapa.getMapaM()[i][j].getTipo() + ",";
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
                    if ("-".equals(columnas[i])) {
                        mapaM[i][j] = new Agente(i, j);
                        Rectangle area = new Rectangle(mapa.getAnchoCuadro() * j, mapa.getAltoCuadro() * i, mapa.getAnchoCuadro(), mapa.getAltoCuadro());
                        mapaM[i][i].setArea(area);
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
                if (mapa.getMapaM()[i][j].getTipo().equals("agente"))
                {
                    agentes.add((Agente) mapa.getMapaM()[i][j]);
                }
            }
        }
        return agentes;
    }
    
}
