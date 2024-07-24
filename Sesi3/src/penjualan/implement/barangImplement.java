/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package penjualan.implement;

import penjualan.interfc.baranginterfc;
import penjualan.entity.barang;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import penjualan.koneksi.koneksi;

/**
 *
 * @author ASUS
 */
public class barangImplement implements baranginterfc{
//    ---untuk insert ke databasе---
    public barang insert (barang o) throws SQLException {
        PreparedStatement st = koneksi.getConnection().prepareStatement("insert into barang values(?,?,?,?)");
        st.setString(1, o.getKode_barang());
        st.setString(2, o.getNama_barang());
        st.setString(3, o.getJml()+ " ");
        st.setString(4, o.getHrg()+ " ");
        st.executeUpdate();
        return o;
    }
    //------untuk update ke database--
    public void update (barang o) throws SQLException {
        PreparedStatement st = koneksi.getConnection().prepareStatement
        ("update barang set nama_barang=?, harga_barang=?,stok_barang=? where kode_barang=?");
        st.setString(1, o.getNama_barang());
        st.setString(2, o.getHrg());
        st.setString(3, o.getJml());
        st.setString(4, o.getKode_barang());
        st.executeUpdate();
    }

//untuk delete data berdasarkan kode_barang---
    public void delete (String kode_barang) throws SQLException {
        PreparedStatement st = koneksi.getConnection().prepareStatement
        ("delete from barang where kode_barang=?");
        st.setString(1, kode_barang);
        st.executeUpdate();
    }
    //---untuk select atau view databarang dari DB ke forn
    public List<barang> getAll() throws SQLException {
        Statement st=koneksi.getConnection().createStatement();
        ResultSet rs=st.executeQuery("select * from barang");
        List<barang>list=new ArrayList<barang>();
        while(rs.next()) {
            barang brg=new barang();
            brg.setKode_barang(rs.getString("kode_barang"));
            brg.setNama_barang(rs.getString("nama_barang"));
            brg.setJml(rs.getString("stok_barang"));
            brg.setHrg(rs.getString("harga_barang"));
            list.add(brg);
        }
    return list;
    }

}
