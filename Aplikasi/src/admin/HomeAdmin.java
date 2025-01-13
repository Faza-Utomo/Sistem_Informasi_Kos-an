package admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class HomeAdmin extends JFrame {

    public HomeAdmin() {
        // Mengatur tampilan frame
        setTitle("Home Admin");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // Menggunakan BorderLayout

        // Menambahkan label judul di bagian atas
        JLabel lblTitle = new JLabel("Home Admin", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));  // Memberikan margin atas dan bawah

        // Tombol logout di kanan atas
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        logoutBtn.setPreferredSize(new Dimension(80, 30)); // Mengatur ukuran tombol logout

        // Menambahkan action listener untuk tombol Logout
        logoutBtn.addActionListener((ActionEvent e) -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin logout?", "Konfirmasi Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose(); // Menutup frame saat ini
                LoginAdmin.main(null); // Membuka halaman login
            }
        });

        // Panel untuk menempatkan tombol logout di kanan atas
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(lblTitle, BorderLayout.CENTER); // Menambahkan judul di tengah
        topPanel.add(logoutBtn, BorderLayout.EAST);  // Menambahkan tombol logout di kanan atas
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Memberikan margin pada panel atas

        add(topPanel, BorderLayout.NORTH); // Menambahkan topPanel ke bagian atas

        // Panel untuk menampung tombol-tombol
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());  // Menggunakan GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Menentukan posisi tombol di kolom 0
        gbc.gridy = 0; // Menentukan posisi tombol di baris 0
        gbc.insets = new Insets(10, 10, 10, 10); // Menambahkan margin antar tombol

        // Membuat tombol-tombol untuk masing-masing halaman
        JButton btnKamar = new JButton("Kamar");
        JButton btnPegawai = new JButton("Pegawai");
        JButton btnPenghuni = new JButton("Penghuni");
        JButton btnReservasi = new JButton("Reservasi");
        JButton btnPembayaran = new JButton("Pembayaran");

        // Menentukan ukuran tombol agar tidak terlalu besar
        Dimension buttonSize = new Dimension(200, 40);
        btnKamar.setPreferredSize(buttonSize);
        btnPegawai.setPreferredSize(buttonSize);
        btnPenghuni.setPreferredSize(buttonSize);
        btnReservasi.setPreferredSize(buttonSize);
        btnPembayaran.setPreferredSize(buttonSize);

        // Menambahkan ActionListener untuk setiap tombol
        btnKamar.addActionListener((ActionEvent e) -> {
            Kamar.main(new String[]{"authenticated"});
            dispose();
        });

        btnPegawai.addActionListener((ActionEvent e) -> {
            Pegawai.main(new String[]{"authenticated"});
            dispose();
        });

        btnPenghuni.addActionListener((ActionEvent e) -> {
            Penghuni.main(new String[]{"authenticated"});
            dispose();
        });

        btnReservasi.addActionListener((ActionEvent e) -> {
            Reservasi.main(new String[]{"authenticated"});
            dispose();
        });

        btnPembayaran.addActionListener((ActionEvent e) -> {
            Pembayaran.main(new String[]{"authenticated"});
            dispose();
        });

        // Menambahkan tombol ke panel
        panel.add(btnKamar, gbc);
        gbc.gridy++;
        panel.add(btnPegawai, gbc);
        gbc.gridy++;
        panel.add(btnPenghuni, gbc);
        gbc.gridy++;
        panel.add(btnReservasi, gbc);
        gbc.gridy++;
        panel.add(btnPembayaran, gbc);

        // Menambahkan panel ke frame
        add(panel, BorderLayout.CENTER);

        // Menampilkan frame di tengah layar
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        boolean isAuthenticated = args.length > 0 && args[0].equals("authenticated");

        if (!isAuthenticated) {
            JOptionPane.showMessageDialog(null, "Silakan login terlebih dahulu.", "Akses Ditolak", JOptionPane.WARNING_MESSAGE);
            LoginAdmin.main(null);
            return;
        }

        SwingUtilities.invokeLater(() -> {
            HomeAdmin homeAdmin = new HomeAdmin();
            homeAdmin.setVisible(true);
        });
    }
}
