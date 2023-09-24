package com.example.notebook.Thread;

import android.os.AsyncTask;
import android.util.Log;

import com.example.notebook.Model.Item_Word;

import java.util.List;

public class UpdateDataAsyncTask extends AsyncTask<List<Item_Word>, Void, List<Item_Word>> {
    private List<Item_Word> itemList;
    public UpdateDataAsyncTask() {}

    public UpdateDataAsyncTask(List<Item_Word> itemList) {
        this.itemList = itemList;

    }

    @Override
    protected List<Item_Word> doInBackground(List<Item_Word>... params) {
        List<Item_Word> get = params[0];
        return get;
    }

    @Override
    protected void onPostExecute(List<Item_Word> updatedItemList) {
        for (Item_Word get : updatedItemList) {
            Log.d("24", "onPostExecute: " + get.getId() + " " + get.getKey());
        }
    }
}