package com.example.testonscrollview.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import android.widget.Toast;


import com.example.testonscrollview.Activity.Login_Achievement;
import com.example.testonscrollview.Activity.SubjectActivity;
import com.example.testonscrollview.Adapter.FolderAdapter;
import com.example.testonscrollview.Adapter.NotificationAdapter;
import com.example.testonscrollview.Adapter.SubjectAdapter;

import com.example.testonscrollview.Adapter.VPAchievementAdapter;
import com.example.testonscrollview.Effect.BottomLineItemDecoration;
import com.example.testonscrollview.Effect.SwipedNotificationRV;
import com.example.testonscrollview.Interface.EffectItemSubject;
import com.example.testonscrollview.Interface.ItemTouchHelperListener;

import com.example.testonscrollview.Interface.ReplaceFragment;
import com.example.testonscrollview.Interface.UpdateHomeFragment;

import com.example.testonscrollview.Model.Folder;
import com.example.testonscrollview.Model.HomeAchievement;
import com.example.testonscrollview.Model.Notification;
import com.example.testonscrollview.Model.Subject;

import com.example.testonscrollview.R;

import com.example.testonscrollview.databinding.DialogNotificationBinding;
import com.example.testonscrollview.databinding.FragmentHomeBinding;


import java.io.Serializable;
import java.util.ArrayList;


public class HomeFragment extends Fragment implements UpdateHomeFragment, ItemTouchHelperListener, EffectItemSubject {
    private static int sizeComponent1;
    private static int paddingTop;
    ArrayList<Subject> arrayList = new ArrayList<>();
    ArrayList<Integer> countNumberClickSubject = new ArrayList<>();
    ArrayList<Folder> arrayList2 = new ArrayList<>();
    ArrayList<Notification> arrayList3 = new ArrayList<>();
    SubjectAdapter subjectAdapter;
    FolderAdapter folderAdapter;
    NotificationAdapter notificationAdapter;
    SnapHelper snapHelperFolder, snapHelperSubject;
    FragmentHomeBinding fragmentHomeBinding;

    ReplaceFragment replaceFragment;
    //Local
    ArrayList<HomeAchievement> dataViewPager = new ArrayList<>();
    //TEST
    Subject sbj;

    public void setReplaceFragment(ReplaceFragment replaceFragment) {
        this.replaceFragment = replaceFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            sbj = (Subject) bundle.getSerializable("Subject");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        return fragmentHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sizeComponent1 = fragmentHomeBinding.component1.getLayoutParams().height;
        paddingTop = fragmentHomeBinding.scrollView.getPaddingTop();
        ViewPagerSetUp();
        fragmentHomeBinding.scrollView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {

            if (scrollY > 140) {
                updateHeightComponent1(fragmentHomeBinding.component1, 0);
                RunOnUiThread(fragmentHomeBinding.component1, 0, scrollY);
                fragmentHomeBinding.scrollView.setPadding(0, 150, 0, 0);
            } else if (scrollY < 50) {
                updateHeightComponent1(fragmentHomeBinding.component1, sizeComponent1);
                RunOnUiThread(fragmentHomeBinding.component1, 0, 0);
                fragmentHomeBinding.scrollView.setPadding(0, paddingTop, 0, 0);
            } else {
                updateHeightComponent1(fragmentHomeBinding.component1, Math.max(sizeComponent1 - scrollY / 5, 0));
                RunOnUiThread(fragmentHomeBinding.component1, 0, scrollY / 5);
            }
        });
        TestData();

        LoadViewForSubject();
        LoadViewForFolder();
        LoadViewNotification();

