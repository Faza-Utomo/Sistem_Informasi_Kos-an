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

        boolean isAuthenticated = args.length > 0 && args[0].equals("authenticated");

        // Jika belum login, kembali ke halaman login
        if (!isAuthenticated) {
            JOptionPane.showMessageDialog(null, "Silakan login terlebih dahulu.", "Akses Ditolak", JOptionPane.WARNING_MESSAGE);
            LoginAdmin.main(null); // Kembali ke halaman login
            return; // Keluar dari method ini
        }

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

        // Panel atas untuk tombol kembali
        JPanel topPanel = new JPanel(new BorderLayout());

        // Tombol Kembali
        JButton btnKembali = new JButton("Kembali");
        btnKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomeAdmin.main(new String[]{"authenticated"}); // Kembali ke halaman HomeAdmin
                frame.dispose(); // Menutup frame utama
            }
        });

        // Tombol logout di kanan atas
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        logoutBtn.setPreferredSize(new Dimension(80, 30)); // Mengatur ukuran tombol logout

        logoutBtn.addActionListener((ActionEvent e) -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Apakah Anda yakin ingin logout?", "Konfirmasi Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                frame.dispose(); // Menutup frame saat ini
                LoginAdmin.main(null); // Membuka halaman login
            }
        });

        // Menambahkan tombol Kembali dan Logout ke panel atas
        JPanel leftTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftTopPanel.add(btnKembali);
        topPanel.add(leftTopPanel, BorderLayout.WEST);
        topPanel.add(logoutBtn, BorderLayout.EAST);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tombol Tambah Data
        JButton btnTambahData = new JButton("Tambah Data");
        btnTambahData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputKamar inputFrame = new inputKamar(frame); // Pass current frame
                inputFrame.setVisible(true);
                frame.dispose(); // Menutup frame utama
            }
        });

        // Menambahkan tombol Tambah Data ke panel bawah
        bottomPanel.add(btnTambahData);

        // Menambahkan panel ke frame
        frame.add(panel, BorderLayout.CENTER);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Atur frame agar terlihat di tengah layar
        frame.setLocationRelativeTo(null);

        // Tampilkan frame
        frame.setVisible(true);
    }
}
