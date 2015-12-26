package stock;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings({"serial", "unused"})
public class StatisticGui extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private JTextField textField;
    private DataBaseOperation dbo = new DataBaseOperation();
    private ProductSet prs = new ProductSet();
    private Group gr = new Group();
    ProductTableModel ptm;

    /**
     * Launch the application.
     */
    public static void start() {
        try {
            StatisticGui dialog = new StatisticGui();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public StatisticGui() {
        setResizable(false);
        setTitle("Statistic info");
        setModalityType(ModalityType.APPLICATION_MODAL);
        setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        setBounds(100, 100, 700, 550);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(null);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        dbo.loadAllProducts(prs);
        dbo.loadAllGroup(gr);
        ptm = new ProductTableModel(prs.statisticProduct(0, new Product()));

        table = new JTable(1, 6);

        table.setBounds(10, 11, 664, 414);
        table.setModel(ptm);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 11, 670, 414);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        JComboBox comboBox = new JComboBox();

        comboBox.setBounds(10, 436, 206, 25);
        comboBox.addItem("All");
        for (String string : dbo.loadGroupList()) {
            comboBox.addItem(string);
        }

        contentPanel.add(comboBox);

        textField = new JTextField();
        textField.setBounds(543, 438, 131, 23);
        contentPanel.add(textField);
        textField.setText(calculateSumm("All"));
        textField.setColumns(10);

        JLabel lblNewLabel = new JLabel("Total cost");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(453, 436, 82, 25);
        contentPanel.add(lblNewLabel);

        JButton btnNewButton = new JButton("Show");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String groupname = comboBox.getSelectedItem().toString();
                if (!groupname.equals("All")) {
                    dbo.loadProductsGroup(prs, groupname);
                    ptm = new ProductTableModel(prs.allSet());
                    table.setModel(ptm);
                    textField.setText(calculateSumm(groupname));
                } else {
                    dbo.loadAllProducts(prs);
                    ptm = new ProductTableModel(prs.allSet());
                    table.setModel(ptm);
                    textField.setText(calculateSumm(groupname));
                }

            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton.setBounds(240, 436, 129, 25);
        contentPanel.add(btnNewButton);

    }

    public String calculateSumm(String groupname) {
        double sum = 0;
        for (int i = 0; i < table.getRowCount(); i++) {
            if (groupname.equals("All") || groupname.equals((String) table.getValueAt(i, 5))) {
                sum += (Integer) table.getValueAt(i, 3) * (Double) table.getValueAt(i, 4);
            }
        }
        String answ = String.format("%.2f", sum);
        return answ;
    }
}
