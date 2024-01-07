/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import static GUI.Koneksi.getConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author Nikho
 */
public class HalamanTransaksi extends javax.swing.JFrame {
    private DefaultTableModel daftarTBL_Transaksi;
    private String SQL;
    
    public void Tampilkandata() {
        daftarTBL_Transaksi = new DefaultTableModel();
        daftarTBL_Transaksi.addColumn("ID Transaksi");
        daftarTBL_Transaksi.addColumn("Nomor KTP");
        daftarTBL_Transaksi.addColumn("Nama Penumpang");
        daftarTBL_Transaksi.addColumn("Tanggal Transaksi");
        daftarTBL_Transaksi.addColumn("Maskapai");
        daftarTBL_Transaksi.addColumn("Rute");
        daftarTBL_Transaksi.addColumn("Kelas");
        daftarTBL_Transaksi.addColumn("Tanggal Berangkat");
        daftarTBL_Transaksi.addColumn("Jam Keberangkatan");
        daftarTBL_Transaksi.addColumn("Jam Kedatangan");
        daftarTBL_Transaksi.addColumn("Harga Satuan");
        daftarTBL_Transaksi.addColumn("Jumlah Tiket");
        daftarTBL_Transaksi.addColumn("Total");


        
        tbTransaksi.setModel(daftarTBL_Transaksi);
        Connection conn = Koneksi.getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            SQL = "select * from transaksi";
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while (res.next()){
                daftarTBL_Transaksi.addRow(new Object[]{
                    res.getString("id_transaksi"),
                    res.getString("nomor_ktp"),
                    res.getString("nama_penumpang"),
                    res.getString("tanggal_transaksi"),
                    res.getString("maskapai"),
                    res.getString("rute"),
                    res.getString("kelas"),
                    res.getString("tanggal_berangkat"),
                    res.getString("jam_keberangkatan"),
                    res.getString("jam_kedatangan"),
                    res.getString("harga_satuan"),
                    res.getString("jumlah_tiket"),
                    res.getString("total") });
            }
            
        int i = daftarTBL_Transaksi.getRowCount();
        lbJumlahData.setText(""+i);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void lebarkolom(){
        TableColumn column;
        tbTransaksi.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF); 
        column = tbTransaksi.getColumnModel().getColumn(0); 
        column.setPreferredWidth(100);
        column = tbTransaksi.getColumnModel().getColumn(1); 
        column.setPreferredWidth(150); 
        column = tbTransaksi.getColumnModel().getColumn(2); 
        column.setPreferredWidth(150); 
        column = tbTransaksi.getColumnModel().getColumn(3); 
        column.setPreferredWidth(120);
        column = tbTransaksi.getColumnModel().getColumn(4); 
        column.setPreferredWidth(150); 
        column = tbTransaksi.getColumnModel().getColumn(5); 
        column.setPreferredWidth(150); 
        column = tbTransaksi.getColumnModel().getColumn(6); 
        column.setPreferredWidth(90); 
        column = tbTransaksi.getColumnModel().getColumn(7); 
        column.setPreferredWidth(120); 
        column = tbTransaksi.getColumnModel().getColumn(8); 
        column.setPreferredWidth(120);  
        column = tbTransaksi.getColumnModel().getColumn(9); 
        column.setPreferredWidth(100);  
        column = tbTransaksi.getColumnModel().getColumn(10); 
        column.setPreferredWidth(100);  
        column = tbTransaksi.getColumnModel().getColumn(11); 
        column.setPreferredWidth(100);  
        column = tbTransaksi.getColumnModel().getColumn(12); 
        column.setPreferredWidth(100);  
    }
    
    public void kosong(){
        tfIdTransaksi.setText("");
        tfNamaPenumpang.setText("");
        tfJamKeberangkatan.setText("");
        tfJamKedatangan.setText("");
        tfJumlahTiket.setText("");
        tfHargaTiket.setText("");
        tfTanggalTransaksi.setText("");
        tfTanggalBerangkat.setText("");
        tfTotal.setText("");
        tfCari.setText("");
        cbNomorKTP.setSelectedIndex(0);
        cbMaskapai.setSelectedIndex(0);
        cbRute.setSelectedIndex(0);
        cbKelas.setSelectedIndex(0);
    }
    
