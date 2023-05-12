package com.example.cozaexpress.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class EmailUtil {
    public static void sendEmail(Context context, String recipientEmail, String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + recipientEmail));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }
}
