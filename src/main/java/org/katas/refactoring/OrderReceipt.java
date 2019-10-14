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
            double totSalesTx = 0d;
            double tot = 0d;
            for (LineItem lineItem : order.getLineItems()) {
                appendWithTab(lineItem.getDescription());
                appendWithTab(lineItem.getPrice());
                appendWithTab(lineItem.getQuantity());
                appendWithNewLine(lineItem.totalAmount());

                // calculate sales tax @ rate of 10%
                double salesTax = lineItem.totalAmount() * .10;
                totSalesTx += salesTax;

                // calculate total amount of lineItem = price * quantity + 10 % sales tax
                tot += lineItem.totalAmount() + salesTax;
            }

            // prints the state tax
            appendWithTab("Sales Tax");
            output.append(totSalesTx);

            // print total amount
            appendWithTab("Total Amount");
            output.append(tot);

            return output.toString();
        }

        private void appendWithTab(Object object) {
            output.append(object);
            output.append('\t');
        }

        private void appendWithNewLine(Object object) {
            output.append(object);
            output.append('\n');
        }

        private void printHeaders() {
            output.append("======Printing Orders======\n");
            output.append(order.getCustomerName());
            output.append(order.getCustomerAddress());
        }
    }
}