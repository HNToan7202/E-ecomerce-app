package com.example.cozaexpress.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cozaexpress.Fragment.CartFragment;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.R;

import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    private static final String TAG = "CheckOutActivity" ;
    FrameLayout personalInfo,summary,payment;
    View personalTosummary,summaryTopayment;
    ImageView tick;
    TextView detailsNumber;
    public Float sum;
    List<Product> products;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(CartFragment.GET_BUNDLE);
        products = (List<Product>) bundle.getSerializable(CartFragment.GET_CART_ITEMS);
        Double sum = (Double) bundle.getDouble(CartFragment.GET_SUM);
        personalInfo = findViewById(R.id.personalInfo);
        summary = findViewById(R.id.orderSummary);
        payment = findViewById(R.id.payment);
        personalTosummary = findViewById(R.id.linePersonal);
        summaryTopayment = findViewById(R.id.lineOrder);
        tick = findViewById(R.id.detailsStatus1);
        detailsNumber = findViewById(R.id.detailsNumber);
        //tick.setVisibility(View.INVISIBLE);
        detailsNumber.setVisibility(View.VISIBLE);
        personalInfo.setBackground(getDrawable(R.drawable.shape_current));
        personalTosummary.setBackgroundColor(Color.parseColor("#84a9ac"));
        summary.setBackground(getDrawable(R.drawable.shape_incomplete));
        summaryTopayment.setBackgroundColor(Color.parseColor("#84a9ac"));
        payment.setBackground(getDrawable(R.drawable.shape_incomplete));

    }
}