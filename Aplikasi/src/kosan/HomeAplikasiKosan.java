package kosan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeAplikasiKosan {

    public static void main(String[] args) {
        boolean isAuthenticated = args.length > 0 && args[0].equals("authenticated");

        // Jika belum login, kembali ke halaman login
        if (!isAuthenticated) {
            JOptionPane.showMessageDialog(null, "Silakan login terlebih dahulu.", "Akses Ditolak", JOptionPane.WARNING_MESSAGE);
            HalamanLogin.main(null); // Kembali ke halaman login
            return; // Keluar dari method ini
        }

        // Membuat frame utama
        JFrame frame = new JFrame("Home Aplikasi Kosan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Panel untuk konten utama
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Label judul
        JLabel titleLabel = new JLabel("Selamat Datang di Aplikasi Kosan");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Tambahkan label ke atas frame
        frame.add(titleLabel, BorderLayout.NORTH);

        // Tombol Lihat Kamar
        JButton lihatKamarBtn = new JButton("List Kamar");
        lihatKamarBtn.setFont(new Font("Arial", Font.PLAIN, 14));

        // Menambahkan action listener untuk tombol Lihat Kamar
        lihatKamarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Menutup frame saat ini
                ListKamar.main(new String[]{"authenticated"}); // Membuka halaman ListKamar
            }
        });

        // Tombol Sewa Kamar
        JButton sewaKamarBtn = new JButton("Sewa Kamar");
        sewaKamarBtn.setFont(new Font("Arial", Font.PLAIN, 14));

        // Menambahkan action listener untuk tombol Sewa Kamar
        sewaKamarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Menu Sewa Kamar belum tersedia.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Menambahkan tombol ke panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lihatKamarBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(sewaKamarBtn, gbc);

        // Tambahkan panel ke tengah frame
        frame.add(panel, BorderLayout.CENTER);

        // Tombol Logout
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        logoutBtn.setHorizontalAlignment(SwingConstants.LEFT);

        // Menambahkan action listener untuk tombol Logout
        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(frame, "Apakah Anda yakin ingin logout?", "Konfirmasi Logout", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    frame.dispose(); // Menutup frame saat ini
                    HalamanLogin.main(null); // Membuka halaman login
                }
            }
        });

        // Panel untuk Logout di bawah kiri
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(logoutBtn, BorderLayout.WEST);

        // Tambahkan panel bawah ke frame
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Atur frame agar terlihat di tengah layar
        frame.setLocationRelativeTo(null);

        // Tampilkan frame
        frame.setVisible(true);
    }
}
