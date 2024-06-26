package studio.jact.gamebox.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import studio.jact.gamebox.game.profit_pioneer.core.IWorker;
import studio.jact.gamebox.game.profit_pioneer.core.WorkHouse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class ProfitPioneerController {

    private boolean gameStarted = false;
    private WorkHouse workHouse = new WorkHouse();
    @GetMapping("/")
    public String index(Model model) {
        prepareModel(model);
        return "index";
    }

    @GetMapping("/start-game")
    public String startGame(Model model) {
        prepareModel(model);
        workHouse = new WorkHouse();
        workHouse.nextStage();
        gameStarted = true;
        return "redirect:/";
    }

    @GetMapping("/assign")
    public String assign(@RequestParam(value = "worker", required = true) int workerId, @RequestParam(value = "job", required = true) int jobId, Model model) {
        workHouse.assignWorkerToJob(workerId,jobId);

        prepareModel(model);
        return "redirect:/";
    }
    @GetMapping("/rules")
    public String rules(Model model) {
        return "rules";
    }

    @GetMapping("/reset")
    public String reset(Model model) {
        workHouse = new WorkHouse();
        gameStarted = false;
        prepareModel(model);
        return "redirect:/";
    }
    @GetMapping("/remove")
    public String remove(@RequestParam(value = "worker",required = true) int workedId, Model model) {
        IWorker worker = workHouse.getWorkerList().stream().filter(x -> x.getId() == workedId).findFirst().get();
        workHouse.getWorkerList().remove(worker);
        prepareModel(model);
        return "redirect:/";
    }

    public void prepareModel(Model model){
        model.addAttribute("workHouse", workHouse);
        model.addAttribute("gameStarted", gameStarted);
    }
    public WorkHouse getWorkHouse(){
        return workHouse;
    }
    public boolean isGameStarted(){
        return gameStarted;
    }
}
