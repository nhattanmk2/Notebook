package com.example.testonscrollview.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testonscrollview.Effect.BottomLineItemDecoration;
import com.example.testonscrollview.Interface.EffectItemSubject;
import com.example.testonscrollview.Interface.goSubjectActivity;
import com.example.testonscrollview.Model.Subject;
import com.example.testonscrollview.databinding.ItemSubjectRvBinding;
import com.google.android.material.animation.AnimationUtils;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.MyViewHolder> {
    private static int widthRecycler;
    ArrayList<Subject> arrayList;


    EffectItemSubject effectItemSubject;
    goSubjectActivity goSubjectActivity;
    Context context;
    public SubjectAdapter(Context context, ArrayList<Subject> arrayList, EffectItemSubject effectItemSubject, goSubjectActivity goSubjectActivity) {
        this.arrayList = arrayList;
        this.context = context;
        this.effectItemSubject = effectItemSubject;
        this.goSubjectActivity = goSubjectActivity;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemSubjectRvBinding binding = ItemSubjectRvBinding.inflate(inflater, parent, false);
        widthRecycler = parent.getWidth();
        return new SubjectAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectAdapter.MyViewHolder holder, int position) {

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        holder.itemView.getRoot().setOnClickListener(v -> {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        });

        ViewGroup.LayoutParams layoutParams = holder.itemView.getRoot().getLayoutParams();
        layoutParams.width = (int) (widthRecycler * 0.8);

        if (position == 0 && layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.leftMargin = (int) (widthRecycler * 0.075);
            marginLayoutParams.rightMargin = 20;
        }

        if (position != 0 && position != arrayList.size() - 1 && layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.leftMargin = 20;
            marginLayoutParams.rightMargin = 20;
        }

        if (position == arrayList.size() - 1 && layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.leftMargin = 20;
            marginLayoutParams.rightMargin = (int) (widthRecycler * 0.075);
        }

        Subject data = arrayList.get(position);

//        holder.itemView.nameSubject.setText(data.getSubject_name());
//
//        holder.itemView.countWords.setText(data.getWord_count() + " Từ vựng");
//
//        holder.itemView.describe.setText(data.getSubject_describe());

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                        holder.itemView.nameSubject.setText(data.getSubject_name());

                        holder.itemView.countWords.setHint(data.getWord_count() + " Từ vựng");

                        holder.itemView.describe.setText(data.getSubject_describe());
            }
        });

        holder.itemView.getRoot().setOnTouchListener((v, event) -> {
            int action = event.getAction();

            if (action == MotionEvent.ACTION_UP ) {
                if (effectItemSubject != null) {
                    effectItemSubject.addEffect(position);
                }
                if (goSubjectActivity != null) {
                    goSubjectActivity.goSubjectActivity(data, position);
                }
            }
            return false;
        });

    }
    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ItemSubjectRvBinding itemView;
        public MyViewHolder(@NonNull ItemSubjectRvBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
    public void releaseContext() {
        context = null;
    }
}
