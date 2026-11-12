package invoice;

/** Affichage : seule classe à modifier si le format d'affichage change. */
public class InvoicePrinter {
    private final InvoiceCalculator calculator;                // reçu par le constructeur

    public InvoicePrinter(InvoiceCalculator calculator) {
        this.calculator = calculator;
    }

    public void print(Invoice invoice) {
        System.out.println("Facture - total : " + calculator.calculateTotal(invoice));
    }
}