package stock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataBaseOperation {

    private Connection con;
    private ResultSet rs;
    private Statement st;
    private PreparedStatement pst;

    public DataBaseOperation() {
        super();
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:product.db");
            st = con.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERROR");
        }
    }

    public void loadAllProducts(ProductSet ps) {
        try {
            ps.clear();
            rs = st.executeQuery("SELECT * from Product");
            for (; rs.next(); ) {
                ps.add(new Product(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadProductsGroup(ProductSet ps, String productname) {
        try {
            ps.clear();
            pst = con.prepareStatement("SELECT * FROM Product WHERE GROUPN=?");
            pst.setString(1, productname);
            pst.execute();
            rs = pst.executeQuery();

            for (; rs.next(); ) {
                ps.add(new Product(rs));
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void deleteProductGroup(Product pr) {

        try {
            pst = con.prepareStatement("DELETE FROM Product WHERE GROUPN=?");
            pst.setString(1, pr.getGroup());
            pst.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void loadAllGroup(Group gr) {
        try {
            gr.clear();
            rs = st.executeQuery("select * from GroupName");
            for (; rs.next(); ) {
                gr.add(rs);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public ArrayList<String> loadGroupList() {
        ArrayList<String> als = new ArrayList<>();
        try {

            rs = st.executeQuery("select * from GroupName");
            for (; rs.next(); ) {
                als.add(rs.getString(1));
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return als;

    }

    public void addProductToDataBase(Product pr) {
        try {

            pst = con.prepareStatement(
                    "INSERT INTO Product " + "( nameProduct, deskription, manufactured, quantyty , price, GROUPN) "
                            + "VALUES (?, ?, ?, ?, ?, ?)");
            pst.setString(1, pr.getNameProduct());
            pst.setString(2, pr.getDescription());
            pst.setString(3, pr.getManufacturer());
            pst.setInt(4, pr.getQuantity());
            pst.setDouble(5, pr.getPrice());
            pst.setString(6, pr.getGroup());
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addGroupToDataBase(String group) {
        try {

            pst = con.prepareStatement("INSERT INTO GroupName " + "( GroupName) " + "VALUES (?)");
            pst.setString(1, group);
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProductIntoDataBase(Product pr) {
        try {
            pst = con.prepareStatement("UPDATE Product SET "
                    + "nameProduct = ?, deskription = ?, manufactured = ?, quantyty = ? , price = ?, GROUPN = ?"
                    + "WHERE nameProduct = ?");

            pst.setString(1, pr.getNameProduct());
            pst.setString(2, pr.getDescription());
            pst.setString(3, pr.getManufacturer());
            pst.setInt(4, pr.getQuantity());
            pst.setDouble(5, pr.getPrice());
            pst.setString(6, pr.getGroup());
            pst.setString(7, pr.getNameProduct());
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProductfromDateBase(Product pr) {
        try {
            pst = con.prepareStatement("DELETE FROM Product WHERE nameProduct = ?");
            pst.setString(1, pr.getNameProduct());
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGroupfromDateBase(String gr) {
        try {
            pst = con.prepareStatement("DELETE FROM GroupName WHERE GroupName = ?");
            pst.setString(1, gr);
            pst.execute();
            deleteProductByGroup(gr);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteProductByGroup(String gr) {
        try {
            pst = con.prepareStatement("DELETE FROM Product WHERE GROUPN=?");
            pst.setString(1, gr);
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGroup(String old, String newn) {
        try {
            pst = con.prepareStatement("UPDATE GroupName SET " + " GroupName = ?" + "WHERE GroupName = ?");
            pst.setString(1, newn);
            pst.setString(2, old);
            pst.execute();
            updateProductGroup(old, newn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateProductGroup(String old, String newn) {
        try {
            pst = con.prepareStatement("UPDATE Product SET " + " GROUPN = ?" + "WHERE GROUPN = ?");
            pst.setString(1, newn);
            pst.setString(2, old);
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
