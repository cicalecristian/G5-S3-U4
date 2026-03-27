package cristiancicale.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("rivista")
public class Rivista extends ElementoCatalogo {

    @Enumerated(EnumType.STRING)
    @Column(name = "periodicita", nullable = true)
    private Periodicita periodicita;

    protected Rivista() {
    }

    public Rivista(String codiceIsbn, String titolo, LocalDate annoPubbliczione, int numeroPagine, Periodicita periodicita) {
        super(codiceIsbn, titolo, annoPubbliczione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "titolo='" + getTitolo() + '\'' +
                ", annoPubblicazione=" + getAnnoPubblicazione() +
                ", numeroPagine=" + getNumeroPagine() +
                ", codiceIsbn='" + getCodiceIsbn() + '\'' +
                ", periodicita=" + periodicita +
                '}';
    }
}
