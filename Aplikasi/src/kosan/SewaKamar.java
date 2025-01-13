package kosan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class SewaKamar {    
    public static Connection con;
    public static Statement stm;

    public static void main(String[] args) {
        boolean isAuthenticated = args.length > 0 && args[0].equals("authenticated");

        // Jika belum login, kembali ke halaman login
        if (!isAuthenticated) {
            JOptionPane.showMessageDialog(null, "Silakan login terlebih dahulu.", "Akses Ditolak", JOptionPane.WARNING_MESSAGE);
            HalamanLogin.main(null); // Kembali ke halaman login
            return; // Keluar dari method ini
        }
        
        JFrame frame = new JFrame("Sewa Kamar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Form Sewa Kamar");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(titleLabel, BorderLayout.NORTH);

        JLabel idPenghuniLabel = new JLabel("ID Penghuni:");
        idPenghuniLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JComboBox<String> idPenghuniComboBox = new JComboBox<>();

        try {
            String url = "jdbc:mysql://localhost/sikosan_db";
            String user = "root";
            String pass = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            stm = con.createStatement();

            String query = "SELECT IdPenghuni FROM penghuni";
            ResultSet resultSet = stm.executeQuery(query);
            while (resultSet.next()) {
                idPenghuniComboBox.addItem(resultSet.getString("IdPenghuni"));
            }
            resultSet.close();
            stm.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(frame, "Gagal memuat data penghuni: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(idPenghuniLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(idPenghuniComboBox, gbc);

        JLabel kamarLabel = new JLabel("Pilih Kamar:");
        JComboBox<String> kamarComboBox = new JComboBox<>();

        try {
            String url = "jdbc:mysql://localhost/sikosan_db";
            String user = "root";
            String pass = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            stm = con.createStatement();

            String query = "SELECT idKamar FROM kamar";
            try (ResultSet resultSet = stm.executeQuery(query)) {
                while (resultSet.next()) {
                    String idKamar = resultSet.getString("idKamar");
                    kamarComboBox.addItem(" " + idKamar);
                }
            }
            stm.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(frame, "Gagal memuat data kamar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(kamarLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(kamarComboBox, gbc);

        JLabel lamaSewaLabel = new JLabel("Lama Sewa:");
        JTextField lamaSewaField = new JTextField(5);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lamaSewaLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(lamaSewaField, gbc);

        JLabel satuanSewaLabel = new JLabel("Satuan Sewa:");
        JComboBox<String> satuanSewaComboBox = new JComboBox<>(new String[]{"Bulan", "Tahun"});

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(satuanSewaLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(satuanSewaComboBox, gbc);

        JButton submitButton = new JButton("Sewa Kamar");
        submitButton.addActionListener((ActionEvent e) -> {
            String idPenghuni = (String) idPenghuniComboBox.getSelectedItem();
            String kamar = (String) kamarComboBox.getSelectedItem();
            String lama_sewa = lamaSewaField.getText();
            String satuan_sewa = (String) satuanSewaComboBox.getSelectedItem();
            
            if (idPenghuni == null || kamar == null || lama_sewa.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Harap lengkapi semua data!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost/sikosan_db", "root", "");
                stm = con.createStatement();
                
                String IdKamar = kamar.split(" --- ")[0].replace("No. ", "");
                
                String query = "INSERT INTO reservasi (IdPenghuni, IdKamar, lama_sewa, satuan_sewa) VALUES ('" + idPenghuni + "', '" + IdKamar + "', '" + lama_sewa + "', '" + satuan_sewa + "')";
                stm.executeUpdate(query);
                
                JOptionPane.showMessageDialog(frame, "Sewa kamar berhasil disimpan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                
                stm.close();
                con.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Gagal menyimpan data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(submitButton, gbc);

        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
