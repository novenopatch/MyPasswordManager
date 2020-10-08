package jin.jerrykel.mypasswordmanager.vue.AppActivity;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import jin.jerrykel.mypasswordmanager.R;
import jin.jerrykel.mypasswordmanager.model.SaveModel.SaveItemCategory;
import jin.jerrykel.mypasswordmanager.model.SaveModel.SaveNoteItem;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private ArrayList<SaveItemCategory> saveItemCategories ;
    private Context context;

    public CustomExpandableListAdapter(Context context, ArrayList<SaveItemCategory> saveItemCategories) {
        this.saveItemCategories = saveItemCategories;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return this.saveItemCategories.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.saveItemCategories.get(groupPosition).getSaveItemCategories().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.saveItemCategories.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.saveItemCategories.get(groupPosition).getSaveItemCategories().get(childPosition);
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
        SaveItemCategory saveItemCategory = (SaveItemCategory) getGroup(groupPosition);
        if(convertView ==null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.layout_expannable_list_group,null);
        }
        TextView textViewCategoryName = (TextView) convertView .findViewById(R.id.textViewCategoryName);
        textViewCategoryName.setTypeface(null, Typeface.BOLD);
        textViewCategoryName.setText(saveItemCategory.getName());

        TextView textViewNumberSave = (TextView) convertView .findViewById(R.id.textViewTotalSaveNumber);
        textViewNumberSave.setTypeface(null, Typeface.BOLD);
        textViewNumberSave.setText(saveItemCategory.getNomberSave());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        SaveNoteItem saveNoteItem = (SaveNoteItem)getChild(groupPosition,childPosition);
        if(convertView ==null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.layout_expannable_list_item,null);
        }
        TextView textViewCategoryName = (TextView) convertView .findViewById(R.id.TextViewItemNoteName);
        textViewCategoryName.setTypeface(null, Typeface.BOLD);
        textViewCategoryName.setText(saveNoteItem.getTitle());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
