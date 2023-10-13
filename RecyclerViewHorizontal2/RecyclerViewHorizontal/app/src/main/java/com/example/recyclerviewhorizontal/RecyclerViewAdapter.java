package com.example.recyclerviewhorizontal;

import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;


import com.example.recyclerviewhorizontal.databinding.ItemRecyclerBinding;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    RecyclerView recyclerView;
    ArrayList<String> arrayList;//Tùy chỉnh
    SnapHelper snapHelper;
    private static int widthRecycler;
    private static int heightRecycler;
    int nowPosition = -1, nowPositionInTouch = -1;

    //Dành cho adapter đã biết trước số lượng phần tử, có thể chưa cần tải hết nội dung
    public RecyclerViewAdapter(ArrayList<String> arrayList, RecyclerView recyclerView) {
        this.arrayList = arrayList;
        this.recyclerView = recyclerView;
        //Giúp item được kéo đến luôn xuất hiện ở chính giữa khung hình (áp dụng cho item recyclerView chiếm > 50% khung hình)
        snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(this.recyclerView);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int itemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();

                // Có 2 item đang chiếm phần trăm tỷ lê màn hình (duy nhất)
                if (itemPosition == -1) {
                    //Diễn ra khi người dùng onTouch hoặc đang onScroll
                    View snapView = snapHelper.findSnapView(recyclerView.getLayoutManager());

                    if (snapView != null) {
                        // Lấy vị trí của item gần nhất
                        int snapPosition = recyclerView.getLayoutManager().getPosition(snapView);

                        if (snapPosition != nowPositionInTouch) {
                            nowPositionInTouch = snapPosition;
                            //Để hiệu ứng luôn được tạo mới
                            nowPosition = -1;
//                            //Phần tử ở vị trí 1, 2, 3, .., recyclerView.getAdapter().getItemCount() - 1
//                            if ((snapPosition > 0) && (snapPosition < recyclerView.getAdapter().getItemCount() - 1)) {
                            //Center
                            for (int j = 1; j <= 10; ++j) {
                                CustomItemDecoration itemDecoration = new CustomItemDecoration(j, snapPosition);
                                recyclerView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        recyclerView.removeItemDecorationAt(snapPosition);
                                        recyclerView.addItemDecoration(itemDecoration, snapPosition);
                                    }
                                });
                            }
//                            }
                        }
                    }
                } else if (itemPosition != nowPosition) {
                    //Khi đã kết thúc cả onTouch và onScroll
                    nowPosition = itemPosition;
                    //Để hiệu ứng luôn được tạo mới
                    nowPositionInTouch = -1;
                    int decorationCount = recyclerView.getItemDecorationCount();
                    if (decorationCount >= recyclerView.getAdapter().getItemCount()) {
                        //Trường hợp itemPosition là trung tâm ở vị trí 0
                        if (itemPosition == 0) {
                            //Center
                            CustomItemDecoration itemDecoration = new CustomItemDecoration(0, itemPosition);
                            recyclerView.post(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView.removeItemDecorationAt(itemPosition);
                                    recyclerView.addItemDecoration(itemDecoration, itemPosition);
                                }
                            });
                            //Right
                            CustomItemDecoration itemDecoration1 = new CustomItemDecoration(10, itemPosition + 1);
                            recyclerView.post(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView.removeItemDecorationAt(itemPosition + 1);
                                    recyclerView.addItemDecoration(itemDecoration1, itemPosition + 1);
                                }
                            });
                        }
                        //Trường hợp itemPosition là trung tâm ở vị trí cuối
                        if (itemPosition == recyclerView.getAdapter().getItemCount() - 1) {
                            //Center
                            CustomItemDecoration itemDecoration = new CustomItemDecoration(0, itemPosition);
                            recyclerView.post(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView.removeItemDecorationAt(itemPosition);
                                    recyclerView.addItemDecoration(itemDecoration, itemPosition);
                                }
                            });
                            //Left
                            CustomItemDecoration itemDecoration1 = new CustomItemDecoration(10, itemPosition - 1);
                            recyclerView.post(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView.removeItemDecorationAt(itemPosition - 1);
                                    recyclerView.addItemDecoration(itemDecoration1, itemPosition - 1);
                                }
                            });
                        }
                        //Trường hợp itemPosition  ở vị trí 1, 2, 3, .., recyclerView.getAdapter().getItemCount() - 1
                        if ((itemPosition > 0) && (itemPosition < recyclerView.getAdapter().getItemCount() - 1)) {
                            //Center
                            CustomItemDecoration itemDecoration = new CustomItemDecoration(0, itemPosition);
                            recyclerView.post(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView.removeItemDecorationAt(itemPosition);
                                    recyclerView.addItemDecoration(itemDecoration, itemPosition);
                                }
                            });
                            //Left
                            CustomItemDecoration itemDecoration1 = new CustomItemDecoration(10, itemPosition - 1);
                            recyclerView.post(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView.removeItemDecorationAt(itemPosition - 1);
                                    recyclerView.addItemDecoration(itemDecoration1, itemPosition - 1);
                                }
                            });
                            //Right
                            CustomItemDecoration itemDecoration2 = new CustomItemDecoration(10, itemPosition + 1);
                            recyclerView.post(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView.removeItemDecorationAt(itemPosition + 1);
                                    recyclerView.addItemDecoration(itemDecoration2, itemPosition + 1);
                                }
                            });
                        }
                    }
                    // Khởi tạo itemDecoration, nếu không tăng recyclerView.getAdapter().getItemCount() thì chỉ chạy 1 lần duy nhất
                    else {
                        //decorationCount = 0 
                        for (int i = decorationCount; i < recyclerView.getAdapter().getItemCount(); ++i) {
                            int finalI = i;
                            if (finalI != 1) {
                                CustomItemDecoration itemDecoration = new CustomItemDecoration(0, finalI);

                                recyclerView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        recyclerView.addItemDecoration(itemDecoration, finalI);

                                    }
                                });
                            }
                            if (finalI == 1) {
                                CustomItemDecoration itemDecoration1 = new CustomItemDecoration(10, finalI);
                                recyclerView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        recyclerView.addItemDecoration(itemDecoration1, finalI);

                                    }
                                });
                            }
                        }
                    }
                }


            }
        });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemRecyclerBinding binding = ItemRecyclerBinding.inflate(inflater, parent, false);
        widthRecycler = parent.getWidth();
        heightRecycler = parent.getHeight();
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String item = arrayList.get(position);//Tùy chỉnh

        ItemRecyclerBinding holderItem = holder.itemView;
        //Tùy chỉnh width item theo kích thước điện thoại
        ViewGroup.LayoutParams layoutParams = holderItem.getRoot().getLayoutParams();
        layoutParams.width = (int) (widthRecycler * 0.8);

        //Tùy chỉnh margin item 0 theo kích thước điện thoại, 14 nhổn vãi lò bỏ qua
        if (position == 0 && layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.leftMargin = (int) (widthRecycler * 0.075);
            marginLayoutParams.rightMargin = 14;
        }
        //Mặc định các item
        if (position != 0 && position != arrayList.size() - 1 && layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.leftMargin = 14;
            marginLayoutParams.rightMargin = 14;
        }
        //Tùy chỉnh margin item cuối cùng theo kích thước điện thoại, 14 nhổn vãi lò bỏ qua
        if (position == arrayList.size() - 1 && layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.leftMargin = 14;
            marginLayoutParams.rightMargin = (int) (widthRecycler * 0.075);
        }
        ////
    }


    @Override
    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ItemRecyclerBinding itemView;

        public MyViewHolder(@NonNull ItemRecyclerBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}
