package jin.jerrykel.mypasswordmanager.vue.AppActivity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;

import jin.jerrykel.mypasswordmanager.model.SaveItemCategory;
import jin.jerrykel.mypasswordmanager.model.SaveNoteItem;

public class customExpandableListAdapter extends BaseExpandableListAdapter {
    private ArrayList<SaveItemCategory> saveItemCategories = new ArrayList<>();
    private ArrayList<SaveNoteItem> saveNoteItemArrayList = new ArrayList<>();
    @Override
    public int getGroupCount() {
        return saveItemCategories.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return saveItemCategories.get(groupPosition).getSaveItemCategories().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return saveItemCategories.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return saveItemCategories.get(groupPosition).getSaveItemCategories().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
