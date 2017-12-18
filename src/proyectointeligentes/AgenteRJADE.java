/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointeligentes;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author JORGE_ALEJANDRO
 */
public class AgenteRJADE extends Agent {
    
    @Override
    public void setup() {
        RecibeAsignacion recibeAsignacion = new RecibeAsignacion();
        addBehaviour(recibeAsignacion);
    }

    class RecibeAsignacion extends CyclicBehaviour {

        @Override
        public void action() {
            try {
                ACLMessage recibirAsignacionAgente = blockingReceive();
                if (recibirAsignacionAgente != null) {
                    Cuadro agente = (Agente) recibirAsignacionAgente.getContentObject();
                    ACLMessage recibirAsignacionCaja = blockingReceive();
                    Cuadro caja = (Caja) recibirAsignacionCaja.getContentObject();
                    ACLMessage recibirAsignacionMarcador = blockingReceive();
                    Cuadro marcador = (Marcador) recibirAsignacionMarcador.getContentObject();
                    ACLMessage recibirAsignacionMapa = blockingReceive();
                    Mapa mapa = (Mapa) recibirAsignacionMapa.getContentObject();
                    Util util = new Util();
                    LinkedList<Nodo> camino = util.ejecutarJuegoUnAgente(agente, caja, marcador, mapa);
                    AID id = new AID();
                    id.setLocalName("agenteMaster");
                    ACLMessage caminoR = new ACLMessage(ACLMessage.REQUEST);
                    caminoR.addReceiver(id);
                    caminoR.setSender(getAID());
                    caminoR.setContentObject(camino);
                    send(caminoR);
                }
            } catch (UnreadableException ex) {
                System.out.println("Error en el serializable");
            } catch (IOException ex) {
                System.out.println("Error al enviar respuesta");
            }

        }
    }
}
