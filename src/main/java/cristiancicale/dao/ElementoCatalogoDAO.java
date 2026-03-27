package cristiancicale.dao;

import cristiancicale.entities.ElementoCatalogo;
import cristiancicale.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;

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
        ElementoCatalogo found = this.findByIsbn(isbn);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.remove(found);

        transaction.commit();
        
        System.out.println("Cancellato");
    }

    public ElementoCatalogo findByIsbn(String isbn) {
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
}
