import Work.Province;
import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class MapData {

    private static List<Province> provinces;

    public static void readMap() throws IOException{
        InputStream filePath = MapData.class.getResourceAsStream("mapData.json");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                (filePath,"utf-8"));
        String line;
        StringBuilder stringBuilder=new StringBuilder();
        while((line=bufferedReader.readLine())!=null){
            stringBuilder.append(line);
        }
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
