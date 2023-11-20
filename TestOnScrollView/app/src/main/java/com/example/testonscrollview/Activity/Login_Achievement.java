package com.example.testonscrollview.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.testonscrollview.Adapter.LoginAcAdapter;
import com.example.testonscrollview.Model.HomeAchievement;
import com.example.testonscrollview.R;
import com.example.testonscrollview.databinding.ActivityLoginAchievementBinding;

import java.util.ArrayList;

public class Login_Achievement extends AppCompatActivity {
    ActivityLoginAchievementBinding achievementBinding;
    ArrayList<HomeAchievement> arrayList = new ArrayList<>();
    LoginAcAdapter loginAcAdapter;
    //Test
    private int stateUser = 3;
    private boolean stateShow = false;
    private int typeAchievement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        achievementBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_achievement);
        setContentView(achievementBinding.getRoot());

        Intent data = getIntent();
        typeAchievement = data.getIntExtra("TypeAchievement", 1);

        if (typeAchievement == 1) {
            achievementBinding.tv.setText(R.string.achievement_1);
        }
        if (typeAchievement == 2) {
            achievementBinding.tv.setText(R.string.achievement_2);
        }
        if (typeAchievement == 3) {
            achievementBinding.tv.setText(R.string.achievement_3);
        }

        addDataHide(typeAchievement);

        achievementBinding.loginRv.setLayoutManager(new GridLayoutManager(this, 2));
        loginAcAdapter = new LoginAcAdapter(arrayList);

        achievementBinding.loginRv.setAdapter(loginAcAdapter);

        achievementBinding.back.setOnClickListener(v -> onBackPressed());

        achievementBinding.show.setOnClickListener(v -> {
            if (!stateShow) {
                arrayList.clear();
                addDataShow(typeAchievement);
                loginAcAdapter.notifyDataSetChanged();
                achievementBinding.show.setText("Thu gọn");
            } else {
                arrayList.clear();
                addDataHide(typeAchievement);
                loginAcAdapter.notifyDataSetChanged();
                achievementBinding.show.setText("Xem tất cả");
            }
            stateShow = !stateShow;
        });
    }

    private void addDataHide(int typeAchievement) {
        if (typeAchievement == 1) {
            if (stateUser >= 3) {
                arrayList.add(new HomeAchievement(R.drawable.a1, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.a11, getResources().getString(R.string.achievement_1_title_false)));
            }
            if (stateUser >= 5) {
                arrayList.add(new HomeAchievement(R.drawable.a2, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.a22, getResources().getString(R.string.achievement_1_title_false)));
            }
            if (stateUser >= 7) {
                arrayList.add(new HomeAchievement(R.drawable.a3, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.a33, getResources().getString(R.string.achievement_1_title_false)));
            }

            if (stateUser >= 14) {
                arrayList.add(new HomeAchievement(R.drawable.a4, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.a44, getResources().getString(R.string.achievement_1_title_false)));
            }
        }
        if (typeAchievement == 2) {
            if (stateUser >= 1) {
                arrayList.add(new HomeAchievement(R.drawable.b1, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.b11, getResources().getString(R.string.achievement_1_title_false)));
            }
            if (stateUser >= 3) {
                arrayList.add(new HomeAchievement(R.drawable.b2, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.b22, getResources().getString(R.string.achievement_1_title_false)));
            }
            if (stateUser >= 7) {
                arrayList.add(new HomeAchievement(R.drawable.b3, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.b33, getResources().getString(R.string.achievement_1_title_false)));
            }

            if (stateUser >= 10) {
                arrayList.add(new HomeAchievement(R.drawable.b4, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.b44, getResources().getString(R.string.achievement_1_title_false)));
            }
        }
        if (typeAchievement == 3) {
            if (stateUser >= 1) {
                arrayList.add(new HomeAchievement(R.drawable.c1, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.c11, getResources().getString(R.string.achievement_1_title_false)));
            }
            if (stateUser >= 5) {
                arrayList.add(new HomeAchievement(R.drawable.c2, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.c22, getResources().getString(R.string.achievement_1_title_false)));
            }
            if (stateUser >= 10) {
                arrayList.add(new HomeAchievement(R.drawable.c3, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.c33, getResources().getString(R.string.achievement_1_title_false)));
            }

            if (stateUser >= 15) {
                arrayList.add(new HomeAchievement(R.drawable.c4, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.c44, getResources().getString(R.string.achievement_1_title_false)));
            }
        }
    }

    //Test
    private void addDataShow(int typeAchievement) {
        if (typeAchievement == 1) {
            if (stateUser >= 3) {
                arrayList.add(new HomeAchievement(R.drawable.a1, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.a11, getResources().getString(R.string.achievement_1_title_false)));
            }
            if (stateUser >= 5) {
                arrayList.add(new HomeAchievement(R.drawable.a2, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.a22, getResources().getString(R.string.achievement_1_title_false)));
            }
            if (stateUser >= 7) {
                arrayList.add(new HomeAchievement(R.drawable.a3, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.a33, getResources().getString(R.string.achievement_1_title_false)));
            }

            if (stateUser >= 14) {
                arrayList.add(new HomeAchievement(R.drawable.a4, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.a44, getResources().getString(R.string.achievement_1_title_false)));
            }
            if (stateUser >= 21) {
                arrayList.add(new HomeAchievement(R.drawable.a5, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.a55, getResources().getString(R.string.achievement_1_title_false)));
            }
            if (stateUser >= 28) {
                arrayList.add(new HomeAchievement(R.drawable.a6, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.a66, getResources().getString(R.string.achievement_1_title_false)));
            }
        }
        /////////////////////2
        if (typeAchievement == 2) {
            if (stateUser >= 1) {
                arrayList.add(new HomeAchievement(R.drawable.b1, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.b11, getResources().getString(R.string.achievement_1_title_false)));
            }
            if (stateUser >= 3) {
                arrayList.add(new HomeAchievement(R.drawable.b2, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.b22, getResources().getString(R.string.achievement_1_title_false)));
            }
            if (stateUser >= 7) {
                arrayList.add(new HomeAchievement(R.drawable.b3, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.b33, getResources().getString(R.string.achievement_1_title_false)));
            }

            if (stateUser >= 10) {
                arrayList.add(new HomeAchievement(R.drawable.b4, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.b44, getResources().getString(R.string.achievement_1_title_false)));
            }
            if (stateUser >= 15) {
                arrayList.add(new HomeAchievement(R.drawable.b5, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.b55, getResources().getString(R.string.achievement_1_title_false)));
            }
            if (stateUser >= 20) {
                arrayList.add(new HomeAchievement(R.drawable.b6, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.b66, getResources().getString(R.string.achievement_1_title_false)));
            }
            if (stateUser >= 25) {
                arrayList.add(new HomeAchievement(R.drawable.b7, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.b77, getResources().getString(R.string.achievement_1_title_false)));
            }
            if (stateUser >= 30) {
                arrayList.add(new HomeAchievement(R.drawable.b8, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.b88, getResources().getString(R.string.achievement_1_title_false)));
            }
        }

        ///////////////3
        if (typeAchievement == 3) {
            if (stateUser >= 1) {
                arrayList.add(new HomeAchievement(R.drawable.c1, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.c11, getResources().getString(R.string.achievement_1_title_false)));
            }
            if (stateUser >= 5) {
                arrayList.add(new HomeAchievement(R.drawable.c2, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.c22, getResources().getString(R.string.achievement_1_title_false)));
            }
            if (stateUser >= 10) {
                arrayList.add(new HomeAchievement(R.drawable.c3, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.c33, getResources().getString(R.string.achievement_1_title_false)));
            }

            if (stateUser >= 15) {
                arrayList.add(new HomeAchievement(R.drawable.c4, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.c44, getResources().getString(R.string.achievement_1_title_false)));
            }
            if (stateUser >= 20) {
                arrayList.add(new HomeAchievement(R.drawable.c5, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.c55, getResources().getString(R.string.achievement_1_title_false)));
            }
            if (stateUser >= 30) {
                arrayList.add(new HomeAchievement(R.drawable.c6, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.c66, getResources().getString(R.string.achievement_1_title_false)));
            }
            if (stateUser >= 40) {
                arrayList.add(new HomeAchievement(R.drawable.c7, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.c77, getResources().getString(R.string.achievement_1_title_false)));
            }
            if (stateUser >= 50) {
                arrayList.add(new HomeAchievement(R.drawable.c8, getResources().getString(R.string.achievement_1_title_true)));
            } else {
                arrayList.add(new HomeAchievement(R.drawable.c88, getResources().getString(R.string.achievement_1_title_false)));
            }
        }
    }
}