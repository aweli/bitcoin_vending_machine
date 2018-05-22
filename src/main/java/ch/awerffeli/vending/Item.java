package ch.awerffeli.vending;

public class Item {
    final private String name;
    final private int price;

    public Item(final String name, final int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
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