// src/invoice/InvoiceLine.java
package invoice;

public record InvoiceLine(String description, int quantity, double unitPrice) { }