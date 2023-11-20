package com.example.testonscrollview.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import com.example.testonscrollview.Model.Word;
import com.example.testonscrollview.R;
import com.example.testonscrollview.databinding.ActivityGame2Binding;
import com.example.testonscrollview.databinding.DialogCreateFolderBinding;
import com.example.testonscrollview.databinding.Game2CorrectPopupBinding;
import com.example.testonscrollview.databinding.Game2WrongPopupBinding;
import com.example.testonscrollview.databinding.SettingG2DialogBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Random;

public class Game2 extends AppCompatActivity {
    ActivityGame2Binding game2Binding;
    ArrayList<Word> words;
    int count_wrong = 0, count_correct = 0, count_word = 0, progressValue = 0, randomNumber = 0;

    int count_easy_correct = 0, count_medium_correct = 0, count_hard_correct = 0;
    //Trạng thái của người chọn
    int selected = 0;
    //Trạng thái mặt câu hỏi
    boolean state = true;
    //Trạng thái loại game chơi
    int typeGame = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game2Binding = DataBindingUtil.setContentView(this, R.layout.activity_game2);
        setContentView(game2Binding.getRoot());

        Intent intent = getIntent();
        data(intent);

        game2Binding.c11.setOnClickListener(v -> onBackPressed());

        game2Binding.c13.setOnClickListener(v -> openSettingDialog());

        game2Binding.opt1.setOnClickListener(onClickListener);
        game2Binding.opt2.setOnClickListener(onClickListener);
        game2Binding.opt3.setOnClickListener(onClickListener);
        game2Binding.opt4.setOnClickListener(onClickListener);

        game2Binding.submit.setOnClickListener(v -> {
            if (selected != randomNumber) {
                setWrong(selected);
                setCorrect(randomNumber);
                count_wrong++;
                progressValue++;
                new Handler().postDelayed(() -> dialogWrong(), 200);
            } else {
                setCorrect(randomNumber);
                count_correct++;
                progressValue++;
                if (typeGame == 1) count_easy_correct ++;
                else if (typeGame == 2) count_medium_correct ++;
                else  if (typeGame == 3) count_hard_correct ++;
                new Handler().postDelayed(() -> dialogCorrect(), 200);
            }
        });

        //Completed
        game2Binding.submitResult.setBackgroundResource(R.drawable.game1_submit_1);

        game2Binding.submitResult.setOnClickListener(v -> onBackPressed());

        game2Binding.submitResult2.setBackgroundResource(R.drawable.game1_result_submit2);

