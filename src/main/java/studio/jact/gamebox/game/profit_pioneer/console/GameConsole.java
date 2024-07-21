package studio.jact.gamebox.game.profit_pioneer.console;

import studio.jact.gamebox.game.profit_pioneer.core.IJob;
import studio.jact.gamebox.game.profit_pioneer.core.IWorker;
import studio.jact.gamebox.game.profit_pioneer.core.WorkHouse;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameConsole {

    public void runGame() {

        WorkHouse workHouse = new WorkHouse();

        while(workHouse.isWorking()){
            while(workHouse.hasJobs()){
                writeInfo(workHouse);

                int workerId = 1;
                int jobId = 1;
                // Read Input

                Pattern pattern = Pattern.compile("^([1-9][0-9]?) TO ([1-9][0-9]?)$", Pattern.CASE_INSENSITIVE);
                String userInput = new Scanner(System.in).nextLine();

                Matcher matcher = pattern.matcher(userInput);

                if(matcher.find()){
                    workerId = Integer.parseInt(matcher.group(1));
                    jobId =  Integer.parseInt(matcher.group(2));
                }


                workHouse.assignWorkerToJob(workerId, jobId);

            }
            workHouse.nextStage();
        }

        System.out.println("GAME OVER");
    }

    private void writeInfo(WorkHouse workHouse){
        System.out.println("Profit: " + workHouse.getProfit());

        for (IWorker worker : workHouse.getWorkerList()){
            System.out.println("== Worker " + worker.getId() + " ==");
            System.out.println("Full name: " + worker.getFullName());
            System.out.println("Payment: " + worker.getPayment());
        }

        for (IJob job : workHouse.getJobList()){
            System.out.println("== Job " + job.getId() + " ==");
            System.out.println("Name: " + job.getName());
            System.out.println("Workers Capacity: " + job.getCapacity());
            System.out.println("Workers Taken: " + job.getAssignedWorkers().size());
            for ( IWorker worker : job.getAssignedWorkers()) {
                System.out.println("  " + worker.getId() + " " + worker.getFullName() + " " + worker.getPayment());
            }
        }
    }

}
