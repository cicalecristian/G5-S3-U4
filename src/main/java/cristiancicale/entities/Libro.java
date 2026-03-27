package cristiancicale.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("libro")
public class Libro extends ElementoCatalogo {

    @Column(name = "autore", nullable = false)
    private String autore;
    @Column(name = "genere", nullable = false)
    private String genere;

    protected Libro() {
    }

    public Libro(String codiceIsbn, String titolo, LocalDate annoPubbliczione, int numeroPagine, String autore, String genere) {
        super(codiceIsbn, titolo, annoPubbliczione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public String getGenere() {
        return genere;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "autore='" + autore + '\'' +
                ", genere='" + genere + '\'' +
                '}';
    }
}