        game2Binding.submitResult2.setOnClickListener(v -> {
            Intent newIntent = new Intent(v.getContext(), MainActivity.class);
            startActivity(newIntent);
            finish();
        });
    }

    private void dialogWrong() {
        Dialog dialog = new Dialog(this);

        Game2WrongPopupBinding binding = Game2WrongPopupBinding.inflate(LayoutInflater.from(this));
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

        binding.title.setText("Bạn đã chọn chưa đúng đáp án!!!");

        if (state) {
            binding.title1.setText("Giải thích câu hỏi:" + " " + game2Binding.questionText.getText() + " đáp án đúng cho câu hỏi này là : "
                    + words.get(progressValue - 1).getMeaning());
        } else {
            binding.title1.setText("Giải thích câu hỏi:" + " " + game2Binding.questionText.getText() + " đáp án đúng cho câu hỏi này là : "
                    + words.get(progressValue - 1).getWord());
        }

        binding.title2.setText("Bạn hãy cố gắng tập trung vào câu hỏi kế tiếp.");

        dialog.setOnDismissListener(dialog1 -> UpdateQuestion());

        dialog.show();
    }

    private void dialogCorrect() {

        Dialog dialog = new Dialog(this);
        Game2CorrectPopupBinding binding = Game2CorrectPopupBinding.inflate(LayoutInflater.from(this));

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

        if (count_correct >= 3) binding.title.setText(R.string.game_2_result_correct_3);
        if (count_correct >= 7) binding.title.setText(R.string.game_2_result_correct_7);
        if (count_correct >= 12) binding.title.setText(R.string.game_2_result_correct_12);

        dialog.setOnDismissListener(dialog1 -> UpdateQuestion());
        dialog.show();
    }

    //Check Answer
    private void setWrong(int selected) {
        if (selected == 1) {
            game2Binding.opt1.setBackground(ContextCompat.getDrawable(this, R.drawable.game2_wrong_answer));
        }
        if (selected == 2) {
            game2Binding.opt2.setBackground(ContextCompat.getDrawable(this, R.drawable.game2_wrong_answer));
        }
        if (selected == 3) {
            game2Binding.opt3.setBackground(ContextCompat.getDrawable(this, R.drawable.game2_wrong_answer));
        }
        if (selected == 4) {
            game2Binding.opt4.setBackground(ContextCompat.getDrawable(this, R.drawable.game2_wrong_answer));
        }
    }

    private void setCorrect(int randomNumber) {
        if (randomNumber == 1) {
            game2Binding.opt1.setBackground(ContextCompat.getDrawable(this, R.drawable.game2_correct_answer));
        }
        if (randomNumber == 2) {
            game2Binding.opt2.setBackground(ContextCompat.getDrawable(this, R.drawable.game2_correct_answer));
        }
        if (randomNumber == 3) {
            game2Binding.opt3.setBackground(ContextCompat.getDrawable(this, R.drawable.game2_correct_answer));
        }
        if (randomNumber == 4) {
            game2Binding.opt4.setBackground(ContextCompat.getDrawable(this, R.drawable.game2_correct_answer));
        }
    }
    //Selected Option
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == game2Binding.opt1) {
                selected = 1;
                game2Binding.opt1.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.selected_game2));
            } else {
                game2Binding.opt1.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.game2_question_option));
            }
            if (v == game2Binding.opt2) {
                selected = 2;
                game2Binding.opt2.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.selected_game2));
            } else {
                game2Binding.opt2.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.game2_question_option));
            }
            if (v == game2Binding.opt3) {
                selected = 3;
                game2Binding.opt3.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.selected_game2));
            } else {
                game2Binding.opt3.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.game2_question_option));
            }
            if (v == game2Binding.opt4) {
                selected = 4;
                game2Binding.opt4.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.selected_game2));
            } else {
                game2Binding.opt4.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.game2_question_option));
            }
        }
    };

    //Join game
    private void data(Intent intent) {
        words = (ArrayList<Word>) intent.getSerializableExtra("wordObject");
        count_word = words.size();
        getAnswer(state, words.get(progressValue));
    }

    //After 1 question
    private void UpdateQuestion() {

        if (progressValue < count_word) {
            getAnswer(state, words.get(progressValue));
        }
        else {

            game2Binding.progressText.setText(progressValue + "/" + count_word);
            game2Binding.progressBar.setMax(count_word);
            game2Binding.progressBar.setProgress(progressValue);
            game2Binding.resultCorrect.setText(String.valueOf(count_correct));
            game2Binding.resultWrong.setText(String.valueOf(count_wrong));
            game2Binding.correctEasy.setText(String.valueOf(count_easy_correct));
            game2Binding.correctMedium.setText(String.valueOf(count_medium_correct));
            game2Binding.correctHard.setText(String.valueOf(count_hard_correct));
            game2Binding.c3.setVisibility(View.VISIBLE);
            game2Binding.c2.setVisibility(View.INVISIBLE);
        }
    }

    //Option
    private void openSettingDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        SettingG2DialogBinding binding = SettingG2DialogBinding.inflate(LayoutInflater.from(this));
        bottomSheetDialog.setContentView(binding.getRoot());

        if (state) {
            binding.choose1.setBackgroundResource(R.drawable.rectangle_border);
            binding.choose2.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        } else {
            binding.choose2.setBackgroundResource(R.drawable.rectangle_border);
            binding.choose1.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        }

        if (typeGame == 1) {
            binding.easy.setBackgroundResource(R.drawable.rectangle_border);
        } else if (typeGame == 2) {
            binding.medium.setBackgroundResource(R.drawable.rectangle_border);
        } else if (typeGame == 3) {
            binding.hard.setBackgroundResource(R.drawable.rectangle_border);
        }
        //Option face card
        binding.choose1.setOnClickListener(v -> {
            if (!state) {
                state = !state;
                v.setBackgroundResource(R.drawable.rectangle_border);
                binding.choose2.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
                if (progressValue < count_word)
                    getAnswer(state, words.get(progressValue));
            }
        });

        binding.choose2.setOnClickListener(v -> {
            if (state) {
                state = !state;
                v.setBackgroundResource(R.drawable.rectangle_border);
                binding.choose1.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
                if (progressValue < count_word)
                    getAnswer(state, words.get(progressValue));
            }
        });
        //Option type Question
        binding.easy.setOnClickListener(v -> {
            if (typeGame != 1) {
                clearType(typeGame, binding.easy, binding.medium, binding.hard);
                v.setBackgroundResource(R.drawable.rectangle_border);
                typeGame = 1;
                if (progressValue < count_word)
                    getAnswer(state, words.get(progressValue));
            }
        });

        binding.medium.setOnClickListener(v -> {
            if (typeGame != 2) {
                clearType(typeGame, binding.easy, binding.medium, binding.hard);
                v.setBackgroundResource(R.drawable.rectangle_border);
                typeGame = 2;
                if (progressValue < count_word)
                    getAnswer(state, words.get(progressValue));
            }
        });

        binding.hard.setOnClickListener(v -> {
            if (typeGame != 3) {
                clearType(typeGame, binding.easy, binding.medium, binding.hard);
                v.setBackgroundResource(R.drawable.rectangle_border);
                typeGame = 3;
                if (progressValue < count_word)
                    getAnswer(state, words.get(progressValue));
            }
        });

        //Reset game
        binding.cp6.setOnClickListener(v -> {
            progressValue = 0; count_wrong = 0; count_correct = 0;
            count_easy_correct = count_medium_correct = count_hard_correct = 0;

            game2Binding.c2.setVisibility(View.VISIBLE);
            game2Binding.c3.setVisibility(View.INVISIBLE);
            getAnswer(state, words.get(progressValue));
        });
        bottomSheetDialog.show();
    }

    private void clearType(int typeGame, TextView easy, TextView medium, TextView hard) {
        if (typeGame == 1) {
            easy.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        }
        if (typeGame == 2) {
            medium.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        }
        if (typeGame == 3) {
            hard.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        }
    }


    private void getAnswer(boolean state, Word word) {

        game2Binding.opt1.setBackground(ContextCompat.getDrawable(this, R.drawable.game2_question_option));
        game2Binding.opt2.setBackground(ContextCompat.getDrawable(this, R.drawable.game2_question_option));
        game2Binding.opt3.setBackground(ContextCompat.getDrawable(this, R.drawable.game2_question_option));
        game2Binding.opt4.setBackground(ContextCompat.getDrawable(this, R.drawable.game2_question_option));

        selected = 0;

        game2Binding.progressText.setText(progressValue + "/" + count_word);
        game2Binding.progressBar.setMax(count_word);
        game2Binding.progressBar.setProgress(progressValue);

        if (state) {

            game2Binding.questionText.setText(word.getWord());

            Random random = new Random();
            randomNumber = random.nextInt(4) + 1;

            if (randomNumber == 1) {
                game2Binding.opt1.setText(word.getMeaning());
            } else if (randomNumber == 2) {
                game2Binding.opt2.setText(word.getMeaning());
            } else if (randomNumber == 3) {
                game2Binding.opt3.setText(word.getMeaning());
            } else {
                game2Binding.opt4.setText(word.getMeaning());
            }

            setAnswer(randomNumber, typeGame);
        }
        else {

            game2Binding.questionText.setText(word.getMeaning());

            Random random = new Random();

            randomNumber = random.nextInt(4) + 1;

            if (randomNumber == 1) {
                game2Binding.opt1.setText(word.getWord());
            } else if (randomNumber == 2) {
                game2Binding.opt2.setText(word.getWord());
            } else if (randomNumber == 3) {
                game2Binding.opt3.setText(word.getWord());
            } else if (randomNumber == 4) {
                game2Binding.opt4.setText(word.getWord());
            }
            setAnswer(randomNumber, typeGame);
        }

    }

    private void setAnswer(int randomNumber, int typeGame) {
        String ans1 = "A", ans2 = "B", ans3 = "C";

        if (typeGame == 1) {
            int p1 = -1, p2 = -1, p3 = -1;
            while (true) {
                Random random = new Random();
                p1 = random.nextInt(count_word);
                if (p1 != progressValue) {
                    break;
                }
            }

            while (true) {
                Random random = new Random();
                p2 = random.nextInt(count_word);
                if (p2 != progressValue && p2 != p1) {
                    break;
                }
            }

            while (true) {
                Random random = new Random();
                p3 = random.nextInt(count_word);
                if (p3 != progressValue && p3 != p1 && p3 != p2) {
                    break;
                }
            }

            if (state) {
                ans1 = p1 != -1 ? words.get(p1).getMeaning() : ans1;
                ans2 = p2 != -1 ? words.get(p2).getMeaning() : ans2;
                ans3 = p3 != -1 ? words.get(p3).getMeaning() : ans3;
            }

            else {
                ans1 = p1 != -1 ? words.get(p1).getWord() : ans1;
                ans2 = p2 != -1 ? words.get(p2).getWord() : ans2;
                ans3 = p3 != -1 ? words.get(p3).getWord() : ans3;
            }

        }
        else if (typeGame == 2) {
            //fetch
        }

        else {
            //fetch
        }

        if (randomNumber == 1) {
            game2Binding.opt2.setText(ans1);
            game2Binding.opt3.setText(ans2);
            game2Binding.opt4.setText(ans3);
        }
        if (randomNumber == 2) {
            game2Binding.opt1.setText(ans1);
            game2Binding.opt3.setText(ans2);
            game2Binding.opt4.setText(ans3);
        }
        if (randomNumber == 3) {
            game2Binding.opt1.setText(ans1);
            game2Binding.opt2.setText(ans2);
            game2Binding.opt4.setText(ans3);
        }
        if (randomNumber == 4) {
            game2Binding.opt1.setText(ans1);
            game2Binding.opt2.setText(ans2);
            game2Binding.opt3.setText(ans3);
        }
    }

}