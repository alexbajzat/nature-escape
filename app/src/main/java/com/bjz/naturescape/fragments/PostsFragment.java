package com.bjz.naturescape.fragments;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bjz.naturescape.R;
import com.bjz.naturescape.adapter.PostsAdapter;
import com.bjz.naturescape.component.ComponentApplication;
import com.bjz.naturescape.component.NetComponent;
import com.bjz.naturescape.model.Post;
import com.bjz.naturescape.service.PostService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PostsFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private PostService postService;
    @Inject
    Retrofit retrofit;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton addToFavActionButton;
    private Toolbar toolbar;

    public PostsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetComponent netComponent = ((ComponentApplication) getActivity().getApplication()).getmNetComponent();
        netComponent.inject(this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_posts, container, false);

        postService = retrofit.create(PostService.class);

        recyclerView = inflate.findViewById(R.id.posts_list);

        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        fetchAllPosts();
        return inflate;
    }

    public void updatePost(Post post) {
        Call<Post> call = postService.updatePost(post.getId(), post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.code() == 200) {
                    Toast.makeText(PostsFragment.this.getContext(), "Succesfuly added to favs!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(PostsFragment.this.getContext(), "Problem occured", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(PostsFragment.this.getContext(), "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private void fetchAllPosts() {
        Call<List<Post>> allPosts = postService.getAllPosts(null, null);

        allPosts.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                adapter = new PostsAdapter(posts, PostsFragment.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                throw new RuntimeException(t);
            }
        });
    }

}
