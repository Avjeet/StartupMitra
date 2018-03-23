package com.teamshunya.silencio.Activities.ShowActivity.Fragments;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.teamshunya.silencio.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by AVJEET on 21-03-2018.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final boolean sub;
    ArrayList<CategoriesModel>list = new ArrayList<>();

   CategoriesAdapter(boolean sub){
       this.sub=sub;
       if(!sub) {
           list.add(new CategoriesModel("Agriculture", R.drawable.ic_assignment_white_24dp));
           list.add(new CategoriesModel("IT", R.drawable.ic_assignment_white_24dp));
           list.add(new CategoriesModel("Tourism", R.drawable.ic_assignment_white_24dp));
           list.add(new CategoriesModel("Health", R.drawable.ic_assignment_white_24dp));
           list.add(new CategoriesModel("Education", R.drawable.ic_assignment_white_24dp));
           list.add(new CategoriesModel("Food", R.drawable.ic_assignment_white_24dp));
           list.add(new CategoriesModel("Trade", R.drawable.ic_assignment_white_24dp));
       }else{
           list.add(new CategoriesModel("Agriculture", R.drawable.ic_assignment_black_24dp));
           list.add(new CategoriesModel("IT", R.drawable.ic_assignment_black_24dp));
           list.add(new CategoriesModel("Tourism", R.drawable.ic_assignment_black_24dp));
           list.add(new CategoriesModel("Health", R.drawable.ic_assignment_black_24dp));
           list.add(new CategoriesModel("Education", R.drawable.ic_assignment_black_24dp));
           list.add(new CategoriesModel("Food", R.drawable.ic_assignment_black_24dp));
           list.add(new CategoriesModel("Trade", R.drawable.ic_assignment_black_24dp));
       }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v;
       if(!sub){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_item,parent,false);
       }else{
           v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_categories_item,parent,false);

       }
       return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titleTextView.setText(list.get(position).title);
        holder.iconImageView.setImageResource(list.get(position).iconImageID);
    }



    @Override
    public int getItemCount() {
        return list.size();
    }



}

class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImageView;
        TextView titleTextView;
    public ViewHolder(View itemView) {
        super(itemView);
        iconImageView=(ImageView)itemView.findViewById(R.id.icon);
        titleTextView=(TextView) itemView.findViewById(R.id.title);
    }
}
