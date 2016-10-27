package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Joe on 10/25/16.
 */

public class GroceryViewAdapter extends RecyclerView.Adapter<GroceryViewAdapter.CustomViewHolder> {
    private List<GroceryItem> mGroceryItemList;

    public GroceryViewAdapter(List<GroceryItem> groceryItemList) {
        mGroceryItemList = groceryItemList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_item,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.mNameText.setText(mGroceryItemList.get(position).getName());
        holder.mDescripText.setText(mGroceryItemList.get(position).getDescription());
        holder.mTypeText.setText(mGroceryItemList.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return mGroceryItemList.size();
    }


    public void replaceData(List<GroceryItem> list){
        mGroceryItemList = list;
        notifyDataSetChanged();
    }
    /**
     * VIEWHOLDER CLASS
     */
    static class CustomViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.name) TextView mNameText;
        @BindView(R.id.description) TextView mDescripText;
        @BindView(R.id.type_text) TextView mTypeText;

        public CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
