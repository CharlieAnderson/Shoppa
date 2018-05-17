package com.example.charlesanderson.shoppa;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by charlesanderson on 11/6/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.PostViewHolder>{

    List<Post> posts;
    Context context;
    RecyclerViewAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public RecyclerViewAdapter.PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        this.context = parent.getContext();
        PostViewHolder viewHolder = new PostViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.PostViewHolder viewHolder, int position) {
        Post post = posts.get(position);
        viewHolder.titleView.setText(post.title);
        String hostname = Utils.getHostname(post.url);
        viewHolder.urlView.setText(hostname);
        String commentsText = post.comments+context.getString(R.string.comments);
        viewHolder.commentsView.setText(commentsText);
        Picasso.with(context).load(post.imgUrl).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView titleView;
        TextView urlView;
        TextView commentsView;
        ImageView imageView;

        PostViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cardView);
            titleView = (TextView)itemView.findViewById(R.id.titleView);
            urlView = (TextView)itemView.findViewById(R.id.urlView);
            commentsView = (TextView)itemView.findViewById(R.id.commentsView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    int position = getAdapterPosition();
                    ((MainActivity)context).startTabActivity(position);
                }
            });
        }
    }

}

