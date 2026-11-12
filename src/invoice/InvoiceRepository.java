package invoice;

public class InvoiceRepository {
    public void save(Invoice invoice) {
        System.out.println("Facture enregistrée (" + invoice.getItems().size() + " lignes)");
    }
}