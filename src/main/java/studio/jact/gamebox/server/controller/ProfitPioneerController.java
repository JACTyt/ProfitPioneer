package studio.jact.gamebox.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import studio.jact.gamebox.game.profit_pioneer.core.IWorker;
import studio.jact.gamebox.game.profit_pioneer.core.WorkHouse;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class ProfitPioneerController {

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
        workHouse.startWork();
        return "redirect:/";
    }

    @GetMapping("/assign")
    public String assign(@RequestParam(value = "worker", required = true) int workerId, @RequestParam(value = "job", required = true) int jobId, Model model) {
        workHouse.assignWorkerToJob(workerId, jobId);

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
        prepareModel(model);
        return "redirect:/";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam(value = "worker", required = true) int workedId, Model model) {
        IWorker worker = workHouse.getWorkerList().stream().filter(x -> x.getId() == workedId).findFirst().get();
        workHouse.getWorkerList().remove(worker);
        if (workHouse.getWorkerList().isEmpty()) {
            workHouse.refreshTurn();
        }

        if (!isGameStarted()) {
            return statisticsPage(model);
        }
        prepareModel(model);
        return "redirect:/";
    }

    @PostMapping("/save")
    public String save(HttpServletResponse response, Model model) {

        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json");
        response.setHeader("Content-Disposition", "attachment; filename=profit_pioneer_save.json");

        try (OutputStream out = response.getOutputStream()) {
            objectMapper.writeValue(out, workHouse);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        prepareModel(model);
        return "redirect:/";
    }

    @PostMapping("/load")
    public String load(@RequestParam("file") MultipartFile file, Model model) {
        if (!file.isEmpty()) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                workHouse = objectMapper.readValue(file.getInputStream(), WorkHouse.class);
                System.out.println("SUCESS");
                prepareModel(model);
                return "redirect:/";
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }
        return "redirect:/";
    }

    public void saveToJson(WorkHouse workHouse, String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(filePath), workHouse);
    }

    @GetMapping("/end")
    public String end(Model model) {
        workHouse.endWork();
        prepareModel(model);
        return statisticsPage(model);
    }

    @GetMapping("/stats")
    private String statisticsPage(Model model) {
        prepareModel(model);
        return "stats";
    }

    public void prepareModel(Model model) {
        model.addAttribute("workHouse", workHouse);
        model.addAttribute("gameStarted", workHouse.isWorking());
    }

    public WorkHouse getWorkHouse() {
        return workHouse;
    }

    public boolean isGameStarted() {
        return workHouse.isWorking();
    }
}
