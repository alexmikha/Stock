package stock;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings({"unused", "serial"})
public class GuiEdit extends JDialog {

    private JPanel contentPane;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private DataBaseOperation dbo = new DataBaseOperation();
    private Group gr;
    private ProductSet ps = new ProductSet();
    @SuppressWarnings("rawtypes")
    private JComboBox comboBox;
    private Product pr;

    /**
     * Launch the application.
     */
    public void start(Product pr) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GuiEdit frame = new GuiEdit(pr);
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
    @SuppressWarnings({"rawtypes", "unchecked"})
    public GuiEdit(Product pr) {

//		this.gr = gr;
        this.pr = pr;
        setModal(true);
        setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        setType(Type.UTILITY);
        setAlwaysOnTop(true);
        setBounds(100, 100, 496, 367);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(10, 28, 219, 29);
        contentPane.add(textField_1);
        textField_1.setText(pr.getNameProduct());
        textField_1.setEditable(false);

        textField_2 = new JTextField();
        textField_2.setBounds(10, 68, 219, 29);
        contentPane.add(textField_2);
        textField_2.setColumns(10);
        textField_2.setText(pr.getDescription());


        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(10, 108, 219, 29);
        contentPane.add(textField_3);
        textField_3.setText(pr.getManufacturer());

        textField_4 = new JTextField();
        textField_3.setColumns(10);
        textField_4.setBounds(10, 148, 219, 29);
        contentPane.add(textField_4);
        textField_4.setText(Integer.toString(pr.getQuantity()));

        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(10, 188, 219, 29);
        contentPane.add(textField_5);
        textField_5.setText(Double.toString(pr.getPrice()));

        JLabel lblNewLabel = new JLabel("Product name");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblNewLabel.setBounds(266, 30, 122, 22);
        contentPane.add(lblNewLabel);

        JLabel label = new JLabel("Description");
        label.setFont(new Font("Times New Roman", Font.BOLD, 14));
        label.setBounds(266, 75, 122, 14);
        contentPane.add(label);

        JLabel label_1 = new JLabel("Manufacturer");
        label_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
        label_1.setBounds(266, 115, 106, 14);
        contentPane.add(label_1);

        JLabel label_2 = new JLabel("Quantity");
        label_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
        label_2.setBounds(266, 155, 106, 14);
        contentPane.add(label_2);

        JLabel label_3 = new JLabel("Price");
        label_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
        label_3.setBounds(266, 195, 86, 14);
        contentPane.add(label_3);

        JLabel label_4 = new JLabel("Group");
        label_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
        label_4.setBounds(266, 237, 46, 14);
        contentPane.add(label_4);

        JButton btnNewButton = new JButton("Edit");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                editProduct();
            }
        });
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnNewButton.setBounds(348, 289, 122, 29);
        contentPane.add(btnNewButton);

        comboBox = new JComboBox();
        comboBox.setBounds(10, 228, 219, 29);
        dbo.loadAllProducts(ps);
        for (String string : dbo.loadGroupList()) {
            comboBox.addItem(string);
        }
        comboBox.setSelectedItem(pr.getGroup());

        contentPane.add(comboBox);
    }

    public void setGroup(Group gr) {
        this.gr = gr;
    }

    public void editProduct() {
        if (textField_1.getText().length() < 1 || textField_2.getText().length() < 1 || textField_3.getText().length() < 1
                || textField_4.getText().length() < 1 || textField_5.getText().length() < 1) {
            JOptionPane.showMessageDialog(rootPane, "Заполните все поля продукта");
            return;
        }
        String productname = textField_1.getText();
        String description = textField_2.getText();
        String manufactured = textField_3.getText();
        int quantity = 0;
        double price = 0;
        String group = comboBox.getSelectedItem().toString();
        try {
            quantity = Integer.parseInt(textField_4.getText());
            price = Double.parseDouble(textField_5.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Введены недопустимые данные для количества или цены товара");
            return;
        }
        Product pr = new Product(productname, description, manufactured, quantity, price, group);

        dbo.updateProductIntoDataBase(pr);
        this.dispose();                 //добавляем новый товар в базу и освобождаем ресурсы
    }

}
