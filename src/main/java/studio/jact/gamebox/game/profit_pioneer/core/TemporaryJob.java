package studio.jact.gamebox.game.profit_pioneer.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TemporaryJob extends Job {
    private int remainTime = 101;
    private int penalty;

    public TemporaryJob() {

    }

    public TemporaryJob(int id) {
        super(id);
        this.penalty = 10;
        this.remainTime = 1;
        Initialize();
    }

    public TemporaryJob(int id, int remainTime, int penalty) {
        super(id);
        this.remainTime = remainTime;
        this.penalty = penalty;
    }

    @Override
    public void Initialize() {
        super.Initialize();
        remainTime = ValueGenerator.generate(1, 3, 1);
        penalty = ValueGenerator.generate(80, 220, 10);
    }

    public int getRemainTime() {
        return remainTime;
    }

    @JsonIgnore
    public String getJobTimeString() {
        return Integer.toString(remainTime);
    }

    public void setRemainTime(int remainTime) {
        this.remainTime = remainTime;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

}
