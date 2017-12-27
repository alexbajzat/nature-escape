package com.bjz.naturescape.adapter;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjz.naturescape.R;
import com.bjz.naturescape.config.AppConfig;
import com.bjz.naturescape.fragments.PostsFragment;
import com.bjz.naturescape.model.Post;
import com.bjz.naturescape.utils.DownloadImageTask;

import java.util.List;

/**
 * Created by bjz on 12/26/2017.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {
    List<Post> posts;
    private Fragment fragment;

    public PostsAdapter(List<Post> posts, Fragment fragment) {
        this.posts = posts;
        this.fragment = fragment;
    }

    @Override
    public PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list_item, parent, false);
        return new PostsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostsViewHolder holder, final int position) {
        final Post post = posts.get(position);
        holder.title.setText(post.getTitle());
        holder.author.setText(post.getAuthor());
        holder.description.setText(post.getDescription());
        String imageName = post.getImage();
        new DownloadImageTask(holder.photo).execute(AppConfig.baseResourcesURL + imageName);

        holder.addToFav.setVisibility(post.isAddedToFavourite() ? View.GONE : View.VISIBLE);
        holder.addToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Post postToUpdate = post;
                postToUpdate.setAddedToFavourite(true);

                ((PostsFragment) fragment).updatePost(postToUpdate);
            }
        });


    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class PostsViewHolder extends RecyclerView.ViewHolder {
        CardView cardview;
        TextView title;
        TextView author;
        TextView description;
        ImageView photo;
        FloatingActionButton addToFav;

        public PostsViewHolder(View itemView) {
            super(itemView);
            cardview = itemView.findViewById(R.id.post_card_view);
            title = itemView.findViewById(R.id.post_title);
            author = itemView.findViewById(R.id.post_author);
            description = itemView.findViewById(R.id.post_description);
            photo = itemView.findViewById(R.id.post_photo);
            addToFav = itemView.findViewById(R.id.add_to_fav_button);
        }

    }


}
