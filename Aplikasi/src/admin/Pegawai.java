package admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class Pegawai {
    public static Connection con;
    public static Statement stm;

    public static void main(String[] args) {
        boolean isAuthenticated = args.length > 0 && args[0].equals("authenticated");

        // Jika belum login, kembali ke halaman login
        if (!isAuthenticated) {
            JOptionPane.showMessageDialog(null, "Silakan login terlebih dahulu.", "Akses Ditolak", JOptionPane.WARNING_MESSAGE);
            LoginAdmin.main(null); // Kembali ke halaman login
            return;
        }

        // Membuat frame utama
        JFrame frame = new JFrame("Halaman Admin Aplikasi Kosan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Panel atas untuk tombol kembali dan logout
        JPanel topPanel = new JPanel(new BorderLayout());

        // Tombol Kembali
        JButton btnKembali = new JButton("Kembali");
        btnKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Membuka kembali halaman HomeAdmin
                HomeAdmin.main(new String[]{"authenticated"});
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
        
        // Panel kanan atas untuk tombol logout
        JPanel rightTopPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightTopPanel.add(logoutBtn);
        topPanel.add(rightTopPanel, BorderLayout.EAST);

        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Menambahkan panel atas ke frame
        frame.add(topPanel, BorderLayout.NORTH);

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

            // Menggunakan try-with-resources untuk koneksi database
            try (Connection con = DriverManager.getConnection(url, user, pass);
                 Statement stm = con.createStatement();
                 ResultSet resultSet = stm.executeQuery("SELECT * FROM pegawai")) {

                // Mengisi tabel dengan data dari database
                while (resultSet.next()) {
                    int idPegawai = resultSet.getInt("IdPegawai");
                    String nama = resultSet.getString("nama");
                    String noHp = resultSet.getString("noHp");
                    String email = resultSet.getString("Email");

                    Object[] row = {idPegawai, nama, noHp, email};
                    tableModel.addRow(row);
                }
            }

        } catch (SQLException e) {
            System.err.println("Koneksi gagal: " + e.getMessage());
        }

        // Menambahkan tabel ke panel dengan JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Menambahkan panel ke frame
        frame.add(panel, BorderLayout.CENTER);

        // Atur frame agar terlihat di tengah layar
        frame.setLocationRelativeTo(null);

        // Tampilkan frame
        frame.setVisible(true);
    }
}

