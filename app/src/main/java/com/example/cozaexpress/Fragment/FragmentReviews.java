package com.example.cozaexpress.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cozaexpress.Activity.ProDetailActivity;
import com.example.cozaexpress.Activity.ShipperActivity;
import com.example.cozaexpress.Adapter.AddressAdapter;
import com.example.cozaexpress.Adapter.ReviewAdapter;
import com.example.cozaexpress.Adapter.ReviewsAdapter;
import com.example.cozaexpress.DataLocal.SharedPrefManager;
import com.example.cozaexpress.Model.Review;
import com.example.cozaexpress.Model.StatusReviews;
import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.R;
import com.example.cozaexpress.api.APIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentReviews extends Fragment {

    StatusReviews statusReviews;

    List<Review> reviews;

    RecyclerView rc_Feedback;

    ReviewsAdapter reviewsAdapter;

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reviews, container, false);
        rc_Feedback = view.findViewById(R.id.rc_Feedback);
        User user = SharedPrefManager.getInstance(getContext()).getUser();
        APIService.apiService.getReviewByUser(user).enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                if(response.isSuccessful()){
                    reviews = response.body();
                    ReviewAdapter reviewAdapter = new ReviewAdapter(getContext(), reviews);
                    rc_Feedback.setHasFixedSize(false);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    rc_Feedback.setLayoutManager(layoutManager);
                    rc_Feedback.setAdapter(reviewAdapter);
                    reviewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {

            }
        });
        return view;
    }
}
