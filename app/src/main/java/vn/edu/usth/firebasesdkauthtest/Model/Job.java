package vn.edu.usth.firebasesdkauthtest.Model;



public class Job {
    private String jobName;
    private String skinColor;
    private int salary;

    public Job() {
    }

    public Job(String jobName, String skinColor, int salary) {
        this.jobName = jobName;
        this.skinColor = skinColor;
        this.salary = salary;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
