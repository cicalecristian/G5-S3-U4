package cristiancicale.dao;

import cristiancicale.entities.Prestito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PrestitoDAO {
    private final EntityManager em;

    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    public void aggiuntaPrestito(Prestito newPrestito) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(newPrestito);

        transaction.commit();

        System.out.println("Il prestito " + newPrestito.getId() + " è stato salvato correttamente");
    }
}
