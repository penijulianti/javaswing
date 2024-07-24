/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.sql.Connection;
import java.sql.SQLException;
import java.awt.HeadlessException;
import java.io.File;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author LENOVO
 */
public class Form_Transaksi extends javax.swing.JFrame {

    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    Statement statBrg = null;
    Boolean ada = false;

    private void koneksi() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/dbminimarket";
            String user = "root";
            String pass = "";
            con = DriverManager.getConnection(url, user, pass);
            statBrg = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = statBrg.executeQuery("select * from penjualan");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            System.exit(0);
        }
    }

    NumberFormat nf = NumberFormat.getNumberInstance(new Locale("in", "ID"));
    DefaultTableModel modelisi = new DefaultTableModel();

private void Auto_No_Faktur() {
    try {
        koneksi(); // Memanggil metode koneksi untuk menginisialisasi koneksi ke database

        // Query untuk mengambil nomor faktur terbaru dari tabel penjualan, diurutkan dari besar ke kecil
        String sql = "SELECT * FROM penjualan ORDER BY no_faktur DESC";

        // Membuat objek Statement dan mengeksekusi query
        Statement stm = con.createStatement();
        ResultSet res = stm.executeQuery(sql);

        if (res.next()) {
            // Mengambil nilai no_faktur terakhir dari hasil query
            String lastNoFaktur = res.getString("no_faktur");

            // Memeriksa apakah nilai no_faktur tidak kosong dan panjangnya mencukupi untuk substring(3)
            if (lastNoFaktur != null && lastNoFaktur.length() >= 3) {
                String id = lastNoFaktur.substring(3); // Mengambil substring setelah 3 karakter pertama

                // Menambahkan 1 ke nomor faktur yang diambil
                String AN = "" + (Integer.parseInt(id) + 1);

                // Membuat string Nol sesuai panjang AN
                String Nol = "";
                if (AN.length() == 1) {
                    Nol = "0000";
                } else if (AN.length() == 2) {
                    Nol = "000";
                } else if (AN.length() == 3) {
                    Nol = "00";
                } else if (AN.length() == 4) {
                    Nol = "0";
                }

                // Menyusun nomor faktur lengkap dan menampilkannya di txtnofaktur
                txtnofaktur.setText("NFS" + Nol + AN);
            } else {
                // Jika tidak ada data dalam tabel penjualan atau no_faktur tidak mencukupi panjangnya
                txtnofaktur.setText("NFS00001");
            }
        } else {
            // Jika tidak ada data dalam tabel penjualan
            txtnofaktur.setText("NFS00001");
        }

    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}


    private void Tampilkan_Barang() {
        DefaultTableModel modelBarang = new DefaultTableModel();
        modelBarang.addColumn("Kode");
        modelBarang.addColumn("Nama Barang");
        modelBarang.addColumn("Harga");
        modelBarang.addColumn("Stok");

        String caribrg = jTextField12.getText(); // Assuming txtcaribrg is declared somewhere

        try {
            String sql = "SELECT * FROM barang WHERE kd_barang LIKE '" + caribrg + "' OR nama_barang LIKE '%" + caribrg + "%'";
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                modelBarang.addRow(new Object[]{
                    res.getString(1), res.getString(2),
                    res.getString(4), res.getString(5)
                });
            }

            jTable2.setModel(modelBarang); // Assuming tabel_barang is declared somewhere

            // Adjust column widths
            TableColumn column;
            column = jTable2.getColumnModel().getColumn(0);
            column.setPreferredWidth(75);
            column = jTable2.getColumnModel().getColumn(1);
            column.setPreferredWidth(170);
            column = jTable2.getColumnModel().getColumn(2);
            column.setPreferredWidth(70);
            column = jTable2.getColumnModel().getColumn(3);
            column.setPreferredWidth(45);

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void Judul_Kolom_Keranjang() {
        modelisi.addColumn("No");
        modelisi.addColumn("Stok Awal");
        modelisi.addColumn("Stok Akhir");
        modelisi.addColumn("Kode Barang");
        modelisi.addColumn("Nama Barang");
        modelisi.addColumn("Harga");
        modelisi.addColumn("Jumlah");
        modelisi.addColumn("Sub Total");
        jTable1.setModel(modelisi);

        // untuk mengatur lebar kolom
        TableColumn column;
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        column = jTable1.getColumnModel().getColumn(0);
        column.setPreferredWidth(50);

        column = jTable1.getColumnModel().getColumn(1);
        column.setPreferredWidth(70);

        column = jTable1.getColumnModel().getColumn(2);
        column.setPreferredWidth(70);

        column = jTable1.getColumnModel().getColumn(3);
        column.setPreferredWidth(90);

        column = jTable1.getColumnModel().getColumn(4);
        column.setPreferredWidth(190);

        column = jTable1.getColumnModel().getColumn(5);
        column.setPreferredWidth(90);

        column = jTable1.getColumnModel().getColumn(6);
        column.setPreferredWidth(60);

        column = jTable1.getColumnModel().getColumn(7);
        column.setPreferredWidth(94);
    }

    private void Tanggal() {
        Date now = new Date();
        tgltransaksi.setDate(now);
    }

    private void Load_Data() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        // Create an array to hold the data for the new row
        Object[] rowData = new Object[8];
        rowData[1] = stokawal_brg.getText();
        rowData[2] = jTextField11.getText();
        rowData[3] = kd_barang.getText();
        rowData[4] = nm_barang.getText();
        rowData[5] = harga_brg.getText();
        rowData[6] = jumlah_brg.getText();
        rowData[7] = subtotal_brg.getText();

        // Add the row to the table model
        model.addRow(rowData);

        // Update the row numbers in the first column
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            String rowNumber = String.valueOf(i + 1);
            model.setValueAt(rowNumber + ".", i, 0);
        }

        // Set the row height for better display
        jTable1.setRowHeight(25);
    }

    private void Clear() {
        txtnofaktur.setText("");
        txt_iduser.setText("");
        id.setText("");
        jTextField4.setText("");
        kd_barang.setText("");
        nm_barang.setText("");
        harga_brg.setText("");
    }

    private void Clear2() {
        txtnofaktur.setText("");
        txt_iduser.setText("");
        id.setText("");
        jTextField4.setText("");
        kd_barang.setText("");
        Auto_No_Faktur();
    }

    private void Total_Biaya() {
        // getRowCount() mengembalikan nilai int yang merupakan jumlah baris tabel
        int jumlahBaris = jTable1.getRowCount();
        int totalBiaya = 0;
        // Variable untuk menyimpan jumlahBarang dan hargaBarang
        int jumlahBarang, hargaBarang;
        for (int i = 0; i < jumlahBaris; i++) {
            // Parsing nilai hargaBarang dari kolom indeks 5 (menggunakan kolom ke-6 dalam visualisasi tabel)
            hargaBarang = Integer.parseInt(jTable1.getValueAt(i, 5).toString());
            // Parsing nilai jumlahBarang dari kolom indeks 6 (menggunakan kolom ke-7 dalam visualisasi tabel)
            jumlahBarang = Integer.parseInt(jTable1.getValueAt(i, 6).toString());
            // Menghitung total biaya dengan memperhatikan jumlahBarang dan hargaBarang
            totalBiaya = totalBiaya + (jumlahBarang * hargaBarang);
        }
        // Mengatur teks pada komponen txttotal dengan format mata uang
        total_brg.setText(nf.format(totalBiaya));
        // Mengatur teks pada komponen txttampiltotal dengan format mata uang yang lebih jelas
        jTextField13.setText("Rp. " + nf.format(totalBiaya));
    }
    
  

    private void Tambah_Transaksi() {
        // Deklarasi variabel dengan nilai awal
        int jumlah, harga, total;
        // Menggunakan try-catch untuk menangani kemungkinan NumberFormatException
        try {
            // Mengambil nilai jumlah dari txtjumlah dan mengonversi ke Integer
            jumlah = Integer.valueOf(jumlah_brg.getText());
            // Mengambil nilai harga dari txthargabrg dan mengonversi ke Integer
            harga = Integer.valueOf(harga_brg.getText());
            // Menghitung total dengan jumlah * harga
            total = jumlah * harga;
            // Mengatur teks pada txttotal dengan format mata uang
            total_brg.setText(nf.format(total));
            // Memanggil method Total_Biaya untuk mengupdate total biaya keseluruhan
            Total_Biaya();
            // Memanggil method Kosongkan_1 untuk mengosongkan field input setelah transaksi selesai
            Clear();
            // Memfokuskan kursor pada txtkodebrg untuk input selanjutnya
            kd_barang.requestFocus();

        } catch (NumberFormatException e) {
            // Menangkap exception jika terjadi NumberFormatException saat parsing input
            JOptionPane.showMessageDialog(this, "Input harus berupa angka", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void Kosongkan_Keranjang() {
        // getModel() mengembalikan TableModel dari tabel keranjang.
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        // Menghapus semua baris dari tabel.
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
    }

    public Form_Transaksi() {
        initComponents();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Auto_No_Faktur();
        Tanggal();
        Tampilkan_Barang();
        Judul_Kolom_Keranjang();
        txtnofaktur.setEnabled(false);
        tgltransaksi.setEnabled(false);
        stokawal_brg.setEnabled(false);
        jTextField11.setEnabled(false);
        nm_barang.setEnabled(false);
        harga_brg.setEnabled(false);
        subtotal_brg.setEnabled(false);
        total_brg.setEnabled(false);
        kembalian_brg.setEnabled(false);
        jTextField13.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtnofaktur = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_iduser = new javax.swing.JTextField();
        id = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        kd_barang = new javax.swing.JTextField();
        nm_barang = new javax.swing.JTextField();
        harga_brg = new javax.swing.JTextField();
        jumlah_brg = new javax.swing.JTextField();
        subtotal_brg = new javax.swing.JTextField();
        stokawal_brg = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField12 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextField13 = new javax.swing.JTextField();
        total_brg = new javax.swing.JTextField();
        bayar_brg = new javax.swing.JTextField();
        kembalian_brg = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        tgltransaksi = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(200, 0), new java.awt.Dimension(200, 0), new java.awt.Dimension(200, 32767));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(1000, 0), new java.awt.Dimension(1000, 0), new java.awt.Dimension(1000, 32767));
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JFrame");
        setBackground(new java.awt.Color(204, 255, 204));
        setIconImages(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Transaksi Penjualan Mini Market Murah Banget");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, -1, -1));

        txtnofaktur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnofakturActionPerformed(evt);
            }
        });
        getContentPane().add(txtnofaktur, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 120, 30));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, -1, -1));

        txt_iduser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_iduserActionPerformed(evt);
            }
        });
        txt_iduser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_iduserKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_iduserKeyTyped(evt);
            }
        });
        getContentPane().add(txt_iduser, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 190, 140, -1));

        id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idActionPerformed(evt);
            }
        });
        getContentPane().add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 140, 90, -1));
        getContentPane().add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 180, 80, -1));

        kd_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kd_barangActionPerformed(evt);
            }
        });
        kd_barang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kd_barangKeyReleased(evt);
            }
        });
        getContentPane().add(kd_barang, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 100, -1));

        nm_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nm_barangActionPerformed(evt);
            }
        });
        getContentPane().add(nm_barang, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 140, -1));
        getContentPane().add(harga_brg, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 290, 130, -1));

        jumlah_brg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlah_brgActionPerformed(evt);
            }
        });
        jumlah_brg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jumlah_brgKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jumlah_brgKeyTyped(evt);
            }
        });
        getContentPane().add(jumlah_brg, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 290, 90, -1));
        getContentPane().add(subtotal_brg, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 290, 110, -1));

        stokawal_brg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stokawal_brgActionPerformed(evt);
            }
        });
        getContentPane().add(stokawal_brg, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 340, 70, -1));
        getContentPane().add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 340, 80, -1));

        jLabel5.setText("Kode Barang");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, -1, -1));

        jLabel6.setText("Nama Barang");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, -1, -1));

        jLabel7.setText("Harga Barang");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, -1, -1));

        jLabel8.setText("Jumlah");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 260, -1, -1));

        jLabel9.setText("SubTotal");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 260, -1, -1));

        jLabel10.setText("Stok Akhir");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, -1, -1));

        jLabel11.setText("Stok Awal");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, -1, -1));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel12.setText("No Faktur");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel13.setText("Tanggal Transaksasi");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel14.setText("Username");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, -1, -1));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel15.setText("ID User");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 140, -1, -1));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel16.setText("Akses");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 180, -1, -1));

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jButton2.setText("Tambah");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 340, 100, 50));

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jButton3.setText("Hapus");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 340, 110, 50));

        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField12KeyReleased(evt);
            }
        });
        getContentPane().add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 150, 300, -1));

        jLabel17.setText("Isi Keranjang");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, -1, -1));

        jLabel18.setText("Cari Barang");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 130, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, 740, 210));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 190, 320, -1));

        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 670, 240, 80));

        total_brg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_brgActionPerformed(evt);
            }
        });
        getContentPane().add(total_brg, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 660, 110, -1));

        bayar_brg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayar_brgActionPerformed(evt);
            }
        });
        getContentPane().add(bayar_brg, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 700, 110, -1));

        kembalian_brg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembalian_brgActionPerformed(evt);
            }
        });
        getContentPane().add(kembalian_brg, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 740, 100, -1));

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jButton4.setText("Cetak Struk & Simpan");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 670, 200, 40));

        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jButton5.setText("Exit");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 730, -1, -1));
        getContentPane().add(tgltransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, -1, -1));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel19.setText("Total Belanja");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 660, -1, -1));

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel20.setText("Bayar");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 700, -1, -1));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel21.setText("Kembalian");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 740, -1, -1));

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel22.setText("TOTAL");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 690, -1, 20));
        getContentPane().add(filler1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1230, -1));

        filler2.setBackground(new java.awt.Color(51, 51, 0));
        getContentPane().add(filler2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1230, 10));

        jPanel1.setBackground(new java.awt.Color(0, 204, 51));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1210, 100));

        jPanel2.setBackground(new java.awt.Color(0, 204, 51));
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 830, 120));

        jPanel3.setBackground(new java.awt.Color(204, 204, 0));
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 830, 400));

        jPanel4.setBackground(new java.awt.Color(204, 204, 0));
        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 120, 350, 520));

        jPanel5.setBackground(new java.awt.Color(0, 204, 51));
        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 650, 1200, 130));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void stokawal_brgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stokawal_brgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stokawal_brgActionPerformed

    private void txt_iduserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_iduserActionPerformed
        
    }//GEN-LAST:event_txt_iduserActionPerformed

    private void kd_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kd_barangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kd_barangActionPerformed

    private void jumlah_brgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlah_brgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlah_brgActionPerformed

    private void kd_barangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kd_barangKeyReleased
        try {
            koneksi();
            String sql = "SELECT * FROM barang WHERE kd_barang=?";
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, kd_barang.getText());
            java.sql.ResultSet res = pst.executeQuery(); // tambahkan "=" di sini untuk assignment
            if (res.next()) {
                String namabrg = res.getString("nama_barang"); // Perbaiki penamaan kolom yang digunakan
                nm_barang.setText(namabrg);
                String stokawal = res.getString("stok_awal");
                stokawal_brg.setText(stokawal);
                String hargabrg = res.getString("harga");
                harga_brg.setText(hargabrg);
            }
        } catch (SQLException e) {
            // Tampilkan pesan kesalahan jika terjadi error
            System.out.println("Error: " + e.getMessage());
        }

    }//GEN-LAST:event_kd_barangKeyReleased

    private void jTextField12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyReleased
        Tampilkan_Barang();
    }//GEN-LAST:event_jTextField12KeyReleased

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        if (id.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Pilih User atau Kasir");
            id.requestFocus(); // Perbaikan: Mengembalikan fokus ke txtiduser
        } else {
            int baris = jTable2.rowAtPoint(evt.getPoint()); // Perbaikan: Menambahkan tanda "=" dan "evt"
            String kode = jTable2.getValueAt(baris, 0).toString(); // Perbaikan: Menggunakan "=" daripada "tabel barang"
            kd_barang.setText(kode);
            String nama = jTable2.getValueAt(baris, 1).toString(); // Perbaikan: Menggunakan "=" daripada "tabel barang"
            nm_barang.setText(nama);
            String harga = jTable2.getValueAt(baris, 2).toString(); // Perbaikan: Menggunakan "=" daripada "tabel barang"
            harga_brg.setText(harga);
            String stok = jTable2.getValueAt(baris, 3).toString(); // Perbaikan: Menggunakan "=" daripada "tabel barang"
            stokawal_brg.setText(stok);
        }

    }//GEN-LAST:event_jTable2MouseClicked

    private void jumlah_brgKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlah_brgKeyReleased
        // Bagian untuk menghitung subtotal
        if (jumlah_brg.getText().isEmpty() || jumlah_brg.getText().equals("0")) {
            subtotal_brg.setText("0");
        } else {
            try {
                int jumlah, harga, subtotal;
                jumlah = Integer.valueOf(jumlah_brg.getText());
                harga = Integer.valueOf(harga_brg.getText());
                subtotal = jumlah * harga;
                subtotal_brg.setText(nf.format(subtotal));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Masukkan jumlah dan harga yang valid");
                subtotal_brg.setText("0");
            }
// Bagian untuk memeriksa stok barang

            int jumlahbeli, stokawal, stokakhir;
            jumlahbeli = Integer.parseInt(jumlah_brg.getText());
            stokawal = Integer.parseInt(stokawal_brg.getText());

            if (jumlahbeli > stokawal) {
                JOptionPane.showMessageDialog(null, "Stok Barang Tidak Mencukupi..!");
                jButton2.setEnabled(false);
                jTextField11.setText("");
                jumlah_brg.requestFocus();
            } else {
                stokakhir = (stokawal - jumlahbeli);
                jTextField11.setText(nf.format(stokakhir));
                jButton2.setEnabled(true);
            }
        }

    }//GEN-LAST:event_jumlah_brgKeyReleased

    private void jumlah_brgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlah_brgKeyTyped
        char enter = evt.getKeyChar();
        if (!(Character.isDigit(enter))) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Masukkan Angka 0 sampai 9");
            jumlah_brg.setText("0");
            jTextField11.setText("0");
        }

    }//GEN-LAST:event_jumlah_brgKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (jumlah_brg.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Masukkan Jumlah Barang");
            id.requestFocus();
        } else {
            Load_Data(); // Memuat data yang diperlukan sebelum memproses transaksi
            Tambah_Transaksi(); // Menambahkan transaksi ke dalam sistem
            Clear2(); // Mengosongkan input setelah transaksi selesai
        }
        // Proses untuk menyimpan transaksi ke dalam database
        try {
            int baris2 = jTable1.getRowCount();
            for (int i = 0; i < baris2; i++) {
                String sqlup = "UPDATE barang SET stok='" + jTable1.getValueAt(i, 2).toString()
                        + "'WHERE kd_barang='" + jTable1.getValueAt(i, 3).toString() + "'";
                koneksi();
                java.sql.PreparedStatement pstm = con.prepareStatement(sqlup);
                pstm.execute();
                // Menutup PreparedStatement setelah digunakan
                pstm.close();
            }

            JOptionPane.showMessageDialog(null, "Data Masuk Keranjang");
            Tampilkan_Barang(); // Memperbarui tampilan barang setelah perubahan

        } catch (HeadlessException | SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Data Gagal..!");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int baris = jTable1.rowAtPoint(evt.getPoint());
        String stokawal = jTable1.getValueAt(baris, 1).toString();
        stokawal_brg.setText(stokawal);
        String stokakhir = jTable1.getValueAt(baris, 2).toString();
        jTextField11.setText(stokakhir);
        String kode = jTable1.getValueAt(baris, 3).toString();
        kd_barang.setText(kode);
        String nama = jTable1.getValueAt(baris, 4).toString();
        nm_barang.setText(nama);
        String harga = jTable1.getValueAt(baris, 5).toString();
        harga_brg.setText(harga);
        String jumlah = jTable1.getValueAt(baris, 6).toString();
        jumlah_brg.setText(jumlah);
        String subtotal = jTable1.getValueAt(baris, 7).toString();
        subtotal_brg.setText(subtotal);

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        try {
            int baris2 = jTable1.getRowCount();
            for (int i = 0; i < baris2; i++) {
                String sqlup = "UPDATE barang SET stok=? WHERE kd_barang=?";
                koneksi();
                java.sql.PreparedStatement pstm = con.prepareStatement(sqlup);

                // Mengambil nilai stok baru dan kode barang dari tabel_keranjang
                int stokBaru = Integer.parseInt(jTable1.getValueAt(i, 1).toString());
                String kodeBarang = jTable1.getValueAt(i, 3).toString();

                // Mengatur parameter PreparedStatement
                pstm.setInt(1, stokBaru);
                pstm.setString(2, kodeBarang);

                pstm.executeUpdate(); // Gunakan executeUpdate() untuk perintah UPDATE

                pstm.close();
            }

            JOptionPane.showMessageDialog(null, "Transaksi Dibatalkan");
            Tampilkan_Barang(); // Jika perlu, panggil method untuk menampilkan barang setelah update
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Data Gagal..!");
        }

        int baris = jTable1.getSelectedRow();
        model.removeRow(baris);
        jTable1.setRowHeight(25);
        Total_Biaya();
        bayar_brg.setText("0");
        kembalian_brg.setText("0");
        Clear();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void kembalian_brgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembalian_brgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kembalian_brgActionPerformed

    private void bayar_brgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayar_brgActionPerformed
        int total, bayar, kembalian;
        total = Integer.parseInt(total_brg.getText().replace(".", ""));
        bayar = Integer.parseInt(bayar_brg.getText().replace(".", ""));
        
        if (total > bayar) {
            JOptionPane.showMessageDialog(null, "Uang tidak cukup untuk melakukan pembayaran");
        } else {
            kembalian = bayar - total;
            kembalian_brg.setText(nf.format(kembalian));
            if (kembalian == 0) {
                // Jika kembalian sama dengan 0, atur txtkembalian agar tidak error saat dicetak
                kembalian_brg.setText("0.1");
                JOptionPane.showMessageDialog(null, "Terima kasih sudah membayar");
            }
        }

    }//GEN-LAST:event_bayar_brgActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            // Mengambil nilai total dan bayar dari JTextField
            int total = Integer.parseInt(total_brg.getText().replace(".", ""));
            int bayar = Integer.parseInt(bayar_brg.getText().replace(".", ""));

            if (bayar < total) {
                JOptionPane.showMessageDialog(null, "Pembayaran Kurang");
            } else {
                // Format tanggal pada JDateChooser
                String tampilan = "yyyy-MM-dd";
                SimpleDateFormat fm = new SimpleDateFormat(tampilan);
                String tanggal = fm.format(tgltransaksi.getDate());

                try {
                    // Iterasi untuk setiap baris dalam jTable1
                    int baris = jTable1.getRowCount();
                    for (int i = 0; i < baris; i++) {
                        String sql = "INSERT INTO penjualan VALUES (NULL, ?, ?, ?, ?, ?, ?)";
                        koneksi();
                        PreparedStatement pstm = con.prepareStatement(sql);
                        pstm.setString(1, txtnofaktur.getText()); // no_faktur
                        pstm.setString(2, tanggal); // tanggal
                        pstm.setString(3, String.valueOf(jTable1.getValueAt(i, 3))); // kd_barang
                        pstm.setString(4, id.getText()); // id_user
                        pstm.setInt(5, Integer.parseInt(String.valueOf(jTable1.getValueAt(i, 6)))); // jumlah
                        pstm.setInt(6, Integer.parseInt(String.valueOf(jTable1.getValueAt(i, 7)).replace(".", ""))); // subtotal
                        pstm.executeUpdate(); // Menggunakan executeUpdate() untuk INSERT, bukan execute()
                        pstm.close();
                    }

                    JOptionPane.showMessageDialog(null, "Data Berhasil di Simpan");

                    // Menyiapkan laporan menggunakan JasperReports
                    JasperReport jr;
                    JasperPrint jp;
                    JasperDesign jd;

                    try {
                        // Koneksi database
                        koneksi();
                        HashMap<String, Object> iniparameter = new HashMap<>();
                        iniparameter.put("no_faktur", txtnofaktur.getText());
                        iniparameter.put("totalbelanja", total_brg.getText());
                        iniparameter.put("bayar", bayar_brg.getText());
                        iniparameter.put("kembalian", kembalian_brg.getText());

                        // Mengambil file ireport strukbelanja yang ada pada folder laporan
                        File report = new File("src/Laporan/strukbelanja.jrxml");
                        jd = JRXmlLoader.load(report);
                        jr = JasperCompileManager.compileReport(jd);
                        jp = JasperFillManager.fillReport(jr, iniparameter, con);
                        JasperViewer.viewReport(jp, false);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }

                    // Memanggil method untuk mengosongkan keranjang dan clear input
                    Kosongkan_Keranjang();
                    Clear2();
                    Tampilkan_Barang();

                    // Mengatur nilai default pada JTextField setelah operasi selesai
                    total_brg.setText("0");
                    bayar_brg.setText("0");
                    kembalian_brg.setText("0");
                    jTextField13.setText("Rp. 0");
                } catch (HeadlessException | SQLException e) {
                    e.printStackTrace(); // Tambahkan ini untuk melihat error stack trace secara detail
                    JOptionPane.showMessageDialog(null, "Data Gagal Disimpan: " + e.getMessage());
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Masukkan Angka dengan Benar");
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void nm_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nm_barangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nm_barangActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // Menampilkan dialog konfirmasi sebelum keluar
        int confirmed = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to exit the program?", "Exit Program Message Box",
                JOptionPane.YES_NO_OPTION);

        // Jika user memilih Yes (0), maka keluar dari program
        if (confirmed == JOptionPane.YES_OPTION) {
            System.exit(0); // Menutup aplikasi
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idActionPerformed

    private void txtnofakturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnofakturActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnofakturActionPerformed

    private void total_brgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_brgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_total_brgActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void txt_iduserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_iduserKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_iduserKeyPressed

    private void txt_iduserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_iduserKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_iduserKeyTyped

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
            java.util.logging.Logger.getLogger(Form_Transaksi.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Transaksi.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Transaksi.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Transaksi.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bayar_brg;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JTextField harga_brg;
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jumlah_brg;
    private javax.swing.JTextField kd_barang;
    private javax.swing.JTextField kembalian_brg;
    private javax.swing.JTextField nm_barang;
    private javax.swing.JTextField stokawal_brg;
    private javax.swing.JTextField subtotal_brg;
    private com.toedter.calendar.JDateChooser tgltransaksi;
    private javax.swing.JTextField total_brg;
    private javax.swing.JTextField txt_iduser;
    private javax.swing.JTextField txtnofaktur;
    // End of variables declaration//GEN-END:variables
}
