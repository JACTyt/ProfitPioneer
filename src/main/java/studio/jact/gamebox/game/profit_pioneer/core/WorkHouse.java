package studio.jact.gamebox.game.profit_pioneer.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class WorkHouse {
    private int stage;
    private int turns;
    private int profit;
    private List<IWorker> workerList = new ArrayList<IWorker>();
    private List <IJob> jobList = new ArrayList<IJob>();

    private void generateJobs(int jobsCount){
        for(int i = 0; i < jobsCount; i++){
            IJob job = new Job(i+1);
            jobList.add(job);
        }
    }

    private void generateTemporaryJobs(int jobsCount){
        int i = 0;
        int k = jobsCount;
        while(k > 0){
            i++;
            int finalI = i;
            if(jobList.stream().noneMatch(x -> x.getId() == finalI)){
                k--;
                IJob job = new TemporaryJob(i);
                jobList.add(job);
                System.out.println(job.getJobName());
            }
        }
    }

    private void generateWorkers(int workersCount){
        for(int i = 0; i < workersCount; i++){
            IWorker worker = new Worker(i+1);
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
        this.turns = 0;
        int jobsCount = stage < 6 ?  stage + 1 : 6;
        int temporaryJobsCount = jobsCount < 1 ?  0 : ValueGenerator.generate(0,2,1);
        jobsCount -= temporaryJobsCount;
        int workersCount = stage < 6 ?  stage + 2 : 8;
        generateWorkers(workersCount);
        generateJobs(jobsCount);
        generateTemporaryJobs(temporaryJobsCount);

        if(temporaryJobsCount > 0)
            System.out.println("TEMPORARY JOBS");
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

    public boolean isWorking() {
        return true;
    }

    public void assignWorkerToJob(int workerId, int jobId){
        Optional<IWorker> workerOptional = workerList.stream().filter(x -> x.getId() == workerId).findFirst();
        Optional<IJob> jobOptional = jobList.stream().filter(x -> x.getId() == jobId).findFirst();
        if(workerOptional.isEmpty()){
            System.out.println("No worker with id " + workerId);
            return;
        }
        if(jobOptional.isEmpty()){
            System.out.println("No job with id " + jobId);
            return;
        }
        IWorker worker = workerOptional.get();
        IJob job = jobOptional.get();
        job.assignWorker(worker);
        workerList.remove(worker);
        if(job.getCapacity() == job.getAssignedWorkers().size()){
            int sum = 0;
            for (IWorker w : job.getAssignedWorkers()) {
                sum += w.getPayment();
            }
            profit += sum;
            jobList.remove(job);
        }
        if(!hasJobs()){
            nextStage();
            return;
        }
        if(!hasWorkers()){
            refreshTurn();
        }
    }

    public void refreshTurn() {
        turns++;
        generateWorkers(jobList.size() + ValueGenerator.generate(0,jobList.size()+1,1));
        Iterator<IJob> jobIterator = jobList.iterator();
        while (jobIterator.hasNext()){
            IJob job = jobIterator.next();
            if(job instanceof TemporaryJob temporaryJob) {
                temporaryJob.setRemainTime(((TemporaryJob) job).getRemainTime()-1);
                if (temporaryJob.getRemainTime() <= 0){
                    profit -= temporaryJob.getPenalty();
                    jobIterator.remove();
                }
            }
        }
        if(!hasJobs()){
            nextStage();
        }
    }

    public int getProfit() {
        return profit;
    }

    public boolean hasJobs() {
        return !jobList.isEmpty();
    }
    public boolean hasWorkers() {
        return !workerList.isEmpty();
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public void endGame(){

    }
}
