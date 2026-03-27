package cristiancicale.dao;

import cristiancicale.entities.ElementoCatalogo;
import cristiancicale.entities.Libro;
import cristiancicale.entities.Prestito;
import cristiancicale.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;

import java.time.LocalDate;
import java.util.List;

public class ElementoCatalogoDAO {
    private final EntityManager em;

    public ElementoCatalogoDAO(EntityManager em) {
        this.em = em;
    }

    public void aggiuntaElementoDelCatalogo(ElementoCatalogo newElementoCatalogo) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(newElementoCatalogo);

        transaction.commit();

        System.out.println("L'elemento " + newElementoCatalogo.getCodiceIsbn() + "è stato salvato correttamente");
    }

    public void rimozioneElementoPerIsbn(String isbn) {
        ElementoCatalogo found = this.cercaPerIsbn(isbn);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.remove(found);

        transaction.commit();

        System.out.println("Cancellato");
    }

    public ElementoCatalogo cercaPerIsbn(String isbn) {
        try {
            return em.createQuery(
                            "SELECT e FROM ElementoCatalogo e WHERE e.codiceIsbn = :isbn",
                            ElementoCatalogo.class)
                    .setParameter("isbn", isbn)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NotFoundException(isbn);
        }
    }

    public List<ElementoCatalogo> cercaPerAnnoPubblicazione(int anno) {
        List<ElementoCatalogo> risultati = em.createQuery(
                        "SELECT e FROM ElementoCatalogo e WHERE FUNCTION('YEAR', e.annoPubblicazione) = :anno",
                        ElementoCatalogo.class)
                .setParameter("anno", anno)
                .getResultList();

        if (risultati.isEmpty()) {
            throw new NotFoundException("Nessun elemento trovato per l'anno: " + anno);
        }

        return risultati;
    }

    public List<Libro> cercaPerAutore(String autore) {
        List<Libro> risultati = em.createQuery(
                        "SELECT l FROM Libro l WHERE l.autore = :autore",
                        Libro.class)
                .setParameter("autore", autore)
                .getResultList();

        if (risultati.isEmpty()) {
            throw new NotFoundException("Nessun elemento trovato per l'autore: " + autore);
        }

        return risultati;
    }

    public List<Libro> cercaPerTitolo(String titolo) {
        List<Libro> risultati = em.createQuery(
                        "SELECT l FROM Libro l WHERE LOWER(l.titolo) LIKE LOWER(:titolo)",
                        Libro.class)
                .setParameter("titolo", "%" + titolo + "%")
                .getResultList();

        if (risultati.isEmpty()) {
            throw new NotFoundException("Nessun elemento trovato per il titolo: " + titolo);
        }

        return risultati;
    }

    public List<ElementoCatalogo> ricercaElementiInPrestitoPerTessera(String numeroDiTessera) {
        List<ElementoCatalogo> risultati = em.createQuery(
                        "SELECT p.elementoPrestato FROM Prestito p " +
                                "WHERE p.utente.numeroDiTessera = :tessera " +
                                "AND p.dataRestituzioneEffettiva IS NULL",
                        ElementoCatalogo.class)
                .setParameter("tessera", numeroDiTessera)
                .getResultList();

        if (risultati.isEmpty()) {
            throw new NotFoundException("Nessun prestito attivo per tessera: " + numeroDiTessera);
        }

        return risultati;
    }

    public List<Prestito> ricercaPrestitiScaduti() {
        List<Prestito> risultati = em.createQuery(
                        "SELECT p FROM Prestito p " +
                                "WHERE p.dataRestituzioneEffettiva IS NULL " +
                                "AND p.dataRestituzionePrevista < :oggi",
                        Prestito.class)
                .setParameter("oggi", LocalDate.now())
                .getResultList();

        if (risultati.isEmpty()) {
            throw new NotFoundException("Nessun prestito scaduto trovato");
        }

        return risultati;
    }
}
