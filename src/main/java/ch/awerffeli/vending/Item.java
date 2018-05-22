package ch.awerffeli.vending;

public class Item {
    final private String name;
    final private int price;
    private int quantity;

    public Item(final String name, final int price, final int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int setQuantity(final int quantity) {
        return this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object obj) {
        return !(obj instanceof Item) ? false : this.getName() == ((Item)obj).getName();
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}