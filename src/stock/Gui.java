package stock;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Gui extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private DataBaseOperation dbo = new DataBaseOperation();
    private ProductSet prs = new ProductSet();
    private Group gr = new Group();
    private int mode = 0;

    /**
     * Launch the application.
     */
    public void start() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Gui frame = new Gui();
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Gui() {
        setTitle("Stock");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent arg0) {
                if (mode == 0) {
                    refreshTable();
                } else {
                    mode = 0;
                }
            }
        });
        setResizable(false);
        //setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 920, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        dbo.loadAllProducts(prs);
        dbo.loadAllGroup(gr);
        ProductTableModel ptm = new ProductTableModel(prs.statisticProduct(0, new Product()));

        table = new JTable(1, 6);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setBounds(10, 11, 774, 485);
        table.setModel(ptm);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 11, 894, 500);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JButton btnNewButton = new JButton("Exit");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int n = JOptionPane.showConfirmDialog(null, new String("EXIT"));
                if (n == 0) {
                    System.exit(0);
                }
            }
        });
        btnNewButton.setBounds(784, 524, 120, 37);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Edit");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                int i = table.getSelectedRow();
                if (i < 0) {
                    JOptionPane.showMessageDialog(rootPane, "First select the product");
                    return;
                }

                String productname = (String) table.getValueAt(i, 0);
                String description = (String) table.getValueAt(i, 1);
                String manufactured = (String) table.getValueAt(i, 2);
                int quantity = (Integer) table.getValueAt(i, 3);
                double price = (Double) table.getValueAt(i, 4);
                String group = (String) table.getValueAt(i, 5);

                Product pr = new Product(productname, description, manufactured, quantity, price, group);
                GuiEdit ge = new GuiEdit(pr);
                ge.start(pr);
            }
        });
        btnNewButton_1.setBounds(654, 524, 120, 37);
        contentPane.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Add");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiAdd ga = new GuiAdd();
                ga.start();
                refreshTable();
            }
        });
        btnNewButton_2.setBounds(524, 524, 120, 37);
        contentPane.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("Delete");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int i = table.getSelectedRow();
                if (i < 0) {
                    JOptionPane.showMessageDialog(rootPane, "First select the product");
                    return;
                }

                String productname = (String) table.getValueAt(i, 0);
                String description = (String) table.getValueAt(i, 1);
                String manufactured = (String) table.getValueAt(i, 2);
                int quantity = (Integer) table.getValueAt(i, 3);
                double price = (Double) table.getValueAt(i, 4);
                String group = (String) table.getValueAt(i, 5);
                Product pr = new Product(productname, description, manufactured, quantity, price, group);
                int answ = JOptionPane.showConfirmDialog(rootPane,
                        "Do you want to delete the item " + pr.getNameProduct() + " ?");
                if (answ == 0) {
                    dbo.deleteProductfromDateBase(pr);
                    refreshTable();
                }
            }
        });
        btnNewButton_3.setBounds(390, 524, 120, 37);
        contentPane.add(btnNewButton_3);

        JButton btnNewButton_4 = new JButton("Find");
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = JOptionPane.showInputDialog(rootPane, "Enter the name of the product");

                if ((name == null) || name.length() < 1) {
                    return;
                }

                int rowsfind = -1;
                for (int i = 0; i < table.getRowCount(); i++) {
                    if (table.getValueAt(i, 0).toString().equalsIgnoreCase(name)) {

                        rowsfind = i;
                    }
                }

                if (rowsfind < 0) {
                    JOptionPane.showMessageDialog(rootPane, "Product not found");
                } else if (rowsfind >= 0) {
                    mode = 1;
                    table.setRowSelectionInterval(rowsfind, rowsfind);
                }
            }
        });
        btnNewButton_4.setBounds(260, 524, 120, 37);
        contentPane.add(btnNewButton_4);

        JButton btnNewButton_5 = new JButton("Statistic");
        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                StatisticGui.start();
            }
        });
        btnNewButton_5.setBounds(130, 524, 120, 37);
        contentPane.add(btnNewButton_5);

        JButton btnNewButton_6 = new JButton("Group");
        btnNewButton_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                GuiGroup.start();
            }
        });
        btnNewButton_6.setBounds(10, 524, 110, 37);
        contentPane.add(btnNewButton_6);

    }

    public void refreshTable() {
        dbo.loadAllProducts(prs);
        ProductTableModel ptm = new ProductTableModel(prs.statisticProduct(0, new Product()));
        table.setModel(ptm);

    }
}
