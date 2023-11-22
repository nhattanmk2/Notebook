package com.example.tan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tan.R;
import com.example.tan.viewmodel.Classroom;


import java.util.List;

public class ClassroomAdapter extends RecyclerView.Adapter<ClassroomAdapter.ClassroomViewHolder>{

    private Context mContext;

    private List<Classroom> mListClass;

    public ClassroomAdapter(Context mContext){this.mContext = mContext;}

    public void setData(List<Classroom> list){
        this.mListClass = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ClassroomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classroom, parent, false);
        return new ClassroomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassroomViewHolder holder, int position) {
        Classroom classroom = mListClass.get(position);

        if (classroom == null) {
            return;
        }

        if (holder.tvClassname != null){
            holder.tvClassname.setText(classroom.getClassName());
        }

        if (holder.tvClasscount != null){
            holder.tvClasscount.setText(classroom.getClassCount());
        }
    }

    @Override
    public int getItemCount() {
        if (mListClass != null){
            return mListClass.size();
        }
        return 0;
    }

    public class ClassroomViewHolder extends  RecyclerView.ViewHolder {
        private TextView tvClassname;
        private TextView tvClasscount;

        public ClassroomViewHolder (@NonNull View itemView){
            super(itemView);
            tvClassname = itemView.findViewById(R.id.tv_classname);
            tvClasscount = itemView.findViewById((R.id.tv_classcount));
        }


    }
}
