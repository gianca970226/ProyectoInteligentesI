/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointeligentes;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

/**
 *
 * @author Ander
 */
public class CrearAgenteC {

    private static ContainerController containerController;
    Runtime runtime;
    private final ProfileImpl profile;
    AgentController Agente;

    public CrearAgenteC() {
        runtime = Runtime.instance();
        profile = new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        profile.setParameter(ProfileImpl.MAIN_PORT, "1099");
        profile.setParameter(ProfileImpl.MAIN, "true");

    }

    public void initController() {
        containerController = runtime.createMainContainer(profile);
    }

    public void initAgent(String name, String agentClass, Object[] args) {
        try {
            Agente = containerController.createNewAgent(name, agentClass, args);
            Agente.start();
        } catch (Exception e) {
            System.out.println("error"+e);
        }
    }

    public AgentController getAgente() {
        return Agente;
    }

    public void setAgente(AgentController Agente) {
        this.Agente = Agente;
    }


    

}
