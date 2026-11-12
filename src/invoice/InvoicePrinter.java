package invoice;


public class InvoicePrinter {
    private final InvoiceCalculator calculator;

    public InvoicePrinter(InvoiceCalculator calculator) {
        this.calculator = calculator;
    }

    public void print(Invoice invoice) {
        System.out.println("=== Facture n° " + invoice.getNumber() + " ===");
        for (InvoiceLine line : invoice.getLines()) {
            System.out.printf("%s x%d : %.2f €%n",
                    line.description(), line.quantity(), calculator.calculateLineAmount(line));
        }
        System.out.printf("TOTAL : %.2f €%n", calculator.calculateTotal(invoice));
    }
}