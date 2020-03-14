package com.sda.model;

public class Adres implements Comparable<Adres> {

    private String ulica;
    private int numerBudynku;
    private String kodPocztowy;
    private String miejscowosc;

    public Adres(String ulica, int numerBudynku, String miejscowosc, String kodPocztowy) {
        setUlica(ulica);
        setMiejscowosc(miejscowosc);
        setKodPocztowy(kodPocztowy);
        setNumerBudynku(numerBudynku);
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public int getNumerBudynku() {
        return numerBudynku;
    }

    public void setNumerBudynku(int numerBudynku) {
        this.numerBudynku = numerBudynku;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public int compareTo(Adres o) {
        if (this.getUlica().compareToIgnoreCase(o.getUlica()) == 0) {
            if (this.getNumerBudynku() > o.getNumerBudynku()) {
                return 1;
            } else if (this.getNumerBudynku() < o.getNumerBudynku()) return -1;
            else return 0;
        } else return this.getUlica().compareToIgnoreCase(o.getUlica());

    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("");
        sb.append(ulica);
        sb.append(" ").append(numerBudynku);
        sb.append(", ").append(kodPocztowy);
        sb.append(" ").append(miejscowosc);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Adres adres = (Adres) o;

        if (numerBudynku != adres.numerBudynku) return false;
        if (!ulica.equals(adres.ulica)) return false;
        if (!kodPocztowy.equals(adres.kodPocztowy)) return false;
        return miejscowosc.equals(adres.miejscowosc);
    }

    @Override
    public int hashCode() {
        int result = ulica.hashCode();
        result = 31 * result + numerBudynku;
        result = 31 * result + kodPocztowy.hashCode();
        result = 31 * result + miejscowosc.hashCode();
        return result;
    }
}
