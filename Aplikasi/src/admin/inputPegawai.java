package admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class inputPegawai extends JFrame {
    public inputPegawai(JFrame parentFrame) {

        // Konfigurasi frame untuk halaman input data kamar
        setTitle("Form Input Data Pegawai");
        setSize(800, 600); // Ukuran frame 800x600
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menambahkan judul di bagian atas tengah
        JLabel lblTitle = new JLabel("Form Input Data Pegawai", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblTitle, BorderLayout.NORTH);

        // Panel utama untuk input form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        // Menambahkan label dan text field untuk input data
        JLabel lblIdPegawai = new JLabel("ID Pegawai:");
        JTextField txtIdPegawai = new JTextField();
        JLabel lblNama = new JLabel("Nama:");
        JTextField txtNama = new JTextField();
        JLabel lblNoHp = new JLabel("No. Handphone:");
        JTextField txtNoHp = new JTextField();
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        JLabel lblPassword = new JLabel("Password:");
        JTextField txtPassword = new JTextField();

        // Tombol Simpan
        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validasi input sebelum menyimpan
                if (txtIdPegawai.getText().isEmpty() || txtNama.getText().isEmpty() ||
                        txtNoHp.getText().isEmpty() || txtEmail.getText().isEmpty() ||
                        txtPassword.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                try {
                    int idPegawai = Integer.parseInt(txtIdPegawai.getText());
                    
                    String url = "jdbc:mysql://localhost/sikosan_db";
                    String user = "root";
                    String pass = "";
                    try (Connection con = DriverManager.getConnection(url, user, pass)) {
                        String query = "INSERT INTO pegawai (idpegawai, nama, noHp, email, password) VALUES (?, ?, ?, ?, ?)";
                        try (PreparedStatement pst = con.prepareStatement(query)) {
                            pst.setInt(1, idPegawai);
                            pst.setString(2, txtNama.getText());
                            pst.setString(3, txtNoHp.getText());
                            pst.setString(4, txtEmail.getText());
                            pst.setString(5, txtPassword.getText());
                            pst.executeUpdate();
                            
                            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
                        }
                    }
                    
                    dispose(); // Menutup frame input
                    Pegawai.main(null); // Membuka halaman Kamar
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID Pegawai harus berupa angka!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Gagal menyimpan data: " + ex.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Menambahkan komponen ke form panel
        formPanel.add(lblIdPegawai);
        formPanel.add(txtIdPegawai);
        formPanel.add(lblNama);
        formPanel.add(txtNama);
        formPanel.add(lblNoHp);
        formPanel.add(txtNoHp);
        formPanel.add(lblEmail);
        formPanel.add(txtEmail);
        formPanel.add(lblPassword);
        formPanel.add(txtPassword);

        // Spacer untuk menyelaraskan tombol di tengah
        formPanel.add(new JLabel()); 
        formPanel.add(btnSimpan);

        // Menambahkan panel ke frame
        add(formPanel, BorderLayout.CENTER);

        // Atur frame agar terlihat di tengah layar
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            inputPegawai frame = new inputPegawai(null); // Tidak ada parent frame di main
            frame.setVisible(true);
        });
    }
}
