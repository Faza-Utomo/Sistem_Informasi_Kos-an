package kosan;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author ACER NITRO5
 */
public class RegisterAkun extends JFrame{
    public RegisterAkun(JFrame parentFrame) {

        // Konfigurasi frame untuk halaman input data kamar
        setTitle("Register Akun");
        setSize(800, 600); // Ukuran frame 800x600
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menambahkan judul di bagian atas tengah
        JLabel lblTitle = new JLabel("Register Akun", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblTitle, BorderLayout.NORTH);

        // Panel utama untuk input form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        // Menambahkan label dan text field untuk input data
        JLabel lblIdPenghuni = new JLabel("ID Penghuni:");
        JTextField txtIdPenghuni = new JTextField();
        txtIdPenghuni.setEditable(false);
        JLabel lblNama = new JLabel("Nama:");
        JTextField txtNama = new JTextField();
        JLabel lblNoHp = new JLabel("No. Handphone:");
        JTextField txtNoHp = new JTextField();
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        JLabel lblPassword = new JLabel("Password:");
        JTextField txtPassword = new JTextField();

        // Tombol Simpan
        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validasi input sebelum menyimpan
                if (txtNama.getText().isEmpty() ||
                        txtNoHp.getText().isEmpty() || txtEmail.getText().isEmpty() ||
                        txtPassword.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                try {
                    
                    String url = "jdbc:mysql://localhost/sikosan_db";
                    String user = "root";
                    String pass = "";
                    try (Connection con = DriverManager.getConnection(url, user, pass)) {
                        String query = "INSERT INTO penghuni (nama, noHp, email, password) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement pst = con.prepareStatement(query)) {
                            pst.setString(1, txtNama.getText());
                            pst.setString(2, txtNoHp.getText());
                            pst.setString(3, txtEmail.getText());
                            pst.setString(4, txtPassword.getText());
                            pst.executeUpdate();
                            
                            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
                        }
                    }
                    
                    dispose(); // Menutup frame input
                    HalamanLogin.main(null); // Membuka halaman Kamar
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Gagal menyimpan data: " + ex.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Menambahkan komponen ke form panel
        formPanel.add(lblIdPenghuni);
        formPanel.add(txtIdPenghuni);
        formPanel.add(lblNama);
        formPanel.add(txtNama);
        formPanel.add(lblNoHp);
        formPanel.add(txtNoHp);
        formPanel.add(lblEmail);
        formPanel.add(txtEmail);
        formPanel.add(lblPassword);
        formPanel.add(txtPassword);

        // Spacer untuk menyelaraskan tombol di tengah
        formPanel.add(new JLabel()); 
        formPanel.add(btnSimpan);

        // Menambahkan panel ke frame
        add(formPanel, BorderLayout.CENTER);

        // Atur frame agar terlihat di tengah layar
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {   
        SwingUtilities.invokeLater(() -> {
            RegisterAkun frame = new RegisterAkun(null); // Tidak ada parent frame di main
            frame.setVisible(true);
        });
    }
}
