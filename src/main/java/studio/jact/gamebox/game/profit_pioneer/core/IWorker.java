package studio.jact.gamebox.game.profit_pioneer.core;

public interface IWorker {
    public void doJob(IJob job);
    public String getFullName();
    public int getId();
    public int getPayment();
}
