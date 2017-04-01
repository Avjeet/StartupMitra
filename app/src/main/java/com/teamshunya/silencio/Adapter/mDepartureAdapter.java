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
import com.teamshunya.silencio.Models.Departure;
import com.teamshunya.silencio.R;

import java.util.List;

/**
 * Created by himanshusingh on 20/03/17.
 */

public class mDepartureAdapter extends ArrayAdapter<Departure> {
    List<Departure> departureList;
    Context context;
    private LayoutInflater mInflater;

    // Constructors
    public mDepartureAdapter(Context context, List<Departure> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        departureList = objects;
    }
    @Override
    public Departure getItem(int position) {
        return departureList.get(position);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final mDepartureAdapter.ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.flight_dep, parent, false);
            vh = mDepartureAdapter.ViewHolder.create((LinearLayout) view);
            view.setTag(vh);
        } else {
            vh = (mDepartureAdapter.ViewHolder) convertView.getTag();
        }
        Departure item = getItem(position);
        vh.status_text.setText(item.getDelay() );
        vh.destination.setText(item.getDestination());
        vh.flight_text.setText(item.getFlightNo());
        vh.time_text.setText(item.getEta());
        vh.source.setText(item.getSource());
        Picasso.with(context).load(item.getLogo()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(vh.imageView);
        return vh.rootView;
    }
    private static class ViewHolder {
        public final LinearLayout rootView;
        public final ImageView imageView;
        public final CustomFontTextView status_text;
        public final CustomFontTextView destination;
        public final CustomFontTextView flight_text;
        public final CustomFontTextView time_text;
        public final CustomFontTextView source;


        private ViewHolder(LinearLayout rootView,ImageView imageView,CustomFontTextView statuss, CustomFontTextView src_text, CustomFontTextView status_text, CustomFontTextView flight_text, CustomFontTextView time_text) {
            this.rootView = rootView;
            this.imageView = imageView;
            this.destination = src_text;
            this.flight_text = flight_text;
            this.status_text = status_text;
            this.time_text = time_text;
            this.source = statuss;
        }

        public static mDepartureAdapter.ViewHolder create(LinearLayout rootView) {
            ImageView imageView = (ImageView) rootView.findViewById(R.id.logo);
            CustomFontTextView src_text = (CustomFontTextView) rootView.findViewById(R.id.src_text);
            CustomFontTextView status_text = (CustomFontTextView) rootView.findViewById(R.id.status_text);
            CustomFontTextView flight_text = (CustomFontTextView) rootView.findViewById(R.id.flight_text);
            CustomFontTextView time_text = (CustomFontTextView) rootView.findViewById(R.id.time_text);
            CustomFontTextView statuss = (CustomFontTextView) rootView.findViewById(R.id.status);
            return new mDepartureAdapter.ViewHolder(rootView,imageView,src_text, status_text,statuss, flight_text, time_text);
        }
    }
}
