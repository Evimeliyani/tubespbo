package model;

public class Presensi {
    private int id;
    private String nama, tanggal, status;

    public Presensi(int id, String nama, String tanggal, String status) {
        this.id = id;
        this.nama = nama;
        this.tanggal = tanggal;
        this.status = status;
    }

    public int getId() { return id; }
    public String getNama() { return nama; }
    public String getTanggal() { return tanggal; }
    public String getStatus() { return status; }
}