        SubjectReadAllAction();
        FolderReadAllAction();
        NotificationAction();
        SearchAction();
    }


    @Override
    public void onNewFolder(Folder newData) {
        arrayList2.add(newData);
        folderAdapter.notifyItemChanged(arrayList2.size() - 2);
        folderAdapter.notifyItemInserted(arrayList2.size() - 1);
    }

    private void NotificationAction() {
        fragmentHomeBinding.component12.setOnClickListener(v -> {
            openNotificationDialog();
        });
    }

    private void FolderReadAllAction() {
        fragmentHomeBinding.goToFolder.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "H2", Toast.LENGTH_SHORT).show();
        });
    }

    private void SubjectReadAllAction() {
        fragmentHomeBinding.goToSubject.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "H3", Toast.LENGTH_SHORT).show();
        });
    }

    private void SearchAction() {
        fragmentHomeBinding.component2.setOnClickListener(v -> {
            if (replaceFragment != null) {
                replaceFragment.replace(new SearchFragment(), 11);
            }
        });
    }


    private void LoadViewNotification() {
        notificationAdapter = new NotificationAdapter(requireContext(), arrayList3);
    }

    private void LoadViewForSubject() {

        subjectAdapter = new SubjectAdapter(requireContext(), arrayList, this
                , (subject, position) -> {
                    if (countNumberClickSubject.get(position) == 2) {
                        countNumberClickSubject.set(position, 0);
                        startSubjectActivity(subject);
                    }
                });

        fragmentHomeBinding.SubjectRV.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));

        fragmentHomeBinding.SubjectRV.setAdapter(subjectAdapter);

        snapHelperSubject = new LinearSnapHelper();

        snapHelperSubject.attachToRecyclerView(fragmentHomeBinding.SubjectRV);
    }

    private void startSubjectActivity(Subject subject) {
        Intent goSubjectActivity = new Intent(requireContext(), SubjectActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("subject", subject);
        goSubjectActivity.putExtras(bundle);
        requireContext().startActivity(goSubjectActivity);
    }

    @Override
    public void addEffect(int position) {
        int highlightColor = Color.RED;
        int lineHeight = 10;
      
        countNumberClickSubject.set(position, countNumberClickSubject.get(position) + 1);

        Handler handler = new Handler();

        BottomLineItemDecoration itemDecoration = new BottomLineItemDecoration(highlightColor, lineHeight, position, requireContext());

        fragmentHomeBinding.SubjectRV.addItemDecoration(itemDecoration);

        handler.post(effect(handler, itemDecoration));
    }

    public Runnable effect(Handler handler, BottomLineItemDecoration itemDecoration) {
        return new Runnable() {
            private static final int DURATION = 1000;
            private static final int FRAME_RATE = 16;
            private int currentAlpha = 255;
            private int targetAlpha = 0;
            private int alphaIncrement = (255 - targetAlpha) * FRAME_RATE / DURATION;

            @Override
            public void run() {
                currentAlpha -= alphaIncrement;
                if (currentAlpha < targetAlpha) {
                    currentAlpha = targetAlpha;
                }

                // Thiết lập alpha cho itemDecoration
                itemDecoration.setAlpha(currentAlpha, fragmentHomeBinding.SubjectRV);

                // Gọi lại Runnable sau mỗi khung hình
                if (currentAlpha > targetAlpha) {
                    handler.postDelayed(this, FRAME_RATE);
                } else {
                    fragmentHomeBinding.SubjectRV.removeItemDecoration(itemDecoration);
                }
            }
        };
    }
    private void LoadViewForFolder() {
        folderAdapter = new FolderAdapter(requireContext(), arrayList2);
        fragmentHomeBinding.FolderRV.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        fragmentHomeBinding.FolderRV.setAdapter(folderAdapter);

        snapHelperFolder = new LinearSnapHelper();
        snapHelperFolder.attachToRecyclerView(fragmentHomeBinding.FolderRV);
    }

    private void updateHeightComponent1(ConstraintLayout component1, int size) {
        ViewGroup.LayoutParams layoutParams = component1.getLayoutParams();
        layoutParams.height = size;
        component1.setLayoutParams(layoutParams);
    }

    public void RunOnUiThread(View v, int x, int y) {
        getActivity().runOnUiThread(() -> v.scrollTo(x, y));
    }

    private void openNotificationDialog() {
        Dialog dialog = new Dialog(requireContext());
        DialogNotificationBinding binding = DialogNotificationBinding.inflate(LayoutInflater.from(requireContext()));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(binding.getRoot());
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setWindowAnimations(R.style.SlideUpAnimation);

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.BOTTOM;

        binding.DialogNotificationRV.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        binding.DialogNotificationRV.setAdapter(notificationAdapter);

        window.setAttributes(windowAttributes);
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
                new SwipedNotificationRV(0, ItemTouchHelper.LEFT, this);

        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.DialogNotificationRV);

        binding.headerIcon.setOnClickListener(v -> {
            dialog.dismiss();
        });

        dialog.show();
    }



    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof NotificationAdapter.MyViewHolder) {
            String data = arrayList3.get(viewHolder.getAdapterPosition()).getTitle();
            int position = viewHolder.getAdapterPosition();
            notificationAdapter.delete(position);
        }
    }
    private void ViewPagerSetUp() {

        dataViewPager.add(new HomeAchievement(1, R.drawable.a0, getResources().getString(R.string.achievement_1_title)));
        dataViewPager.add(new HomeAchievement(2, R.drawable.b0, getResources().getString(R.string.achievement_2_title)));
        dataViewPager.add(new HomeAchievement(3, R.drawable.c0, getResources().getString(R.string.achievement_3_title)));

        fragmentHomeBinding.vp2.setOffscreenPageLimit(3);
        fragmentHomeBinding.vp2.setClipToPadding(false);
        fragmentHomeBinding.vp2.setClipChildren(false);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(20));
        fragmentHomeBinding.vp2.setPageTransformer(compositePageTransformer);

        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * 0.15f);
        });

        VPAchievementAdapter adapter = new VPAchievementAdapter(dataViewPager, typeAchievement -> {
            Intent loginAchievement = new Intent(getActivity(), Login_Achievement.class);
            loginAchievement.putExtra("TypeAchievement", typeAchievement);
            startActivity(loginAchievement);
        });

        fragmentHomeBinding.vp2.setAdapter(adapter);
        fragmentHomeBinding.vp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handlerViewPager.removeCallbacks(runnableViewPager);
                handlerViewPager.postDelayed(runnableViewPager, 2209);
            }
        });
        fragmentHomeBinding.ci3.setViewPager(fragmentHomeBinding.vp2);
    }
    Handler handlerViewPager = new Handler(Looper.getMainLooper());
    Runnable runnableViewPager = new Runnable() {
        @Override
        public void run() {
            int currentPos = fragmentHomeBinding.vp2.getCurrentItem();
            if (currentPos == dataViewPager.size() - 1) {
                fragmentHomeBinding.vp2.setCurrentItem(0);
            } else {
                fragmentHomeBinding.vp2.setCurrentItem(currentPos + 1);
            }
        }
    };
    private void TestData() {
        if (arrayList.size() == 0) {
            arrayList.add(new Subject("Toi la son", "toi la hoc sinh", "10"));
            arrayList.add(new Subject("Toi la 2", "toi la hoc 2", "10"));
            arrayList.add(new Subject("Toi la 3", "toi la hoc 2", "10"));
            arrayList.add(new Subject("Toi la 4", "toi la hoc 4", "10"));
            arrayList.add(new Subject("Toi la 5", "toi la hoc 1242", "10"));
            arrayList.add(new Subject("Toi la 42", "toi la hoc 24", "10"));
            arrayList.add(new Subject("Toi la 22", "toi la hoc 22", "10"));

            if (sbj != null ){
                arrayList.add(sbj);
                countNumberClickSubject.add(0);
            }

            countNumberClickSubject.add(0);
            countNumberClickSubject.add(0);
            countNumberClickSubject.add(0);
            countNumberClickSubject.add(0);
            countNumberClickSubject.add(0);
            countNumberClickSubject.add(0);
            countNumberClickSubject.add(0);
        }
        if (arrayList2.size() == 0) {
            arrayList2.add(new Folder("hi", "hii", "1", "1"));
            arrayList2.add(new Folder("hi", "hii", "1", "1"));
            arrayList2.add(new Folder("hi", "hii", "1", "1"));
            arrayList2.add(new Folder("hi", "hii", "1", "1"));
            arrayList2.add(new Folder("hi", "hii", "1", "1"));
            arrayList2.add(new Folder("hi", "hii", "1", "1"));
        }
        if (arrayList3.size() == 0) {
            arrayList3.add(new Notification("Tuyệt vời. Bạn đang có chuỗi 3 ngày liên tục. Hãy duy trì động lực vào học tiếp.5g", "1"));
            arrayList3.add(new Notification("Chúc mừng năm mới", "2"));
            arrayList3.add(new Notification("Chúc mừng ngày mới", "2"));
            arrayList3.add(new Notification("Chúc mừng sinh nhật", "2"));
            arrayList3.add(new Notification("Chúc mừng năm mới", "2"));
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        handlerViewPager.removeCallbacks(runnableViewPager);
    }

    @Override
    public void onResume() {
        super.onResume();
        handlerViewPager.postDelayed(runnableViewPager, 2209);
    }
}