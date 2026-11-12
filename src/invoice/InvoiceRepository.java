package invoice;


public class InvoiceRepository {
    public void save(Invoice invoice) {
        // ici : requête SQL, JPA ou écriture dans un fichier
        System.out.println("Facture " + invoice.getNumber()
                + " enregistrée (" + invoice.getLines().size() + " lignes).");
    }
}