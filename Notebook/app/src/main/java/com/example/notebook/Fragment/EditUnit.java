package com.example.notebook.Fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.notebook.Adapter.ForEditUnit;
import com.example.notebook.Model.Item_Favourite;
import com.example.notebook.Model.Item_Word;
import com.example.notebook.R;

import java.util.ArrayList;
import java.util.List;

public class EditUnit extends Fragment {

    List<Item_Word> list_word_inUnit = new ArrayList<>();
    //Khởi tạo tạm
    List<Item_Favourite> list_database = new ArrayList<>();
    RecyclerView rvEditUnit;
    ForEditUnit rvEditUnitAdapter;
    TextView project_name;
    String value;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StartDB();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_unit, container, false);

        Bundle args = getArguments();

        if (args != null) {
            value = args.getString("key");
            getOptionUnit(value);
        }

//        Log.d("35", "onCreateView: " + args);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);

        rvEditUnit = view.findViewById(R.id.edit_RV);
        rvEditUnit.setLayoutManager(new LinearLayoutManager(getContext()));
        project_name = view.findViewById(R.id.project_name);
        if (list_word_inUnit.size() > 0) {
            project_name.setText(value);
        }
        // Khởi tạo và gán adapter cho RecyclerView
        rvEditUnitAdapter = new ForEditUnit(list_word_inUnit);

        rvEditUnit.setAdapter(rvEditUnitAdapter);
        rvEditUnitAdapter.notifyDataSetChanged();

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rvEditUnit);


//        Log.d("36", "onViewCreated: "+ rvEditUnitAdapter.getItemCount());
    }

    //    Cho phép vuốt mục về phía trái và phải, nhưng không cho phép di chuyển các mục bằng cách kéo và thả
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT, ItemTouchHelper.LEFT) {
        private final float MAX_SWIPE_DISTANCE = 400;
        private int previousActionState = -1;
        boolean isSwipeEnabled = true;
        boolean isFrozen = false;
        private RecyclerView recyclerView;
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Log.d("42", "onSwiped: " + recyclerView);
            clearView(recyclerView, viewHolder);

        }
        private Canvas convertViewHolderToCanvas(RecyclerView.ViewHolder viewHolder) {
            // Lấy View từ ViewHolder
            View itemView = viewHolder.itemView;

            // Tạo Bitmap với kích thước của View
            Bitmap bitmap = Bitmap.createBitmap(itemView.getWidth(), itemView.getHeight(), Bitmap.Config.ARGB_8888);

            // Tạo Canvas từ Bitmap
            Canvas canvas = new Canvas(bitmap);

            // Vẽ View lên Canvas
            itemView.draw(canvas);

            return canvas;
        }
        @Override
        public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
            return .2f;
        }
        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//            previousActionState = actionState;
//            this.recyclerView = recyclerView;
//            Log.d("43", "onViewCreated: " + recyclerView);
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                //Khoảng cách vuốt tối đa
//                if (Math.abs(dX) <= MAX_SWIPE_DISTANCE) {
//                    Log.d("38", "onChildDraw: " + isSwipeEnabled + " " + MAX_SWIPE_DISTANCE );
                // Vẽ đối tượng từ tệp tin XML
                Drawable drawable = ContextCompat.getDrawable(recyclerView.getContext(), R.drawable.create);
                if (drawable != null) {
//                        Log.d("37", "onChildDraw: " + viewHolder.itemView.getRight() + " " + viewHolder.itemView.getTop() + " " + viewHolder.itemView.getRight() + " " + viewHolder.itemView.getBottom() + " " + dX);
                    drawable.setBounds(viewHolder.itemView.getRight() + (int) dX, viewHolder.itemView.getTop(), viewHolder.itemView.getRight(), viewHolder.itemView.getBottom());
                    drawable.draw(c);
                }
//                }

            }
        }
        @Override
        public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//            previousActionState = actionState;


            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE && !isCurrentlyActive) {


                Drawable drawable = ContextCompat.getDrawable(recyclerView.getContext(), R.drawable.create);
                if (drawable != null) {
//                        Log.d("37", "onChildDraw: " + viewHolder.itemView.getRight() + " " + viewHolder.itemView.getTop() + " " + viewHolder.itemView.getRight() + " " + viewHolder.itemView.getBottom() + " " + dX);
                    drawable.setBounds(viewHolder.itemView.getRight() + (int) dX, viewHolder.itemView.getTop(), viewHolder.itemView.getRight(), viewHolder.itemView.getBottom());
                    drawable.draw(c);
                }
            }

        }

        @Override
        public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

            super.clearView(recyclerView, viewHolder);

            // Reset trạng thái khi vuốt đã xong
            isFrozen = false;
        }
        @Override
        public boolean isItemViewSwipeEnabled() {
//            Log.d("40", "isItemViewSwipeEnabled: " + isSwipeEnabled);
            return isSwipeEnabled;
        }
    };

    public void getOptionUnit(String value) {
        list_word_inUnit.clear();
        for (Item_Favourite i : list_database) {
            if (i.getTitle().equals(value)) {
                for (Item_Word j : i.getList_Favourite())
                    list_word_inUnit.add(j);
                break;
            }
        }
    }

    public void StartDB() {
        List<Item_Word> litem1 = new ArrayList<>();
        litem1.add(new Item_Word("quyen 1", 0));
        litem1.add(new Item_Word("quyen 2", 0));
        litem1.add(new Item_Word("quyen 3", 0));
        litem1.add(new Item_Word("quyen 4", 0));
        Item_Favourite item1 = new Item_Favourite("Animal", 10, litem1);
        list_database.add(item1);
//        statusDropdowns.add(false);
        List<Item_Word> litem2 = new ArrayList<>();
        litem2.add(new Item_Word("quyen 1", 1));
        litem2.add(new Item_Word("quyen 2", 1));
        litem2.add(new Item_Word("quyen 3", 1));
        litem2.add(new Item_Word("quyen 4", 1));
        Item_Favourite item2 = new Item_Favourite("Tiger", 5, litem2);
        list_database.add(item2);
//        statusDropdowns.add(false);
        List<Item_Word> litem3 = new ArrayList<>();
        litem3.add(new Item_Word("quyen 1", 2));
        litem3.add(new Item_Word("quyen 2", 2));
        litem3.add(new Item_Word("quyen 3", 2));
        litem3.add(new Item_Word("quyen 4", 2));
        Item_Favourite item3 = new Item_Favourite("Animal", 20, litem3);
        list_database.add(item3);
    }
}