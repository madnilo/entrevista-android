package com.popcode.madnilo.starwiki;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.CustomViewHolder> {
    private List<People> peopleList;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public MyRecyclerViewAdapter(Context context, List<People> peopleList) {
        this.peopleList = peopleList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        final People person = peopleList.get(i);

        //Download image using picasso library
        if (!TextUtils.isEmpty(person.getThumbnail())) {
            Picasso.with(mContext).load(person.getThumbnail())
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(customViewHolder.imageView);
        }

        //Setting text view title
        customViewHolder.name.setText((CharSequence) person.getName());
        customViewHolder.gender.setText(person.getGender());
        customViewHolder.height.setText(person.getHeight().equals("unknown") ? "unknown height" : person.getHeight()+"cm");
        customViewHolder.weight.setText(person.getMass().equals("unknown") ? "unknown weight" : person.getMass()+"kg");




        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(person);
            }
        };
        customViewHolder.imageView.setOnClickListener(listener);
        customViewHolder.name.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return (null != peopleList ? peopleList.size() : 0);
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView name;
        protected TextView gender;
        protected TextView weight;
        protected TextView height;

        public CustomViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
            this.name = (TextView) view.findViewById(R.id.name);
            this.gender = (TextView) view.findViewById(R.id.gender);
            this.height = (TextView) view.findViewById(R.id.height);
            this.weight = (TextView) view.findViewById(R.id.weight);
        }
    }


    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}