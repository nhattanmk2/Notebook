package com.example.tan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tan.R;
import com.example.tan.test.Course;


import java.util.ArrayList;
import java.util.List;


public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> implements Filterable {

    private Context mContext;
    private List<Course> mListCourse;
    List<Course> mListCourseRoot;

    public CourseAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Course> list){
        this.mListCourse = list;
        this.mListCourseRoot = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = mListCourse.get(position);

        if (course == null) {
            return;
        }

        if (holder.tvName != null){
            holder.tvName.setText(course.getName());
        }

    }

    @Override
    public int getItemCount() {
        if (mListCourse != null){
            return mListCourse.size();
        }
        return 0;
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.name_folder);
        }

    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String search = constraint.toString();
                Toast.makeText(mContext, search, Toast.LENGTH_SHORT).show();
                if (search.isEmpty()) {
                    mListCourse = mListCourseRoot;
                }
                else {
                    ArrayList<Course> res = new ArrayList<>();
                    for (Course i : mListCourseRoot)
                        if (i.getName().toLowerCase().contains(search))
                        {
                            res.add(i);
                        }
                    mListCourse = res;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mListCourse;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mListCourse = (List<Course>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
