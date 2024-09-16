package vn.edu.usth.firebasesdkauthtest.Model;

public class JobItem {
    private String jobId;
    private Job job;

    public JobItem(String jobId, Job job) {
        this.jobId = jobId;
        this.job = job;
    }

    // Getter và Setter
    public String getJobId() {
        return jobId;
    }
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public Job getJob() {
        return job;
    }
    public void setJob(Job job) {
        this.job = job;
    }
}
