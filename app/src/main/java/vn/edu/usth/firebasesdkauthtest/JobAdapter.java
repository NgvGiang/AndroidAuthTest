package vn.edu.usth.firebasesdkauthtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.usth.firebasesdkauthtest.Model.Job;
import vn.edu.usth.firebasesdkauthtest.Model.JobItem;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder>{
    private ArrayList<JobItem> jobItemList;
    private Context context;
    public JobAdapter(Context context, ArrayList<JobItem> jobItemList){
        this.context = context;
        this.jobItemList = jobItemList;
    }
    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.job_card,parent,false);
        return new JobAdapter.JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {

        holder.jobNameTextView.setText(jobItemList.get(position).getJob().getJobName());
        holder.skinColorTextView.setText(jobItemList.get(position).getJob().getSkinColor());
        holder.salaryTextView.setText(String.valueOf(jobItemList.get(position).getJob().getSalary()));
        //delete button
//        JobItem jobItem = jobItemList.get(position);
//        Job job = jobItem.getJob();
//        holder.deleteButton.setOnClickListener(v -> deleteJob(jobItem.getJobId())); // initialize this delete function in JobFragment
    }

    @Override
    public int getItemCount() {
        return jobItemList.size();
    }

    //inner class viewholder
    public static class JobViewHolder extends RecyclerView.ViewHolder{
        TextView jobNameTextView, skinColorTextView, salaryTextView;
        Button deleteButton;
        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            jobNameTextView = itemView.findViewById(R.id.jobNameTextView);
            skinColorTextView = itemView.findViewById(R.id.skinColorTextView);
            salaryTextView = itemView.findViewById(R.id.salaryTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
