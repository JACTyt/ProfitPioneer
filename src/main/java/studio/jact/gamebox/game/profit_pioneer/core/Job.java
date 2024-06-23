package studio.jact.gamebox.game.profit_pioneer.core;

import java.util.ArrayList;
import java.util.List;

public class Job implements IJob{

    private int jobCapacity;
    private int jobPenalty;
    private List<IWorker> assignedWorkers = new ArrayList<IWorker>();
    public Job() {
        jobCapacity = 1;
    }
    public Job(int jobCapacity, List<IWorker> assignedWorkers) {
        this.jobCapacity = jobCapacity;
        this.assignedWorkers = assignedWorkers;
    }

    @Override
    public void assignWorker(IWorker worker) {
        if(!assignedWorkers.contains(worker))
            assignedWorkers.add(worker);
    }
}
