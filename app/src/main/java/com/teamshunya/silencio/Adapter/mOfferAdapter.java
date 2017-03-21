package com.teamshunya.silencio.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;
import com.teamshunya.silencio.Classes.CustomFontTextView;
import com.teamshunya.silencio.Models.Offer;
import com.teamshunya.silencio.R;

import java.util.List;

/**
 * Created by himanshusingh on 21/03/17.
 */

public class mOfferAdapter extends ArrayAdapter<Offer> {
        List<Offer> offerList;
        Context context;
private LayoutInflater mInflater;

// Constructors
public mOfferAdapter(Context context, List<Offer> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        offerList = objects;
        }
@Override
public Offer getItem(int position) {
        return offerList.get(position);
        }

@Override
public View getView(int position, View convertView, ViewGroup parent) {
final mOfferAdapter.ViewHolder vh;
        if (convertView == null) {
        View view = mInflater.inflate(R.layout.flight_offer, parent, false);
        vh = mOfferAdapter.ViewHolder.create((LinearLayout) view);
        view.setTag(vh);
        } else {
        vh = (mOfferAdapter.ViewHolder) convertView.getTag();
        }

        Offer item = getItem(position);
        vh.name_company.setText(item.getName());
        vh.tags.setText(item.getTag());
        vh.expiry.setText(item.getExpiry());
        Picasso.with(context).load(item.getImg()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(vh.imageView);

        return vh.rootView;
        }
private static class ViewHolder {
    public final LinearLayout rootView;
    public final ImageView imageView;
    public final CustomFontTextView name_company;
    public final CustomFontTextView tags;
    public final CustomFontTextView expiry;


    private ViewHolder(LinearLayout rootView, ImageView imageView, CustomFontTextView name_company, CustomFontTextView expiry, CustomFontTextView tags) {
        this.rootView = rootView;
        this.imageView = imageView;
        this.name_company = name_company;
        this.tags = tags;
        this.expiry = expiry;

    }

    public static mOfferAdapter.ViewHolder create(LinearLayout rootView) {
        ImageView imageView = (ImageView) rootView.findViewById(R.id.logo);
        CustomFontTextView name_company = (CustomFontTextView) rootView.findViewById(R.id.name_company);
        CustomFontTextView expiry = (CustomFontTextView) rootView.findViewById(R.id.expiry);
        CustomFontTextView tags = (CustomFontTextView) rootView.findViewById(R.id.tags);
        return new mOfferAdapter.ViewHolder(rootView, imageView, name_company, expiry, tags);
    }
}
}
