package com.vsahin.twitter_api_search.View.TweetList;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vsahin.twitter_api_search.Model.Entity.Entities;
import com.vsahin.twitter_api_search.Model.Entity.Media;
import com.vsahin.twitter_api_search.Model.Entity.Status;
import com.vsahin.twitter_api_search.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * Created by Volkan Şahin on 22.08.2017.
 */

public class TweetsRecyclerViewAdapter extends RecyclerView.Adapter<TweetsRecyclerViewAdapter.MyViewHolder> {

    private List<Status> statuses;
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm"); //you can add dd/MM/yyyy for date
    private  LayoutInflater layoutInflater;
    RecyclerVewItemClickListener recyclerVewItemClickListener;
    private int lastPosition = -1;
    Context context;

    public TweetsRecyclerViewAdapter(Context context, ArrayList<Status> statuses, RecyclerVewItemClickListener listener) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.statuses = statuses;
        this.recyclerVewItemClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = layoutInflater.inflate(R.layout.item_tweet_list_row, viewGroup, false);
        Log.d(TAG, "*** onCreateViewHolder: created");
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        Status s = statuses.get(position);
        Entities e = s.getEntities();
        Media m = null;

        if(e != null){
            m = e.getMedia();
        }

        if(m != null){
            viewHolder.getImageView().setVisibility(View.VISIBLE);
            Picasso.with(context).load(m.getMedia_url()).into(viewHolder.getImageView());
        }
        viewHolder.getTextViewUserName().setText(s.getUser().getName());
        viewHolder.getTextViewText().setText(s.getText());

        setAnimation(viewHolder.itemView, position);

        Log.d(TAG, "*** onBindViewHolder: binded");
    }

    @Override
    public int getItemCount() {
        return statuses.size();
    }

    public void updateItems(List<Status> statuses){
        this.statuses.addAll(statuses);
        notifyDataSetChanged();
    }

    public void clearItems(){
        this.statuses.clear();
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation slide_up = AnimationUtils.loadAnimation(context, R.anim.slide_up);
            viewToAnimate.startAnimation(slide_up);
            lastPosition = position;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.textview_username)
        TextView textViewId;

        @BindView(R.id.textview_text)
        TextView textViewText;

        @BindView(R.id.imageview_tweet)
        ImageView imageView;

        public MyViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            v.setOnClickListener(this);
        }

        public TextView getTextViewText() {
            return textViewText;
        }

        public TextView getTextViewUserName() {
            return textViewId;
        }

        public ImageView getImageView() {
            return imageView;
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if(position != -1){
                recyclerVewItemClickListener.onItemClick(statuses.get(position));
            }
        }
    }
}
