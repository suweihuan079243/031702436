import Work.*;
import com.alibaba.fastjson.JSON;
import com.squareup.moshi.Json;
import com.sun.xml.internal.ws.api.message.MessageWritable;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Trim {
    private Person person;

    private  String newInformation;

    private int level;

    private String personName;

    private Province province;

    private City city;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getNewInformation() {
        return newInformation;
    }

    public void setNewInformation(String newInformation) {
        this.newInformation = newInformation;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level)  {
        this.level = level;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    private County county;

    private Town town;

    private Street street;

    public Person getPerson(String orign) {
        this.person = new Person(orign);
        return this.person;
    }

    public void trimPhone() {
        String regexPhone = "\\d{11}";
        Pattern pattern = Pattern.compile(regexPhone);
        Matcher matcher = pattern.matcher(person.getInformation());
        if (matcher.find()) {
            //System.out.println(matcher.group());
            person.setPhone(matcher.group());
        }
        newInformation = matcher.replaceAll("").trim();
        // System.out.println(newInformation);
        person.setInformation(newInformation);
    }

    public void trimLevel() {

        String regexLevel = "[!,]";

        String[] split = newInformation.split(regexLevel);

        this.level = Integer.parseInt(split[0]);

        System.out.println(level);

        personName = split[1];

        person.setName(personName);

        newInformation = split[2];
        person.setInformation(newInformation);
        //System.out.println(newInformation);
    }

    public void trimProvince() {
        try {
            MapData.readMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 福建福州市鼓楼区鼓西街道湖滨路110号湖滨大厦一层.
        String provinceInformation = newInformation.substring(0, 2);//福建

        String provinceName =null;

        for (Province province : MapData.getProvinces()) {
           provinceName = province.getProvinceName().substring(0, 2);//福建
            if (provinceInformation.equals(provinceName)) {
                //省名字的长度
                if (provinceName.equals("北京") || provinceName.equals("重庆") ||
                        provinceName.equals("天津") ||provinceName.equals("上海")){
                    //
                    person.setProvince(province.getProvinceName());
                    person.setCity(provinceName + "市");
                } else {
                    person.setProvince(province.getProvinceName());
                }
                newInformation = trimInformation(newInformation, province.getProvinceName());
                this.province=province;
                break;
            }
        }
        if(this.province!=null){
            trimCity(this.province);
        }else {
            person.setProvince("\"\"");
        }
    }

    public void trimCity(Province province){
        try {
            MapData.readMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //福州市鼓楼区鼓西街道湖滨路110号湖滨大厦一层.
        String cityInformation = newInformation.substring(0, 2);//福州
        //System.out.println(cityInformation);
        String cityName=null;

        for(City city:province.getCities()){
            cityName=city.getCityName().substring(0,2);

            if(cityInformation.equals(cityName)){
                newInformation = trimInformation(newInformation, city.getCityName());
                this.city=city;
                person.setCity(city.getCityName());
                //System.out.println(person.getCity());
                break;
            }
        }
        if(this.city!=null){
            trimCounty(this.city);
        }else{
            person.setCity("\"\"");
            if(this.province!=null){
                List<City> cityList = this.province.getCities();
                for(City city:cityList){
                    trimCounty(city);
                }
            }
        }
    }

    private void trimCounty(City city) {
        try {
            MapData.readMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //鼓楼区鼓西街道湖滨路110号湖滨大厦一层.
        String couontyInformation = newInformation.substring(0, 2);//

        String countyName=null;

        for(County county:city.getCounties()){
            countyName=county.getCountyName().substring(0,2);
            if(couontyInformation.equals(countyName)){
                newInformation = trimInformation(newInformation,county.getCountyName());
                this.county=county;
                person.setCounty(county.getCountyName());
                //System.out.println(person.getCounty());
                break;
            }
        }
        if(this.county!=null){
            trimTown(this.county);
        }else{
            person.setCounty("\"\"");
            if(this.city!=null){
                List<County> countyList = this.city.getCounties();
                for(County county:countyList){
                    trimTown(county);
                }
            }
        }
    }

    private void trimTown(County county) {
        try {
            MapData.readMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //鼓西街道湖滨路110号湖滨大厦一层.
        String townInformation = newInformation.substring(0, 2);//

        String townName=null;
        for(Town town:county.getTowns()){
            townName=town.getTownName().substring(0,2);
            if(townInformation.equals(townName)){
                newInformation = trimInformation(newInformation, town.getTownName());
                this.town=town;
                person.setTown(town.getTownName());
               // System.out.println(person.getTown());
                //System.out.println(newInformation);
                break;
            }
        }
        if(this.town!=null){
            trimStreet(this.town);
        }else{
            person.setTown("\"\"");
            if(this.town!=null){
                List<Town> townList = this.county.getTowns();
                for(Town town:townList){
                    trimStreet(town);
                }
            }
        }
    }

    private void trimStreet(Town town) {
        //湖滨路110号湖滨大厦一层.
        System.out.println(newInformation);
        if(level==1){
            person.setRestAddress(newInformation);
        }else {
        }
    }


    public String trimInformation(String information, String provinceName) {
        //福建福州市鼓楼区鼓西街道湖滨路110号湖滨大厦一层
        //福建省
       // System.out.println(information);
       // System.out.println(provinceName);
        int length=Math.min(information.length(), provinceName.length());
       // System.out.println(length);
        int index=length;
        for(int i=0;i<length;i++){
            if(information.charAt(i)!=provinceName.charAt(i)){
                index=i;
                break;
            }
        }

       // System.out.println(index);
       // System.out.println(information.substring(index));
        return information.substring(index);
    }
    public void sout(){
        //String message=JSON.toJSONString(person);
        //System.out.println(message);
        System.out.println("{");
        System.out.println("[");
        System.out.println("    "+"\"姓名\":"+"\""+person.getName()+"\",");
        System.out.println("    "+"\"手机\":"+"\""+person.getPhone()+"\",");
        System.out.println("    "+"\"地址\":"+"["+"\r\n"+"    "+"\""+person.getProvince()+"\",");
        System.out.println("    "+"\""+person.getCity()+"\",");
        System.out.println("    "+"\""+person.getCounty()+"\",");
        System.out.println("    "+"\""+person.getTown() +"\",");
        System.out.println("    "+"\""+person.getRestAddress()+"\""+"\r\n"+"]"+"\r\n"+"},");
    }

}
