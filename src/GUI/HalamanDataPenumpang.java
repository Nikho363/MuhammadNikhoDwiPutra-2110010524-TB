/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;
import static GUI.Koneksi.getConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Nikho
 */
public final class HalamanDataPenumpang extends javax.swing.JFrame {

    private DefaultTableModel daftarTBL_Penumpang;
    private String SQL;
    
    public void Tampilkandata() {
        daftarTBL_Penumpang = new DefaultTableModel();
        daftarTBL_Penumpang.addColumn("Nomor KTP");
        daftarTBL_Penumpang.addColumn("Nama Penumpang");
        daftarTBL_Penumpang.addColumn("Jenis Kelamin");
        daftarTBL_Penumpang.addColumn("Tanggal Lahir");
        daftarTBL_Penumpang.addColumn("Alamat");
        daftarTBL_Penumpang.addColumn("Nomor Telepon");
        
        tbPenumpang.setModel(daftarTBL_Penumpang);
        Connection conn = Koneksi.getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            SQL = "select * from penumpang";
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while (res.next()){
                daftarTBL_Penumpang.addRow(new Object[]{
                    res.getString("nomor_ktp"),
                    res.getString("nama_penumpang"),        
                    res.getString("jenis_kelamin"),        
                    res.getString("tanggal_lahir"),        
                    res.getString("alamat"), 
                    res.getString("nomor_telepon") });
            }
            
        int i = daftarTBL_Penumpang.getRowCount();
        lbJumlahData.setText(""+i);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void kosong(){
        tfNomorKTP.setText("");
        tfNamaPemumpang.setText("");
        bgJenisKelamin.clearSelection();
        tfTanggalLahir.setText("");
        mmAlamat.setText("");
        tfNomorTelepon.setText("");
        tfCari.setText("");
    }
    
    private boolean isIdExists(String id) throws SQLException {
        String query = "SELECT COUNT(*) FROM penumpang WHERE nomor_ktp = ?";
        Connection conn = Koneksi.getConnection();
        java.sql.PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, id);
        ResultSet rs = pst.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        return count > 0;
    }
    
