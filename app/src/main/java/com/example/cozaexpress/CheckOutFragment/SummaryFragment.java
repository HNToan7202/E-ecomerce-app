package com.example.cozaexpress.CheckOutFragment;

import static android.content.ContentValues.TAG;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cozaexpress.Adapter.OrderSumaryAdapter;
import com.example.cozaexpress.DAO.ProductDAO;
import com.example.cozaexpress.DataLocal.SharedPrefManager;
import com.example.cozaexpress.Database.ProductDatabase;
import com.example.cozaexpress.Model.Order;
import com.example.cozaexpress.Model.OrderItem;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.Model.StatusOrder;
import com.example.cozaexpress.R;
import com.example.cozaexpress.api.APIService;
import com.example.cozaexpress.databinding.FragmentSummaryBinding;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SummaryFragment extends Fragment implements PaymentResultWithDataListener {

    FrameLayout personalInfo,summary,payment;
    View personalTosummary,summaryTopayment;
    ImageView tick;
    TextView detailsNumber;
    Button continueToPayment;
    List<Product> products;
    private FragmentSummaryBinding binding;
    private Double sum = 0.0;
    private RecyclerView summaryRecyclerView;

    Order order;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        products = new ArrayList<>();
        personalInfo = getActivity().findViewById(R.id.personalInfo);
        summary = getActivity().findViewById(R.id.orderSummary);
        payment = getActivity().findViewById(R.id.payment);
        personalTosummary = getActivity().findViewById(R.id.linePersonal);
        summaryTopayment = getActivity().findViewById(R.id.lineOrder);
        continueToPayment = getActivity().findViewById(R.id.continueToPayment);
        tick = getActivity().findViewById(R.id.detailsStatus1);
        detailsNumber = getActivity().findViewById(R.id.detailsNumber);
        tick.setVisibility(View.VISIBLE);
        detailsNumber.setVisibility(View.INVISIBLE);
        personalInfo.setBackground(getActivity().getDrawable(R.drawable.shape_completed));
        personalTosummary.setBackgroundColor(Color.parseColor("#11FF0A"));
        summary.setBackground(getActivity().getDrawable(R.drawable.shape_current));
        summaryTopayment.setBackgroundColor(Color.parseColor("#84a9ac"));
        payment.setBackground(getActivity().getDrawable(R.drawable.shape_incomplete));
//        database = FirebaseDatabase.getInstance();
//        mRef = database.getReference();
//        user = FirebaseAuth.getInstance().getCurrentUser();

        binding.showAddress.name.setText(getArguments().getString("name"));
        String address = getArguments().getString("houseName") + "\n" + getArguments().getString("area");
        binding.showAddress.address.setText(address);
        binding.showAddress.city.setText(getArguments().getString("city"));
        binding.showAddress.state.setText(getArguments().getString("state"));
        binding.showAddress.pincode.setText(getArguments().getString("pincode"));
        binding.showAddress.phoneNumber.setText(getArguments().getString("phoneNumber"));
        binding.showAddress.changeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_summaryFragment_to_personalDetailsFragment2);
            }
        });
        List<Product> productList = ProductDatabase.getInstance(getContext()).productDAO().getAll();

        for(Product product: productList){
            sum = sum + product.getPrice()*product.getQuantity();
        }
        binding.priceDetails.price.setText("Đ"+sum);
        binding.priceDetails.finalPayment.setText("Đ"+String.valueOf(sum+50.0));
        binding.finalPaymentHead.setText("Đ"+String.valueOf(sum+50.0));


        OrderSumaryAdapter summaryAdapter = new OrderSumaryAdapter(getContext(), productList);
        binding.summaryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.summaryRecyclerView.setAdapter(summaryAdapter);

        order = new Order();
        order.setAddress(address);
        order.setUser(SharedPrefManager.getInstance(getActivity()).getUser());
        order.setPrice(sum);
        order.setStatusOrder(StatusOrder.CHOXACNHAN);
        List<OrderItem> orderItems = new ArrayList<>();
        for(Product product: productList){
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setCount(product.getQuantity());
            orderItems.add(orderItem);
        }
        //order.setOrderitem(orderItems);
        order.setPhone(getArguments().getString("phoneNumber"));
        order.setCreateat((new Date()).toString());
        order.setUpdateat((new Date()).toString());


        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setCount(productList.get(0).getQuantity());
        orderItem.setProduct(productList.get(0));

        continueToPayment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    APIService.apiService.createOrder(order).enqueue(new Callback<Order>() {
                        @Override
                        public void onResponse(Call<Order> call, Response<Order> response) {
                            Order orderResponse = response.body();

                            List<OrderItem> orderItems = new ArrayList<>();
                            for(Product product: productList){
                                OrderItem orderItem = new OrderItem();
                                orderItem.setOrder(orderResponse);
                                orderItem.setProduct(product);
                                orderItem.setCount(product.getQuantity());
                                orderItem.setTotalprice(2000000.0);
                                orderItems.add(orderItem);
                            }

                            APIService.apiService.addOrderItem(orderItems).enqueue(new Callback<List<OrderItem>>() {
                                @Override
                                public void onResponse(Call<List<OrderItem>> call, Response<List<OrderItem>> response) {
                                    if (response.isSuccessful()){
                                        Toast.makeText(getContext(), "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                                        ProductDatabase.getInstance(getContext()).productDAO().deleteAll();
                                        List<OrderItem> orderItems1 = response.body();
                                        //Navigation.findNavController(view).navigate(R.id.action_summaryFragment_to_paymentFragment);
                                    }
                                    else
                                        Toast.makeText(getContext(), "Đặt hàng thất bại", Toast.LENGTH_SHORT).show();
                                }
                                @Override
                                public void onFailure(Call<List<OrderItem>> call, Throwable t) {
                                    Toast.makeText(getContext(), "Đặt hàng thất bại 2", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }

                        @Override
                        public void onFailure(Call<Order> call, Throwable t) {
                            Toast.makeText(getContext(), "Đặt hàng thất bại 1", Toast.LENGTH_SHORT).show();
                        }
                    });
                startPayment();
                }


        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSummaryBinding.inflate(inflater,container,false);
        return binding.getRoot();

    }

    public void startPayment() {

        final SummaryFragment activity = this;

        final Checkout co = new Checkout();



        try {
            JSONObject options = new JSONObject();
            options.put("name", "Pathak Print");
            options.put("description", "NoteBook Payment");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://firebasestorage.googleapis.com/v0/b/ecommerce-d6fcb.appspot.com/o/logo.jpg?alt=media&token=d9b208cf-2a54-4206-b7ae-9caf2328afc5");
            options.put("currency", "INR");
            String payment = String.valueOf(sum+50.0);
            // amount is in paise so please multiple it by 100
            //Payment failed Invalid amount (should be passed in integer paise. Minimum value is 100 paise, i.e. ₹ 1)
            double total = Double.parseDouble(payment);
            total = total * 100;
            options.put("amount", total);

            JSONObject preFill = new JSONObject();
            //preFill.put("email", user.getEmail());
            preFill.put("contact", getArguments().getString("phoneNumber"));

            options.put("prefill", preFill);

            //co.open(requireActivity(), options);
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_summaryFragment_to_paymentFragment2);

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_summaryFragment_to_paymentFragment2);
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {

    }
}