import invoice.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Facture ---");
        Invoice invoice = new Invoice();
        invoice.addItem(10.0);
        invoice.addItem(20.0);
        invoice.addItem(5.5);
        invoice.print();
        invoice.save();
    }
}