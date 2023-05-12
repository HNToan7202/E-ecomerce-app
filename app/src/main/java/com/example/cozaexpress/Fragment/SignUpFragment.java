package com.example.cozaexpress.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.cozaexpress.R;
import com.hbb20.CountryCodePicker;

public class SignUpFragment extends Fragment {

    EditText edtUsername,edtPassword,edtEmail,edtPhone,edtAddress,edtConfirmPassword;

    com.rilixtech.widget.countrycodepicker.CountryCodePicker ccp;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_signup, container, false);
        edtUsername = view.findViewById(R.id.etName);
        ccp = view.findViewById(R.id.ccp);
        return view;

    }
}