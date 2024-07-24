/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package penjualan.view;
import penjualan.entity.barang;
import penjualan.implement.barangImplement;
import penjualan.interfc.baranginterfc;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event. ListSelectionEvent;
import javax.swing.event. ListSelectionListener;
import javax.swing.table. DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class barangView extends javax.swing.JFrame {
    //    menampung getAll() barangImplement
    List<barang> record=new ArrayList<barang>();
// membuat objek dari class implement barang (barangImplement). 
    baranginterfc brgServis;
// membantu dalam menampilkan data di textfield kode, nama, jumlah dan harga 
    int row;
    JTable tabelBarang = new JTable();

    /**
     * Creates new form barangView
     */
    public barangView() {
        initComponents();
        setLocationRelativeTo(null);
        brgServis = new barangImplement();
        tabelBarang.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
        public void valueChanged (ListSelectionEvent e) {
            row = tabelBarang.getSelectedRow();
            if(row!=-1){
                isiText();
            }
         }
        });
        this.statusAwal();
    }
    
    void loadData() {
        try {
            record = brgServis.getAll();
        }catch (SQLException ex) {
            Logger.getLogger (barangView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void isiTabel() {
        Object data[][]=new Object[record.size()][4];
        int x=0;
        for (barang brg:record) {
            data[x][0]=brg.getKode_barang();
            data[x][1]=brg.getNama_barang();
            data[x][2]=brg.getHrg();
            data[x][3]=brg.getJml();
            x++;
        }
        String judul[]={"kode barang", "nama barang","harga","jumlah"}; 
        tabelBarang.setModel (new DefaultTableModel (data, judul));
        jScrollPane1.setViewportView(tabelBarang);
    }

    void isiText() {
        barang brg=record.get(row);
        txtkodebarang.setText(brg.getKode_barang());
        txtnamabarang.setText(brg.getNama_barang());
        txthargabarang.setText(brg.getHrg()+"");
        txtjumlahbarang.setText(brg.getJml()+" ");
    }
    
    void kosongkanText() {
        txtkodebarang.setText("");
        txtnamabarang.setText("");
        txthargabarang.setText("");
        txtjumlahbarang.setText("2");
    }

    void statusAwal () {
        kosongkanText();
        loadData();
        isiTabel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtnamabarang = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtkodebarang = new javax.swing.JTextField();
        txthargabarang = new javax.swing.JTextField();
        txtjumlahbarang = new javax.swing.JTextField();
        tambah = new javax.swing.JButton();
        ubah = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBarang = new javax.swing.JTable();
        kembali = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 153, 255));

        txtnamabarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamabarangActionPerformed(evt);
            }
        });

        jLabel1.setText("KODE BARANG");

        jLabel2.setText("Nama Barang");

        jLabel3.setText("Harga");

        jLabel4.setText("Jumlah");

        tambah.setText("Tambah");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        ubah.setText("Ubah");
        ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahActionPerformed(evt);
            }
        });

        hapus.setText("Hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        tableBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Kode Barang", "Nama Barang", "Harga", "Jumlah"
            }
        ));
        jScrollPane1.setViewportView(tableBarang);

        kembali.setText("Kembali");
        kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtkodebarang)
                            .addComponent(txtnamabarang)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtjumlahbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tambah)
                                        .addGap(49, 49, 49)
                                        .addComponent(ubah)
                                        .addGap(49, 49, 49)
                                        .addComponent(hapus))
                                    .addComponent(txthargabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(kembali)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtkodebarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txthargabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtjumlahbarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tambah)
                    .addComponent(ubah)
                    .addComponent(hapus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kembali)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtnamabarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamabarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamabarangActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        // TODO add your handling code here:
        try {
            barang brg = new barang();
            brg.setKode_barang(txtkodebarang.getText());
            brg.setNama_barang(txtnamabarang.getText());
            brg.setHrg(txthargabarang.getText());
            brg.setJml(txtjumlahbarang.getText());
            brgServis.insert(brg);
            this.statusAwal();
            JOptionPane.showMessageDialog(this, "data tersimpan");
            } catch (SQLException ex) {
                Logger.getLogger(barangView.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this,ex); 
            }
    }//GEN-LAST:event_tambahActionPerformed

    private void ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahActionPerformed
        // TODO add your handling code here:
        try {
            barang brg = new barang();
            brg.setKode_barang(txtkodebarang.getText());
            brg.setNama_barang(txtnamabarang.getText());
            brg.setHrg(txthargabarang.getText());
            brg.setJml(txtjumlahbarang.getText());
            brgServis.update(brg);
            this.statusAwal();
            JOptionPane.showMessageDialog(this, "data berhasil diubah");
        } catch (SQLException ex) {
            Logger.getLogger(barangView.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this,ex);
        }
    }//GEN-LAST:event_ubahActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        // TODO add your handling code here:
        barang brg = new barang();
        String kode_barang = txtkodebarang.getText();
        try {
            brgServis.delete(kode_barang);
        } catch (SQLException ex) {
            Logger.getLogger(barangView.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex);
        }
        this.statusAwal();
        JOptionPane.showMessageDialog(this, "data berhasil dihapus");
    }//GEN-LAST:event_hapusActionPerformed

    private void kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliActionPerformed
        // TODO add your handling code here:
        this.dispose(); 
        java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new ToolbarMenu().setVisible(true);
        }
        });    
        
    }//GEN-LAST:event_kembaliActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(barangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(barangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(barangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(barangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new barangView().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton kembali;
    private javax.swing.JTable tableBarang;
    private javax.swing.JButton tambah;
    private javax.swing.JTextField txthargabarang;
    private javax.swing.JTextField txtjumlahbarang;
    private javax.swing.JTextField txtkodebarang;
    private javax.swing.JTextField txtnamabarang;
    private javax.swing.JButton ubah;
    // End of variables declaration//GEN-END:variables
}
