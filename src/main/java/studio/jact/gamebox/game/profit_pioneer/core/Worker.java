package studio.jact.gamebox.game.profit_pioneer.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Worker implements IWorker {
    private int id;
    private String fullName;
    private int payment;

    public Worker() {

    }

    public Worker(int id) {
        this.id = id;
        Initialize();
    }

    public Worker(int id, String fullName, int payment) {
        this.id = id;
        this.fullName = fullName;
        this.payment = payment;
    }

    private void Initialize() {
        String name = JSONGetter.getRandomStringFromJson("src/main/java/studio/jact/gamebox/game/profit_pioneer/names/first-names.json");
        String surname = JSONGetter.getRandomStringFromJson("src/main/java/studio/jact/gamebox/game/profit_pioneer/names/middle-names.json");
        fullName = name + " " + surname;
        payment = ValueGenerator.generate(10, 90, 5);
    }

    @Override
    public void doJob(IJob job) {
        job.assignWorker(this);
    }

    @JsonProperty("fullName")
    @Override
    public String getFullName() {
        return fullName;
    }

    public int getId() {
        return id;
    }

    @Override
    public int getPayment() {
        return payment;
    }
}
