package studio.jact.gamebox.game.profit_pioneer.core;

import java.util.ArrayList;
import java.util.List;

public class WorkHouse {
    private int stage;
    private int turns;
    private List<IWorker> workerList = new ArrayList<IWorker>();
    private List <IJob> jobList = new ArrayList<IJob>();

    private void generateJobs(){

    }

    private void generateWorkers(int workersCount){
        for(int i = 0; i < workersCount; i++){
            IWorker worker = new Worker();
            workerList.add(worker);
        }
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public void nextStage(){
        this.stage++;
        int jobsCount = stage < 6 ?  stage + 1 : 6;
        int workersCount = stage < 6 ?  stage + 2 : 8;
        generateWorkers(workersCount);
//        generateJobs(jobsCount);
    }

    public List<IWorker> getWorkerList() {
        return workerList;
    }

    public void setWorkerList(List<IWorker> workerList) {
        this.workerList = workerList;
    }

    public List<IJob> getJobList() {
        return jobList;
    }

    public void setJobList(List<IJob> jobList) {
        this.jobList = jobList;
    }
}
