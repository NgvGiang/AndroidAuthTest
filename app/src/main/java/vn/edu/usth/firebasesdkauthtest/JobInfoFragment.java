package vn.edu.usth.firebasesdkauthtest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import vn.edu.usth.firebasesdkauthtest.Model.Job;
import vn.edu.usth.firebasesdkauthtest.Model.JobItem;


public class JobInfoFragment extends Fragment {
    private ArrayList<JobItem> jobItemList;
    private RecyclerView jobsRecyclerView;
    private Button addJobButton;
    private FirebaseAuth mAuth;
    private String userId;
    private DatabaseReference personRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_job_info, container, false);
        jobItemList = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        jobsRecyclerView = rootView.findViewById(R.id.jobsRecyclerView);
        addJobButton = rootView.findViewById(R.id.addJobButton);

        jobItemList.add(new JobItem("jobID1",new Job("Teacher","Light",2000)));
        jobItemList.add(new JobItem("jobID2",new Job("Gamer","Light",300)));
        jobItemList.add(new JobItem("jobID3",new Job("Engineer","Dark",4000)));
        jobItemList.add(new JobItem("jobID4",new Job("Pilot","Light",10000)));
        // adapter config
        JobAdapter jobAdapter = new JobAdapter(getContext(), jobItemList);
        jobsRecyclerView.setAdapter(jobAdapter);
        jobsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return rootView;
    }

}