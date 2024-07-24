/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package albertokelvin;

/**
 *
 * @author ASUS
 */

public class KursRupiah {
    private double kursJualUSD;
    private double nominalRupiah;

    // Constructor tanpa parameter
    public KursRupiah() {
        this.kursJualUSD = 0.0;
        this.nominalRupiah = 0.0;
    }

    // Constructor dengan parameter
    public KursRupiah(double kursJualUSD, double nominalRupiah) {
        this.kursJualUSD = kursJualUSD;
        this.nominalRupiah = nominalRupiah;
    }

    // Method untuk konversi ke USD
    public double konversiKeUSD() {
        if (kursJualUSD != 0) {
            return nominalRupiah / kursJualUSD;
        } else {
            return 0.0;
        }
    }

    // Getter dan Setter
    public double getKursJualUSD() {
        return kursJualUSD;
    }

    public void setKursJualUSD(double kursJualUSD) {
        this.kursJualUSD = kursJualUSD;
    }

    public double getNominalRupiah() {
        return nominalRupiah;
    }

    public void setNominalRupiah(double nominalRupiah) {
        this.nominalRupiah = nominalRupiah;
    }
}

