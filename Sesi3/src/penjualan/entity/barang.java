/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package penjualan.entity;

/**
 *
 * @author ASUS
 */
public class barang {
    private String kode_barang;
    private String nama_barang;
    private int stok_barang;
    private double harga_barang;
    private String jml = String.valueOf(stok_barang);
    private String hrg = String.valueOf(harga_barang);

    /**
     * @return the kode_barang
     */
    public String getKode_barang() {
        return kode_barang;
    }

    /**
     * @param kode_barang the kode_barang to set
     */
    public void setKode_barang(String kode_barang) {
        this.kode_barang = kode_barang;
    }

    /**
     * @return the nama_barang
     */
    public String getNama_barang() {
        return nama_barang;
    }

    /**
     * @param nama_barang the nama_barang to set
     */
    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    /**
     * @return the stok_barang
     */
    public int getStok_barang() {
        return stok_barang;
    }

    /**
     * @param stok_barang the stok_barang to set
     */
    public void setStok_barang(int stok_barang) {
        this.stok_barang = stok_barang;
    }

    /**
     * @return the harga_barang
     */
    public double getHarga_barang() {
        return harga_barang;
    }

    /**
     * @param harga_barang the harga_barang to set
     */
    public void setHarga_barang(double harga_barang) {
        this.harga_barang = harga_barang;
    }

    /**
     * @return the jml
     */
    public String getJml() {
        return jml;
    }

    /**
     * @param jml the jml to set
     */
    public void setJml(String jml) {
        this.jml = jml;
    }

    /**
     * @return the hrg
     */
    public String getHrg() {
        return hrg;
    }

    /**
     * @param hrg the hrg to set
     */
    public void setHrg(String hrg) {
        this.hrg = hrg;
    }
    
}
