package invoice;

public class InvoiceCalculator {
    public double calculateTotal(Invoice invoice) {
        double total = 0;
        for (double item : invoice.getItems()) total += item;
        return total;
    }
}