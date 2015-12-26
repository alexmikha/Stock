package stock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;

public class Product implements Comparator<Product> {

    private String nameProduct;
    private String description;
    private String manufacturer;
    private int quantity;
    private double price;
    private String group;

    public Product() {

    }

    public Product(String nameProduct, String description, String manufacturer, int quantity, double price,
                   String group) {
        super();
        this.nameProduct = nameProduct;
        this.description = description;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
        this.price = price;
        this.group = group;
    }

    public Product(ResultSet rs) {
        super();
        try {
            this.nameProduct = rs.getString(1);
            this.description = rs.getString(2);
            this.manufacturer = rs.getString(3);
            this.quantity = rs.getInt(4);
            this.price = rs.getDouble(5);
            this.group = rs.getString(6);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return nameProduct + "\t" + description + "\t" + manufacturer + "\t" + quantity + "\t" + price + "\t" + group;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        }
        Product anotherProduct = (Product) obj;
        return nameProduct.equalsIgnoreCase(anotherProduct.nameProduct);
    }

    @Override
    public int compare(Product o1, Product o2) {
        return o1.getNameProduct().compareToIgnoreCase(o2.getNameProduct());
    }
}
