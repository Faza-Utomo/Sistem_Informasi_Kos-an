package admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class Kamar {

    public static Connection con;
    public static Statement stm;

    public static void main(String args[]) {
        // Membuat frame utama
        JFrame frame = new JFrame("Home Aplikasi Kosan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Panel untuk konten utama
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Membuat model tabel
        String[] columnNames = {"ID Kamar", "No. Kamar", "Harga", "Jenis Kamar", "Ketersediaan"};
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
                    int idKamar = resultSet.getInt("idKamar");
                    String noKamar = resultSet.getString("noKamar");
                    int harga = resultSet.getInt("harga");
                    String jenisKamar = resultSet.getString("jenisKamar");
                    String ketersediaan = resultSet.getString("ketersediaan");

                    Object[] row = {idKamar, noKamar, harga, jenisKamar, ketersediaan};
                    tableModel.addRow(row);
                }
            }
            stm.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Koneksi gagal: " + e.getMessage());
        }

        // Menambahkan tabel ke panel dengan JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel bawah untuk tombol
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Tombol Tambah Data
        JButton btnTambahData = new JButton("Tambah Data");
        btnTambahData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Membuka halaman baru (inputKamar)
                inputKamar inputFrame = new inputKamar(frame); // Pass current frame
                inputFrame.setVisible(true);
                frame.dispose(); // Menutup frame utama
            }
        });

        // Menambahkan tombol ke panel bawah
        bottomPanel.add(btnTambahData);

        // Menambahkan panel bawah ke frame
        frame.add(panel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Atur frame agar terlihat di tengah layar
        frame.setLocationRelativeTo(null);

        // Tampilkan frame
        frame.setVisible(true);
    }
}
