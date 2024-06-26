package studio.jact.gamebox.game.profit_pioneer.core;

public class JobExtras {
    public static boolean isTemporaryJob(IJob job) {
        return job instanceof TemporaryJob;
    }

    public static int getTemporaryJobTime(IJob job) {
        if (job instanceof TemporaryJob) {
            return ((TemporaryJob) job).getRemainTime();
        }
        throw new IllegalArgumentException("Job is not a TemporaryJob");
    }

    public static int getPenalty(IJob job) {
        if (job instanceof TemporaryJob) {
            return ((TemporaryJob) job).getPenalty();
        }
        throw new IllegalArgumentException("Job is not a TemporaryJob");
    }
}
