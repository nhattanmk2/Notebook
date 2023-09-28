package com.example.notebook.Adapter;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notebook.Model.Item_Word;
import com.example.notebook.R;

import java.util.Arrays;
import java.util.List;

public class ForEditUnit extends RecyclerView.Adapter<ForEditUnit.ViewHolder> {
    List<Item_Word> list_word_Unit;
    MyAdapterListener check;
    public void setListener(MyAdapterListener check) {
        this.check = check;
    }
    public ForEditUnit(List<Item_Word> list_word_Unit) {
        this.list_word_Unit = list_word_Unit;
    }

    public List<Item_Word> getList_word_Unit() {
        return list_word_Unit;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Log.d("90", "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_edit_list_rc, parent, false);
        return new ForEditUnit.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        List<Item_Word> item_words = this.getList_word_Unit();
        Item_Word item_word = item_words.get(position);
        if (item_word.getWord() != null) {
            holder.word.setText(item_word.getWord());
        }
        else {
            holder.word.setText(holder.itemView.getContext().getString(R.string.Edit_Word));
        }
        if (item_word.getMeaning() != null) {
            holder.meaning.setText(item_word.getMeaning());
        }
        else {
            holder.meaning.setText(holder.itemView.getContext().getString(R.string.Edit_Meaning));
        }
        if (item_word.getSynonyms() != null) {
            List<String> getSynonyms = item_word.getSynonyms();
            String joinedString = TextUtils.join("\n", getSynonyms);
            holder.Synonyms.setText(joinedString);
        }
        else {
            holder.Synonyms.setText(holder.itemView.getContext().getString(R.string.Edit_Synonyms));
        }
        if (item_word.getAntonyms() != null) {
            List<String> getAntonyms = item_word.getAntonyms();
            String joinedString = TextUtils.join("\n", getAntonyms);
            holder.Antonyms.setText(joinedString);
        }
        else {
            holder.Antonyms.setText(holder.itemView.getContext().getString(R.string.Edit_Antonyms));
        }
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.word.setEnabled(!holder.word.isEnabled());
                holder.meaning.setEnabled(!holder.meaning.isEnabled());
                holder.Synonyms.setEnabled(!holder.Synonyms.isEnabled());
                holder.Antonyms.setEnabled(!holder.Antonyms.isEnabled());
            }
        });

        holder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                item_word.setWord(holder.word.getText().toString());
                item_word.setMeaning(holder.meaning.getText().toString());
                Log.d("51", "onClick: " + item_word.getWord()
                + " " + item_word.getMeaning());
                String getTextSynonyms = holder.Synonyms.getText().toString();
                String[] stringArray = getTextSynonyms.split("[\\s\\n]+");
                List<String> resSynonyms = Arrays.asList(stringArray);
                item_word.setSynonyms(resSynonyms);

                String getTextAntonyms = holder.Antonyms.getText().toString();
                stringArray = getTextAntonyms.split("[\\s\\n]+");
                List<String> resAntonyms = Arrays.asList(stringArray);
                item_word.setAntonyms(resAntonyms);
                item_words.set(position, item_word);

                check.checked();
            }
        });
    }

    public interface MyAdapterListener {
        void checked();
    }
    @Override
    public int getItemCount() {
        return getList_word_Unit().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button edit, save;
        EditText word, meaning, Synonyms, Antonyms;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.Word);
            meaning = itemView.findViewById(R.id.Meaning);
            Synonyms = itemView.findViewById(R.id.Synonyms);
            Antonyms = itemView.findViewById(R.id.Antonyms);
            edit = itemView.findViewById(R.id.edit);
            save = itemView.findViewById(R.id.save);
        }
    }
}
