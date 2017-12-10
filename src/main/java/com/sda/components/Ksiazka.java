package com.sda.components;

public class Ksiazka {

    private String Tytul;
    private String Autor;
    private String RokWydania;

    public Ksiazka(String tytul, String autor, String rokWydania) {
        Tytul = tytul;
        Autor = autor;
        RokWydania = rokWydania;
    }

    public String getTytul() {
        return Tytul;
    }

    public void setTytul(String tytul) {
        Tytul = tytul;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    public String getRokWydania() {
        return RokWydania;
    }

    public void setRokWydania(String rokWydania) {
        RokWydania = rokWydania;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ksiazka ksiazka = (Ksiazka) o;

        if (!Tytul.equals(ksiazka.Tytul)) return false;
        if (!Autor.equals(ksiazka.Autor)) return false;
        return RokWydania.equals(ksiazka.RokWydania);
    }

    @Override
    public int hashCode() {
        int result = Tytul.hashCode();
        result = 31 * result + Autor.hashCode();
        result = 31 * result + RokWydania.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Ksiazka{");
        sb.append("Tytul='").append(Tytul).append('\'');
        sb.append(", Autor='").append(Autor).append('\'');
        sb.append(", RokWydania='").append(RokWydania).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
