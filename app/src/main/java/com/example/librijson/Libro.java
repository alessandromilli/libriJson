package com.example.librijson;

public class Libro {
    private String genere;
    private String titolo;
    private String autore;
    private float prezzo;

    public void setGenere(String genere){
        this.genere = genere;
    }

    public void setAutore(String autore){
        this.autore = autore;
    }

    public void setTitolo(String titolo){
        this.titolo = titolo;
    }

    public void setPrezzo(float prezzo){
        this.prezzo = prezzo;
    }

    public String getGenere()
    {
        return this.genere;
    }

    public String getTitolo()
    {
        return this.titolo;
    }

    public String getAutore()
    {
        return this.autore;
    }

    public float getPrezzo()
    {
        return this.prezzo;
    }

    public String toString(){
        return "Libro: \n" + " titolo: " + this.getTitolo() + "\n autore: "
                + this.getAutore() + "\n genere: " + this.getGenere() +
                "\n prezzo: " + this.getPrezzo() + "euro";
    }
}