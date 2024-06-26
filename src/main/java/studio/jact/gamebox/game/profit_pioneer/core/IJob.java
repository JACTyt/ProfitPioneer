package studio.jact.gamebox.game.profit_pioneer.core;

import java.util.List;

public interface IJob {
    void Initialize();

    void assignWorker(IWorker worker);
    public String getJobName();
    public int getId();
    public int getCapacity();
    public List<IWorker> getAssignedWorkers();
    public int getDealProfit();
}