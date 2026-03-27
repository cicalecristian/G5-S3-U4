package cristiancicale.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipologia")
@Table(name = "catalogo")
public abstract class ElementoCatalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "tipologia_id")
    private long id;
    @Column(name = "codice_isbn", nullable = false, length = 30, unique = true)
    private String codiceIsbn;
    @Column(name = "titolo", nullable = false, length = 20)
    private String titolo;
    @Column(name = "anno_pubblicazione", nullable = false)
    private LocalDate annoPubbliczione;
    @Column(name = "numero_pagine", nullable = false)
    private int numeroPagine;

    @OneToOne(mappedBy = "elementoPrestato")
    private Prestito prestitoAttivo;

    protected ElementoCatalogo() {
    }

    public ElementoCatalogo(String codiceIsbn, String titolo, LocalDate annoPubbliczione, int numeroPagine) {
        this.codiceIsbn = codiceIsbn;
        this.titolo = titolo;
        this.annoPubbliczione = annoPubbliczione;
        this.numeroPagine = numeroPagine;
    }

    public long getId() {
        return id;
    }

    public String getCodiceIsbn() {
        return codiceIsbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public LocalDate getAnnoPubbliczione() {
        return annoPubbliczione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public Prestito getPrestitoAttivo() {
        return prestitoAttivo;
    }

    public void setPrestitoAttivo(Prestito prestitoAttivo) {
        this.prestitoAttivo = prestitoAttivo;
    }

    @Override
    public String toString() {
        return "ElementoCatalogo{" +
                "id=" + id +
                ", codiceIsbn='" + codiceIsbn + '\'' +
                ", titolo='" + titolo + '\'' +
                ", annoPubbliczione=" + annoPubbliczione +
                ", numeroPagine=" + numeroPagine +
                ", prestitoAttivo=" + prestitoAttivo +
                '}';
    }
}
