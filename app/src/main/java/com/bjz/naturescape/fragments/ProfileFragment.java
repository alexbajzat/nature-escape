package com.bjz.naturescape.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bjz.naturescape.R;
import com.bjz.naturescape.component.ComponentApplication;
import com.bjz.naturescape.model.Profile;
import com.bjz.naturescape.service.ProfileService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class ProfileFragment extends Fragment {
    @Inject
    Retrofit retrofit;
    private ProfileService profileService;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((ComponentApplication) getActivity().getApplication()).getmNetComponent().inject(this);

        profileService = retrofit.create(ProfileService.class);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_profile, container, false);
        fetchProfile();

        return inflate;

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


            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Toast.makeText(ProfileFragment.this.getContext(), "Error fetching profile info", Toast.LENGTH_LONG);
            }
        });
    }


}


