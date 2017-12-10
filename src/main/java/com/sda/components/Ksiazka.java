package com.sda.components;

public class Ksiazka implements Comparable<Ksiazka>{

    private String Tytul;
    private String autorImie;
    private String autorNazwisko;
    private int RokWydania;
    boolean dostepna;

    public Ksiazka(String tytul, String autorImie, String autorNazwisko, int rokWydania) {
        Tytul = tytul;
        this.autorImie = autorImie;
        this.autorNazwisko = autorNazwisko;
        RokWydania = rokWydania;
    }

    public String getTytul() {
        return Tytul;
    }

    public void setTytul(String tytul) {
        Tytul = tytul;
    }

    public String getAutorImie() {
        return autorImie;
    }

    public void setAutorImie(String autorImie) {
        this.autorImie = autorImie;
    }

    public String getAutorNazwisko() {
        return autorNazwisko;
    }

    public void setAutorNazwisko(String autorNazwisko) {
        this.autorNazwisko = autorNazwisko;
    }

    public int getRokWydania() {
        return RokWydania;
    }

    public void setRokWydania(int rokWydania) {
        RokWydania = rokWydania;
    }

    public void setDostepna(boolean dostepna) {
        this.dostepna = dostepna;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ksiazka ksiazka = (Ksiazka) o;

        if (RokWydania != ksiazka.RokWydania) return false;
        if (!Tytul.equals(ksiazka.Tytul)) return false;
        return autorNazwisko.equals(ksiazka.autorNazwisko);
    }

    @Override
    public int hashCode() {
        int result = Tytul.hashCode();
        result = 31 * result + autorNazwisko.hashCode();
        result = 31 * result + autorImie.hashCode();
        result = 31 * result + RokWydania;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(autorNazwisko);
        sb.append(" ").append(autorImie);
        sb.append(", \"").append(Tytul).append("\"");
        sb.append(", ").append(RokWydania);
        return sb.toString();
    }

    @Override
    public int compareTo(Ksiazka o) {

        if (this.equals(o) ) return 0;
        if (this.autorNazwisko.equals(o.getAutorNazwisko())
            && this.autorImie.equals(getAutorImie())
            && this.Tytul.equals(o.getTytul())) return 0;

        if ( autorNazwisko.equals(o.getAutorNazwisko()) ) {
            if (autorImie.equals(o.getAutorImie())) {
                return Tytul.compareToIgnoreCase(o.Tytul);
            } else {
                return autorImie.compareToIgnoreCase(autorNazwisko);
            }

        }

        return autorNazwisko.compareToIgnoreCase(o.getAutorNazwisko());
    }
}
