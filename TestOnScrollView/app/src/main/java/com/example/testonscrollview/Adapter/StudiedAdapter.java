package com.example.testonscrollview.Adapter;

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

import com.example.testonscrollview.Model.Subject;
import com.example.testonscrollview.R;

import java.util.ArrayList;
import java.util.List;

public class StudiedAdapter extends RecyclerView.Adapter<StudiedAdapter.CourseViewHolder> implements Filterable {

    private Context mContext;
    private List<Subject> mListCourse;
    List<Subject> mListCourseRoot;

    public StudiedAdapter(Context mContext, List<Subject> mListCourse) {
        this.mContext = mContext;
        this.mListCourse = mListCourse;
        this.mListCourseRoot = mListCourse;
    }


    @NonNull
    @Override
    public StudiedAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_course, parent, false);
        return new StudiedAdapter.CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudiedAdapter.CourseViewHolder holder, int position) {
        Subject course = mListCourse.get(position);

        if (course == null) {
            return;
        }

        if (holder.tvName != null){
            holder.tvName.setText(course.getSubject_name());
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
                    ArrayList<Subject> res = new ArrayList<>();
                    for (Subject i : mListCourseRoot)
                        if (i.getSubject_name().toLowerCase().contains(search))
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
                mListCourse = (List<Subject>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}

