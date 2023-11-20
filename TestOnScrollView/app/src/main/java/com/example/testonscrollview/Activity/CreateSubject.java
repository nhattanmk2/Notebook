package com.example.testonscrollview.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.testonscrollview.Adapter.CreateSubjectAdapter;
import com.example.testonscrollview.Effect.SwipedCreateSubjectRV;
import com.example.testonscrollview.Interface.ItemTouchHelperListener;
import com.example.testonscrollview.Interface.PutSubject;
import com.example.testonscrollview.Interface.UpdateWordChange;
import com.example.testonscrollview.Model.Subject;
import com.example.testonscrollview.Model.Word;
import com.example.testonscrollview.R;
import com.example.testonscrollview.databinding.ActivityCreateSubjectBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class CreateSubject extends AppCompatActivity implements ItemTouchHelperListener, UpdateWordChange {
    int typeNow;
    String title;
    ActivityCreateSubjectBinding createSubjectBinding;
    ArrayList<Word> wordArrayList = new ArrayList<>();

    CreateSubjectAdapter subjectAdapter;
    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createSubjectBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_subject);
        setContentView(createSubjectBinding.getRoot());
        Intent data = getIntent();
        putData(data);

        createSubjectBinding.c11.setOnClickListener(v -> {
            onBackPressed();
        });
        createSubjectBinding.c13.setOnClickListener(v -> openSettingDialog());

        createSubjectBinding.c14.setOnClickListener(v -> {
            //put database
            //TEST
            if (typeNow == 1) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("Subject", new Subject(createSubjectBinding.header1.getText().toString(),
                        createSubjectBinding.header2.getText().toString(), String.valueOf(subjectAdapter.getItemCount()),
                        subjectAdapter.getArrayList()));
                startActivity(intent);
                finish();
            }
            else if (typeNow == 2) {
                Intent intent = new Intent();
                intent.putExtra("ResultSubject", subjectAdapter.getArrayList());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
            //TEST
//            for (int i = 0; i < subjectAdapter.getArrayList().size(); ++ i) {
//                Log.d("i", "onCreate: " + subjectAdapter.getArrayList().get(i).getWord() + " " + subjectAdapter.getArrayList().get(i).getMeaning());
//            }
        });

        createSubjectBinding.newWord.setOnClickListener(v -> {

                subjectAdapter.add();
        });

        setViewRv();
    }

    private void setViewRv() {
        createSubjectBinding.createWordRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        subjectAdapter = new CreateSubjectAdapter(wordArrayList, this);
        createSubjectBinding.createWordRv.setAdapter(subjectAdapter);

        ItemTouchHelper.SimpleCallback simpleCallback = new SwipedCreateSubjectRV(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(createSubjectBinding.createWordRv);
    }

    private void openSettingDialog() {
    }

    private void putData(Intent data) {
        typeNow = data.getIntExtra("TypeCreate", 1);
        title = data.getStringExtra("Title");

        createSubjectBinding.c12.setText(title);

        if (typeNow != 1) {
            wordArrayList = (ArrayList<Word>)data.getSerializableExtra("ListWord");
        }

        else {
            wordArrayList.add(new Word("", ""));
            wordArrayList.add(new Word("", ""));
            wordArrayList.add(new Word("", ""));

        }
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof CreateSubjectAdapter.ViewHolder) {
            int position = viewHolder.getAdapterPosition();

            Word item = wordArrayList.get(viewHolder.getAdapterPosition());

            subjectAdapter.Remove(position);

            Snackbar snackbar = Snackbar.make(createSubjectBinding.getRoot(), ""+ position, Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", v -> {
                subjectAdapter.undo(item, position);

            });

            snackbar.setActionTextColor(Color.BLUE);
            snackbar.show();
            check = 0;
        }
    }

    @Override
    public void wordChange(String word, int type, int position) {
        check = 1;
        if (type == 1) {
            if (position < wordArrayList.size()) {
                Word cp = wordArrayList.get(position);
                cp.setWord(word);

            }
        }
        if (type == 2) {
            if (position < wordArrayList.size()) {
                Word cp = wordArrayList.get(position);
                cp.setMeaning(word);
            }
        }
    }
}