/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointeligentes;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author JORGE_ALEJANDRO
 */
public class MapaUI extends javax.swing.JFrame {

    GraphicsDevice grafica;
    Mapa mapa;
    AreaItems areaItems;
    ArrayList<AgenteJADE> agentesJADE;

    /**
     * Creates new form Menu
     *
     * @param tamaño del mapa
     * @param archivo nombre del archivo en donde esta cargado el mapa
     */
    public MapaUI(int tamaño, String archivo) {
        initComponents();
        grafica = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        grafica.setFullScreenWindow(this);
        panel1.setFocusable(true);
        if (archivo == null) {
            crearMapa(tamaño);
        } else {
            Util util = new Util();
            mapa = util.cargarMapa(this.getWidth(), this.getHeight(), archivo);
        }
        panel1.setMapa(mapa);
        graficarAreaItems();
        setResizable(false);
        setVisible(true);
    }

    private void graficarAreaItems() {
        int anchoAreaItemsX1 = mapa.getN() * mapa.getAnchoCuadro();
        int anchoAreaItemsX2 = this.getWidth() - (int) (this.getWidth() * 0.05);
        areaItems = new AreaItems(anchoAreaItemsX1, anchoAreaItemsX2);
        areaItems.cargarItems();
        panel1.setAreaItems(areaItems);
    }

    private void crearMapa(int tamaño) {
        //Obtengo el ancho para el cuadro de acuerdo al numero de columnas de la matriz del mapa.
        int anchoCuadro = (int) ((this.getWidth() * 0.8) / tamaño);
        //obtengo el alto para el cuadro de acuerdo al numero de filas de la matriz mapa.
        int altoCuadro = (int) ((this.getHeight() - 100) / tamaño);
        //El ancho del mapa es el numero de columnas por el ancho de un cuadro.
        int anchoMapa = tamaño * anchoCuadro;
        //El alto de la ciudad es el numero de filas por el alto del campo.
        int altoMapa = altoCuadro * tamaño;
        Cuadro mapaM[][] = new Cuadro[tamaño][tamaño];
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                mapaM[i][j] = new Cuadro(i, j);
                Rectangle area = new Rectangle(anchoCuadro * j, altoCuadro * i, anchoMapa, altoMapa);
                mapaM[i][j].setArea(area);
            }
        }
        mapa = new Mapa(mapaM, tamaño, anchoCuadro, altoCuadro, anchoMapa, altoMapa);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new proyectointeligentes.Panel();
        guardarCiudadBoton = new javax.swing.JButton();
        ejecutarJuego = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        guardarCiudadBoton.setText("Guardar Ciudad");
        guardarCiudadBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarCiudadBotonActionPerformed(evt);
            }
        });

        ejecutarJuego.setText("Ejecutar");
        ejecutarJuego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarJuegoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(guardarCiudadBoton)
                .addGap(18, 18, 18)
                .addComponent(ejecutarJuego)
                .addContainerGap(692, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap(566, Short.MAX_VALUE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardarCiudadBoton)
                    .addComponent(ejecutarJuego))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardarCiudadBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarCiudadBotonActionPerformed
        String archivo = JOptionPane.showInputDialog(this, "Ingrese el nombre del mapa", "Guardar Ciudad", JOptionPane.INFORMATION_MESSAGE);
        Util util = new Util();
        util.GuardarMapa(mapa, archivo);
        JOptionPane.showMessageDialog(this, "Guardado con exito el mapa", "Exito al guardar mapa", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_guardarCiudadBotonActionPerformed

    private void ejecutarJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejecutarJuegoActionPerformed
        AEstrella aEstrella1 = new AEstrella(); //Hago objeto de algoritmo estrella
        aEstrella1.cargarInicioFinAgenteCaja(mapa); //Cargo los nodos posiciones del agente y la caja
        int inicioI = aEstrella1.getInicio().getI(); //Obtengo las posiciones del nodo inicio que es el agente 
        int inicioJ = aEstrella1.getInicio().getJ();
        Agente agente = (Agente) mapa.getMapaM()[inicioI][inicioJ].clone(); //Obtengo el agente que se selecciono
        panel1.setAgenteMovimiento(agente); //Seteo el agente en el panel
        agente.setMapa(mapa); //seteo el mapa en el agente
        agente.setPanel(panel1); //Seteo el panel en el agente
        mapa.getMapaM()[inicioI][inicioJ] = new Cuadro(inicioI, inicioJ); //Hago un cuadro blanco en esa posicion
        //HASTA AQUI EL AGENTE HAY QUE MODIFICARLE EL CAMINO
        AEstrella aEstrella2 = new AEstrella(); //Creo el otro objeto para el a estrella desde la caja al marcador
        aEstrella2.cargarInicioFinCajaMarcador(mapa, aEstrella1.getFin()); //Cargo desde los nodos inicio y fin caja y marcador
        LinkedList<Nodo> camino2 = aEstrella2.ejecutar(mapa); //Obtengo ese camino
        Nodo cuadrar = camino2.getFirst(); //Como hay que cambiar el nodo fin del agente a la caja entonces tomo el primer nodo camino de la caja
        inicioI=aEstrella2.getInicio().getI();
        inicioJ=aEstrella2.getInicio().getJ();
        Caja caja = (Caja) mapa.getMapaM()[inicioI][inicioJ].clone(); //Tomo la caja seleccionada
        Nodo finAgente = null; //Nodo para cambiar el nodo fin al agente
        if (caja.getI() + 1 == cuadrar.getI() && caja.getJ() == cuadrar.getJ()) { //Verificaciones para saber cual es el nodo fin para acomodar el agente
            finAgente = new Nodo(mapa.getMapaM()[caja.getI()-1][caja.getJ()]);
        }
        else if (caja.getI() - 1 == cuadrar.getI() && caja.getJ() == cuadrar.getJ())
        {
            finAgente = new Nodo(mapa.getMapaM()[caja.getI()+1][caja.getJ()]);
        }
        else if (caja.getJ() + 1 == cuadrar.getJ() && caja.getI() == cuadrar.getI())
        {
            finAgente = new Nodo(mapa.getMapaM()[caja.getI()][caja.getJ()-1]);
        }
        else
        {
            finAgente = new Nodo(mapa.getMapaM()[caja.getI()][caja.getJ()+1]);
        }
        aEstrella1.actualizarFin(finAgente); //Le actualizo el fin al primer algoritmo a estrella
        LinkedList<Nodo> camino1 = aEstrella1.ejecutar(mapa); //Obtengo el camino del agente
        agente.setCamino1(camino1); //Seteo el primer camino al agente
        agente.setCamino2(camino2); //Luego el segundo camino
//        agente.setCajaAsignada(caja); //La caja que debe mover
        panel1.setCajaMovimiento(caja); //Le doy la caja al panel para que lo pinte
        panel1.setAgenteMovimiento(agente); //le doy el agente al panel para que lo pinte
        
//        for (int i = 0; i < camino2.size(); i++) {
//            System.out.println(camino2.get(i).getI() + "-" + camino2.get(i).getJ());
//        }
        
        panel1.getAgenteMovimiento().start(); //Ejecuto el hilo


    }//GEN-LAST:event_ejecutarJuegoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MapaUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MapaUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MapaUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MapaUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MapaUI(0, null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ejecutarJuego;
    private javax.swing.JButton guardarCiudadBoton;
    private proyectointeligentes.Panel panel1;
    // End of variables declaration//GEN-END:variables
}
