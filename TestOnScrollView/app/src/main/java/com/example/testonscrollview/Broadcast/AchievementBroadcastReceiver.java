package com.example.testonscrollview.Broadcast;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;


import androidx.core.content.ContextCompat;

import com.example.testonscrollview.R;

import com.example.testonscrollview.databinding.DialogBroadcastBinding;

public class AchievementBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent != null) {
            int drawableId = intent.getIntExtra("img", 0);
            String text = intent.getStringExtra("title_achievement");
            Drawable drawable = ContextCompat.getDrawable(context, drawableId);
            openDialog(text, drawable, context);
        }
    }

    private void openDialog(String text, Drawable drawable, Context context) {
        Dialog dialog = new Dialog(context);
        DialogBroadcastBinding binding = DialogBroadcastBinding.inflate(LayoutInflater.from(context));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(binding.getRoot());
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }

        window.setLayout(1000, 600);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setWindowAnimations(R.style.SlideUpAnimation);
        binding.imgSubject.setImageDrawable(drawable);
        binding.textSubject.setText(text);
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;

        window.setAttributes(windowAttributes);

        Handler handler = new Handler();

        handler.postDelayed(() -> {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }, 3000);

        dialog.show();
    }
}
