package studio.jact.gamebox.game.profit_pioneer.core;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONGetter {

    public static String getRandomStringFromJson(String fileName) {
        JSONArray jsonStrings = new JSONArray();
        try (FileReader reader = new FileReader(fileName)) {
            JSONParser parser = new JSONParser();
            jsonStrings = (JSONArray) parser.parse(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        int randString = (int) (Math.random() * jsonStrings.size());
        return (String) jsonStrings.get(randString);
    }

}
