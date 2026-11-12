package invoice;

/** Persistance : seule classe à modifier si le moyen de stockage change. */
public class InvoiceRepository {
    public void save(Invoice invoice) {
        System.out.println("Facture enregistrée (" + invoice.getItems().size() + " lignes)");
    }
}