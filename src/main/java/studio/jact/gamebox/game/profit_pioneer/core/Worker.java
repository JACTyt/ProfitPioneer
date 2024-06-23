package studio.jact.gamebox.game.profit_pioneer.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Worker implements IWorker{
    private String name;
    private String surname;
    private int payment;

    public Worker() {
        generateFullName();
    }

    public Worker(String name, String surname, int payment) {
        this.name = name;
        this.surname = surname;
        this.payment = payment;
    }

    @Override
    public void doJob(IJob job) {
        job.assignWorker(this);
    }

    public String getFullName(){
        return  name + " " + surname;
    }

    private void generateFullName(){
        JSONArray jsonNames = new JSONArray();

        try (FileReader reader = new FileReader("src/main/java/studio/jact/gamebox/game/profit_pioneer/names/first-names.json"))
        {
            //Read JSON file
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(reader);
            jsonNames = (JSONArray) obj;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        int randName = (int)(Math.random()*jsonNames.size());
        name = (String)jsonNames.get(randName);
        System.out.println(name);

        JSONArray jsonSurnames = new JSONArray();
        try (FileReader reader = new FileReader("src/main/java/studio/jact/gamebox/game/profit_pioneer/names/middle-names.json"))
        {
            //Read JSON file
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(reader);
            jsonSurnames = (JSONArray) obj;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        int randSurname = (int)(Math.random()*jsonSurnames.size());
        surname = (String)jsonSurnames.get(randSurname);
        System.out.println(surname);
    }
}
