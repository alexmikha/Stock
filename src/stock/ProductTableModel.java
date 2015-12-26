package stock;

import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ProductTableModel implements TableModel {

    private ArrayList<Product> al = new ArrayList<>();
    ;

    private void fillProduct(HashSet<Product> hst) {

        for (Product product : hst) {
            al.add(product);
        }
        al.sort(new Product());

    }

    // Модель при создании получает список товаров
    public ProductTableModel(HashSet<Product> h) {
        super();
        fillProduct(h); // заполнить

    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        // TODO Auto-generated method stub

    }

    @Override
    // Для визуализации содержимого ячеек графическим компонентом определяем тип ячеек столбца
    public Class<?> getColumnClass(int i) {
        if (i == 0 || i == 1 || i == 2 || i == 5) {
            return String.class;
        } else if (i == 3) {
            return Integer.class;
        } else if (i == 4) {
            return Double.class;
        }

        return null;
    }

    // Количество столбцов
    @Override
    public int getColumnCount() {
        return 6;
    }

    // Вернем наименование колонки
    @Override
    public String getColumnName(int column) {

        String[] colNames = {"Product", "Description", "Manufacturer", "Quantity", "Price", "Group"};
        return colNames[column];
    }

    // Количество строк равно числу записей
    @Override
    public int getRowCount() {
        if (al != null) {
            return al.size();
        }
        return 0;
    }

    // Возвращаем данные для определенной строки и столбца
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product pr = al.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pr.getNameProduct();
            case 1:
                return pr.getDescription();
            case 2:
                return pr.getManufacturer();
            case 3:
                return pr.getQuantity();
            case 4:
                return pr.getPrice();
            case 5:
                return pr.getGroup();
        }

        return "";

    }

    // возвращает товар по номеру строки
    public Object getValueAtRows(int rowIndex) {
        Product pr = al.get(rowIndex);
        return pr;

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub

    }

}
