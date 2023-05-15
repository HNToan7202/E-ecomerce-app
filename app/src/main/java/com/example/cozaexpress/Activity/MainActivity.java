package com.example.cozaexpress.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cozaexpress.Adapter.ViewPagerAdapter;
import com.example.cozaexpress.Fragment.CartFragment;
import com.example.cozaexpress.R;
import com.example.cozaexpress.DataLocal.DataLocalManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.Label;
import jxl.write.WriteException;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcvData;
    SwipeRefreshLayout swipeRefreshLayout; //refresh lại trang


    //Nhấn 2 lần để thoát
    private long backPressTime;
    Toast mToast;
    BottomNavigationView mBottom_nav;
    ViewPager mViewPager;

    private void exportToExcel() {
        // Create a WorkbookSettings object to customize the Workbook
        WorkbookSettings workbookSettings = new WorkbookSettings();
        workbookSettings.setLocale(new Locale("en", "EN"));

        // Get the external storage directory
        File externalStorageDir = Environment.getExternalStorageDirectory();

        // Create the Excel file
        String filePath = externalStorageDir.getAbsolutePath() + "/doanhthu.xls";
        WritableWorkbook workbook;
        try {
            workbook = Workbook.createWorkbook(new File(filePath), workbookSettings);

            // Create a new sheet
            WritableSheet sheet = workbook.createSheet("Doanh thu", 0);

            // Data: products and revenues
            String[] products = {"Sản phẩm A", "Sản phẩm B", "Sản phẩm C"};
            double[] revenues = {1000.0, 2000.0, 1500.0};

            // Add headers
            sheet.addCell(new Label(0, 0, "Sản phẩm"));
            sheet.addCell(new Label(1, 0, "Doanh thu"));

            // Add data
            for (int i = 0; i < products.length; i++) {
                sheet.addCell(new Label(0, i + 1, products[i]));
                sheet.addCell(new jxl.write.Number(1, i + 1, revenues[i]));
            }

            // Write the workbook
            workbook.write();
            workbook.close();

            //String filePath = externalStorageDir.getAbsolutePath() + "/doanhthu.xls";
            File excelFile = new File(filePath);

            Log.d("ExportToExcel", "File path: " + filePath);

            if (excelFile.exists()) {
                // Tệp Excel đã được lưu thành công
                Log.d("ExportToExcel", "Excel file saved successfully.");
            } else {
                // Không thể lưu tệp Excel
                Log.d("ExportToExcel", "Failed to save Excel file.");
            }
        } catch (IOException | WriteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FirstInstall();
        //exportToExcel();
        AnhXa();
        setUpViewPager();
        setUpNavigationView();

    }

    public void navigateToCartFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Tạo một instance của CartFragment
        CartFragment cartFragment = new CartFragment();

        // Thay thế Fragment hiện tại bằng CartFragment
        fragmentTransaction.replace(R.id.action_message, cartFragment);

        // Hoàn thành FragmentTransaction
        fragmentTransaction.commit();
    }

    public void navigateToMessageFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Tạo một instance của CartFragment
        CartFragment cartFragment = new CartFragment();

        // Thay thế Fragment hiện tại bằng CartFragment
        fragmentTransaction.replace(R.id.action_message, cartFragment);

        // Hoàn thành FragmentTransaction
        fragmentTransaction.commit();
    }

    private void setUpNavigationView() {
        mBottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.action_mall:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.action_message:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.action_cart:
                        mViewPager.setCurrentItem(3);
                        break;
                    case R.id.action_account:
                        mViewPager.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });
    }

    private void AnhXa() {
        mBottom_nav = findViewById(R.id.bottom_nav);
        mViewPager = findViewById(R.id.viewPager);
    }

    private void setUpViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //sự kiện chuyển page khi xử lý page
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0 :
                        mBottom_nav.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1 :
                        mBottom_nav.getMenu().findItem(R.id.action_mall).setChecked(true);
                        break;
                    case 2 :
                        mBottom_nav.getMenu().findItem(R.id.action_message).setChecked(true);
                        break;
                    case 3 :
                        mBottom_nav.getMenu().findItem(R.id.action_cart).setChecked(true);
                        break;
                    case 4 :
                        mBottom_nav.getMenu().findItem(R.id.action_account).setChecked(true);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void FirstInstall() {
        if(!DataLocalManager.getFirstInstall()){
            Toast.makeText(this, "First Install App", Toast.LENGTH_LONG).show();
            DataLocalManager.setFirstInstall(true);
        }
    }

    @Override
    public void onBackPressed() {
        if(backPressTime + 2000 > System.currentTimeMillis()){
            mToast.cancel();
            super.onBackPressed();
            return;
        }else {
            mToast = Toast.makeText(MainActivity.this, "Nhấn lần nữa để thoát", Toast.LENGTH_LONG);
            mToast.show();
        }
        backPressTime = System.currentTimeMillis();
    }
}