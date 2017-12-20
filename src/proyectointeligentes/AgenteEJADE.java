/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointeligentes;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author JORGE_ALEJANDRO
 */
public class AgenteEJADE extends GuiAgent {

    private int comando = -1;
    transient protected MapaUI juego;

    @Override
    protected void setup() {
        juego = new MapaUI(this);
        juego.setVisible(true);
    }

    @Override
    protected void onGuiEvent(GuiEvent ge) {
        comando = ge.getType();
        if (comando == 1) {
            EnviarAsignaciones enviarAsignaciones = new EnviarAsignaciones();
            addBehaviour(enviarAsignaciones);
            EnviarColisiones enviarColisiones=new EnviarColisiones();
            addBehaviour(enviarColisiones);
//            System.out.println("Lllegue");
        }
    }

    class EnviarAsignaciones extends SimpleBehaviour {

        @Override
        public void action() {
            try {
                for (int i = 0; i < juego.getMapa().getAgentes().size(); i++) {
                    AID id = new AID();
                    id.setLocalName("agente" + i);
                    ACLMessage agenteE = new ACLMessage(ACLMessage.REQUEST);
                    agenteE.addReceiver(id);
                    agenteE.setSender(getAID());
                    Agente agente = juego.getMapa().getAgentes().get(i);
                    Agente sagente = new Agente(agente.getI(), agente.getJ());
                    agenteE.setContentObject(sagente);
                    send(agenteE);

                    ACLMessage cajaE = new ACLMessage(ACLMessage.REQUEST);
                    cajaE.addReceiver(id);
                    cajaE.setSender(getAID());
                    Caja caja = juego.getMapa().getCajas().get(i);
                    Caja scaja = new Caja(caja.getI(), caja.getJ());
                    cajaE.setContentObject(scaja);
                    send(cajaE);

                    ACLMessage marcadorE = new ACLMessage(ACLMessage.REQUEST);
                    marcadorE.addReceiver(id);
                    marcadorE.setSender(getAID());
                    Marcador marcador = juego.getMapa().getMarcadores().get(i);
                    Marcador smarcador = new Marcador(marcador.getI(), marcador.getJ());
                    marcadorE.setContentObject(smarcador);
                    send(marcadorE);

                    ACLMessage mapaE = new ACLMessage(ACLMessage.REQUEST);
                    mapaE.addReceiver(id);
                    mapaE.setSender(getAID());
                    mapaE.setContentObject(juego.getMapa().eliminarPanelMapaM(juego.getMapa().getAgentes().get(i)));
                    send(mapaE);

                    ACLMessage recibirCaminoAgente = blockingReceive();
                    if (recibirCaminoAgente != null) {
                        LinkedList<Nodo> camino = (LinkedList<Nodo>) recibirCaminoAgente.getContentObject();
                        juego.getMapa().getAgentes().get(i).setCamino(camino);
                        juego.getMapa().getAgentes().get(i).start();

                    }
                }
            } catch (IOException ex) {
                System.out.println("Error en la serializacion: " + ex.toString());
            } catch (UnreadableException ex) {
                System.out.println("Error al recibir camino");
            }
        }

        @Override
        public boolean done() {
            return true;
        }
    }

    class EnviarColisiones extends CyclicBehaviour {

        @Override
        public void action() {

            for (int i = 0; i < juego.getMapa().getAgentes().size(); i++) {
                if (juego.getMapa().getAgentes().get(i).isColision()) {
                    juego.getMapa().getAgentes().get(i).setColision(false);
                    try {
                        AID id = new AID();
                        id.setLocalName("agente" + i);
                        ACLMessage agenteE = new ACLMessage(ACLMessage.REQUEST);
                        agenteE.addReceiver(id);
                        agenteE.setSender(getAID());
                        Agente agente = juego.getMapa().getAgentes().get(i);
                        Agente sagente = new Agente(agente.getI(), agente.getJ());
                        agenteE.setContentObject(sagente);
                        send(agenteE);

                        ACLMessage cajaE = new ACLMessage(ACLMessage.REQUEST);
                        cajaE.addReceiver(id);
                        cajaE.setSender(getAID());
                        Caja caja = juego.getMapa().getCajas().get(i);
                        Caja scaja = new Caja(caja.getI(), caja.getJ());
                        cajaE.setContentObject(scaja);
                        send(cajaE);

                        ACLMessage marcadorE = new ACLMessage(ACLMessage.REQUEST);
                        marcadorE.addReceiver(id);
                        marcadorE.setSender(getAID());
                        Marcador marcador = juego.getMapa().getMarcadores().get(i);
                        Marcador smarcador = new Marcador(marcador.getI(), marcador.getJ());
                        marcadorE.setContentObject(smarcador);
                        send(marcadorE);

                        ACLMessage mapaE = new ACLMessage(ACLMessage.REQUEST);
                        mapaE.addReceiver(id);
                        mapaE.setSender(getAID());
                        mapaE.setContentObject(juego.getMapa().eliminarPanelMapaM(juego.getMapa().getAgentes().get(i)));
                        send(mapaE);

                        ACLMessage recibirCaminoAgente = blockingReceive();
                        if (recibirCaminoAgente != null) {
                            LinkedList<Nodo> camino = (LinkedList<Nodo>) recibirCaminoAgente.getContentObject();
                            juego.getMapa().getAgentes().get(i).setCamino(camino);
                            juego.getMapa().getAgentes().get(i).start();
                        }
                    } catch (IOException ex) {
                        System.out.println("Error en la serializacion: " + ex.toString());
                    } catch (UnreadableException ex) {
                        System.out.println("Error al recibir camino");
                    }
                }
            }
            block(2000);
        }
    }
}
