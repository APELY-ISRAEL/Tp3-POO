package invoice;

/** Calcul du total : seule classe à modifier si une règle de calcul change (TVA, remise...). */
public class InvoiceCalculator {
    public double calculateTotal(Invoice invoice) {
        double total = 0;
        for (double item : invoice.getItems()) total += item;
        return total;
    }
}