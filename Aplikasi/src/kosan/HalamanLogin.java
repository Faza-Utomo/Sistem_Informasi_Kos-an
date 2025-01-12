package kosan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HalamanLogin {

    public static void main(String[] args) {
        // Membuat frame utama
        JFrame frame = new JFrame("Halaman Login Aplikasi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        
        // Panel untuk konten utama
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Membuat label untuk bagian Input Gmail
        JLabel gmailLabel = new JLabel("Gmail:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(gmailLabel, gbc);
        gmailLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // Membuat text field untuk menginputkan Gmail
        JTextField gmailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(gmailField, gbc);

        // Membuat label untuk bagian Input Password
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);
        
        // Membuat text field untuk menginputkan Password
        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        // Tombol untuk Tampilkan/Sembunyikan Password
        JButton showHideButton = new JButton("Tampilkan");
        gbc.gridx = 2; 
        panel.add(showHideButton, gbc);

        // Menambahkan action listener untuk tombol "Tampilan/Sembunyikan Password"
        showHideButton.addActionListener(new ActionListener() {
            private boolean isPasswordVisible = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (isPasswordVisible) {
                    passwordField.setEchoChar('*');
                    showHideButton.setText("Tampilkan");
                } else {
                    passwordField.setEchoChar((char) 0);
                    showHideButton.setText("Sembunyikan");
                }
                isPasswordVisible = !isPasswordVisible;
            }
        });

        // Tombol untuk login
        JButton loginButton = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(loginButton, gbc);

        // Menambah panel pada tengah Frame
        frame.add(panel, BorderLayout.CENTER);

        // Menambahkan Action Listener pada tombol login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = gmailField.getText();
                String password = new String(passwordField.getPassword());

                if (email.endsWith("@gmail.com") && !password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Login Berhasil!", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Gmail atau Password salah.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Membuat Frame utama agar berada di tengah layar
        frame.setLocationRelativeTo(null);

        // Menampilkan Frame
        frame.setVisible(true);
    }
}
