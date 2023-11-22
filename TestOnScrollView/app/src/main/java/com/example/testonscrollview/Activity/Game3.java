package com.example.testonscrollview.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.widget.TextView;

import com.example.testonscrollview.Adapter.Game3Adapter;
import com.example.testonscrollview.Model.Answer;
import com.example.testonscrollview.R;
import com.example.testonscrollview.databinding.ActivityGame3Binding;
import com.example.testonscrollview.databinding.DialogBroadcastBinding;
import com.example.testonscrollview.databinding.Game3OkDialogBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Game3 extends AppCompatActivity {
    ActivityGame3Binding game3Binding;
    private int stateDifficult = 1;
    private int countQuestions = 10;
    private int nowGame = 0;
    private boolean suggest = true;
    //Test
    Answer data;
    Game3Adapter game3Adapter;

    SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game3Binding = DataBindingUtil.setContentView(this, R.layout.activity_game3);
        setContentView(game3Binding.getRoot());
        game3Binding.c11.setOnClickListener(v -> {
            onBackPressed();
        });
        game3Binding.game3Rv.setLayoutManager(new GridLayoutManager(this, 5));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, 0) {
            boolean isUserDragging = false;

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Game3Adapter adapter = (Game3Adapter) recyclerView.getAdapter();
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                adapter.moveItem(from, to);

                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);

                if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                    viewHolder.itemView.setAlpha(0.8f);
                    isUserDragging = true;
                } else {
                    isUserDragging = false;
                    String nowWord = "";
                    for (Character i : game3Adapter.getList1())
                        nowWord += i;
                    solve(nowWord, data.getWord1());
                }

            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setAlpha(1f);
            }

        });

        itemTouchHelper.attachToRecyclerView(game3Binding.game3Rv);

        game3Binding.submitResult.setBackgroundResource(R.drawable.game1_submit_1);

        game3Binding.submitResult.setOnClickListener(v -> onBackPressed());

        game3Binding.submitResult2.setBackgroundResource(R.drawable.game1_result_submit2);

        game3Binding.submitResult2.setOnClickListener(v -> {
            Intent newIntent = new Intent(v.getContext(), MainActivity.class);
            startActivity(newIntent);
            finish();
        });


        SettingView();
    }

    private void SettingView() {
        game3Binding.easy.setBackgroundResource(R.drawable.rectangle_border);
        game3Binding.medium.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        game3Binding.hard.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        game3Binding.vhard.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        game3Binding.medium.setOnClickListener(stateDifficultListener);
        game3Binding.easy.setOnClickListener(stateDifficultListener);
        game3Binding.hard.setOnClickListener(stateDifficultListener);
        game3Binding.vhard.setOnClickListener(stateDifficultListener);

        game3Binding.c10.setBackgroundResource(R.drawable.rectangle_border);
        game3Binding.c20.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        game3Binding.c25.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        game3Binding.c10.setOnClickListener(countQuestionsListener);
        game3Binding.c20.setOnClickListener(countQuestionsListener);
        game3Binding.c25.setOnClickListener(countQuestionsListener);


        game3Binding.ready.setOnClickListener(v -> {
            countQuestions = 3;
            game3Binding.c2.setVisibility(View.INVISIBLE);
            game3Binding.c3.setVisibility(View.VISIBLE);
            suggest = game3Binding.btnMode.isChecked();
            GameView();
        });
    }

    ArrayList<Character> ques = new ArrayList<>();
    private int seconds = 0;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            seconds++;
            int hours = seconds / 3600;
            int minutes = (seconds % 3600) / 60;
            int remainingSeconds = seconds % 60;

            String time = String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
            game3Binding.time.setText("Time: " + time);

            handler.postDelayed(this, 1000);
        }
    };

    private void GameView() {

        if (nowGame == 0) handler.post(runnable);

        if (nowGame >= countQuestions) {
            EndGame();
            return;
        }

        nowGame++;
        game3Binding.notification.setText(R.string.game3_notification_0);
        game3Binding.learn.setText("Số lượng câu đã làm: " + nowGame + "/" + countQuestions);

        data = Query(stateDifficult);
        ques.clear();

        for (int i = 0; i < data.getWord().length(); ++i)
            ques.add(data.getWord().charAt(i));

        game3Adapter = new Game3Adapter(ques);

        game3Binding.game3Rv.setAdapter(null);
        game3Binding.game3Rv.setAdapter(game3Adapter);
        game3Adapter.notifyDataSetChanged();

    }

    private void solve(String nowWord, String word1) {

        List<Integer> arr = new ArrayList<>();
        List<Integer> has = new ArrayList<>();

        for (int i = 0; i < nowWord.length(); i++)
            for (int j = 0; j < word1.length(); j++)
                if (nowWord.charAt(i) == word1.charAt(j)) {
                    boolean check = true;
                    for (int k = 0; k < has.size(); ++k)
                        if (j == has.get(k)) {
                            check = false;
                            break;
                        }
                    if (check) {
                        arr.add(j);
                        has.add(j);
                        break;
                    }
                }
        String res = "";
        for (int i = 0; i < arr.size(); ++i) res += String.valueOf(arr.get(i));
        int[] dp = new int[arr.size()];
        int maxLength = 1;
        dp[0] = 1;
        for (int i = 1; i < arr.size(); i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr.get(i) > arr.get(j) && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    if (dp[i] > maxLength) {
                        maxLength = dp[i];
                    }
                }
            }
        }
        if (arr.size() - maxLength == 4) {
            game3Binding.notification.setText(R.string.game3_notification_1);
            AnimationFor(game3Binding.notification);
        }
        if (arr.size() - maxLength == 3) {
            game3Binding.notification.setText(R.string.game3_notification_2);
            AnimationFor(game3Binding.notification);
        }
        if (arr.size() - maxLength == 2) {
            game3Binding.notification.setText(R.string.game3_notification_3);
            AnimationFor(game3Binding.notification);
        }
        if (arr.size() - maxLength == 1) {
            game3Binding.notification.setText(R.string.game3_notification_4);
            AnimationFor(game3Binding.notification);
        }
        if (arr.size() - maxLength == 0) {
            CongratulationDialog();
            GameView();
        }
    }

    private void CongratulationDialog() {

        Dialog dialog = new Dialog(this);
        Game3OkDialogBinding binding = Game3OkDialogBinding.inflate(LayoutInflater.from(this));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(binding.getRoot());
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }

        window.setLayout(1000, 600);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setWindowAnimations(R.style.SlideUpAnimation);

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);
        binding.title.setText(data.getWord1() + " là từ khóa của câu đố này. \n Từ " + data.getWord1() + " có nghĩa là: " + data.getMeaning());

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }, 3000);

        dialog.show();

    }

    //Test
    String idUser = "123";

    private void EndGame() {
        game3Binding.c3.setVisibility(View.INVISIBLE);
        game3Binding.c4.setVisibility(View.VISIBLE);
        handler.removeCallbacks(runnable);

        sharedPrefs = getSharedPreferences(idUser + "RecordGame3", MODE_PRIVATE);
        String query = String.valueOf(countQuestions) + "questions"
                + String.valueOf(stateDifficult) + "difficult";

        String value = sharedPrefs.getString(query, "");
        Log.d("TAG", "EndGame: " + query + " " + (value == null));
        Map<String, Integer> dataList;
        Gson gson = new Gson();

        if (!value.isEmpty()) {
            Type type = new TypeToken<Map<String, Integer>>() {
            }.getType();
            dataList = gson.fromJson(value, type);
        } else {
            dataList = new HashMap<>();
        }

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateString = dateFormat.format(calendar.getTime());


        dataList.put(dateString, seconds);

        TreeMap<String, Integer> sort = new TreeMap<>((key1, key2) -> {
            Integer value1 = dataList.get(key1);
            Integer value2 = dataList.get(key2);
            int result = value1.compareTo(value2);
            if (result == 0) {
                return key1.compareTo(key2);
            } else {
                return result;
            }
        });
        sort.putAll(dataList);

        if (sort.size() > 5) {
            Map.Entry<String, Integer> lastEntry = sort.pollLastEntry();
            if (lastEntry.getValue() != seconds) {
                newRecord(sort, 1);
            }
            newRecord(sort, 0);
        } else {
            newRecord(sort, 1);
        }
        String newValue = "";
        newValue = gson.toJson(sort);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(query, newValue);

        editor.apply();

    }

    private void newRecord(Map<String, Integer> dataList, int state) {
        String result = "", mode = "";
        int cnt = 1;
        if (state == 1) {
            game3Binding.userNotification.setText("Chúc mừng bạn đã đạt thành tựu mới");
        }
        if (stateDifficult == 1) mode = "Easy";
        if (stateDifficult == 2) mode = "Medium";
        if (stateDifficult == 3) mode = "Hard";
        if (stateDifficult == 4) mode = "2Hard";

        game3Binding.titleRecord.setText("Ranking Record on " + countQuestions + " Questions " + mode +
                " Mode");

        for (Map.Entry<String, Integer> entry : dataList.entrySet()) {
            int key = entry.getValue();

            int hours = key / 3600;
            int minutes = (key % 3600) / 60;
            int remainingSeconds = key % 60;

            String timeString = String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
            String value = entry.getKey();
            result += "Ranking" + " " + cnt + "\n\n";
            result += timeString + " " + value + "\n\n";
            game3Binding.endTitle.setText(result);
            cnt++;
        }
    }

    private void AnimationFor(TextView notification) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(notification, "translationX", 0, 50, -50, 50, -50, 0);
        animator.setDuration(800);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();

        int[] colors = {Color.WHITE, Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.WHITE};

        ObjectAnimator colorAnimator = ObjectAnimator.ofInt(notification, "backgroundColor", colors);
        colorAnimator.setEvaluator(new ArgbEvaluator());
        colorAnimator.setDuration(800);
        colorAnimator.start();
    }
    //Test
    private Answer Query(int stateDifficult) {
        String question = "elhol";
        String target = "hello";
        String meaning = "Xin chao";
        return new Answer(question, target, meaning);
    }

    View.OnClickListener stateDifficultListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == game3Binding.easy) {
                game3Binding.easy.setBackgroundResource(R.drawable.rectangle_border);
                game3Binding.medium.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.white));
                game3Binding.hard.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.white));
                game3Binding.vhard.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.white));
                stateDifficult = 1;
            } else if (v == game3Binding.medium) {
                game3Binding.easy.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.white));
                game3Binding.medium.setBackgroundResource(R.drawable.rectangle_border);
                game3Binding.hard.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.white));
                game3Binding.vhard.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.white));
                stateDifficult = 2;
            } else if (v == game3Binding.hard) {
                game3Binding.easy.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.white));
                game3Binding.medium.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.white));
                game3Binding.hard.setBackgroundResource(R.drawable.rectangle_border);
                game3Binding.vhard.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.white));
                stateDifficult = 3;
            } else if (v == game3Binding.vhard) {
                game3Binding.easy.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.white));
                game3Binding.medium.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.white));
                game3Binding.hard.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.white));
                game3Binding.vhard.setBackgroundResource(R.drawable.rectangle_border);
                stateDifficult = 4;
            }
        }
    };
    View.OnClickListener countQuestionsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == game3Binding.c10) {
                countQuestions = 10;
                game3Binding.c10.setBackgroundResource(R.drawable.rectangle_border);
                game3Binding.c20.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.white));
                game3Binding.c25.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.white));
            } else if (v == game3Binding.c20) {
                countQuestions = 20;
                game3Binding.c10.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.white));
                game3Binding.c20.setBackgroundResource(R.drawable.rectangle_border);
                game3Binding.c25.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.white));
            } else if (v == game3Binding.c25) {
                countQuestions = 25;
                game3Binding.c10.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.white));
                game3Binding.c20.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.white));
                game3Binding.c25.setBackgroundResource(R.drawable.rectangle_border);
            }
        }
    };
}