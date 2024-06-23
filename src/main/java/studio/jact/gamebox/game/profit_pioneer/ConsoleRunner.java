package studio.jact.gamebox.game.profit_pioneer;

import studio.jact.gamebox.game.profit_pioneer.core.WorkHouse;

public class ConsoleRunner {
    public static void main(String[] args) {
        System.out.println("Profit Pioneer");
        WorkHouse workHouse = new WorkHouse();
        workHouse.nextStage();
    }
}
