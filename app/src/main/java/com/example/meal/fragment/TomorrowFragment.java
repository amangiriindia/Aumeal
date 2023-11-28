package com.example.meal.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meal.adapter.DailyItemAdapter;
import com.example.meal.model.DailyItemModel;
import com.example.meal.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TomorrowFragment extends Fragment {

    RecyclerView recyclerView;
    List<DailyItemModel> dailyItemModelList;
    DailyItemAdapter dailyItemAdapter;
    private FirebaseFirestore firestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tomorrow, container, false);
        firestore = FirebaseFirestore.getInstance();

        // Initialize RecyclerView and set its layout manager and adapter
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        dailyItemModelList = new ArrayList<>();
        dailyItemAdapter= new DailyItemAdapter(getContext(), dailyItemModelList);
        recyclerView.setAdapter(dailyItemAdapter);

        String nextday =getNextDay().toLowerCase();
       // Toast.makeText(getActivity(), ""+nextday, Toast.LENGTH_SHORT).show();

        // Fetch data from Firestore
        firestore.collection(nextday) // Adjust the collection name
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        // Clear the existing list before adding new items
                        dailyItemModelList.clear();

                        // Loop through the query snapshot and add items to the list
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            DailyItemModel dailyItemModel = document.toObject(DailyItemModel.class);
                            dailyItemModelList.add(dailyItemModel);
                        }

                        // Notify the adapter about the changes
                        dailyItemAdapter.notifyDataSetChanged();

                        // Display a toast or perform other actions as needed
                       // Toast.makeText(getActivity(), "Data loaded successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle the failure case
                        Toast.makeText(getActivity(), "Failed to load data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        return rootView;
    }
    public static String getNextDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
        return dateFormat.format(calendar.getTime());
    }
}

