package admin;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Kamar {
    public static Connection con;
    public static Statement stm;
    public static void main(String args[]){
        // Membuat frame utama
        JFrame frame = new JFrame("Home Aplikasi Kosan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Panel untuk konten utama
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
       
        DefaultListModel<String> roomListModel = new DefaultListModel<>();
        
        try {
            String url ="jdbc:mysql://localhost/sikosan_db";
            String user="root";
            String pass="";
            Class.forName("com.mysql.jdbc.Driver");
            con =DriverManager.getConnection(url,user,pass);
            stm = con.createStatement();
            //System.out.println("koneksi berhasil;");
            
            // Query untuk mendapatkan data kamar
            String query = "SELECT * FROM kamar";
            ResultSet resultSet = stm.executeQuery(query);

            // Mengisi list kamar dengan data dari database
            while (resultSet.next()) {
                String roomInfo = resultSet.getInt("idKamar") + "  " + resultSet.getString("noKamar") + "  " + resultSet.getInt("harga") + "  " + resultSet.getString("jenisKamar") + "  " + resultSet.getString("ketersediaan");
                roomListModel.addElement(roomInfo);
            }

            // Menutup koneksi
            resultSet.close();
            stm.close();
            con.close();
            
        } catch (Exception e) {
            System.err.println("koneksi gagal" +e.getMessage());
        }
        
        JList<String> roomList = new JList<>(roomListModel);
        roomList.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(roomList);
        scrollPane.setPreferredSize(new Dimension(300, 150));

        // Menambahkan tombol dan list ke panel
        panel.add(scrollPane);

        // Tambahkan panel ke tengah frame
        frame.add(panel, BorderLayout.CENTER);

        // Atur frame agar terlihat di tengah layar
        frame.setLocationRelativeTo(null);

        // Tampilkan frame
        frame.setVisible(true);
    }
}
