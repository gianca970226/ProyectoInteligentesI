/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointeligentes;

import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author JORGE_ALEJANDRO
 */
public class Proyectointeligentes implements Serializable {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Ingrese el numero de agentes");
        Scanner lectura=new Scanner(System.in);
        int agentes=lectura.nextInt();
        CrearAgenteC crearAgenteC=new CrearAgenteC();
        crearAgenteC.initController();
        for (int i=0; i<agentes; i++)
        {
            crearAgenteC.initAgent("agente"+i, "proyectointeligentes.AgenteRJADE", null);
        }
        crearAgenteC.initAgent("agenteMaster", "proyectointeligentes.AgenteEJADE", null);
    }
    
}
