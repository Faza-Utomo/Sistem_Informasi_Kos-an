package admin;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class Pegawai {
    public static Connection con;
    public static Statement stm;

    public static void main(String args[]) {
        
        boolean isAuthenticated = args.length > 0 && args[0].equals("authenticated");

        // Jika belum login, kembali ke halaman login
        if (!isAuthenticated) {
            JOptionPane.showMessageDialog(null, "Silakan login terlebih dahulu.", "Akses Ditolak", JOptionPane.WARNING_MESSAGE);
            LoginAdmin.main(null); // Kembali ke halaman login
            return; // Keluar dari method ini
        }
        
        // Membuat frame utama
        JFrame frame = new JFrame("Halaman Admin Aplikasi Kosan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Panel untuk konten utama
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Membuat model tabel
        String[] columnNames = {"ID Pegawai", "Nama Pegawai", "No. Handphone", "Email"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        try {
            String url = "jdbc:mysql://localhost/sikosan_db";
            String user = "root";
            String pass = "";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            stm = con.createStatement();
            //System.out.println("koneksi berhasil;");

            // Query untuk mendapatkan data kamar
            String query = "SELECT * FROM pegawai";
            // Mengisi tabel dengan data dari database
            try (ResultSet resultSet = stm.executeQuery(query)) {
                // Mengisi tabel dengan data dari database
                while (resultSet.next()) {
                    int idPegawai = resultSet.getInt("IdPegawai");
                    String nama = resultSet.getString("nama");
                    String noHp = resultSet.getString("noHp");
                    String email = resultSet.getString("Email");
                    
                    Object[] row = {idPegawai, nama, noHp, email};
                    tableModel.addRow(row);
                }
                // Menutup koneksi
            }
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

        // Atur frame agar terlihat di tengah layar
        frame.setLocationRelativeTo(null);

        // Tampilkan frame
        frame.setVisible(true);
    }
}
