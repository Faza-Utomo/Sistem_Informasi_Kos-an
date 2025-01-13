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
        add(lblTitle, BorderLayout.NORTH);  // Menambahkan label ke bagian utara

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
            // Membuka halaman Kamar
            Kamar.main(null);
            dispose(); // Menutup halaman home admin
        });

        btnPegawai.addActionListener((ActionEvent e) -> {
            // Membuka halaman Pegawai
            Pegawai.main(null);  // Asumsi Pegawai memiliki method main()
            dispose(); // Menutup halaman home admin
        });

        btnPenghuni.addActionListener((ActionEvent e) -> {
            // Membuka halaman Penghuni
            Penghuni.main(null);  // Asumsi Penghuni memiliki method main()
            dispose(); // Menutup halaman home admin
        });

        btnReservasi.addActionListener((ActionEvent e) -> {
            // Membuka halaman Reservasi
            Reservasi.main(null);  // Asumsi Reservasi memiliki method main()
            dispose(); // Menutup halaman home admin
        });

        btnPembayaran.addActionListener((ActionEvent e) -> {
            // Membuka halaman Pembayaran
            Pembayaran.main(null);  // Asumsi Pembayaran memiliki method main()
            dispose(); // Menutup halaman home admin
        });

        // Menambahkan tombol ke panel
        panel.add(btnKamar, gbc);
        gbc.gridy++; // Pindah ke baris berikutnya
        panel.add(btnPegawai, gbc);
        gbc.gridy++; // Pindah ke baris berikutnya
        panel.add(btnPenghuni, gbc);
        gbc.gridy++; // Pindah ke baris berikutnya
        panel.add(btnReservasi, gbc);
        gbc.gridy++; // Pindah ke baris berikutnya
        panel.add(btnPembayaran, gbc);

        // Menambahkan panel ke frame
        add(panel, BorderLayout.CENTER);  // Menambahkan panel tombol ke bagian tengah

        // Menampilkan frame di tengah layar
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
        
        // Menjalankan halaman HomeAdmin
        SwingUtilities.invokeLater(() -> {
            HomeAdmin homeAdmin = new HomeAdmin();
            homeAdmin.setVisible(true);
        });
    }
}
