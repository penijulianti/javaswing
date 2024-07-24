/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package penjualan.entity;

/**
 *
 * @author ASUS
 */
public class pelanggan {
    private int id_pelanggan;
    private String nama;
    private String jeniskel;
    private String alamat;
    private String notelp;
    private String idpel = String.valueOf(id_pelanggan);

    /**
     * @return the id_pelanggan
     */
    public int getId_pelanggan() {
        return id_pelanggan;
    }

    /**
     * @param id_pelanggan the id_pelanggan to set
     */
    public void setId_pelanggan(int id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @return the jeniskel
     */
    public String getJeniskel() {
        return jeniskel;
    }

    /**
     * @param jeniskel the jeniskel to set
     */
    public void setJeniskel(String jeniskel) {
        this.jeniskel = jeniskel;
    }

    /**
     * @return the alamat
     */
    public String getAlamat() {
        return alamat;
    }

    /**
     * @param alamat the alamat to set
     */
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    /**
     * @return the notelp
     */
    public String getNotelp() {
        return notelp;
    }

    /**
     * @param notelp the notelp to set
     */
    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    /**
     * @return the idpel
     */
    public String getIdpel() {
        return idpel;
    }

    /**
     * @param idpel the idpel to set
     */
    public void setIdpel(String idpel) {
        this.idpel = idpel;
    }
    
}
