package kosan;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class ListKamar {
    public static Connection con;
    public static Statement stm;

    public static void main(String[] args) {
        // Memeriksa apakah pengguna sudah login
        boolean isAuthenticated = args.length > 0 && args[0].equals("authenticated");

        // Jika belum login, kembali ke halaman login
        if (!isAuthenticated) {
            JOptionPane.showMessageDialog(null, "Silakan login terlebih dahulu.", "Akses Ditolak", JOptionPane.WARNING_MESSAGE);
            HalamanLogin.main(null); // Kembali ke halaman login
            return; // Keluar dari method ini
        }

        // Membuat frame utama
        JFrame frame = new JFrame("List Kamar - Aplikasi Kosan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Panel untuk konten utama
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Membuat model tabel
        String[] columnNames = {"Nomor Kamar", "Jenis Kamar", "Harga", "Ketersediaan"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        try {
            String url = "jdbc:mysql://localhost/sikosan_db";
            String user = "root";
            String pass = "";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            stm = con.createStatement();

            // Query untuk mendapatkan data kamar
            String query = "SELECT * FROM kamar";
            try (ResultSet resultSet = stm.executeQuery(query)) {
                // Mengisi tabel dengan data dari database
                while (resultSet.next()) {
                    String noKamar = resultSet.getString("noKamar");
                    String jenisKamar = resultSet.getString("jenisKamar");
                    String harga = resultSet.getString("harga");
                    String ketersediaan = resultSet.getString("ketersediaan");

                    Object[] row = {noKamar, jenisKamar, harga, ketersediaan};
                    tableModel.addRow(row);
                }
            }
            // Menutup koneksi
            stm.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Koneksi gagal: " + e.getMessage());
        }

        // Menambahkan tabel ke panel dengan JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Tambahkan panel ke frame
        frame.add(panel, BorderLayout.CENTER);

        // Panel untuk tombol atas
        JPanel topPanel = new JPanel(new BorderLayout());

        // Tombol Kembali
        JButton backBtn = new JButton("Kembali");
        backBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        backBtn.addActionListener(e -> {
            frame.dispose(); // Menutup frame saat ini
            HomeAplikasiKosan.main(new String[]{"authenticated"}); // Membuka halaman utama
        });
        topPanel.add(backBtn, BorderLayout.WEST);

        // Tombol Logout
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        logoutBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Apakah Anda yakin ingin logout?", "Konfirmasi Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                frame.dispose(); // Menutup frame saat ini
                HalamanLogin.main(null); // Membuka halaman login
            }
        });
        topPanel.add(logoutBtn, BorderLayout.EAST);

        // Tambahkan panel atas ke frame
        frame.add(topPanel, BorderLayout.NORTH);

        // Atur frame agar terlihat di tengah layar
        frame.setLocationRelativeTo(null);

        // Tampilkan frame
        frame.setVisible(true);
    }
}
