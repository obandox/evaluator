/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Core.Scripter;
import evaluador.Task;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author miguel
 */
public class Gui extends javax.swing.JFrame {

    public static Gui _shared=null;
    
    public static void print(String str){
        if(_shared!=null)
        _shared.getTextOutput().append(str+"\n");
    }
    
    /**
     * Creates new form Gui
     */
    public Gui() {
        initComponents();
        _shared=this;
            DefaultCaret caret = (DefaultCaret)textOutput.getCaret();  
            
            
            caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        comboCase.setModel(new javax.swing.DefaultComboBoxModel(Scripter.getCases()));
    }

    public JTextArea getTextOutput() {
        return textOutput;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textCode = new javax.swing.JTextArea();
        comboLang = new javax.swing.JComboBox();
        textFilename = new javax.swing.JTextField();
        buttonEvaluate = new javax.swing.JButton();
        comboCase = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        textOutput = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textCode.setColumns(20);
        textCode.setRows(5);
        jScrollPane1.setViewportView(textCode);

        textFilename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFilenameActionPerformed(evt);
            }
        });

        buttonEvaluate.setText("EVALUATE");
        buttonEvaluate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEvaluateActionPerformed(evt);
            }
        });

        comboCase.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "a", "b" }));
        comboCase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCaseActionPerformed(evt);
            }
        });

        textOutput.setColumns(20);
        textOutput.setRows(5);
        jScrollPane2.setViewportView(textOutput);

        jButton1.setText("EVALUAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboCase, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(buttonEvaluate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textFilename)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE))
                    .addComponent(comboLang, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(comboCase, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comboLang, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(textFilename, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonEvaluate, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textFilenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFilenameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFilenameActionPerformed

    private void comboCaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCaseActionPerformed
      
        
        String caseName= comboCase.getSelectedItem().toString();
        comboLang.setModel(new DefaultComboBoxModel(Scripter.getLanguages(caseName)));
        
        
        
    }//GEN-LAST:event_comboCaseActionPerformed

    private void buttonEvaluateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEvaluateActionPerformed
        
        String caseName= comboCase.getSelectedItem().toString();
        String languageName= comboLang.getSelectedItem().toString();
        
        String filename= textFilename.getText().trim();
        String content= textCode.getText().trim();
        
        Scripter.execute(caseName, languageName, filename, content);
        
        
    }//GEN-LAST:event_buttonEvaluateActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        new Task().validate();
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEvaluate;
    private javax.swing.JComboBox comboCase;
    private javax.swing.JComboBox comboLang;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea textCode;
    private javax.swing.JTextField textFilename;
    private javax.swing.JTextArea textOutput;
    // End of variables declaration//GEN-END:variables
}
