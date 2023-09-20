package com.example.notebook.Fragment;


import android.content.ClipData;
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

import com.example.notebook.Adapter.ForTitleRV;
import com.example.notebook.Adapter.ForTopRV;
import com.example.notebook.Model.Item_Word;
import com.example.notebook.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class ListWord extends Fragment {
    //DB
    private List<String> headers = new ArrayList<>();
    private List<Boolean> status = new ArrayList<>();
    List<List<Item_Word>> childData = new ArrayList<>();
    List<Item_Word> listView1Data = new ArrayList<>();

    RecyclerView TopRecyclerView, TitleRecyclerView;
    ForTopRV TopRecyclerViewAdapter;
    ForTitleRV TitleRecyclerViewAdapter;
//    FloatingActionButton fab;
//    boolean statusFab = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_word, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);
        TopRecyclerView = view.findViewById(R.id.topRecyclerView);
        TitleRecyclerView = view.findViewById(R.id.titleRecyclerView);
//        fab = view.findViewById(R.id.fab);
        dataInitializeForTop();
        dataInitializeForTitle();
        LoadTitleRV();
        LoadTopRV();
//        listView1Data.add(new Item_Word("Item_Word 1.3"));
//        listView1Data.add(new Item_Word("Item_Word 1.3"));
//        childData.set(0, listView1Data);
//
//        List<Item_Word> listView5Data = new ArrayList<>();
//        listView5Data.add(new Item_Word("Item_Word 5.3"));
//        listView5Data.add(new Item_Word("Item_Word 5.3"));
//        listView5Data.add(new Item_Word("Item_Word 5.3"));
//        listView5Data.add(new Item_Word("Item_Word 5.3"));
//        listView5Data.add(new Item_Word("Item_Word 5.3"));
//        listView5Data.add(new Item_Word("Item_Word 5.3"));
//        listView5Data.add(new Item_Word("Item_Word 5.3"));
//        listView5Data.add(new Item_Word("Item_Word 5.3"));
//        childData.add(listView5Data);
//        List<Item_Word> listView6Data = new ArrayList<>();
//        listView6Data.add(new Item_Word("Item_Word 6.3"));
//        listView6Data.add(new Item_Word("Item_Word 6.3"));
//        listView6Data.add(new Item_Word("Item_Word 6.3"));
//        listView6Data.add(new Item_Word("Item_Word 6.3"));
//        childData.add(listView6Data);
//        Log.d("9999", "onViewCreated: " + childData.size());
//        ActionFab();
    }

    public void LoadTopRV() {

        TopRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        TopRecyclerView.setHasFixedSize(true);
        TopRecyclerViewAdapter = new ForTopRV(headers, status, TitleRecyclerView);

        TopRecyclerView.setAdapter(TopRecyclerViewAdapter);
        TopRecyclerViewAdapter.notifyDataSetChanged();

    }

    public void LoadTitleRV() {
        TitleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        TitleRecyclerView.setHasFixedSize(true);
        TitleRecyclerViewAdapter = new ForTitleRV(childData);
        TitleRecyclerView.setAdapter(TitleRecyclerViewAdapter);
//        TitleRecyclerView.smoothScrollToPosition(TopRecyclerViewAdapter.getOnClick());
        TitleRecyclerViewAdapter.notifyDataSetChanged();
//        Log.d("9999", "onViewCreated: " + childData.size());
        TitleRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                View firstVisibleItemView = layoutManager.findViewByPosition(firstVisibleItemPosition);
                int itemTop = firstVisibleItemView.getTop();
                int itemBottom = firstVisibleItemView.getBottom();
//                Log.d("3", "LoadTitleRV: " + firstVisibleItemPosition + " " + itemTop + " " + itemBottom);
                int recyclerViewHeight = recyclerView.getHeight();
//                Log.d("4", "LoadTitleRV: " + recyclerViewHeight);
                if (itemTop < 0) itemTop = Math.abs(itemTop);
                int viewHeight = itemTop + itemBottom;
                double visiblePercentage = (double) itemBottom / recyclerViewHeight;

                if (visiblePercentage < 0.3) {
                    TopRecyclerViewAdapter.changeStatus(firstVisibleItemPosition + 1);
                } else if (visiblePercentage > 0.6) {
                    TopRecyclerViewAdapter.changeStatus(firstVisibleItemPosition);
                }


            }
        });
    }