    /**
     * Creates new form HalamanDataPenumpang
     */
    public HalamanDataPenumpang() {
        initComponents();
        this.Tampilkandata();
        kosong();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgJenisKelamin = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfNomorKTP = new javax.swing.JTextField();
        tfNamaPemumpang = new javax.swing.JTextField();
        btSimpan = new javax.swing.JButton();
        btUbah = new javax.swing.JButton();
        btHapus = new javax.swing.JButton();
        btKosong = new javax.swing.JButton();
        tfCari = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPenumpang = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        lbJumlahData = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        mmAlamat = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        rbLakiLaki = new javax.swing.JRadioButton();
        rbPerempuan = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        tfTanggalLahir = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        tfNomorTelepon = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 102, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DATA PENUMPANG");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nomor KTP");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama Penumpang");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Alamat");

        btSimpan.setText("Simpan");
        btSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSimpanActionPerformed(evt);
            }
        });

        btUbah.setText("Ubah");
        btUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUbahActionPerformed(evt);
            }
        });

        btHapus.setText("Hapus");
        btHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusActionPerformed(evt);
            }
        });

        btKosong.setText("Kosongkan");
        btKosong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btKosongActionPerformed(evt);
            }
        });

        tfCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfCariKeyReleased(evt);
            }
        });

        tbPenumpang.setModel(new javax.swing.table.DefaultTableModel(
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
        tbPenumpang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPenumpangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbPenumpang);

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Jumlah Data :");

        lbJumlahData.setForeground(new java.awt.Color(255, 255, 255));
        lbJumlahData.setText("0");

        mmAlamat.setColumns(20);
        mmAlamat.setLineWrap(true);
        mmAlamat.setRows(3);
        jScrollPane2.setViewportView(mmAlamat);

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Cari");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Jenis Kelamin");

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));

        bgJenisKelamin.add(rbLakiLaki);
        rbLakiLaki.setForeground(new java.awt.Color(255, 255, 255));
        rbLakiLaki.setText("Laki-Laki");
        rbLakiLaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbLakiLakiActionPerformed(evt);
            }
        });

        bgJenisKelamin.add(rbPerempuan);
        rbPerempuan.setForeground(new java.awt.Color(255, 255, 255));
        rbPerempuan.setText("Perempuan");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbLakiLaki)
                .addGap(18, 18, 18)
                .addComponent(rbPerempuan)
                .addContainerGap(145, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbLakiLaki)
                    .addComponent(rbPerempuan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Tanggal Lahir");

        jButton1.setText("Cetak");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Kembali");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nomor Telepon");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btSimpan)
                                .addGap(18, 18, 18)
                                .addComponent(btUbah)
                                .addGap(18, 18, 18)
                                .addComponent(btHapus)
                                .addGap(18, 18, 18)
                                .addComponent(btKosong)
                                .addGap(19, 19, 19)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(tfCari, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(12, 12, 12)
                                    .addComponent(lbJumlahData))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(25, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfNamaPemumpang, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(tfNomorKTP, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(57, 57, 57)
                                        .addComponent(jLabel4)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(156, 156, 156))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(247, 247, 247)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfNomorTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(206, 206, 206))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfNomorKTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfNamaPemumpang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel8))
                    .addComponent(tfNomorTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel9))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(tfTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btSimpan)
                            .addComponent(btUbah)
                            .addComponent(btHapus)
                            .addComponent(btKosong)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(lbJumlahData)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel7))
                    .addComponent(tfCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btKosongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btKosongActionPerformed
        kosong();
    }//GEN-LAST:event_btKosongActionPerformed

    private void tbPenumpangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPenumpangMouseClicked
        int baris = tbPenumpang.rowAtPoint(evt.getPoint());
        String nomor_ktp =tbPenumpang.getValueAt(baris, 0).toString();
        tfNomorKTP.setText(nomor_ktp);
        String nama_penumpang =tbPenumpang.getValueAt(baris, 1).toString();
        tfNamaPemumpang.setText(nama_penumpang);
        
        String jenisKelamin = tbPenumpang.getValueAt(baris, 2).toString();
        if (jenisKelamin.equals("Laki-Laki")) {
            rbLakiLaki.setSelected(true);
            rbPerempuan.setSelected(false);
        } else if (jenisKelamin.equals("Perempuan")) {
            rbLakiLaki.setSelected(false);
            rbPerempuan.setSelected(true);
        } else {
            rbLakiLaki.setSelected(false);
            rbPerempuan.setSelected(false);
        }
        
        String tanggal_lahir =tbPenumpang.getValueAt(baris, 3).toString();
        tfTanggalLahir.setText(tanggal_lahir);
        String alamat =tbPenumpang.getValueAt(baris, 4).toString();
        mmAlamat.setText(alamat);
        String nomor_telepon =tbPenumpang.getValueAt(baris, 5).toString();
        tfNomorTelepon.setText(nomor_telepon);
    }//GEN-LAST:event_tbPenumpangMouseClicked

    private void btSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSimpanActionPerformed
        try {          
            String pilihanJK = "";
            if (rbLakiLaki.isSelected()) {
                pilihanJK = rbLakiLaki.getText();
            } else if (rbPerempuan.isSelected()) {
                pilihanJK = rbPerempuan.getText();
            }
            
            if (tfNomorKTP.getText().isEmpty() || tfNamaPemumpang.getText().isEmpty() || pilihanJK.isEmpty() ||
                tfTanggalLahir.getText().isEmpty() || mmAlamat.getText().isEmpty() || tfNomorTelepon.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Semua kolom harus diisi");
                    return; 
                }
            
            if (isIdExists(tfNomorKTP.getText())) {
                JOptionPane.showMessageDialog(this, "ID sudah terdaftar. Gunakan ID lain.");
                return;
            }
            
            String sql = "INSERT INTO penumpang VALUES"
                    + "('"+tfNomorKTP.getText()+"','"+tfNamaPemumpang.getText()+"','"+pilihanJK+"',"
                    + "'"+tfTanggalLahir.getText()+"','"+mmAlamat.getText()+"','"+tfNomorTelepon.getText()+"')";
            Connection conn = Koneksi.getConnection();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        Tampilkandata();
        kosong();
    }//GEN-LAST:event_btSimpanActionPerformed

    private void btUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUbahActionPerformed
        try {          
            String pilihanJK = "";
            if (rbLakiLaki.isSelected()) {
                pilihanJK = rbLakiLaki.getText();
            } else if (rbPerempuan.isSelected()) {
                pilihanJK = rbPerempuan.getText();
            }
            
            if (tfNomorKTP.getText().isEmpty() || tfNamaPemumpang.getText().isEmpty() || pilihanJK.isEmpty() ||
                tfTanggalLahir.getText().isEmpty() || mmAlamat.getText().isEmpty() || tfNomorTelepon.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Semua kolom harus diisi");
                    return; 
                }
            
            String sql = "UPDATE penumpang SET nama_penumpang ="
                    + "'"+tfNamaPemumpang.getText()+"', jenis_kelamin = '"+pilihanJK+"', tanggal_lahir = '"+tfTanggalLahir.getText()+"',"
                    + "alamat = '"+mmAlamat.getText()+"', nomor_telepon = '"+tfNomorTelepon.getText()+"'"
                    + "WHERE nomor_ktp = '"+tfNomorKTP.getText()+"'";
            Connection conn = Koneksi.getConnection();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        Tampilkandata();
        kosong();
    }//GEN-LAST:event_btUbahActionPerformed

    private void btHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapusActionPerformed
        try {
        if (tfNomorKTP.getText().isEmpty()) {
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
                String sql = "DELETE FROM penumpang WHERE nomor_ktp='"+tfNomorKTP.getText()+"'";
                Connection conn = Koneksi.getConnection();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        Tampilkandata();
        kosong();
    }//GEN-LAST:event_btHapusActionPerformed

    private void rbLakiLakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbLakiLakiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbLakiLakiActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new HalamanUtama().show();
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tfCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCariKeyReleased
        daftarTBL_Penumpang = new DefaultTableModel();
        daftarTBL_Penumpang.addColumn("Nomor KTP");
        daftarTBL_Penumpang.addColumn("Nama Penumpang");
        daftarTBL_Penumpang.addColumn("Jenis Kelamin");
        daftarTBL_Penumpang.addColumn("Tanggal Lahir");
        daftarTBL_Penumpang.addColumn("Alamat");
        daftarTBL_Penumpang.addColumn("Nomor Telepon");
        
        tbPenumpang.setModel(daftarTBL_Penumpang);
        Connection conn = Koneksi.getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            SQL = "SELECT * FROM penumpang WHERE nomor_ktp LIKE '%"+tfCari.getText()+"%'"
                  + "OR nama_penumpang LIKE '%"+tfCari.getText()+"%'";
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while (res.next()){
                daftarTBL_Penumpang.addRow(new Object[]{
                    res.getString("nomor_ktp"),
                    res.getString("nama_penumpang"),        
                    res.getString("jenis_kelamin"),        
                    res.getString("tanggal_lahir"),        
                    res.getString("alamat"), 
                    res.getString("nomor_telepon") });
            }
            
        int i = daftarTBL_Penumpang.getRowCount();
        lbJumlahData.setText(""+i);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_tfCariKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String reportPath = "src/Report/reportPenumpang.jasper";
            Connection conn = getConnection();
            
            HashMap<String, Object> paramaters = new HashMap<>();
            JasperPrint print = JasperFillManager.fillReport(reportPath, paramaters, conn);
            JasperViewer viewer = new JasperViewer(print, false);
            viewer.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error displaying report", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
                new HalamanDataPenumpang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgJenisKelamin;
    private javax.swing.JButton btHapus;
    private javax.swing.JButton btKosong;
    private javax.swing.JButton btSimpan;
    private javax.swing.JButton btUbah;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbJumlahData;
    private javax.swing.JTextArea mmAlamat;
    private javax.swing.JRadioButton rbLakiLaki;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.JTable tbPenumpang;
    private javax.swing.JTextField tfCari;
    private javax.swing.JTextField tfNamaPemumpang;
    private javax.swing.JTextField tfNomorKTP;
    private javax.swing.JTextField tfNomorTelepon;
    private javax.swing.JTextField tfTanggalLahir;
    // End of variables declaration//GEN-END:variables
}
