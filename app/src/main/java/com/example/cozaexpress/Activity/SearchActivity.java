package com.example.cozaexpress.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.cozaexpress.R;

public class SearchActivity extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView;

    AppCompatButton btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        AnhXa();


        String[] country = getResources().getStringArray(R.array.country);//sau này thay bằng tên sp import vào
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, country);
        autoCompleteTextView.setAdapter(arrayAdapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = autoCompleteTextView.getText().toString();
                Intent intent = new Intent(SearchActivity.this, ResultSeachActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("key", key);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private void AnhXa() {
        autoCompleteTextView = findViewById(R.id.autoCompleteTv);
        btnSearch = findViewById(R.id.btnSearch1);
    }


}