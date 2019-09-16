
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        BufferedReader bufferedReader=null;
        BufferedWriter bufferedWriter=null;
        try{
            bufferedReader=new BufferedReader(new InputStreamReader
                    (new FileInputStream(args[0]), "utf-8"));
            bufferedWriter=new BufferedWriter(new OutputStreamWriter
                    (new FileOutputStream(args[1]), StandardCharsets.UTF_8));
            String line;
            while((line=bufferedReader.readLine())!=null){
                Trim trim=new Trim();
                trim.setNewInformation(line);
                trim.trimPhoneNumber();
                trim.trimLevel();
                trim.trimProvince();
                String jsonArray=trim.sout();
                bufferedWriter.write(jsonArray);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(bufferedReader!=null){
                    bufferedReader.close();
                }
                if(bufferedWriter!=null){
                    bufferedWriter.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
}
}
