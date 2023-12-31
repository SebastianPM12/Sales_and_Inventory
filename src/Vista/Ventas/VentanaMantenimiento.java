/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista.Ventas;

import Controlador.Venta.AsesorController;
import Controlador.Venta.EmpresaController;
import Controlador.Venta.MantenimientoController;
import Controlador.Venta.VendedorController;
import Modelo.DTO.Venta.Asesor;
import Modelo.DTO.Venta.Empresa;
import Modelo.DTO.Venta.Vendedor;
import Modelo.Dao.Venta.AsesorDao;
import Modelo.Dao.Venta.EmpresaDao;
import Modelo.Dao.Venta.VendedorDao;
import Vista.Inventario.*;

/**
 *
 * @author SEBAS
 */
public class VentanaMantenimiento extends javax.swing.JFrame {

    /**
     * Creates new form VentanaEntradas
     */
    public VentanaMantenimiento() {
        initComponents();
        this.setLocationRelativeTo(null);
        MantenimientoController mc = new MantenimientoController(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonOtros = new javax.swing.JButton();
        buttonVendedores = new javax.swing.JButton();
        buttonAsesores = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MANTENIMIENTO");
        setResizable(false);

        buttonOtros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/elipsis.png"))); // NOI18N
        buttonOtros.setText("Otros");
        buttonOtros.setBorderPainted(false);
        buttonOtros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOtrosActionPerformed(evt);
            }
        });

        buttonVendedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/grupo.png"))); // NOI18N
        buttonVendedores.setText("Vendedores");
        buttonVendedores.setBorderPainted(false);
        buttonVendedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVendedoresActionPerformed(evt);
            }
        });

        buttonAsesores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/operador.png"))); // NOI18N
        buttonAsesores.setText("Asesores");
        buttonAsesores.setBorderPainted(false);
        buttonAsesores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAsesoresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(buttonVendedores)
                .addGap(18, 18, 18)
                .addComponent(buttonAsesores)
                .addGap(18, 18, 18)
                .addComponent(buttonOtros)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonVendedores)
                    .addComponent(buttonOtros)
                    .addComponent(buttonAsesores))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonOtrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOtrosActionPerformed
        // TODO add your handling code here:
        

    }//GEN-LAST:event_buttonOtrosActionPerformed

    private void buttonVendedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVendedoresActionPerformed
        // TODO add your handling code here:
       


    }//GEN-LAST:event_buttonVendedoresActionPerformed

    private void buttonAsesoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAsesoresActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_buttonAsesoresActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaMantenimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaMantenimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaMantenimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaMantenimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaMantenimiento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton buttonAsesores;
    public javax.swing.JButton buttonOtros;
    public javax.swing.JButton buttonVendedores;
    // End of variables declaration//GEN-END:variables
}
