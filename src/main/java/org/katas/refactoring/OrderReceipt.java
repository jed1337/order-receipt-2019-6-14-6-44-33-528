package org.katas.refactoring;

public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        return new ReceiptPrinter().printReceipt();
    }

    private class ReceiptPrinter {
        public static final double TAX_RATE = .10;

        private final StringBuilder output;

        private ReceiptPrinter() {
            output = new StringBuilder();
        }

        public String printReceipt() {
            printHeaders();
            printLineItems();
            printStateTax();
            printTotalAmount();

            return output.toString();
        }

        private void printHeaders() {
            appendWithNewLine("======Printing Orders======\n");
            append(order.getCustomerName());
            append(order.getCustomerAddress());
        }

        private void printLineItems() {
            for (LineItem lineItem : order.getLineItems()) {
                appendWithTab(lineItem.getDescription());
                appendWithTab(lineItem.getPrice());
                appendWithTab(lineItem.getQuantity());
                appendWithNewLine(lineItem.totalAmount());
            }
        }

        private void printStateTax() {
            appendWithTab("Sales Tax");
            append(getTotalSalesTax());
        }

        private double getTotalSalesTax() {
            return order.getLineItems().stream()
                    .mapToDouble(LineItem::totalAmount)
                    .map(totalAmount -> totalAmount * TAX_RATE)
                    .sum();
        }

        private void printTotalAmount() {
            appendWithTab("Total Amount");
            append(getTotal());
        }

        private double getTotal() {
            return order.getLineItems().stream()
                    .mapToDouble(LineItem::totalAmount)
                    .map(totalAmount -> totalAmount + totalAmount * TAX_RATE)
                    .sum();
        }

        private void appendWithTab(Object object) {
            append(object);
            append('\t');
        }

        private void appendWithNewLine(Object object) {
            append(object);
            append('\n');
        }

        private void append(Object object) {
            output.append(object);
        }
    }
}