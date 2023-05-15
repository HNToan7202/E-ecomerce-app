package com.example.cozaexpress.CheckOutFragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.cozaexpress.Activity.CheckoutActivity;
import com.example.cozaexpress.Activity.MainActivity;
import com.example.cozaexpress.Activity.StatusOrderActivity;
import com.example.cozaexpress.Fragment.CartFragment;
import com.example.cozaexpress.R;

public class PaymentFragment extends Fragment {

    FrameLayout personalInfo,summary,payment;
    ImageView tick;
    View personalTosummary,summaryTopayment;

    TextView detailsNumber;

    AppCompatButton submitContinue, btn_next_to_order;

    TextView orderId;

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

        submitContinue = view.findViewById(R.id.btn_continue_shopping);

        btn_next_to_order = view.findViewById(R.id.btn_next_to_order);

//        String order = getArguments().getString("orderId");
//        orderId.setText(order);

        //Log.e("ORDERID", "onViewCreated: "+order);

        submitContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MainActivity mainActivity = (MainActivity) getActivity();
//                if (mainActivity != null) {
//                    mainActivity.navigateToCartFragment();
//                }
                startActivity(new Intent(getActivity(), MainActivity.class));
                ((CheckoutActivity)getActivity()).finish();

            }
        });

        btn_next_to_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StatusOrderActivity.class);
                startActivity(intent);
                ((CheckoutActivity)getActivity()).finish();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false);
    }
}