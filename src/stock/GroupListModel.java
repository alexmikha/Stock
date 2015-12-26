package stock;

import java.util.ArrayList;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class GroupListModel implements ListModel<String> {
    private ArrayList<String> als;

    public GroupListModel(ArrayList<String> als) {
        super();
        this.als = als;
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getElementAt(int index) {
        if (index >= 0 && index < als.size()) {
            return als.get(index);
        }
        return "";
    }

    @Override
    public int getSize() {
        if (als != null) {
            return als.size();
        }
        return 0;
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        // TODO Auto-generated method stub

    }

}
