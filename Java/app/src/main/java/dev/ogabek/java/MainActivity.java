package dev.ogabek.java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.toString();
    TextView tv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    private void initViews() {
        tv_main = findViewById(R.id.tv_main);
        setLinks(tv_main, tv_main.getText().toString());
    }

    private void setLinks(TextView tv_main, String text) {
        SpannableString spannableString = new SpannableString(text);
        String[] strings = text.split(" ");
        for (String s : strings) {
            SpannableString string = new SpannableString(s);
            if (!URLUtil.isValidUrl(s)) {
                continue;
            }
            spannableString.setSpan(new ClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {

                }

                @Override
                public void updateDrawState(@NonNull TextPaint ds) {
                    ds.setColor(Color.parseColor("#33b5e5"));
                    ds.setUnderlineText(false);
                }
            }, text.indexOf(string.toString()), text.indexOf(string.toString()) + string.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        tv_main.setMovementMethod(new LinkMovementMethod());
        tv_main.setText(spannableString);
    }
}