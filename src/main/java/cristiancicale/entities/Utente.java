package cristiancicale.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "utente")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "utente_id")
    private long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "cognome", nullable = false)
    private String cognome;
    @Column(name = "data_di_nascita", nullable = false)
    private LocalDate dataDiNascita;
    @Column(name = "numero_di_tessera", nullable = false, unique = true)
    private String numeroDiTessera;

    @OneToOne(mappedBy = "utente")
    private Prestito prestitoAttivo;

    protected Utente() {
    }

    public Utente(String nome, String cognome, LocalDate dataDiNascita, String numeroDiTessera) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.numeroDiTessera = numeroDiTessera;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public String getNumeroDiTessera() {
        return numeroDiTessera;
    }

    public Prestito getPrestitoAttivo() {
        return prestitoAttivo;
    }

    public void setPrestitoAttivo(Prestito prestitoAttivo) {
        this.prestitoAttivo = prestitoAttivo;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataDiNascita=" + dataDiNascita +
                ", numeroDiTessera='" + numeroDiTessera + '\'' +
                ", prestitoAttivo=" + prestitoAttivo +
                '}';
    }
}
