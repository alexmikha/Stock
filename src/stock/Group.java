package stock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Group {
    private ArrayList<String> lst = new ArrayList<>();

    public void add(String groupname) throws GroupException {
        if (lst.contains(groupname)) {
            throw new GroupException();
        }
        lst.add(groupname);
    }

    public void clear() {
        lst.clear();
    }

    public void add(ResultSet rs) {

        try {
            String groupname = rs.getString(1);
            if (!lst.contains(groupname)) {
                lst.add(groupname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getGroup(int i) {
        String temp = "";
        if (i >= 0 && i < lst.size()) {
            temp = lst.get(i);
        }
        return temp;
    }

    public void renameGroup(String newgroupName, int i) {
        if (i <= 0 && i < lst.size()) {
            lst.set(i, newgroupName);
        }
    }

    public void deleteGroup(int i) {
        if (i <= 0 && i < lst.size()) {
            lst.remove(i);
        }
    }

    public ArrayList<String> getList() {
        ArrayList<String> all = new ArrayList<String>();
        for (String string : lst) {
            all.add(string);
        }
        return all;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (String string : lst) {
            sb.append(string);
            sb.append("\n");
        }
        return sb.toString();
    }

}
