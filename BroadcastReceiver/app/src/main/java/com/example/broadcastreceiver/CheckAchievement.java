package com.example.broadcastreceiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckAchievement {
    private int number_get;
    private int type;
    private final List<Integer> days = new ArrayList<>(Arrays.asList(3, 5, 7, 10, 15, 20, 25, 30, 40, 50));
    Context context;
    public CheckAchievement(int type, int number_get, Context context) {
        this.number_get = number_get;
        this.context = context;
        this.type = type;
    }
    public void SolvedAchievement() {
        Log.d("TAG", "SolvedAchievement: " + number_get);
        if (!days.contains(number_get)) return;
        Intent intent = new Intent("com.example.ACHIEVEMENT_UNLOCKED");

        if (type == 1) {
            intent.putExtra("img", context.getResources().getIdentifier(String.valueOf(R.drawable.a0), "drawable", context.getPackageName()));
            intent.putExtra("title_achievement", "Chúc mừng bạn đã tham gia được "
                    + number_get + " ngày ! \n Mọi nỗ lực của bạn sẽ được ghi nhận.");
        }
        if (type == 2) {
            intent.putExtra("img", context.getResources().getIdentifier(String.valueOf(R.drawable.b0), "drawable", context.getPackageName()));
            intent.putExtra("title_achievement", "Chúc mừng bạn đã tạo được"
                    + number_get + " thư mục! Bạn hãy cố gắng học chăm nhé!");
        }
        if (type == 3) {
            intent.putExtra("img", context.getResources().getIdentifier(String.valueOf(R.drawable.c0), "drawable", context.getPackageName()));
            intent.putExtra("title_achievement", "Chúc mừng bạn đã học được"
                    + number_get + " học phần! Nỗ lực sẽ được đền đáp xứng đáng!");
        }
        context.sendBroadcast(intent);

    }

}
