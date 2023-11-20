package com.example.testonscrollview.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testonscrollview.Interface.UpdateWordChange;
import com.example.testonscrollview.Model.Word;
import com.example.testonscrollview.R;

import java.util.ArrayList;

public class CreateSubjectAdapter extends RecyclerView.Adapter<CreateSubjectAdapter.ViewHolder> {
    ArrayList<Word> arrayList;
    UpdateWordChange listenChange;


    public CreateSubjectAdapter(ArrayList<Word> arrayList, UpdateWordChange listenChange) {
        this.arrayList = arrayList;
        this.listenChange = listenChange;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_create_subject, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Word data = arrayList.get(position);

        //Khi xóa 1 phần tử và thêm phần tử mới vào recycler view không tạo mới mà chỉ gọi thay đổi phần tử cũ,
        // nếu kích thước thực sư thay đổi so với ban đầu thì mới thêm
        holder.word.setText(data.getWord());

        holder.meaning.setText(data.getMeaning());

        holder.word.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                data.setWord(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                data.setWord(s.toString());
//                listenChange.wordChange(s.toString(), 1, position);

            }
        });

        holder.meaning.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                data.setMeaning(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                data.setMeaning(s.toString());
//                listenChange.wordChange(s.toString(), 2, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public EditText word, meaning;
        public CardView cardView;
//        FrameLayout fr;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.word);
            meaning = itemView.findViewById(R.id.meaning);
            cardView = itemView.findViewById(R.id.card_view);
//            fr = itemView.findViewById(R.id.FR);
        }
    }

    public void Remove(int position) {
        arrayList.remove(position);
        notifyItemRemoved(position);
    }

    public void undo(Word word, int position) {
        arrayList.add(position, word);
        notifyItemInserted(position);
    }
    public void add() {
        arrayList.add(new Word("", ""));
        notifyItemInserted(getItemCount() - 1);
    }
    public ArrayList<Word> getArrayList() {
        return this.arrayList;
    }
}
