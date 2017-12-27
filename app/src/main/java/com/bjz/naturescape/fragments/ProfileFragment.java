package com.bjz.naturescape.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bjz.naturescape.R;
import com.bjz.naturescape.adapter.PostsAdapter;
import com.bjz.naturescape.component.ComponentApplication;
import com.bjz.naturescape.config.AppConfig;
import com.bjz.naturescape.model.Post;
import com.bjz.naturescape.model.Profile;
import com.bjz.naturescape.service.PostService;
import com.bjz.naturescape.service.ProfileService;
import com.bjz.naturescape.utils.DownloadImageTask;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class ProfileFragment extends Fragment {
    @Inject
    Retrofit retrofit;
    private ProfileService profileService;
    private PostService postService;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private TextView nameView;
    private TextView levelView;
    private ImageView profilePic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((ComponentApplication) getActivity().getApplication()).getmNetComponent().inject(this);

        profileService = retrofit.create(ProfileService.class);
        postService = retrofit.create(PostService.class);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_profile, container, false);
        recyclerView = inflate.findViewById(R.id.faved_posts_list);

        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        nameView = inflate.findViewById(R.id.profile_name);
        levelView = inflate.findViewById(R.id.profile_level);
        profilePic = inflate.findViewById(R.id.profile_image);

        fetchProfile();
        fetchFavedPosts();
        return inflate;

    }

    private void fetchFavedPosts() {
        Call<List<Post>> allPosts = postService.getAllPosts("true", null);

        allPosts.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                adapter = new PostsAdapter(posts, ProfileFragment.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                throw new RuntimeException(t);
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
    }

    private void fetchProfile() {
        profileService.getProfile().enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                Log.d(this.getClass().toString(), " Succes on GET /profile");
                Profile profile = response.body();
                nameView.setText(profile.getName());
                levelView.setText("level: " + profile.getLevel());
                new DownloadImageTask(profilePic).execute(AppConfig.baseResourcesURL + profile.getImage());

            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Toast.makeText(ProfileFragment.this.getContext(), "Error fetching profile info", Toast.LENGTH_LONG);
            }
        });
    }


}


