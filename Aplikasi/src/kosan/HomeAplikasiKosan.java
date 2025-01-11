package kosan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeAplikasiKosan {
    public static void main(String[] args) {
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
                ListKamar listKamar = new ListKamar(); // Membuka halaman ListKamar
                listKamar.setVisible(true);
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

        // Atur frame agar terlihat di tengah layar
        frame.setLocationRelativeTo(null);

        // Tampilkan frame
        frame.setVisible(true);
    }
}
