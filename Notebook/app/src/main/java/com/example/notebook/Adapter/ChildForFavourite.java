package com.example.notebook.Adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notebook.Model.Item_Favourite;
import com.example.notebook.Model.Item_Word;
import com.example.notebook.R;

import java.util.List;

public class ChildForFavourite extends RecyclerView.Adapter<ChildForFavourite.ViewHolder> {

    List<Item_Word> items_child_favourite;
    public ChildForFavourite(List<Item_Word> items_child_favourite) {
        this.items_child_favourite = items_child_favourite;
    }

    public List<Item_Word> getItems_child_favourite() {
        return items_child_favourite;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item_favourite_list_rc, parent, false);
        return new ChildForFavourite.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Item_Word item_word= items_child_favourite.get(position);
        holder.words.setText(item_word.getId());
        holder.favouriteI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.5f);
                alphaAnimation.setDuration(300);
                holder.favouriteI.startAnimation(alphaAnimation);

                // Thu nhỏ hình ảnh
                ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.8f, 1.0f, 0.8f,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation.setDuration(300);
                holder.favouriteI.startAnimation(scaleAnimation);

                PopupWindow popupWindow = new PopupWindow(holder.itemView.getContext());
                View customView = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.popup_menu, null);

                popupWindow.setContentView(customView);
                popupWindow.setWidth(700);
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);

                ViewGroup rootView = (ViewGroup)  holder.itemView.getParent().getParent().getParent();

                TextView title = customView.findViewById(R.id.TitlePopup);
                title.setText("Do you want cancel favourite in " + items_child_favourite.get(position).getId() + "?");
                ImageView accept = customView.findViewById(R.id.accept);
                ImageView decline = customView.findViewById(R.id.decline);
                accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        items_child_favourite.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, items_child_favourite.size());
                        Toast.makeText(customView.getContext(), "You change the favourite", Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                });
                decline.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(customView.getContext(), "You dont change it", Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                });

                Drawable dimmingDrawable = new ColorDrawable(Color.parseColor("#80000000")); // Dimming color
                dimmingDrawable.setBounds(0, 0, rootView.getWidth(), rootView.getHeight());
                dimmingDrawable.setAlpha(180); // Adjust the alpha value as per your preference

                rootView.getOverlay().add(dimmingDrawable);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {

                        rootView.getOverlay().remove(dimmingDrawable);
                    }
                });

// Show the PopupWindow at the desired location
                popupWindow.showAtLocation(holder.itemView, Gravity.CENTER, 0, 0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items_child_favourite.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView words, spellings, synonyms, antonyms, meanings;
        ImageView favouriteI, speakerI;
        //Item này đang được thích
        boolean status = true;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            words = itemView.findViewById(R.id.words);
            spellings = itemView.findViewById(R.id.spellings);
            synonyms = itemView.findViewById(R.id.synonyms);
            antonyms = itemView.findViewById(R.id.antonyms);
            meanings = itemView.findViewById(R.id.meanings);
            favouriteI = itemView.findViewById(R.id.favourite);
        }
    }
}
