package stock;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings({"unused", "serial"})
public class GuiAdd extends JDialog {

    private JPanel contentPane;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private DataBaseOperation dbo = new DataBaseOperation();
    private Group gr;
    private ProductSet prs = new ProductSet();
    private Product product = new Product();
    @SuppressWarnings("rawtypes")
    private JComboBox comboBox;

    /**
     * Launch the application.
     */
    public void start() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GuiAdd frame = new GuiAdd();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     *
     * @param
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public GuiAdd() {
        setTitle("Product add");
//		this.gr = gr;
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

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(10, 68, 219, 29);
        contentPane.add(textField_2);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(10, 108, 219, 29);
        contentPane.add(textField_3);

        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(10, 148, 219, 29);
        contentPane.add(textField_4);

        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(10, 188, 219, 29);
        contentPane.add(textField_5);

        JLabel lblNewLabel = new JLabel("Product name");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblNewLabel.setBounds(266, 30, 122, 22);
        contentPane.add(lblNewLabel);

        JLabel lblDeskription = new JLabel("Description");
        lblDeskription.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblDeskription.setBounds(266, 75, 122, 14);
        contentPane.add(lblDeskription);

        JLabel lblManufacturer = new JLabel("Manufacturer");
        lblManufacturer.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblManufacturer.setBounds(266, 115, 106, 14);
        contentPane.add(lblManufacturer);

        JLabel lblQuantity = new JLabel("Quantity");
        lblQuantity.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblQuantity.setBounds(266, 155, 106, 14);
        contentPane.add(lblQuantity);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblPrice.setBounds(266, 195, 86, 14);
        contentPane.add(lblPrice);

        JLabel lblGroup = new JLabel("Group");
        lblGroup.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblGroup.setBounds(266, 237, 46, 14);
        contentPane.add(lblGroup);

        JButton btnNewButton = new JButton("Add");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                addProduct();
            }
        });

        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnNewButton.setBounds(348, 289, 122, 29);
        contentPane.add(btnNewButton);

        comboBox = new JComboBox();
        comboBox.setBounds(10, 228, 219, 29);
        dbo.loadAllProducts(prs);
        for (String string : dbo.loadGroupList()) {
            comboBox.addItem(string);
        }
        contentPane.add(comboBox);
    }

    public void setGroup(Group gr) {
        this.gr = gr;
    }

    public void addProduct() {
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

        if (prs.containProduct(pr)) {
            JOptionPane.showMessageDialog(rootPane, "Продукт с именем" + " " + textField_1.getText() + " " + "уже существует в базе");
            return;
        }

        dbo.addProductToDataBase(pr);
        this.dispose();              // добавляем новый товар в базу и освобождаем ресурсы

    }
}

