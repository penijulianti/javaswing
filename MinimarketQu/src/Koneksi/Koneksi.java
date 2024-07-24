import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    
    public static Connection configDB() {
        try {
            String url = "jdbc:mysql://localhost/dbminimarket"; // Ganti sesuai dengan URL database Anda
            String user = "root"; // Ganti dengan username database Anda
            String password = ""; // Ganti dengan password database Anda
            
            // Membuat koneksi ke database
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Berhasil terkoneksi dengan database!");
            return conn;
            
        } catch (SQLException e) {
            System.out.println("Gagal terkoneksi dengan database: " + e.getMessage());
            return null;
        }
    }
}
