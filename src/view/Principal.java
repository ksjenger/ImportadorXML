/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.Arquivo;

import java.io.File;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author ksjenger
 */
public class Principal extends javax.swing.JFrame {

    Arquivo arq = new Arquivo();

    public Principal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jPanelImportar = new javax.swing.JPanel();
        btnSelecionar = new javax.swing.JButton();
        lbnInstrucoes = new javax.swing.JLabel();
        txtUrl = new javax.swing.JTextField();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Importador XML Nfe");

        btnSelecionar.setText("Selecionar");
        btnSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarActionPerformed(evt);
            }
        });

        lbnInstrucoes.setText("Selecione os arquivos para importar");

        txtUrl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUrlActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelImportarLayout = new javax.swing.GroupLayout(jPanelImportar);
        jPanelImportar.setLayout(jPanelImportarLayout);
        jPanelImportarLayout.setHorizontalGroup(
            jPanelImportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelImportarLayout.createSequentialGroup()
                .addGroup(jPanelImportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelImportarLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(lbnInstrucoes))
                    .addGroup(jPanelImportarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtUrl, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSelecionar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelImportarLayout.setVerticalGroup(
            jPanelImportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelImportarLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lbnInstrucoes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelImportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSelecionar)
                    .addComponent(txtUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanelImportar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelImportar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setSize(new java.awt.Dimension(580, 132));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarActionPerformed
        try {
            File arquivos[];
            JFileChooser files = new JFileChooser();
            files.setDialogTitle("Selecione o diretorio dos arquivos XML");
            files.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            files.showOpenDialog(files);
            files.getSelectedFile();
            String url = "" + files.getSelectedFile().getPath();
            arq.setUrl(url);
            txtUrl.setText(arq.getUrl());
            File diretorio = new File(arq.getUrl());
            arquivos = diretorio.listFiles();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            JOptionPane.showMessageDialog(null, "Arquivos Importados!");

            JFileChooser fileExp = new JFileChooser();
            fileExp.setDialogTitle("Selecione o local para salvar o arquivo");

            fileExp.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
            fileExp.setSelectedFile(new File("C:\\Notas.csv"));
            int resultado = fileExp.showSaveDialog(this);
            if (resultado == JFileChooser.CANCEL_OPTION) {
                return;
            }

            fileExp.getSelectedFile();

            String urlExp = "" + fileExp.getSelectedFile().getPath() + "\\Notas.csv";
            FileWriter exp = new FileWriter(urlExp);
            PrintWriter gravExp = new PrintWriter(exp);
            gravExp.printf("Pedido;Nota Fiscal;Chave da Nota %n");

            for (int i = 0; i < arquivos.length; i++) {
                try {
                    String dir = arquivos[i].toString();
                    Document doc = builder.parse(dir);

                    NodeList prod = doc.getElementsByTagName("prod");

                    Node pedido = prod.item(0);

                    //NodeList pedidoChild = (NodeList) pedido.getLastChild();
                    NodeList pedidoChild = pedido.getChildNodes();

                    int cont = 0;
                    for (int j = 0; j < pedidoChild.getLength(); j++) {
                        Node xPed = pedidoChild.item(j);
                        if (xPed.getNodeName().equals("xPed")) {
                            gravExp.printf(xPed.getTextContent() + ";");
                            cont++;
                        }

                    }
                    if (cont == 0) {
                        gravExp.printf(";");
                    }

                    NodeList nf = doc.getElementsByTagName("nNF");

                    Node noNota = nf.item(0);
                    Element elementoNf = (Element) noNota;

                    gravExp.printf(elementoNf.getTextContent() + ";");

                    NodeList ch = doc.getElementsByTagName("chNFe");

                    Node noChave = ch.item(0);
                    Element elementoCh = (Element) noChave;

                    gravExp.print("'" + elementoCh.getTextContent() + "'");

                } catch (SAXException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }

                gravExp.printf("%n");
            }//fim do for
            gravExp.close();
            Process pro;
            pro = Runtime.getRuntime().exec("cmd.exe /c  " + urlExp);
            pro.waitFor();
            System.exit(WIDTH);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnSelecionarActionPerformed

    private void txtUrlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUrlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUrlActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSelecionar;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JPanel jPanelImportar;
    private javax.swing.JLabel lbnInstrucoes;
    private javax.swing.JTextField txtUrl;
    // End of variables declaration//GEN-END:variables
}
