package admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class inputKamar extends JFrame {
    public inputKamar(JFrame parentFrame) {

        // Konfigurasi frame untuk halaman input data kamar
        setTitle("Form Input Data Kamar");
        setSize(800, 600); // Ukuran frame 800x600
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menambahkan judul di bagian atas tengah
        JLabel lblTitle = new JLabel("Form Input Data Kamar", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblTitle, BorderLayout.NORTH);

        // Panel utama untuk input form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        // Menambahkan label dan text field untuk input data
        JLabel lblIdKamar = new JLabel("ID Kamar:");
        JTextField txtIdKamar = new JTextField();
        JLabel lblNoKamar = new JLabel("No. Kamar:");
        JTextField txtNoKamar = new JTextField();
        JLabel lblHarga = new JLabel("Harga:");
        JTextField txtHarga = new JTextField();
        JLabel lblJenisKamar = new JLabel("Jenis Kamar:");
        JTextField txtJenisKamar = new JTextField();
        JLabel lblKetersediaan = new JLabel("Ketersediaan:");
        JTextField txtKetersediaan = new JTextField();

        // Tombol Simpan
        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.addActionListener((ActionEvent e) -> {
            // Validasi input sebelum menyimpan
            if (txtIdKamar.getText().isEmpty() || txtNoKamar.getText().isEmpty() ||
                    txtHarga.getText().isEmpty() || txtJenisKamar.getText().isEmpty() ||
                    txtKetersediaan.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            try {
                int idKamar = Integer.parseInt(txtIdKamar.getText());
                int harga = Integer.parseInt(txtHarga.getText());
                
                String url = "jdbc:mysql://localhost/sikosan_db";
                String user = "root";
                String pass = "";
                try (Connection con = DriverManager.getConnection(url, user, pass)) {
                    String query = "INSERT INTO kamar (idKamar, noKamar, harga, jenisKamar, ketersediaan) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement pst = con.prepareStatement(query)) {
                        pst.setInt(1, idKamar);
                        pst.setString(2, txtNoKamar.getText());
                        pst.setInt(3, harga);
                        pst.setString(4, txtJenisKamar.getText());
                        pst.setString(5, txtKetersediaan.getText());
                        pst.executeUpdate();
                        
                        JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
                    }
                }
                
                dispose(); // Menutup frame input
                Kamar.main(null); // Membuka halaman Kamar
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID Kamar dan Harga harus berupa angka!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Gagal menyimpan data: " + ex.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Menambahkan komponen ke form panel
        formPanel.add(lblIdKamar);
        formPanel.add(txtIdKamar);
        formPanel.add(lblNoKamar);
        formPanel.add(txtNoKamar);
        formPanel.add(lblHarga);
        formPanel.add(txtHarga);
        formPanel.add(lblJenisKamar);
        formPanel.add(txtJenisKamar);
        formPanel.add(lblKetersediaan);
        formPanel.add(txtKetersediaan);

        // Spacer untuk menyelaraskan tombol di tengah
        formPanel.add(new JLabel()); 
        formPanel.add(btnSimpan);

        // Menambahkan panel ke frame
        add(formPanel, BorderLayout.CENTER);

        // Atur frame agar terlihat di tengah layar
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        
        boolean isAuthenticated = args.length > 0 && args[0].equals("authenticated");

        // Jika belum login, kembali ke halaman login
        if (!isAuthenticated) {
            JOptionPane.showMessageDialog(null, "Silakan login terlebih dahulu.", "Akses Ditolak", JOptionPane.WARNING_MESSAGE);
            LoginAdmin.main(null); // Kembali ke halaman login
            return; // Keluar dari method ini
        }
        
        SwingUtilities.invokeLater(() -> {
            inputKamar frame = new inputKamar(null); // Tidak ada parent frame di main
            frame.setVisible(true);
        });
    }
}
