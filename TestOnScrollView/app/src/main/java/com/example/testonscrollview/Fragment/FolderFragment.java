package com.example.testonscrollview.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.testonscrollview.Adapter.FolderFragmentAdapter;
import com.example.testonscrollview.Model.Folder;
import com.example.testonscrollview.R;

import java.util.ArrayList;
import java.util.List;

public class FolderFragment extends Fragment {
    private RecyclerView rcvFolderFragment;

    private FolderFragmentAdapter folderFragmentAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_folder, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvFolderFragment = view.findViewById(R.id.rcv_folder_fragment);
        folderFragmentAdapter = new FolderFragmentAdapter(requireContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false);
        rcvFolderFragment.setLayoutManager(linearLayoutManager);

        folderFragmentAdapter.setData(getListFolder());
        rcvFolderFragment.setAdapter(folderFragmentAdapter);
    }

    private List<Folder> getListFolder() {
        List<Folder> list = new ArrayList<>();
        list.add(new Folder("English","2 courses"));
        list.add(new Folder("Vietnamese", "3 courses"));
        list.add(new Folder("Spanish","4 courses"));

        list.add(new Folder("English","2 courses"));
        list.add(new Folder("Vietnamese", "3 courses"));
        list.add(new Folder("Spanish","4 courses"));

        return list;
    }


}