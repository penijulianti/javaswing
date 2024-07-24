/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package penjualan.interfc;
import java.util.List;
import penjualan.entity.barang;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public interface baranginterfc {
    barang insert (barang o) throws SQLException;
    void update (barang o) throws SQLException;
    void delete (String kode_barang) throws SQLException;
    List<barang> getAll() throws SQLException;
}

