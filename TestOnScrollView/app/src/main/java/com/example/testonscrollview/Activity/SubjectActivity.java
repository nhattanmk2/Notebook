package com.example.testonscrollview.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.testonscrollview.Adapter.SubjectHorizontalAdapter;
import com.example.testonscrollview.Adapter.SubjectVerticalAdapter;
import com.example.testonscrollview.Effect.CustomCardView;
import com.example.testonscrollview.Interface.GotoLearnActivity;
import com.example.testonscrollview.Model.Subject;
import com.example.testonscrollview.Model.Word;
import com.example.testonscrollview.R;
import com.example.testonscrollview.databinding.ActivitySubjectBinding;

import com.example.testonscrollview.databinding.DialogDeleteBinding;
import com.example.testonscrollview.databinding.DialogSettingSubjectBinding;
import com.example.testonscrollview.databinding.DialogSortBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubjectActivity extends AppCompatActivity implements GotoLearnActivity {
    ActivitySubjectBinding subjectBinding;
    SubjectHorizontalAdapter subjectHorizontalAdapter;
    SubjectVerticalAdapter subjectVerticalAdapter;
    SnapHelper snapHelperForHorizontal;
    int StateSort = 1;
    ArrayList<Word> verticalList = new ArrayList<>();
    ArrayList<Word> verticalList2 = new ArrayList<>();
    ArrayList<Word> horizontalList = new ArrayList<>();
    ArrayList<Boolean> stateList = new ArrayList<>();
    Subject subject;
    private final int REQ_CODE_CHANGE_SUB_WORD = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subjectBinding = DataBindingUtil.setContentView(this, R.layout.activity_subject);
        setContentView(subjectBinding.getRoot());
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            subject = (Subject) bundle.getSerializable("subject");
            subjectBinding.title.setText(subject.getSubject_name());
            subjectBinding.count.setText(subject.getWord_count() + " từ vựng");
            subjectBinding.describe.setText("Describe " + subject.getSubject_describe());
        }

        addData(subject.getWordList());
        ViewForHorizontal();
        ViewForVertical();

        ((CustomCardView) subjectBinding.game1Img.getParent().getParent()).setGotoLearnActivity(this);
        ((CustomCardView) subjectBinding.game2Img.getParent().getParent()).setGotoLearnActivity(this);

        subjectBinding.scrollView.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    View touchView = subjectBinding.scrollView.getChildAt(0);
                    if (touchView != null) {
                        if (touchView instanceof LinearLayout) {
                            LinearLayout viewChildScroll = (LinearLayout) touchView;
                            if (viewChildScroll != null) {
                                if (viewChildScroll instanceof LinearLayout) {
                                    LinearLayout viewChildChildChildScroll = (LinearLayout) viewChildScroll.getChildAt(1);
                                    if (viewChildChildChildScroll != null) {
                                        for (int i = 0; i < viewChildChildChildScroll.getChildCount(); ++i) {
                                            isCheckScroll(viewChildChildChildScroll, i);
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
                return false;
            }
        });
        subjectBinding.back.setOnClickListener(v -> {
            onBackPressed();
        });
        subjectBinding.share.setOnClickListener(v -> {

        });
        subjectBinding.setting.setOnClickListener(v -> {
            openDialogSetting();
        });
        subjectBinding.cp34.setOnClickListener(v -> {
            openSortFilter();
        });
    }

    private void isCheckScroll(LinearLayout viewChildChildChildScroll, int i) {

        View onView = viewChildChildChildScroll.getChildAt(i);
        if (onView != null) {
            if (onView instanceof CustomCardView) {
                CustomCardView view = (CustomCardView) onView;
                if (view.isClicked()) {

                    view.setClicked(false);
                }
            }
        }
    }


    @Override
    public void onChoose(int state) {
        if (state == 0) {
            Intent goGame1 = new Intent(this, Game1.class);
            goGame1.putExtra("wordObject", horizontalList);
            goGame1.putExtra("stateCard", true);
            goGame1.putExtra("count_word", horizontalList.size());
            goGame1.putExtra("number_wrong", 0);
            goGame1.putExtra("number_correct", 0);
            goGame1.putExtra("learning", 0);
            startActivity(goGame1);

        } else if (state == 1) {
            Intent goGame2 = new Intent(this, Game2.class);
            goGame2.putExtra("wordObject", horizontalList);
            startActivity(goGame2);
        }
    }

    private void openSortFilter() {
        Dialog dialog = new Dialog(this);
        DialogSortBinding binding = DialogSortBinding.inflate(LayoutInflater.from(this));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(binding.getRoot());
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setWindowAnimations(R.style.SlideUpAnimation);

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.BOTTOM;
        window.setAttributes(windowAttributes);

        if (StateSort == 1) {
            binding.img2.setVisibility(View.VISIBLE);
            binding.img3.setVisibility(View.INVISIBLE);
        } else {
            binding.img2.setVisibility(View.INVISIBLE);
            binding.img3.setVisibility(View.VISIBLE);
        }

        binding.filter1.setOnClickListener(v -> {
            binding.img2.setVisibility(View.VISIBLE);
            binding.img3.setVisibility(View.INVISIBLE);
            Toast.makeText(v.getContext(), verticalList2.get(0).getWord(), Toast.LENGTH_SHORT).show();

            for (int i = 0; i < verticalList2.size(); ++i)
                verticalList.set(i, verticalList2.get(i));
            subjectVerticalAdapter.notifyDataSetChanged();
            StateSort = 1;
            dialog.dismiss();
        });
        binding.filter2.setOnClickListener(v -> {
            StateSort = 2;
            binding.img2.setVisibility(View.INVISIBLE);
            binding.img3.setVisibility(View.VISIBLE);

            //Thay thế bằng query database

            for (int i = 0; i < sort(verticalList).size(); ++i)
                verticalList.set(i, sort(verticalList).get(i));
            subjectVerticalAdapter.notifyDataSetChanged();

            dialog.dismiss();
        });
        dialog.show();
    }

    private ArrayList<Word> sort(ArrayList<Word> list) {

        Collections.sort(list);

        return list;
    }
    //Delete, Update, Share
    private void openDialogSetting() {
        Dialog dialog = new Dialog(this);
        DialogSettingSubjectBinding binding = DialogSettingSubjectBinding.inflate(LayoutInflater.from(this));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(binding.getRoot());
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setWindowAnimations(R.style.SlideUpAnimation);

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.BOTTOM;
        window.setAttributes(windowAttributes);

        binding.cp3.setOnClickListener(v -> {
            openDialogDelete();
            dialog.dismiss();
        });
        binding.cp2.setOnClickListener(v -> {
            Intent createSubject = new Intent(v.getContext(), CreateSubject.class);
            createSubject.putExtra("Title", "Chỉnh sửa học phần");
            createSubject.putExtra("ListWord", horizontalList);
            createSubject.putExtra("TypeCreate", 2);
            startActivityForResult(createSubject, REQ_CODE_CHANGE_SUB_WORD);
            dialog.dismiss();
        });
        binding.cp4.setOnClickListener(v -> {

        });

        binding.cp5.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    private void openDialogDelete() {
        Dialog dialog = new Dialog(this);
        DialogDeleteBinding binding = DialogDeleteBinding.inflate(LayoutInflater.from(this));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(binding.getRoot());
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setWindowAnimations(R.style.SlideUpAnimation);

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);

        binding.cancel.setOnClickListener(v -> dialog.dismiss());
        binding.accept.setOnClickListener(v -> {
            dialog.dismiss();

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            ProgressBar progressBar = new ProgressBar(this);

            alertDialogBuilder.setView(progressBar);

            AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialog.show();

            //Xóa database subject này
            Intent goToMainActivity = new Intent(this, MainActivity.class);
            new Handler().postDelayed(() -> {
                alertDialog.dismiss();

                goToMainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(goToMainActivity);

            }, 1000);

        });
        dialog.show();
    }

    private void ViewForVertical() {
        subjectVerticalAdapter = new SubjectVerticalAdapter(verticalList);
        subjectBinding.wordRvVer.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        subjectBinding.wordRvVer.setAdapter(subjectVerticalAdapter);
    }

    private void ViewForHorizontal() {
        float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        float elevationInDp = 2f;
        float elevationInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, elevationInDp, getResources().getDisplayMetrics());
        AnimatorSet frontAnim = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.front_animator);
        AnimatorSet backAnim = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.back_animator);

        subjectHorizontalAdapter = new SubjectHorizontalAdapter(horizontalList, stateList, scale, frontAnim, backAnim, elevationInPixels);
        subjectBinding.wordRvHor.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        subjectBinding.wordRvHor.setAdapter(subjectHorizontalAdapter);

        subjectBinding.wordRvHor.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int sum = 0;
            int previous = 1;

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                sum += dx;
                int pos = sum / 1079 + 1;

                if (pos > previous ) {
                    previous = pos;
                    dotMove(pos , true);
                }
                else if (pos < previous ) {
                    previous = pos;
                    dotMove(pos, false);
                }
            }
        });

        snapHelperForHorizontal = new LinearSnapHelper();
        snapHelperForHorizontal.attachToRecyclerView(subjectBinding.wordRvHor);
    }

    private void dotMove(int dot, boolean b) {
        if (b) {
            dot %= 5;
            if (dot == 1) {
                subjectBinding.dot1.setImageResource(R.drawable.bdot);
                subjectBinding.dot5.setImageResource(R.drawable.dot);
            }
            if (dot == 2) {
                subjectBinding.dot2.setImageResource(R.drawable.bdot);
                subjectBinding.dot1.setImageResource(R.drawable.dot);
            }
            if (dot == 3) {
                subjectBinding.dot3.setImageResource(R.drawable.bdot);
                subjectBinding.dot2.setImageResource(R.drawable.dot);
            }
            if (dot == 4) {
                subjectBinding.dot4.setImageResource(R.drawable.bdot);
                subjectBinding.dot3.setImageResource(R.drawable.dot);
            }
            if (dot == 0) {
                subjectBinding.dot5.setImageResource(R.drawable.bdot);
                subjectBinding.dot4.setImageResource(R.drawable.dot);
            }
        }
        if (!b) {
            dot %= 5;
            if (dot == 1) {
                subjectBinding.dot1.setImageResource(R.drawable.bdot);
                subjectBinding.dot2.setImageResource(R.drawable.dot);
            }
            if (dot == 2) {
                subjectBinding.dot2.setImageResource(R.drawable.bdot);
                subjectBinding.dot3.setImageResource(R.drawable.dot);
            }
            if (dot == 3) {
                subjectBinding.dot3.setImageResource(R.drawable.bdot);
                subjectBinding.dot4.setImageResource(R.drawable.dot);
            }
            if (dot == 4) {
                subjectBinding.dot4.setImageResource(R.drawable.bdot);
                subjectBinding.dot5.setImageResource(R.drawable.dot);
            }
            if (dot == 0) {
                subjectBinding.dot5.setImageResource(R.drawable.bdot);
                subjectBinding.dot1.setImageResource(R.drawable.dot);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQ_CODE_CHANGE_SUB_WORD && data != null) {
            ArrayList<Word> newWords = (ArrayList<Word>)data.getSerializableExtra("ResultSubject");
            horizontalList.clear();
            for (Word i : newWords) {
                horizontalList.add(i);
                stateList.add(true);
            }
            subjectHorizontalAdapter.notifyDataSetChanged();
        }
    }

    private void addData(List<Word> subject) {
        //TEST
        if (subject != null) {
            for (int i = 0; i < subject.size(); ++ i) {
                verticalList.add(subject.get(i));
                verticalList2.add(subject.get(i));
                horizontalList.add(subject.get(i));
                stateList.add(true);
            }
        }
        //TEST
        else {
            verticalList.add(new Word("Hello", "Xin chao"));
            verticalList.add(new Word("Bello", "Xin chao"));
            verticalList.add(new Word("Aello", "Xin chao"));
            verticalList.add(new Word("Thank u", "Cam on"));
            verticalList.add(new Word("Hello", "Xin chao"));

            verticalList2.add(new Word("Hello", "Xin chao"));
            verticalList2.add(new Word("Bello", "Xin chao"));
            verticalList2.add(new Word("Aello", "Xin chao"));
            verticalList2.add(new Word("Thank u", "Cam on"));
            verticalList2.add(new Word("Hello", "Xin chao"));

            horizontalList.add(new Word("Hello", "Xin chao"));
            horizontalList.add(new Word("Bello", "Xin chao"));
            horizontalList.add(new Word("Aello", "Xin chao"));
            horizontalList.add(new Word("Thank u", "Cam on"));
            horizontalList.add(new Word("Hello", "Xin chao"));
            horizontalList.add(new Word("Aello", "Xin chao"));
            horizontalList.add(new Word("Thank u", "Cam on"));
            horizontalList.add(new Word("Hello", "Xin chao"));

            stateList.add(true);
            stateList.add(true);
            stateList.add(true);
            stateList.add(true);
            stateList.add(true);
            stateList.add(true);
            stateList.add(true);
            stateList.add(true);
        }
    }
}