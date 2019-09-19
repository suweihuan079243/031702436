import Work.Person;
import com.alibaba.fastjson.JSON;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        List<Person> persons =null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader
                    (new FileInputStream(args[0]), "utf-8"));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter
                    (new FileOutputStream(args[1]), StandardCharsets.UTF_8));
            String line;
            persons = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                Trim trim = new Trim();
                Person person=new Person();
                trim.setNewInformation(line);
                trim.trimPhoneNumber();
                trim.trimLevel();
                trim.trimProvince();
                person=trim.getPerson();
                persons.add(person);
            }
            bufferedWriter.write(JSON.toJSONString(persons));
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
