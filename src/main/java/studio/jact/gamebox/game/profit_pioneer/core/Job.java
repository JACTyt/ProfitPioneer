package studio.jact.gamebox.game.profit_pioneer.core;

import java.util.ArrayList;
import java.util.List;

public class Job implements IJob {
    private int id;
    private int capacity;
    private String name;
    private List<IWorker> assignedWorkers = new ArrayList<IWorker>();

    public Job() {

    }

    public Job(int id) {
        this.id = id;
        capacity = ValueGenerator.generate(1, 4, 1);
        Initialize();
    }

    @Override
    public void Initialize() {
        name = JSONGetter.getRandomStringFromJson("src/main/java/studio/jact/gamebox/game/profit_pioneer/names/jobs.json");
    }

    public Job(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        Initialize();
    }

    @Override
    public void assignWorker(IWorker worker) {
        if (!assignedWorkers.contains(worker))
            assignedWorkers.add(worker);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public List<IWorker> getAssignedWorkers() {
        return assignedWorkers;
    }

    @Override
    public int getDealProfit() {
        int result = 0;
        for (IWorker worker : assignedWorkers) {
            result += worker.getPayment();
        }
        return result;
    }
}