//    public void ActionFab() {
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                statusFab = !statusFab;
//                if (statusFab) {
//                    fab.setImageResource(R.drawable.baseline_arrow_drop_down_24);
//                    if (TitleRecyclerViewAdapter.getViewHolderList().size() > TopRecyclerViewAdapter.getStatus()) {
//                        ForTitleRV.ViewHolder viewHolder = TitleRecyclerViewAdapter.getViewHolderList().get(TopRecyclerViewAdapter.getStatus());
//                        viewHolder.itemView.findViewById(R.id.itemTitleRecyclerView).setVisibility(View.VISIBLE);
//                    }
//                } else {
//                    fab.setImageResource(R.drawable.baseline_arrow_drop_up_24);
//
//                    Log.d("8", "onClick: " + TitleRecyclerViewAdapter.getViewHolderList().size());
//                    if (TitleRecyclerViewAdapter.getViewHolderList().size() > TopRecyclerViewAdapter.getStatus()) {
//                            ForTitleRV.ViewHolder viewHolder = TitleRecyclerViewAdapter.getViewHolderList().get(TopRecyclerViewAdapter.getStatus());
//                            viewHolder.itemView.findViewById(R.id.itemTitleRecyclerView).setVisibility(View.GONE);
//                    }
//
//                }
//            }
//        });
//    }

    public void dataInitializeForTop() {
        headers.add("Titlđasdsesds 1");
        headers.add("Title 2");
        headers.add("Title 3");
        headers.add("Title 4");
        headers.add("Title 5");
        headers.add("Title 6");
        status.add(true);
        status.add(false);
        status.add(false);
        status.add(false);
        status.add(false);
        status.add(false);
    }

    public void dataInitializeForTitle() {

        listView1Data.add(new Item_Word("Item_Word 1.1", false));
        listView1Data.add(new Item_Word("Item_Word 1.2", false));
        listView1Data.add(new Item_Word("Item_Word 1.3", false));
        listView1Data.add(new Item_Word("Item_Word 1.3", false));
        childData.add(listView1Data);

// Tạo bộ dữ liệu cho ListView 2
        List<Item_Word> listView2Data = new ArrayList<>();
        listView2Data.add(new Item_Word("Item_Word 2.1", false));
        listView2Data.add(new Item_Word("Item_Word 2.2", false));
        listView2Data.add(new Item_Word("Item_Word 2.3", false));
        listView2Data.add(new Item_Word("Item_Word 2.3", false));
        listView2Data.add(new Item_Word("Item_Word 2.3", false));
        childData.add(listView2Data);

// Tạo bộ dữ liệu cho ListView 3
        List<Item_Word> listView3Data = new ArrayList<>();
        listView3Data.add(new Item_Word("Item_Word 3.1", false));
        listView3Data.add(new Item_Word("Item_Word 3.2", false));
        listView3Data.add(new Item_Word("Item_Word 3.3", false));
        childData.add(listView3Data);

        List<Item_Word> listView4Data = new ArrayList<>();
        listView4Data.add(new Item_Word("Item_Word 4.1", false));
        listView4Data.add(new Item_Word("Item_Word 4.2", false));
        listView4Data.add(new Item_Word("Item_Word 4.3", false));
        listView4Data.add(new Item_Word("Item_Word 4.3", false));
        listView4Data.add(new Item_Word("Item_Word 4.3", false));
        listView4Data.add(new Item_Word("Item_Word 4.3", false));
        listView4Data.add(new Item_Word("Item_Word 4.3", false));
        childData.add(listView4Data);
    }
}