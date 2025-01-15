package kosan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pembayaran {

    public static void main(String[] args) {
        String idReservasiDariSewa = args.length > 0 ? args[0] : null;

        // Membuat frame utama
        JFrame frame = new JFrame("Metode Pembayaran");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout(10, 10));

        // Panel utama untuk form
        JPanel mainPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Label dan input untuk IdPembayaran (hidden)
        JLabel lblIdPembayaran = new JLabel("Id Pembayaran (Hidden):");
        JTextField txtIdPembayaran = new JTextField();
        txtIdPembayaran.setEditable(false);
        txtIdPembayaran.setText("Auto-Generated"); // Simulasi hidden field

        // Label dan input untuk IdReservasi
        JLabel lblIdReservasi = new JLabel("Id Reservasi:");
        JTextField txtIdReservasi = new JTextField();
        txtIdReservasi.setEditable(false); // Membuat field tidak dapat diubah

        // Set nilai IdReservasi jika ada dari args
        if (idReservasiDariSewa != null) {
            txtIdReservasi.setText(idReservasiDariSewa);
        } else {
            JOptionPane.showMessageDialog(frame, "Id Reservasi tidak ditemukan.", "Error", JOptionPane.ERROR_MESSAGE);
            frame.dispose();
            return;
        }

        // Label dan input untuk tglPembayaran
        JLabel lblTglPembayaran = new JLabel("Tanggal Pembayaran:");
        JTextField txtTglPembayaran = new JTextField();
        txtTglPembayaran.setEditable(false);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        txtTglPembayaran.setText(now.format(formatter));

        // Label dan dropdown untuk metodePembayaran
        JLabel lblMetodePembayaran = new JLabel("Metode Pembayaran:");
        JComboBox<String> cmbMetodePembayaran = new JComboBox<>(new String[]{"Dana", "Ovo", "Gopay", "Transfer Bank"});

        // Tombol Simpan
        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.setPreferredSize(new Dimension(100, 30));
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idPembayaran = txtIdPembayaran.getText();
                String idReservasi = txtIdReservasi.getText();
                String tglPembayaran = txtTglPembayaran.getText();
                String metodePembayaran = (String) cmbMetodePembayaran.getSelectedItem();

                try {
                    String url = "jdbc:mysql://localhost/sikosan_db";
                    String user = "root";
                    String pass = "";
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, user, pass);

                    String insertQuery = "INSERT INTO pembayaran (idReservasi, tglPembayaran, metodePembayaran) VALUES (?, ?, ?)";
                    PreparedStatement pstmt = con.prepareStatement(insertQuery);
                    pstmt.setString(1, idReservasi);
                    pstmt.setString(2, tglPembayaran);
                    pstmt.setString(3, metodePembayaran);

                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(frame, "Pembayaran berhasil disimpan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);

                    pstmt.close();
                    con.close();

                    // Kembali ke halaman HomeAplikasiKosan
                    frame.dispose(); // Menutup frame saat ini
                    HomeAplikasiKosan.main(new String[]{"authenticated"});
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Gagal menyimpan data pembayaran: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Menambahkan komponen ke panel utama
        mainPanel.add(lblIdPembayaran);
        mainPanel.add(txtIdPembayaran);
        mainPanel.add(lblIdReservasi);
        mainPanel.add(txtIdReservasi);
        mainPanel.add(lblTglPembayaran);
        mainPanel.add(txtTglPembayaran);
        mainPanel.add(lblMetodePembayaran);
        mainPanel.add(cmbMetodePembayaran);

        // Panel atas untuk tombol navigasi
        JPanel topPanel = new JPanel(new BorderLayout());
        JButton btnKembali = new JButton("Kembali");
        btnKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HomeAplikasiKosan.main(new String[]{"authenticated"});
            }
        });
        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(frame, "Apakah Anda yakin ingin logout?", "Konfirmasi Logout", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    HalamanLogin.main(null);
                }
            }
        });

        topPanel.add(btnKembali, BorderLayout.WEST);
        topPanel.add(btnLogout, BorderLayout.EAST);

        // Panel bawah untuk tombol simpan
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnSimpan);

        // Menambahkan panel ke frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Atur frame agar terlihat di tengah layar
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
