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

        // Label judul
        JLabel titleLabel = new JLabel("Selamat Datang di Aplikasi Kosan");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Tambahkan label ke atas frame
        frame.add(titleLabel, BorderLayout.NORTH);

        // Tombol Sewa Kamar
        JButton sewabtn = new JButton("Sewa Kamar");
        sewabtn.setFont(new Font("Arial", Font.PLAIN, 14));

        // Menambahkan action listener untuk tombol
        sewabtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Menu Sewa Kamar belum tersedia.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // List Kamar
        DefaultListModel<String> roomListModel = new DefaultListModel<>();
        roomListModel.addElement("Kamar 1 - Rp 500.000/bulan");
        roomListModel.addElement("Kamar 2 - Rp 600.000/bulan");
        roomListModel.addElement("Kamar 3 - Rp 700.000/bulan");
        

        JList<String> roomList = new JList<>(roomListModel);
        roomList.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(roomList);
        scrollPane.setPreferredSize(new Dimension(300, 150));

        // Menambahkan tombol dan list ke panel
        panel.add(sewabtn);
        panel.add(scrollPane);

        // Tambahkan panel ke tengah frame
        frame.add(panel, BorderLayout.CENTER);

        // Atur frame agar terlihat di tengah layar
        frame.setLocationRelativeTo(null);

        // Tampilkan frame
        frame.setVisible(true);
    }
}

