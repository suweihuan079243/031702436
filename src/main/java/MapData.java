import Work.Province;
import com.alibaba.fastjson.JSON;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MapData {

    private static List<Province> provinces;

    public static void readMap() throws IOException{
        String filePath = "Address/mapData.json";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                (new FileInputStream(filePath),"utf-8"));
        //BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter("map.txt"));
        String line=null;
        StringBuilder stringBuilder=new StringBuilder();
        while((line=bufferedReader.readLine())!=null){
            stringBuilder.append(line);
//            bufferedWriter.write(line);
//            bufferedWriter.newLine();
//            bufferedWriter.flush();
        }
        bufferedReader.close();
       // bufferedWriter.close();
        bufferedReader.close();
        provinces= JSON.parseArray(stringBuilder.toString(),Province.class);
    }

    public static List<Province> getProvinces() {
        return provinces;
    }

    public static void setProvinces(List<Province> provinces) {
        MapData.provinces = provinces;
    }

}
