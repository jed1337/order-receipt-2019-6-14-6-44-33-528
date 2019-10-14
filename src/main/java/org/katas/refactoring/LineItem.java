package org.katas.refactoring;

public class LineItem {
    private final String desc;
    private final double p;
    private final int qty;

    public LineItem(String desc, double p, int qty) {
        super();
        this.desc = desc;
        this.p = p;
        this.qty = qty;
    }

    public String getDescription() {
        return desc;
    }

    public double getPrice() {
        return p;
    }

    public int getQuantity() {
        return qty;
    }

    public double totalAmount() {
        return p * qty;
    }
}