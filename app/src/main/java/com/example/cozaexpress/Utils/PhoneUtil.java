package com.example.cozaexpress.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class PhoneUtil {
    public static void makePhoneCall(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }
}
