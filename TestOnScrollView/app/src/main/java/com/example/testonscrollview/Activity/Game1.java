package com.example.testonscrollview.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.testonscrollview.Effect.CustomTextView;
import com.example.testonscrollview.Model.Word;
import com.example.testonscrollview.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class Game1 extends AppCompatActivity {
    CustomTextView item_1, item_2, item_3;
    TextView cnt_wrong, cnt_correct, title, result_wrong, result_correct;
    ImageView setting, flip, back;
    ProgressBar pb;
    ViewGroup rootLayout;
    FrameLayout replace, main;
    View wrong, correct, center;
    Button result_choose1, result_choose2;
    boolean isTextNow = true;
    boolean settingFaceCard = true;
    float scale;
    float elevationInDp = 2f;
    float elevationInPixels;
    AnimatorSet frontAnim;
    AnimatorSet backAnim;
    int count_wrong = 0, count_correct = 0, count_word = 0, progressValue = 0;
    ArrayList<Word> wordObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        rootLayout = (ViewGroup) inflater.inflate(R.layout.activity_game1, null);

        setContentView(rootLayout);
        main = findViewById(R.id.c3);
        replace = findViewById(R.id.c4);
        Intent intent = getIntent();

        //anim flip
        scale = getApplicationContext().getResources().getDisplayMetrics().density;
        elevationInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, elevationInDp, getResources().getDisplayMetrics());
        frontAnim = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.front_animator_rotatey);
        backAnim = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.back_animator_rotatey);

        //result
        result_wrong = findViewById(R.id.result_wrong);
        result_correct = findViewById(R.id.result_correct);
        result_choose1 = findViewById(R.id.submit_result);
        result_choose1.setBackgroundResource(R.drawable.game1_submit_1);
        result_choose2 = findViewById(R.id.submit_result2);
        result_choose2.setBackgroundResource(R.drawable.game1_result_submit2);

        result_choose1.setOnClickListener(v -> {
            onBackPressed();
        });
        result_choose2.setOnClickListener(v -> {
            Intent newIntent = new Intent(this, MainActivity.class);
            startActivity(newIntent);
            finish();
        });
        //main
        back = findViewById(R.id.c1_1);

        setting = findViewById(R.id.c1_3);

        cnt_wrong = findViewById(R.id.count_wrong);

        cnt_correct = findViewById(R.id.count_correct);

        title = findViewById(R.id.c1_2);

        pb = findViewById(R.id.c2_1);

        flip = findViewById(R.id.flip);

        //textview white
        item_1 = findViewById(R.id.item_c1);
        //textview front
        item_2 = findViewById(R.id.item_c2);

        //textview back
        item_3 = findViewById(R.id.item_c3);

        //wrong side
        wrong = findViewById(R.id.wrong);
        //correct side
        correct = findViewById(R.id.correct);

        back.setOnClickListener(v -> onBackPressed());

        if(intent != null ) extraIntent(intent);

        flip.setOnClickListener(v -> {
            flipCard();
        });

        //anim_drag_front
        item_2.setSt((st, v, flipState) -> {
            if (st && flipState) {
                createCenterView(v);
                item_2.setVisibility(View.INVISIBLE);
                item_1.setVisibility(View.VISIBLE);
            }
            else if(!st && flipState){
                item_2.setVisibility(View.VISIBLE);
                item_1.setVisibility(View.INVISIBLE);
            }
        });

        //anim_drag_back
        item_3.setSt((st, v, flipState) -> {
            if (st && flipState) {
                createCenterView(v);
                item_3.setVisibility(View.INVISIBLE);
                item_1.setVisibility(View.VISIBLE);
            }
            else if(!st && flipState){
                item_3.setVisibility(View.VISIBLE);
                item_1.setVisibility(View.INVISIBLE);
            }
        });

        //drag wrong
        wrong.setOnDragListener(onDragListener);
        //drag correct
        correct.setOnDragListener(onDragListener);

        setting.setOnClickListener(v -> openSettingDialog());
    }

    private void extraIntent(Intent intent) {
        wordObject = (ArrayList<Word>) intent.getSerializableExtra("wordObject");
        settingFaceCard = intent.getBooleanExtra("stateCard", true);

        count_word = intent.getIntExtra("count_word", 0);
        count_wrong = intent.getIntExtra("number_wrong", 0);
        count_correct = intent.getIntExtra("number_correct", 0);
        progressValue = intent.getIntExtra("learning", 0);

        setup();
    }

    private void flipCard() {
        item_2.setElevation(0);
        item_3.setElevation(0);

        new Handler().postDelayed(() -> {
            item_2.setElevation(elevationInPixels);
            item_3.setElevation(elevationInPixels);
        }, 700);

        if (isTextNow) {
            item_3.setVisibility(View.VISIBLE);

            item_2.setFlipState(false);
            item_3.setFlipState(true);

            frontAnim.setTarget(item_2);
            backAnim.setTarget(item_3);

            frontAnim.start();
            backAnim.start();
            new Handler().postDelayed(() -> {
                item_2.setVisibility(View.INVISIBLE);
            }, 800);
        }
        else {
            item_2.setVisibility(View.VISIBLE);

            item_2.setFlipState(true);
            item_3.setFlipState(false);

            frontAnim.setTarget(item_3);
            backAnim.setTarget(item_2);

            backAnim.start();
            frontAnim.start();

            new Handler().postDelayed(() -> {
                item_3.setVisibility(View.INVISIBLE);
            }, 800);
        }
        isTextNow = !isTextNow;
    }

    private void openSettingDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View dialogView = getLayoutInflater().inflate(R.layout.setting_g_one_dialog, null);
        bottomSheetDialog.setContentView(dialogView);

        TextView choose1 = dialogView.findViewById(R.id.choose1);
        TextView choose2 = dialogView.findViewById(R.id.choose2);
        TextView reset = dialogView.findViewById(R.id.cp4);
        if (settingFaceCard) {
            choose1.setBackgroundResource(R.drawable.rectangle_border);
            choose2.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        }
        else {
            choose1.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
            choose2.setBackgroundResource(R.drawable.rectangle_border);
        }
        choose1.setOnClickListener(v -> {
            if (!settingFaceCard) {
                choose1.setBackgroundResource(R.drawable.rectangle_border);
                choose2.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.white));
                settingFaceCard = !settingFaceCard;
                if (item_2.getAlpha() == 0)  flipCard();
            }
        });
        choose2.setOnClickListener(v -> {
            if (settingFaceCard) {
                choose1.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.white));
                choose2.setBackgroundResource(R.drawable.rectangle_border);
                settingFaceCard = !settingFaceCard;
                if (item_3.getAlpha() == 0) flipCard();
            }
        });
        reset.setOnClickListener(v -> {
            Reset();
            bottomSheetDialog.dismiss();
        });
        bottomSheetDialog.show();
    }




    private void createCenterView(View v) {

        center = new View(v.getContext());

        FrameLayout pa = (FrameLayout) v.getParent();

        int left = pa.getChildAt(0).getRight();

        int right = pa.getChildAt(1).getLeft();

        int height = pa.getHeight();

        FrameLayout.LayoutParams f = new FrameLayout.LayoutParams(right - left - 2, height);
        f.gravity = Gravity.CENTER;

        center.setLayoutParams(f);
        center.layout(left + 1, 0, right - 1, height);
        center.setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup)rootLayout.getChildAt(2)).addView(center);
        //don't drag
        center.setOnDragListener(onDragListener);
    }

    View.OnDragListener onDragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            if (event.getAction() == DragEvent.ACTION_DRAG_ENTERED) {

                if (v.getLeft() == 0) {
                    correct.setBackgroundColor(Color.TRANSPARENT);
                    v.setBackgroundColor(Color.RED);
                }
                else if (v.getRight() == 1080) {
                    wrong.setBackgroundColor(Color.TRANSPARENT);
                    v.setBackgroundColor(Color.GREEN);
                }
                else {
                    correct.setBackgroundColor(Color.TRANSPARENT);
                    wrong.setBackgroundColor(Color.TRANSPARENT);
                }
            }
            else  if (event.getAction() == DragEvent.ACTION_DROP) {

                correct.setBackgroundColor(Color.TRANSPARENT);
                wrong.setBackgroundColor(Color.TRANSPARENT);

                ((ViewGroup)rootLayout.getChildAt(2)).removeView(center);
                center = null;

                if (isTextNow) {
                    item_1.setVisibility(View.INVISIBLE);
                    item_2.setVisibility(View.VISIBLE);
                }
                else {
                    item_1.setVisibility(View.INVISIBLE);
                    item_3.setVisibility(View.VISIBLE);
                }
                if (v.getLeft() == wrong.getLeft() && v.getRight() == wrong.getRight()) {
                    count_wrong += 1;
                    progressValue += 1;
                    cnt_wrong.setText(String.valueOf(count_wrong));
                    if (isTextNow != settingFaceCard) {
                        flipCard();
                        new Handler().postDelayed(() -> onTheNext(), 1000);
                    }
                    else {
                        onTheNext();
                    }
                }
                else if (v.getLeft() == correct.getLeft() && v.getRight() == correct.getRight()) {
                    count_correct += 1;
                    progressValue += 1;
                    cnt_correct.setText(String.valueOf(count_correct));
                    if (isTextNow != settingFaceCard) {
                        flipCard();
                        new Handler().postDelayed(() -> onTheNext(), 1000);
                    }
                    else {
                        onTheNext();
                    }
                }
            }
            return true;
        }
    };

    private void onTheNext() {
        if (progressValue < count_word) {
            correct.setBackgroundColor(Color.TRANSPARENT);
            wrong.setBackgroundColor(Color.TRANSPARENT);
            center = null;
            setup();
        }
        else {
            //Put notification
            pb.setProgress(progressValue);
            main.setVisibility(View.INVISIBLE);
            replace.setVisibility(View.VISIBLE);
            result_wrong.setText(String.valueOf(count_wrong));
            result_correct.setText(String.valueOf(count_correct));
        }
    }
    private void Reset() {
        progressValue = 0;
        count_wrong = 0;
        count_correct = 0;
        if (main.getVisibility() == View.INVISIBLE) {
            main.setVisibility(View.VISIBLE);
            replace.setVisibility(View.INVISIBLE);
        }
        setup();
    }
    private void setup() {

        item_2.setText(wordObject.get(progressValue).getWord());
        item_3.setText(wordObject.get(progressValue).getMeaning());

        isTextNow = settingFaceCard;
        if (settingFaceCard) {
            item_2.setFlipState(true);
            item_3.setFlipState(false);
        }
        else {
            item_2.setAlpha(0);
            item_2.setVisibility(View.INVISIBLE);
            item_3.setAlpha(1f);
            item_3.setVisibility(View.VISIBLE);
            item_2.setFlipState(false);
            item_3.setFlipState(true);
        }

        title.setText((progressValue + 1) + "/" + count_word);

        cnt_wrong.setText(String.valueOf(count_wrong));
        cnt_correct.setText(String.valueOf(count_correct));

        pb.setMax(count_word);
        pb.setProgress(progressValue);
    }
    private void putIntent() {
//        Intent goGame1 = new Intent(this, Game1.class);
//        goGame1.putExtra("wordObject", wordObject);
//        goGame1.putExtra("stateCard", settingFaceCard);
//
//        goGame1.putExtra("count_word", count_word);
//        goGame1.putExtra("number_wrong", count_wrong);
//        goGame1.putExtra("number_correct", count_correct);
//        goGame1.putExtra("learning", progressValue + 1);
//        startActivity(goGame1);
//        finish();
    }
}