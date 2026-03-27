package cristiancicale.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("L'elemento con id " + id + " non è stata trovato!");
    }

    public NotFoundException(String isbn) {
        super("Elemento con ISBN " + isbn + " non trovato");
    }
}
