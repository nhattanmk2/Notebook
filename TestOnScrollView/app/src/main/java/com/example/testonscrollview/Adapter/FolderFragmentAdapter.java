package com.example.testonscrollview.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.testonscrollview.Model.Folder;
import com.example.testonscrollview.R;


import java.util.List;

public class FolderFragmentAdapter extends RecyclerView.Adapter<FolderFragmentAdapter.FolderViewHolder>{

    private Context mContext;

    private List<Folder> mListFolder;

    public FolderFragmentAdapter(Context mContext){this.mContext = mContext;}

    public void setData(List<Folder> list){
        this.mListFolder = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FolderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_folder_library, parent, false);
        return new FolderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FolderViewHolder holder, int position) {
        Folder folder = mListFolder.get(position);

        if (folder == null) {
            return;
        }

        if (holder.tvNameFolder != null){
            holder.tvNameFolder.setText(folder.getFolder_name());
        }

        if (holder.tvDescribe != null){
            holder.tvDescribe.setText(folder.getFolder_describe());
        }
    }

    @Override
    public int getItemCount() {
        if (mListFolder != null){
            return mListFolder.size();
        }
        return 0;
    }

    public class FolderViewHolder extends  RecyclerView.ViewHolder {
        private TextView tvNameFolder;
        private TextView tvDescribe;

        public FolderViewHolder (@NonNull View itemView){
            super(itemView);
            tvNameFolder = itemView.findViewById(R.id.name_folder);
            tvDescribe = itemView.findViewById((R.id.describe));
        }


    }
}
