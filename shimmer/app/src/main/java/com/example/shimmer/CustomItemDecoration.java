package com.example.shimmer;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable scrollBar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutParams layoutParams;
    private int sum = 0;
    private int maxDistanceScroll = 0;
    private int onMoveDistance = 0 ; //?
    private double moveTop = 0;
    private Handler handler = new Handler();
    private Runnable idleRunnable;
    private int idleTimeout = 3000;
    public CustomItemDecoration(Context context, RecyclerView recyclerView) {
        // Tạo drawable cho thanh cuộn
        scrollBar = ContextCompat.getDrawable(context, R.drawable.custom_scrollbar_drawable);
        this.recyclerView = recyclerView;

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //Cập nhật giao diện khi người dùng cuộn
                scrollBar.setAlpha(255);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                //Giả sử đây các item giống nhau
                View itemView = linearLayoutManager.findViewByPosition(0);
                if (itemView != null) {
                    layoutParams = (RecyclerView.LayoutParams) itemView.getLayoutParams();
                    Log.d("10", "layoutParams " + layoutParams.topMargin + " " + layoutParams.bottomMargin);
                }

                //Độ cao của một item tính cả margin của nó
                int heightAtChild = recyclerView.getChildAt(0).getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;

                //Độ dài của recyclerView mà nó có hiển thị trên màn hình / một thời điểm
                int heightRvOnDisplay = (((ViewGroup) recyclerView.getParent()).getHeight()-recyclerView.getTop());

                //Số lượng item mà màn hình có thể hiện thị / một thời điểm
                int maxItemOnDisplay = heightRvOnDisplay / heightAtChild;

                //Độ dài tối đa có thể cuộn tại một thời điểm cụ thể, (có thể thay đổi) -> (thêm item vào danh sách, etc)
                maxDistanceScroll = heightAtChild - heightRvOnDisplay % heightAtChild +
                        heightAtChild * (recyclerView.getAdapter().getItemCount() - maxItemOnDisplay - 1);

                //Quãng đường đã di chuyển khi người dùng cuộn
                onMoveDistance = dy;
                //Tổng quãng đường đã di chuyển khi người dùng cuộn
                sum += dy;
                Log.d("1", "onDrawOver: " + sum + " " + maxDistanceScroll);

//                if (linearLayoutManager != null) {
//                Vị trí item cuối cùng hiển thị trọn vẹn trên màn hình
//                    item = linearLayoutManager.findLastCompletelyVisibleItemPosition();
//                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    handler.removeCallbacks(idleRunnable);
                    handler.postDelayed(idleRunnable, idleTimeout);
                }
            }
        });
    }

    @Override
    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        //Chiều dài thanh cuộn
        int heightOnScroll = (int)Math.round((double)(((ViewGroup) parent.getParent()).getHeight() - parent.getTop()) / state.getItemCount());

        Log.d("2", "onDrawOver: " + heightOnScroll + " " + state.getItemCount() + " " + (((ViewGroup) parent.getParent()).getHeight() - parent.getTop()));

        //Ẩn thanh cuộn khi người dùng không còn cuộn trong 3s
        idleRunnable = new Runnable() {
            @Override
            public void run() {
                ValueAnimator alphaAnimator = ValueAnimator.ofInt(255, 10);
                alphaAnimator.setDuration(500); // Thời gian để hoàn thành hiệu ứng mờ dần
                alphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int animatedAlpha = (int) animation.getAnimatedValue();
                        scrollBar.setAlpha(animatedAlpha);
                        recyclerView.invalidate(); // Yêu cầu vẽ lại RecyclerView
                    }
                });
                alphaAnimator.start();
            }
        };
        //Xác định vị trí hiện thị thanh cuộn, ở đây là bên phải
        int left = ((ViewGroup) parent.getParent()).getWidth() - scrollBar.getIntrinsicWidth();
        int right = ((ViewGroup) parent.getParent()).getWidth();
        // x/y = a/b Thus: x = onMoveDistance, a =  maxDistanceScroll, b = (heightOnScroll * (state.getItemCount() - 1)
        //Tỷ lệ giữa nội dung cuộn và quãng đường di chuyển của thanh cuộn
        double result = (double) heightOnScroll * (state.getItemCount() - 1) / maxDistanceScroll;

        //Vị trí hiện tại của thanh cuộn
        moveTop =  (sum * result) ; //~ onMoveDistance*result
        //Giới hạn cuộn dưới
        moveTop = moveTop > heightOnScroll * (state.getItemCount() - 1) ? heightOnScroll * (state.getItemCount() - 1) : moveTop;
        //Giới hạn cuộn trên
        moveTop = moveTop < 0 ? 0 : moveTop;

        int top = (int) moveTop;
        int bottom = top + heightOnScroll;

        Log.d("3", "onDrawOver: " + top + " " + (onMoveDistance * result));
        scrollBar.setBounds(left, top, right, bottom);
        scrollBar.draw(canvas);
    }
}