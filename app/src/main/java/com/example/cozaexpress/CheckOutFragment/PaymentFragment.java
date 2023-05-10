package com.example.cozaexpress.CheckOutFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cozaexpress.R;

public class PaymentFragment extends Fragment {

    FrameLayout personalInfo,summary,payment;
    ImageView tick;
    View personalTosummary,summaryTopayment;

    TextView detailsNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        summary = getActivity().findViewById(R.id.orderSummary);
        payment = getActivity().findViewById(R.id.payment);
        personalTosummary = getActivity().findViewById(R.id.linePersonal);
        summaryTopayment = getActivity().findViewById(R.id.lineOrder);
        tick = getActivity().findViewById(R.id.detailsStatus2);
        detailsNumber = getActivity().findViewById(R.id.detailsNumber2);
        tick.setVisibility(View.VISIBLE);
        detailsNumber.setVisibility(View.INVISIBLE);
        summary.setBackground(getActivity().getDrawable(R.drawable.shape_completed));
        personalTosummary.setBackgroundColor(Color.parseColor("#11FF0A"));
        payment.setBackground(getActivity().getDrawable(R.drawable.shape_completed));
        summaryTopayment.setBackgroundColor(Color.parseColor("#11FF0A"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false);
    }
}