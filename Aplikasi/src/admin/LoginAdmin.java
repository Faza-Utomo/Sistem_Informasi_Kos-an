package kosan;

import admin.HomeAdmin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginAdmin {

    // Variabel untuk koneksi database
    private static final String URL = "jdbc:mysql://localhost:3306/sikosan_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        // Membuat frame utama
        JFrame frame = new JFrame("Halaman Login Aplikasi Pegawai");
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

        // Pada Action Listener tombol login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = gmailField.getText();
                String password = new String(passwordField.getPassword());

                if (email.endsWith("@gmail.com") && !password.isEmpty()) {
                    if (authenticateUser(email, password)) {
                        JOptionPane.showMessageDialog(frame, "Login Berhasil!", "Berhasil", JOptionPane.INFORMATION_MESSAGE);

                        // Pindah ke halaman HomeAplikasiKosan dengan parameter login berhasil
                        frame.dispose(); // Tutup halaman login
                        HomeAdmin.main(new String[]{"authenticated"}); // Kirim flag "authenticated"
                    } else {
                        JOptionPane.showMessageDialog(frame, "Gmail atau Password salah.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Format Gmail atau Password salah.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        // Menambah panel pada tengah Frame
        frame.add(panel, BorderLayout.CENTER);

        // Membuat Frame utama agar berada di tengah layar
        frame.setLocationRelativeTo(null);

        // Menampilkan Frame
        frame.setVisible(true);
    }

    // Method untuk menghubungkan ke database dan memverifikasi pengguna
    private static boolean authenticateUser(String email, String password) {
        boolean isAuthenticated = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Membuat koneksi ke database
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Query untuk memeriksa email dan password di tabel penghuni
            String query = "SELECT * FROM pegawai WHERE email = ? AND password = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);

            resultSet = statement.executeQuery();

            // Jika ditemukan hasil, berarti login berhasil
            if (resultSet.next()) {
                isAuthenticated = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada koneksi database.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Menutup koneksi dan statement
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return isAuthenticated;
    }
}
