package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity, price and amount.
 * calculates the sales tax @ 10% and prints as part of order.
 * computes the total order amount (amount of individual lineItems + total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        return new ReceiptPrinter().printReceipt();
    }

    private class ReceiptPrinter{
        private final StringBuilder output;

        private ReceiptPrinter() {
            output = new StringBuilder();
        }

        public String printReceipt() {
            printHeaders();

            // prints lineItems
            for (LineItem lineItem : order.getLineItems()) {
                appendWithTab(lineItem.getDescription());
                appendWithTab(lineItem.getPrice());
                appendWithTab(lineItem.getQuantity());
                appendWithNewLine(lineItem.totalAmount());
            }

            double totSalesTx = 0d;
            double tot = 0d;
            for (LineItem lineItem : order.getLineItems()) {
                // calculate sales tax @ rate of 10%
                double salesTax = lineItem.totalAmount() * .10;
                totSalesTx += salesTax;

                // calculate total amount of lineItem = price * quantity + 10 % sales tax
                tot += lineItem.totalAmount() + salesTax;
            }

            // prints the state tax
            appendWithTab("Sales Tax");
            append(totSalesTx);

            // print total amount
            appendWithTab("Total Amount");
            append(tot);

            return output.toString();
        }

        private void printHeaders() {
            appendWithNewLine("======Printing Orders======\n");
            append(order.getCustomerName());
            append(order.getCustomerAddress());
        }

        private void appendWithTab(Object object) {
            append(object);
            append('\t');
        }

        private void appendWithNewLine(Object object) {
            append(object);
            append('\n');
        }
        private void append(Object object){
            output.append(object);
        }
    }
}