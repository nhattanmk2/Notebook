package com.example.notebook.Fragment;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.PopupWindow;

import com.example.notebook.Adapter.ForFavourite;
import com.example.notebook.Adapter.forFavouriteSearch;
import com.example.notebook.Model.Item_Favourite;
import com.example.notebook.Model.Item_Header;
import com.example.notebook.Model.Item_Word;
import com.example.notebook.R;

import java.util.ArrayList;
import java.util.List;

public class ListFavourite extends Fragment {

    RecyclerView FavouriteRV, FindRV;
    ForFavourite forFavourite;
    forFavouriteSearch searchList;
    List<Item_Favourite> item_favourites = new ArrayList<>();
    List<Item_Word> item_words = new ArrayList<>();
    List<Boolean> statusDropdowns = new ArrayList<>();
    SearchView searchView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_favourite, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);
        FavouriteRV = view.findViewById(R.id.rvFa);
        FindRV = view.findViewById(R.id.search_rv);
        mainView();
        searchResultView();
        dataInitialize();
    }
    public void mainView() {

        FavouriteRV.setLayoutManager(new LinearLayoutManager(getContext()));
        forFavourite = new ForFavourite(item_favourites, statusDropdowns);

        FavouriteRV.setAdapter(forFavourite);
    }
    public void searchResultView(){
        FindRV.setLayoutManager(new LinearLayoutManager(getContext()));
        searchList = new forFavouriteSearch(item_words);
        FindRV.setAdapter(searchList);
        searchList.setOnItemClickListener(new forFavouriteSearch.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int positionInforFavourite = -1;
                Log.d("19", "onItemClick: " + position);
                for (int i = 0; i < forFavourite.getItemCount(); ++ i) {
                    //numCount lấy số lượng các từ ưu thích trong một chủ đề
                    int numCount = forFavourite.getListFavourites().get(i).getList_Favourite().size();
                    if (position >= numCount) {
                        position -= numCount;
                    }
                    else {
                        positionInforFavourite = i;
                        break;
                    }
                }
                if (positionInforFavourite != -1) {
                    FindRV.setVisibility(View.GONE);

                    forFavourite.setStatus(positionInforFavourite, true);
                    forFavourite.notifyItemChanged(positionInforFavourite);
                    FavouriteRV.smoothScrollToPosition(positionInforFavourite);
                    FavouriteRV.setVisibility(View.VISIBLE);

                    Log.d("18", "onItemClick: " + positionInforFavourite);
                }
            }
        });
        FindRV.setVisibility(View.GONE);
    }

    //Inflater là chuyển định dạng xml sang định dạng kiểu tương ứng như Menu <-> MenuInflater, ...etc
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
        SearchManager searchManager = (SearchManager) getContext().getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchMenuItem.getActionView();
        //Lấy phạm vi tìm kiếm requireActivity().getComponentName() -> fragment
        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().getComponentName()));

        searchView.setMaxWidth(700);
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // Đóng thanh gõ chữ khi người dùng click ra ngoài
                    searchView.setIconified(true);
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Truyền tham số search
                searchList.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    searchList.getFilter().filter(newText);
//                    FavouriteRV.setVisibility(View.VISIBLE);
//                    FindRV.setVisibility(View.GONE);
                } else {
                    FindRV.setVisibility(View.VISIBLE);
                    searchList.getFilter().filter(newText);
                    FavouriteRV.setVisibility(View.GONE);
                }
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                FavouriteRV.setVisibility(View.VISIBLE);
                FindRV.setVisibility(View.GONE);
                return false;
            }
        });
    }
    void dataInitialize(){
        List<Item_Word> litem1 = new ArrayList<>();
        litem1.add(new Item_Word("quyen 1", 0));
        litem1.add(new Item_Word("quyen 2", 0));
        litem1.add(new Item_Word("quyen 3", 0));
        litem1.add(new Item_Word("quyen 4", 0));
        Item_Favourite item1 = new Item_Favourite("muc 1", 10, litem1);
        item_favourites.add(item1);
        statusDropdowns.add(false);
        List<Item_Word> litem2 = new ArrayList<>();
        litem2.add(new Item_Word("quyen 1", 1));
        litem2.add(new Item_Word("quyen 2", 1));
        litem2.add(new Item_Word("quyen 3", 1));
        litem2.add(new Item_Word("quyen 4", 1));
        Item_Favourite item2 = new Item_Favourite("muc 1", 5, litem2);
        item_favourites.add(item2);
        statusDropdowns.add(false);
        List<Item_Word> litem3 = new ArrayList<>();
        litem3.add(new Item_Word("quyen 1", 2));
        litem3.add(new Item_Word("quyen 2", 2));
        litem3.add(new Item_Word("quyen 3", 2));
        litem3.add(new Item_Word("quyen 4", 2));
        Item_Favourite item3 = new Item_Favourite("muc 1", 20, litem3);
        item_favourites.add(item3);
        statusDropdowns.add(false);
        int cnt = 0;
        for (int i = 0; i < item_favourites.size(); ++ i) {
            List<Item_Word> items = item_favourites.get(i).getList_Favourite();
            for (int j = 0; j < items.size(); ++ j) {
                Item_Word val = items.get(j);
                val.setKey(cnt);
                item_words.add(val);
                cnt ++;
            }
        }
    }
}