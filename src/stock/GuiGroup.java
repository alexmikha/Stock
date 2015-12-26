package stock;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GuiGroup extends JDialog {
    private DataBaseOperation dbo = new DataBaseOperation();
    @SuppressWarnings("unused")
    private ProductSet prs = new ProductSet();
    private Group gr = new Group();
    private GroupListModel glsm;
    @SuppressWarnings("rawtypes")
    private JList list;

    /**
     * Launch the application.
     */
    public static void start() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GuiGroup dialog = new GuiGroup();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the dialog.
     */
    @SuppressWarnings("rawtypes")
    public GuiGroup() {
        setResizable(false);
        setModal(true);
        setTitle("Group");
        setBounds(100, 100, 476, 460);
        getContentPane().setLayout(null);

        list = new JList();
        list.setBounds(10, 28, 272, 356);
        updateList();
        getContentPane().add(list);

        JButton btnNewButton = new JButton("Add");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                addGroup();
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton.setBounds(318, 25, 120, 35);
        getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Delete");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                deleteGroup();
            }
        });
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton_1.setBounds(318, 71, 120, 35);
        getContentPane().add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Rename");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                updateGroup();
            }
        });
        btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton_2.setBounds(318, 117, 120, 35);
        getContentPane().add(btnNewButton_2);

    }

    @SuppressWarnings("unchecked")
    public void updateList() {
        dbo.loadAllGroup(gr);
        glsm = new GroupListModel(gr.getList());
        list.setModel(glsm);
    }

    public void addGroup() {
        String grn = JOptionPane.showInputDialog(rootPane, "Input new group name?");

        if (grn == null || grn.length() < 1) {
            JOptionPane.showMessageDialog(rootPane, "Necessary input group name!");
            return;
        }
        if (gr.getList().contains(grn)) {
            JOptionPane.showMessageDialog(rootPane, "This group already exists");
            return;
        }
        dbo.addGroupToDataBase(grn);
        updateList();
    }

    public void deleteGroup() {
        if (list.isSelectionEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Select group first");
        }
        String groupname = list.getSelectedValue().toString();
        int answ = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to delete group " + groupname
                + " ? This removes all the products of this group.");
        if (answ == 0) {
            dbo.deleteGroupfromDateBase(groupname);
            updateList();
        }
    }

    public void updateGroup() {
        if (list.isSelectionEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Select group first");
        }
        String groupname = list.getSelectedValue().toString();
        String grn = JOptionPane.showInputDialog(rootPane, "Input new group name?");
        if (grn == null || grn.length() < 1) {
            JOptionPane.showMessageDialog(rootPane, "Necessary input group name!");
            return;
        }
        if (gr.getList().contains(grn)) {
            JOptionPane.showMessageDialog(rootPane, "This group already exists");
            return;
        }

        int answ = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to rename group " + groupname
                + " ? This rename all the products of this group.");
        if (answ == 0) {
            dbo.updateGroup(groupname, grn);
            updateList();
        }
    }
}
