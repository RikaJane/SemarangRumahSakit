package com.rika.semarangrs.model;

/**
 * Created by User on 18/12/2017.
 */

public class RS {
    String nama,keterangan,detail,gambar,koordinat,fasilitas;
    public RS(){

    }

    public RS(String nama, String keterangan, String detail, String gambar, String koordinat, String fasilitas) {
        this.nama = nama;
        this.keterangan = keterangan;
        this.detail = detail;
        this.gambar = gambar;
        this.koordinat = koordinat;
        this.fasilitas = fasilitas;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getKoordinat() {
        return koordinat;
    }

    public void setKoordinat(String koordinat) {
        this.koordinat = koordinat;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }
}
