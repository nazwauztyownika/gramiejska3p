package com.example.gra3p;

public class Lokalizacja {
    private int idZdjecia;
    private String opiszdj;
    private double szer;
    private double dlug;

    public Lokalizacja(int idZdjecia, String opiszdj, double szer, double dlug) {
        this.idZdjecia = idZdjecia;
        this.opiszdj = opiszdj;
        this.szer = szer;
        this.dlug = dlug;
    }

    public int getIdZdjecia() {
        return idZdjecia;
    }

    public void setIdZdjecia(int idZdjecia) {
        this.idZdjecia = idZdjecia;
    }

    public String getOpiszdj() {
        return opiszdj;
    }

    public void setOpiszdj(String opiszdj) {
        this.opiszdj = opiszdj;
    }

    public double getSzer() {
        return szer;
    }

    public void setSzer(double szer) {
        this.szer = szer;
    }

    public double getDlug() {
        return dlug;
    }

    public void setDlug(double dlug) {
        this.dlug = dlug;
    }
}