    private boolean isIdExists(String id) throws SQLException {
        String query = "SELECT COUNT(*) FROM transaksi WHERE id_transaksi = ?";
        Connection conn = Koneksi.getConnection();
        java.sql.PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, id);
        ResultSet rs = pst.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        return count > 0;
    }
    
    public void isiComboboxNomorKTP(){
        cbNomorKTP.removeAllItems();
        cbNomorKTP.addItem("-- Pilih --");
        try {
            String query = "SELECT nomor_ktp FROM penumpang";
            ResultSet resultSet = Koneksi.executeQuery(query);
            Set<String> uniqueNomorKTP = new HashSet<>();

            while (resultSet.next()) {
                String data = resultSet.getString("nomor_ktp");
                uniqueNomorKTP.add(data);
            }
            
            for (String maskapai : uniqueNomorKTP) {
                cbNomorKTP.addItem(maskapai);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void isiTextFieldNamaPenumpang(String selectedItem){
        tfJamKeberangkatan.setText("");
        try {
            String query = "SELECT nama_penumpang FROM penumpang WHERE nomor_ktp = ?";
            PreparedStatement preparedStatement = Koneksi.getConnection().prepareStatement(query);
            preparedStatement.setString(1, selectedItem);
           
            ResultSet resultSet = preparedStatement.executeQuery();
            Set<String> uniqueNamaPenumpang = new HashSet<>();

            while (resultSet.next()) {
                String data = resultSet.getString("nama_penumpang");
                uniqueNamaPenumpang.add(data);
            }

            StringBuilder namapenumpangText = new StringBuilder("");

            for (String uniqueNama : uniqueNamaPenumpang) {
                namapenumpangText.append(uniqueNama);
            }

            tfNamaPenumpang.setText(namapenumpangText.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void isiComboboxMaskapai(){
        cbRute.removeAllItems();
        cbRute.addItem("-- Pilih --");
        try {
            String query = "SELECT maskapai FROM penerbangan";
            ResultSet resultSet = Koneksi.executeQuery(query);
            Set<String> uniqueMaskapai = new HashSet<>();

            while (resultSet.next()) {
                String data = resultSet.getString("maskapai");
                uniqueMaskapai.add(data);
            }
            
            for (String maskapai : uniqueMaskapai) {
                cbMaskapai.addItem(maskapai);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void isiComboboxRute(String selectedItem){
        cbRute.removeAllItems();
        cbRute.addItem("-- Pilih --");
        
        try {
            String query = "SELECT rute FROM penerbangan WHERE maskapai = ?";
            PreparedStatement preparedStatement = Koneksi.getConnection().prepareStatement(query);
            preparedStatement.setString(1, selectedItem);

            ResultSet resultSet = preparedStatement.executeQuery();
            Set<String> uniqueRutes = new HashSet<>();

            while (resultSet.next()) {
                String data = resultSet.getString("rute");
                uniqueRutes.add(data);
            }

            for (String uniqueRute : uniqueRutes) {
                cbRute.addItem(uniqueRute);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void isiComboboxKelas(String selectedFirstItem, String selectedSecondItem){
        cbKelas.removeAllItems();
        cbKelas.addItem("-- Pilih --");
        
        try {
            String query = "SELECT kelas FROM penerbangan WHERE maskapai = ? AND rute = ?";
            PreparedStatement preparedStatement = Koneksi.getConnection().prepareStatement(query);
            preparedStatement.setString(1, selectedFirstItem);
            preparedStatement.setString(2, selectedSecondItem);

            ResultSet resultSet = preparedStatement.executeQuery();
            Set<String> uniqueKelas = new HashSet<>();

            while (resultSet.next()) {
                String data = resultSet.getString("kelas");
                uniqueKelas.add(data);
            }


            for (String uniqueKela : uniqueKelas) {
                cbKelas.addItem(uniqueKela);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void isiJamKeberangkatan(String selectedFirstItem, String selectedSecondItem, String selectedThirdItem){       
        tfJamKeberangkatan.setText("");
        try {
            String query = "SELECT jam_keberangkatan FROM penerbangan WHERE maskapai = ? AND rute = ? AND kelas = ?";
            PreparedStatement preparedStatement = Koneksi.getConnection().prepareStatement(query);
            preparedStatement.setString(1, selectedFirstItem);
            preparedStatement.setString(2, selectedSecondItem);
            preparedStatement.setString(3, selectedThirdItem);

            ResultSet resultSet = preparedStatement.executeQuery();
            Set<String> uniqueJamKeberangkatan = new HashSet<>();

            while (resultSet.next()) {
                String data = resultSet.getString("jam_keberangkatan");
                uniqueJamKeberangkatan.add(data);
            }

            StringBuilder jamKeberangkatanText = new StringBuilder("");

            for (String uniqueBerangkat : uniqueJamKeberangkatan) {
                jamKeberangkatanText.append(uniqueBerangkat);
            }

            tfJamKeberangkatan.setText(jamKeberangkatanText.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void isiJamKedatangan(String selectedFirstItem, String selectedSecondItem, String selectedThirdItem){       
        tfJamKedatangan.setText("");
        try {
            String query = "SELECT jam_kedatangan FROM penerbangan WHERE maskapai = ? AND rute = ? AND kelas = ?";
            PreparedStatement preparedStatement = Koneksi.getConnection().prepareStatement(query);
            preparedStatement.setString(1, selectedFirstItem);
            preparedStatement.setString(2, selectedSecondItem);
            preparedStatement.setString(3, selectedThirdItem);

            ResultSet resultSet = preparedStatement.executeQuery();
            Set<String> uniqueJamKedatangan = new HashSet<>();

            while (resultSet.next()) {
                String data = resultSet.getString("jam_kedatangan");
                uniqueJamKedatangan.add(data);
            }

            StringBuilder jamKedatanganText = new StringBuilder("");

            for (String uniqueBerangkat : uniqueJamKedatangan) {
                jamKedatanganText.append(uniqueBerangkat);
            }

            tfJamKedatangan.setText(jamKedatanganText.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void isiHargaTiket(String selectedFirstItem, String selectedSecondItem, String selectedThirdItem){
     tfHargaTiket.setText("");
        try {
            String query = "SELECT harga_tiket FROM penerbangan WHERE maskapai = ? AND rute = ? AND kelas = ?";
            PreparedStatement preparedStatement = Koneksi.getConnection().prepareStatement(query);
            preparedStatement.setString(1, selectedFirstItem);
            preparedStatement.setString(2, selectedSecondItem);
            preparedStatement.setString(3, selectedThirdItem);

            ResultSet resultSet = preparedStatement.executeQuery();
            Set<String> uniqueHargaTiket = new HashSet<>();

            while (resultSet.next()) {
                String data = resultSet.getString("harga_tiket");
                uniqueHargaTiket.add(data);
            }

            StringBuilder hargatiketText = new StringBuilder("");

            for (String uniqueHarga : uniqueHargaTiket) {
                hargatiketText.append(uniqueHarga);
            }

            tfHargaTiket.setText(hargatiketText.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }
    
    public void hitungTotal(){
        try {
            double harga = Double.parseDouble(tfHargaTiket.getText());
            double jumlah = Double.parseDouble(tfJumlahTiket.getText());
            int total = (int) (harga * jumlah);
            tfTotal.setText(String.valueOf(total));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang valid untuk Harga dan Jumlah.");
        }
    }
        
    
    
    /**
     * Creates new form HalamanDataBandara
     */
    public HalamanTransaksi() {
        initComponents();
        this.Tampilkandata();
        lebarkolom();
        kosong();
        isiComboboxNomorKTP();
        isiComboboxMaskapai();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfIdTransaksi = new javax.swing.JTextField();
        tfNamaPenumpang = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        tfCari = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lbJumlahData = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbMaskapai = new javax.swing.JComboBox<>();
        cbRute = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbKelas = new javax.swing.JComboBox<>();
        tfJumlahTiket = new javax.swing.JTextField();
        tfHargaTiket = new javax.swing.JTextField();
        tfTotal = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        tfTanggalTransaksi = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        tfJamKeberangkatan = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cbNomorKTP = new javax.swing.JComboBox<>();
        tfJamKedatangan = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        tfTanggalBerangkat = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbTransaksi = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("TRANSAKSI TIKET PESAWAT");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID Transaksi");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nomor KTP");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nama Penumpang");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Maskapai");

        jButton1.setText("Simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ubah");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Hapus");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Kosongkan");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        tfCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfCariKeyReleased(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Jumlah Data :");

        lbJumlahData.setForeground(new java.awt.Color(255, 255, 255));
        lbJumlahData.setText("0");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Cari");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Rute");

        cbMaskapai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --" }));
        cbMaskapai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbMaskapaiItemStateChanged(evt);
            }
        });

        cbRute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --" }));
        cbRute.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbRuteItemStateChanged(evt);
            }
        });
        cbRute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRuteActionPerformed(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Kelas");

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Jumlah Tiket");

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Harga Tiket");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Total");

        cbKelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --" }));
        cbKelas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbKelasItemStateChanged(evt);
            }
        });

        tfTotal.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jButton5.setText("Total");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Tanggal Transaksi");

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Berangkat/Datang");

        jLabel15.setText("-");

        cbNomorKTP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --" }));
        cbNomorKTP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbNomorKTPItemStateChanged(evt);
            }
        });

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Tanggal Berangkat");

        tbTransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        tbTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTransaksiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbTransaksi);

        jButton6.setText("Cetak");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Kembali");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel13)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfIdTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbNomorKTP, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNamaPenumpang, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfTanggalTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbMaskapai, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbRute, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel9)
                            .addComponent(jLabel16)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfTanggalBerangkat, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfJumlahTiket, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfHargaTiket, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tfJamKeberangkatan, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfJamKedatangan, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton5))))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton1)
                            .addGap(18, 18, 18)
                            .addComponent(jButton2)
                            .addGap(18, 18, 18)
                            .addComponent(jButton3)
                            .addGap(18, 18, 18)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton6)
                            .addGap(18, 18, 18)
                            .addComponent(jButton7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7)
                            .addGap(30, 30, 30)
                            .addComponent(tfCari, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(9, 9, 9)
                                .addComponent(lbJumlahData))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 976, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(tfIdTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbNomorKTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfNamaPenumpang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel14))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfTanggalTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbMaskapai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cbRute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(17, 17, 17)
                                    .addComponent(jLabel12))))
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfTanggalBerangkat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(tfJamKeberangkatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(tfJamKedatangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfHargaTiket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfJumlahTiket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5))))
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton1)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tfCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jLabel7))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(lbJumlahData))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        kosong();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {          
            if (tfIdTransaksi.getText().isEmpty() || tfNamaPenumpang.getText().isEmpty() || tfTanggalTransaksi.getText().isEmpty()
                || tfTanggalBerangkat.getText().isEmpty() || tfJamKeberangkatan.getText().isEmpty() || tfJamKedatangan.getText().isEmpty()
                || tfHargaTiket.getText().isEmpty() || tfJumlahTiket.getText().isEmpty() || tfTotal.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Semua kolom harus diisi");
                    return; 
                }
            
            if (isIdExists(tfIdTransaksi.getText())) {
                JOptionPane.showMessageDialog(this, "ID sudah terdaftar. Gunakan ID lain.");
                return;
            }
            
            String pilihancbNomorKTP = cbNomorKTP.getSelectedItem().toString();
            String pilihancbMaskapai = cbMaskapai.getSelectedItem().toString();
            String pilihancbRute = cbRute.getSelectedItem().toString();
            String pilihancbKelas = cbKelas.getSelectedItem().toString();
            
            String sql = "INSERT INTO transaksi VALUES"
                    + "('"+tfIdTransaksi.getText()+"','"+pilihancbNomorKTP+"','"+tfNamaPenumpang.getText()+"','"+tfTanggalTransaksi.getText()+"',"
                    + "'"+pilihancbMaskapai+"','"+pilihancbRute+"','"+pilihancbKelas+"',"
                    + "'"+tfTanggalBerangkat.getText()+"','"+tfJamKeberangkatan.getText()+"','"+tfJamKedatangan.getText()+"',"
                    + "'"+tfHargaTiket.getText()+"','"+tfJumlahTiket.getText()+"','"+tfTotal.getText()+"')";
            Connection conn = Koneksi.getConnection();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        Tampilkandata();
        lebarkolom();
        kosong();    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {          
            if (tfIdTransaksi.getText().isEmpty() || tfNamaPenumpang.getText().isEmpty() || tfTanggalTransaksi.getText().isEmpty()
                || tfTanggalBerangkat.getText().isEmpty() || tfJamKeberangkatan.getText().isEmpty() || tfJamKedatangan.getText().isEmpty()
                || tfHargaTiket.getText().isEmpty() || tfJumlahTiket.getText().isEmpty() || tfTotal.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Semua kolom harus diisi");
                    return; 
                }            
            
            String pilihancbNomorKTP = cbNomorKTP.getSelectedItem().toString();
            String pilihancbMaskapai = cbMaskapai.getSelectedItem().toString();
            String pilihancbRute = cbRute.getSelectedItem().toString();
            String pilihancbKelas = cbKelas.getSelectedItem().toString();
            
            String sql = "UPDATE transaksi SET nomor_ktp ="
                    + "'"+pilihancbNomorKTP+"', nama_penumpang = '"+tfNamaPenumpang.getText()+"', tanggal_transaksi = '"+tfTanggalTransaksi.getText()+"',"
                    + "maskapai = '"+pilihancbMaskapai+"', rute = '"+pilihancbRute+"', kelas = '"+pilihancbKelas+"',"
                    + "tanggal_berangkat = '"+tfTanggalBerangkat.getText()+"', jam_keberangkatan = '"+tfJamKeberangkatan.getText()+"', jam_kedatangan = '"+tfJamKedatangan.getText()+"',"
                    + "harga_satuan = '"+tfHargaTiket.getText()+"', jumlah_tiket = '"+tfJumlahTiket.getText()+"', total = '"+tfTotal.getText()+"'"
                    + "WHERE id_transaksi = '"+tfIdTransaksi.getText()+"'";
            Connection conn = Koneksi.getConnection();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        Tampilkandata();
        lebarkolom();
        kosong();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
        if (tfIdTransaksi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Silakan isi ID terlebih dahulu");
            return;
        }
            
        Object[] options = {"Iya", "Tidak"};
        int konfirmasi = JOptionPane.showOptionDialog(
                null,
                "Apakah Anda yakin ingin menghapus data?",
                "Konfirmasi Hapus Data",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
            if (konfirmasi == JOptionPane.YES_OPTION) {
                String sql = "DELETE FROM transaksi WHERE id_transaksi = '"+tfIdTransaksi.getText()+"'";
                Connection conn = Koneksi.getConnection();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        Tampilkandata();
        lebarkolom();
        kosong();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cbRuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRuteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbRuteActionPerformed

    private void cbMaskapaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbMaskapaiItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED){
            isiComboboxRute(cbMaskapai.getSelectedItem().toString());
        }
    }//GEN-LAST:event_cbMaskapaiItemStateChanged

    private void cbRuteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbRuteItemStateChanged
       if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED){
            isiComboboxKelas(cbMaskapai.getSelectedItem().toString(), cbRute.getSelectedItem().toString());
       }
    }//GEN-LAST:event_cbRuteItemStateChanged

    private void cbKelasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbKelasItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED){
            isiJamKeberangkatan(cbMaskapai.getSelectedItem().toString(), cbRute.getSelectedItem().toString(), cbKelas.getSelectedItem().toString());
            isiJamKedatangan(cbMaskapai.getSelectedItem().toString(), cbRute.getSelectedItem().toString(), cbKelas.getSelectedItem().toString());
            isiHargaTiket(cbMaskapai.getSelectedItem().toString(), cbRute.getSelectedItem().toString(), cbKelas.getSelectedItem().toString());
        }
    }//GEN-LAST:event_cbKelasItemStateChanged

    private void cbNomorKTPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbNomorKTPItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED){
            isiTextFieldNamaPenumpang(cbNomorKTP.getSelectedItem().toString());
        }
    }//GEN-LAST:event_cbNomorKTPItemStateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        hitungTotal();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tbTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTransaksiMouseClicked
        int baris = tbTransaksi.rowAtPoint(evt.getPoint());
        String id_transaksi =tbTransaksi.getValueAt(baris, 0).toString();
        tfIdTransaksi.setText(id_transaksi);
        Object nomor_ktp = tbTransaksi.getValueAt(baris, 1);
        cbNomorKTP.setSelectedItem(nomor_ktp.toString());
        String tanggal_transaksi =tbTransaksi.getValueAt(baris, 3).toString();
        tfTanggalTransaksi.setText(tanggal_transaksi);
        Object maskapai = tbTransaksi.getValueAt(baris, 4);
        cbMaskapai.setSelectedItem(maskapai.toString());
        Object rute = tbTransaksi.getValueAt(baris, 5);
        cbRute.setSelectedItem(rute.toString());
        Object kelas = tbTransaksi.getValueAt(baris, 6);
        cbKelas.setSelectedItem(kelas.toString());
        String tanggal_berangkat =tbTransaksi.getValueAt(baris, 7).toString();
        tfTanggalBerangkat.setText(tanggal_berangkat);
        String jumlah_tiket =tbTransaksi.getValueAt(baris, 11).toString();
        tfJumlahTiket.setText(jumlah_tiket);
        String total =tbTransaksi.getValueAt(baris, 12).toString();
        tfTotal.setText(total);
    }//GEN-LAST:event_tbTransaksiMouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        new HalamanUtama().show();
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void tfCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCariKeyReleased
        daftarTBL_Transaksi = new DefaultTableModel();
        daftarTBL_Transaksi.addColumn("ID Transaksi");
        daftarTBL_Transaksi.addColumn("Nomor KTP");
        daftarTBL_Transaksi.addColumn("Nama Penumpang");
        daftarTBL_Transaksi.addColumn("Tanggal Transaksi");
        daftarTBL_Transaksi.addColumn("Maskapai");
        daftarTBL_Transaksi.addColumn("Rute");
        daftarTBL_Transaksi.addColumn("Kelas");
        daftarTBL_Transaksi.addColumn("Tanggal Berangkat");
        daftarTBL_Transaksi.addColumn("Jam Keberangkatan");
        daftarTBL_Transaksi.addColumn("Jam Kedatangan");
        daftarTBL_Transaksi.addColumn("Harga Satuan");
        daftarTBL_Transaksi.addColumn("Jumlah Tiket");
        daftarTBL_Transaksi.addColumn("Total");


        
        tbTransaksi.setModel(daftarTBL_Transaksi);
        Connection conn = Koneksi.getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            SQL = "SELECT * FROM transaksi WHERE id_transaksi LIKE '%"+tfCari.getText()+"%'"
                  + "OR nomor_ktp LIKE '%"+tfCari.getText()+"%' OR nama_penumpang LIKE '%"+tfCari.getText()+"%'";
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while (res.next()){
                daftarTBL_Transaksi.addRow(new Object[]{
                    res.getString("id_transaksi"),
                    res.getString("nomor_ktp"),
                    res.getString("nama_penumpang"),
                    res.getString("tanggal_transaksi"),
                    res.getString("maskapai"),
                    res.getString("rute"),
                    res.getString("kelas"),
                    res.getString("tanggal_berangkat"),
                    res.getString("jam_keberangkatan"),
                    res.getString("jam_kedatangan"),
                    res.getString("harga_satuan"),
                    res.getString("jumlah_tiket"),
                    res.getString("total") });
            }
        lebarkolom();
           
        int i = daftarTBL_Transaksi.getRowCount();
        lbJumlahData.setText(""+i);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_tfCariKeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            String reportPath = "src/Report/reportTransaksi.jasper";
            Connection conn = getConnection();
            
            HashMap<String, Object> paramaters = new HashMap<>();
            JasperPrint print = JasperFillManager.fillReport(reportPath, paramaters, conn);
            JasperViewer viewer = new JasperViewer(print, false);
            viewer.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error displaying report", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(HalamanTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HalamanTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HalamanTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HalamanTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HalamanTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbKelas;
    private javax.swing.JComboBox<String> cbMaskapai;
    private javax.swing.JComboBox<String> cbNomorKTP;
    private javax.swing.JComboBox<String> cbRute;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbJumlahData;
    private javax.swing.JTable tbTransaksi;
    private javax.swing.JTextField tfCari;
    private javax.swing.JTextField tfHargaTiket;
    private javax.swing.JTextField tfIdTransaksi;
    private javax.swing.JTextField tfJamKeberangkatan;
    private javax.swing.JTextField tfJamKedatangan;
    private javax.swing.JTextField tfJumlahTiket;
    private javax.swing.JTextField tfNamaPenumpang;
    private javax.swing.JTextField tfTanggalBerangkat;
    private javax.swing.JTextField tfTanggalTransaksi;
    private javax.swing.JTextField tfTotal;
    // End of variables declaration//GEN-END:variables

}