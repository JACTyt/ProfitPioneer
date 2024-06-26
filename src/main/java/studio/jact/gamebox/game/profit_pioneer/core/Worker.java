package studio.jact.gamebox.game.profit_pioneer.core;

public class Worker implements IWorker{
    private int id;
    private String name;
    private String surname;
    private int payment;

    public Worker(int id) {
        this.id = id;
        Initialize();
    }

    public Worker(int id, String name, String surname, int payment) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.payment = payment;
    }

    private void Initialize() {
        name = JSONGetter.getRandomStringFromJson("src/main/java/studio/jact/gamebox/game/profit_pioneer/names/first-names.json");
        surname = JSONGetter.getRandomStringFromJson("src/main/java/studio/jact/gamebox/game/profit_pioneer/names/middle-names.json");
        payment = ValueGenerator.generate(10,90,5);
    }

    @Override
    public void doJob(IJob job) {
        job.assignWorker(this);
    }

    @Override
    public String getFullName(){
        return  name + " " + surname;
    }

    public int getId(){
        return id;
    }

    @Override
    public int getPayment() {
        return payment;
    }
}
