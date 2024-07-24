/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projectsatu;

/**
 *
 * @author ASUS
 */
import java.awt.Color;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Tugas extends javax.swing.JFrame {

    /**
     * Creates new form Tugas
     */
     private JTable jurusanTable;
    private JTable studentTable;
    private JComboBox<String> jurusanComboBox;
    private JTextField keyTextField;
    private JButton cekDetailButton;
     private JLabel keyLabel;
    private JLabel pilihanJurusanLabel;
    public Tugas() {
        initComponents();
        setTitle("Informasi Jurusan & Mahasiswa");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(245, 196, 196));
      
        jurusanTable = new JTable(new DefaultTableModel(new Object[]{"IDJurusan", "Nama"}, 0));
        studentTable = new JTable(new DefaultTableModel(new Object[]{"Nomor", "Noreg", "Nama", "Alamat", "Jurusan"}, 0));
        jurusanComboBox = new JComboBox<>(new String[]{"--Pilih Jurusan--", "Matematika", "Ilkom", "Ekonomi", "Hukum"});
        keyTextField = new JTextField();
        cekDetailButton = new JButton("Cek Detail");
        keyLabel = new JLabel("KEY*");
        pilihanJurusanLabel = new JLabel("Pilihan Jurusan");

       JScrollPane jurusanScrollPane = new JScrollPane(jurusanTable);
        JScrollPane studentScrollPane = new JScrollPane(studentTable);
         jurusanScrollPane.setBounds(20, 20, 540, 100);
        studentScrollPane.setBounds(20, 200, 540, 150);
        keyLabel.setBounds(20, 150, 50, 30); 
        keyTextField.setBounds(50, 150, 100, 30);
        cekDetailButton.setBounds(150, 150, 100, 30);
        pilihanJurusanLabel.setBounds(290, 150, 100, 30); 
        jurusanComboBox.setBounds(390, 150, 160, 30);
        
        jurusanTable.setBackground(new Color(230, 230, 250)); 
        studentTable.setBackground(new Color(240, 255, 240));
        cekDetailButton.setBackground(new Color(173, 216, 230)); 
        jurusanComboBox.setBackground(new Color(255, 239, 213));

        add(jurusanScrollPane);
        add(studentScrollPane);
        add(jurusanComboBox);
        add(keyTextField);
        add(cekDetailButton);
        add(keyLabel); 
        add(pilihanJurusanLabel);

        // Populate jurusan table
        DefaultTableModel jurusanModel = (DefaultTableModel) jurusanTable.getModel();
        jurusanModel.addRow(new Object[]{"J1", "Matematika"});
        jurusanModel.addRow(new Object[]{"J2", "Ilkom"});
        jurusanModel.addRow(new Object[]{"J3", "Ekonomi"});
        jurusanModel.addRow(new Object[]{"J4", "Hukum"});

        // Populate student table
        DefaultTableModel studentModel = (DefaultTableModel) studentTable.getModel();
        studentModel.addRow(new Object[]{"1", "22101", "Rian", "Penfui", "J2"});
        studentModel.addRow(new Object[]{"2", "23101", "Rahmawati", "Jagakarsa", "J1"});
        studentModel.addRow(new Object[]{"3", "23102", "Maria Raffles", "Kupang", "J1"});
        studentModel.addRow(new Object[]{"4", "40101", "Jojo", "Matani", "J3"});
        studentModel.addRow(new Object[]{"5", "51101", "Erik", "Ende", "J4"});

        jurusanComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterByJurusan((String) jurusanComboBox.getSelectedItem());
            }
        });

        cekDetailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterByKey(keyTextField.getText());
            }
        });
    }

    private void filterByJurusan(String jurusan) {
        String idJurusan = getIdJurusan(jurusan);
        DefaultTableModel studentModel = (DefaultTableModel) studentTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(studentModel);
        studentTable.setRowSorter(sorter);
        if (jurusan.equals("--Pilih Jurusan--")) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter(idJurusan));
        }
    }
    private String getIdJurusan(String jurusan) {
        switch (jurusan) {
            case "Matematika":
                return "J1";
            case "Ilkom":
                return "J2";
            case "Ekonomi":
                return "J3";
            case "Hukum":
                return "J4";
            default:
                return "";
        }
    }
    private void filterByKey(String key) {
        DefaultTableModel studentModel = (DefaultTableModel) studentTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(studentModel);
        studentTable.setRowSorter(sorter);
        if (key.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter(key));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Tugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tugas().setVisible(true);
            }
        });
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

