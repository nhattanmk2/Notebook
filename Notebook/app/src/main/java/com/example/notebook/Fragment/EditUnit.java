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

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notebook.Adapter.ForEditUnit;
import com.example.notebook.Model.Item_Favourite;
import com.example.notebook.Model.Item_Header;
import com.example.notebook.Model.Item_Word;
import com.example.notebook.Model.Item_Word_By_Unit;
import com.example.notebook.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class EditUnit extends Fragment {

    DatabaseReference databaseReference;
    ValueEventListener valueEventListener;

    List<Item_Word> list_word_inUnit = new ArrayList<>();
    //Khởi tạo tạm
    List<Item_Word_By_Unit> list_database = new ArrayList<>();
    List<String> list_Key = new ArrayList<>();
    RecyclerView rvEditUnit;
    ForEditUnit rvEditUnitAdapter;
    MaterialCardView addCard;
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

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);

        rvEditUnit = view.findViewById(R.id.edit_RV);
        rvEditUnit.setLayoutManager(new LinearLayoutManager(getContext()));
        project_name = view.findViewById(R.id.project_name);
        addCard = view.findViewById(R.id.addCard);
        if (list_word_inUnit.size() > 0) {
            project_name.setText(value);
            addCard.setVisibility(View.VISIBLE);
        } else {
            addCard.setVisibility(View.GONE);
        }

        // Khởi tạo và gán adapter cho RecyclerView
        rvEditUnitAdapter = new ForEditUnit(list_word_inUnit);
        rvEditUnitAdapter.setListener(new ForEditUnit.MyAdapterListener() {
            @Override
            public void checked() {
                updateDatabase(value, "Change", "", list_word_inUnit);
            }
        });
        rvEditUnit.setAdapter(rvEditUnitAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rvEditUnit);

        rvEditUnitAdapter.notifyDataSetChanged();
        addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item_Word item_word = new Item_Word();
                list_word_inUnit.add(item_word);
                updateDatabase(value, "Add", "", list_word_inUnit);
//                rvEditUnitAdapter.notifyItemInserted(list_word_inUnit.size() - 1);
                rvEditUnit.smoothScrollToPosition(list_word_inUnit.size() - 1);

            }
        });


    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT, ItemTouchHelper.LEFT) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                // Xóa item khỏi danh sách dữ liệu
                list_word_inUnit.remove(position);
                String key_value_del = list_Key.get(position);
                list_Key.remove(position);
                rvEditUnitAdapter.notifyItemRemoved(position);
                Log.d("101", "onSwiped: " + key_value_del);
                updateDatabase(value, "Delete", key_value_del, list_word_inUnit);
            }
        }

    };

    public void updateDatabase(String key, String type, String position, List<Item_Word> item_words) {
        if (type == "Add") {
            FirebaseDatabase.getInstance().getReference("All Word").child(key).child("count")
                    .setValue(item_words.size()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(), "Add1", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

            FirebaseDatabase.getInstance().getReference("All Word").child(key).child("list_Word")
                    .setValue(item_words).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(), "Add2", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

        if (type == "Delete") {
            FirebaseDatabase.getInstance().getReference("All Word").child(key).child("count")
                    .setValue(item_words.size()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
            FirebaseDatabase.getInstance().getReference("All Word").child(key).child("list_Word").child(position)
                    .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(), "Delete", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

        if (type == "Change") {
            FirebaseDatabase.getInstance().getReference("All Word").child(key).child("list_Word")
                    .setValue(item_words).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public void getOptionUnit(String value) {
        list_word_inUnit.clear();
        list_Key.clear();
        if (TextUtils.isEmpty(value)) {
            return;
        }

        for (Item_Word_By_Unit i : list_database) {

            if (i.getTitle().equals(value) && i.getList_Word() != null) {
              int psKey = 0;
                for (Item_Word j : i.getList_Word()) {
                    if (j != null) {
                        list_word_inUnit.add(j);
                        list_Key.add(String.valueOf(psKey));
                    }
                    psKey ++;
                }
                break;
            }
        }
    }

    public void StartDB() {


        databaseReference = FirebaseDatabase.getInstance().getReference("All Word");
        valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                list_database.clear();
//                if (snapshot.exists()) {
//                    GenericTypeIndicator<List<Item_Word_By_Unit>> genericTypeIndicator = new GenericTypeIndicator<List<Item_Word_By_Unit>>() {};
//                    List<Item_Word_By_Unit> dataList = snapshot.getValue(genericTypeIndicator);
//                }
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    Item_Word_By_Unit item = itemSnapshot.getValue(Item_Word_By_Unit.class);

                    list_database.add(item);
                    getOptionUnit(value);
                }
//                list_word_inUnit.clear(); list_Key.clear();
//                int psKey = 0;
//                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
//
////                    Item_Word item = itemSnapshot.getValue(Item_Word.class);
////                    if (item != null) {
////                        list_word_inUnit.add(item);
////                        list_Key.add(String.valueOf(psKey));
////                    }
////                    psKey ++;
//                }
//                rvEditUnitAdapter.notifyDataSetChanged();
//                Log.d("777", "onDataChange: " + psKey);
//                // Thực hiện các thao tác khác với danh sách list_database
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý khi có lỗi xảy ra
            }
        });

    }
}