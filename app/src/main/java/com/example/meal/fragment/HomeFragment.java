package com.example.meal.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.meal.DailyItemAdapter;
import com.example.meal.DailyItemModel;
import com.example.meal.ImageSliderAdapter;
import com.example.meal.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private ViewPager2 viewPager2;
    private ImageSliderAdapter imageSliderAdapter;
    private Timer timer;
    private final long DELAY_MS = 2000;
    private final long PERIOD_MS = 3000;

    RecyclerView recyclerView;
    List<DailyItemModel> itemModelList;
    DailyItemAdapter myAdapter;
   // private FirebaseFirestore firestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
     //   firestore = FirebaseFirestore.getInstance();

        viewPager2 = rootView.findViewById(R.id.imageSliderViewPager);
        imageSliderAdapter = new ImageSliderAdapter();
        viewPager2.setAdapter(imageSliderAdapter);

        startAutoSlide();

        recyclerView = rootView.findViewById(R.id.recyclerView);  // Make sure to add the correct ID for your RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        itemModelList = new ArrayList<>();
        myAdapter = new DailyItemAdapter( getContext(), (ArrayList<DailyItemModel>) itemModelList);
        recyclerView.setAdapter(myAdapter);
//
//        firestore.collection("monday")
//                .get()
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        QuerySnapshot querySnapshot = task.getResult();
//                        for (QueryDocumentSnapshot document : querySnapshot) {
//                            itemModel item = document.toObject(itemModel.class);
//                            itemModelList.add(item);
//                        }
//                        myAdapter.notifyDataSetChanged();  // Move this line outside the loop
//                    } else {
//                        // Handle the task failure
//                        Toast.makeText(getActivity(), "Restart or Please Wait...", Toast.LENGTH_SHORT).show();
//                    }
//                });

        return rootView;
    }

    private void startAutoSlide() {
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            public void run() {
                int itemCount = imageSliderAdapter.getItemCount();
                if (itemCount > 0) {
                    int nextPage = (viewPager2.getCurrentItem() + 1) % itemCount;
                    viewPager2.setCurrentItem(nextPage, true);
                }
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_MS, PERIOD_MS);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
