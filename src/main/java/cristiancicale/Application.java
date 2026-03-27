package cristiancicale;

import cristiancicale.dao.ElementoCatalogoDAO;
import cristiancicale.dao.PrestitoDAO;
import cristiancicale.dao.UtenteDAO;
import cristiancicale.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("catalogobibliotecario");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ElementoCatalogoDAO ed = new ElementoCatalogoDAO(entityManager);
        UtenteDAO ud = new UtenteDAO(entityManager);
        PrestitoDAO pd = new PrestitoDAO(entityManager);

        // LIBRI

        Libro libro1 = new Libro(
                "ISBN001",
                "Il Signore degli Anelli",
                LocalDate.of(1954, 7, 29),
                1200,
                "J.R.R. Tolkien",
                "Fantasy"
        );

        Libro libro2 = new Libro(
                "ISBN002",
                "1984",
                LocalDate.of(1949, 6, 8),
                328,
                "George Orwell",
                "Distopico"
        );

        Libro libro3 = new Libro(
                "ISBN003",
                "Il Nome della Rosa",
                LocalDate.of(1980, 9, 1),
                512,
                "Umberto Eco",
                "Storico"
        );

        Libro libro4 = new Libro(
                "ISBN004",
                "Harry Potter e la Pietra Filosofale",
                LocalDate.of(1997, 6, 26),
                309,
                "J.K. Rowling",
                "Fantasy"
        );

        Libro libro5 = new Libro(
                "ISBN005",
                "La Divina Commedia",
                LocalDate.of(1320, 1, 1),
                700,
                "Dante Alighieri",
                "Poesia"
        );

        // RIVISTE

        Rivista rivista1 = new Rivista(
                "ISBN101",
                "Focus",
                LocalDate.of(2023, 1, 1),
                100,
                Periodicita.MENSILE
        );

        Rivista rivista2 = new Rivista(
                "ISBN102",
                "National Geographic",
                LocalDate.of(2022, 5, 1),
                120,
                Periodicita.MENSILE
        );

        Rivista rivista3 = new Rivista(
                "ISBN103",
                "Time",
                LocalDate.of(2024, 3, 1),
                80,
                Periodicita.SETTIMANALE
        );

        Rivista rivista4 = new Rivista(
                "ISBN104",
                "Wired",
                LocalDate.of(2023, 9, 1),
                90,
                Periodicita.MENSILE
        );

        Rivista rivista5 = new Rivista(
                "ISBN105",
                "Harvard Business Review",
                LocalDate.of(2021, 6, 1),
                110,
                Periodicita.SEMESTRALE
        );

//        ed.aggiuntaElementoDelCatalogo(libro1);
//        ed.aggiuntaElementoDelCatalogo(libro2);
//        ed.aggiuntaElementoDelCatalogo(libro3);
//        ed.aggiuntaElementoDelCatalogo(libro4);
//        ed.aggiuntaElementoDelCatalogo(libro5);
//        ed.aggiuntaElementoDelCatalogo(rivista1);
//        ed.aggiuntaElementoDelCatalogo(rivista2);
//        ed.aggiuntaElementoDelCatalogo(rivista3);
//        ed.aggiuntaElementoDelCatalogo(rivista4);
//        ed.aggiuntaElementoDelCatalogo(rivista5);

//        ed.rimozioneElementoPerIsbn("ISBN105");

//        System.out.println(ed.cercaPerIsbn("ISBN005"));

//        System.out.println(ed.cercaPerAnnoPubblicazione(2023));

//        System.out.println(ed.cercaPerAutore("Dante Alighieri"));

//        System.out.println(ed.cercaPerTitolo("Foc"));

        // UTENTI

        Utente utente1 = new Utente("Mario", "Rossi", LocalDate.of(1990, 5, 12), "TESS001");
        Utente utente2 = new Utente("Laura", "Bianchi", LocalDate.of(1985, 8, 20), "TESS002");

//        ud.aggiuntaUtente(utente1);
//        ud.aggiuntaUtente(utente2);

        Utente utente1fromDB = entityManager.find(Utente.class, 1);
        Utente utente2fromDB = entityManager.find(Utente.class, 2);

        Libro libro1fromDB = entityManager.find(Libro.class, 1);
        Rivista rivista1fromDB = entityManager.find(Rivista.class, 6);

        // PRESTITI

        Prestito prestito1 = new Prestito(
                utente1fromDB,                  // Utente
                libro1fromDB,                   // Elemento prestato
                LocalDate.of(2026, 3, 20),// Data inizio prestito
                null,                     // La data prevista verrà calcolata automaticamente
                null                      // Ancora non restituito
        );

        Prestito prestito2 = new Prestito(
                utente2fromDB,
                rivista1fromDB,
                LocalDate.of(2026, 3, 10),
                null,
                LocalDate.of(2026, 3, 25) // Già restituito
        );

//        pd.aggiuntaPrestito(prestito1);
//        pd.aggiuntaPrestito(prestito2);

//        System.out.println(ed.cercaElementiInPrestitoPerTessera("TESS001"));

        entityManager.close();
        entityManagerFactory.close();
    }
}
