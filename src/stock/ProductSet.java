package stock;

import java.util.HashSet;
import java.util.Set;

public class ProductSet {
    private Set<Product> hst = new HashSet<Product>();

    public void add(Product pr) {
        hst.add(pr);
    }

    public void delete(Product pr) {
        hst.remove(pr);
    }

    public void clear() {
        hst.clear();
    }

    public boolean containProduct(Product pr) {
        for (Product product : hst)
            if (product.getNameProduct().equalsIgnoreCase(pr.getNameProduct())) {
                return true;
            }
        return false;
    }

    public String[][] generateStringarray() {
        String[][] array = new String[hst.size()][6];
        int i = 0;
        for (Product pr : hst) {
            array[i++] = pr.toString().split("[\t]");
        }
        return array;
    }

    public HashSet<Product> statisticProduct(int i, Product prd) {

        HashSet<Product> ht = new HashSet<>();

        for (Product product : hst) {
            if (i == 0) {
                ht.add(product);
            } else if (i == 1 && prd.getGroup().equals(product.getGroup())) {
                ht.add(product);
            }

        }

        return ht;
    }

    public HashSet<Product> allSet() {

        HashSet<Product> ht = new HashSet<>();

        for (Product product : hst) {
            ht.add(product);

        }

        return ht;
    }

    public double statisticPrise(int i, Product prd) {
        double price = 0;
        for (Product product : hst) {
            if (i == 0) {
                price += product.getPrice();
            } else if (i == 1 && prd.getGroup().equals(product.getGroup())) {
                price += product.getPrice();
            }

        }

        return price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Product product : hst) {
            sb.append(product);
            sb.append("\n");
        }
        return sb.toString();
    }

}
