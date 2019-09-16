import Work.*;
import com.alibaba.fastjson.JSON;
import com.squareup.moshi.Json;
import com.sun.deploy.perf.PerfRollup;
import com.sun.xml.internal.ws.api.message.MessageWritable;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Trim {
    private  Person person;

    private String phoneNumber;

    private  String newInformation;

    private int level;

    private String personName;

    private Province province;

    private City city;

    private County county;

    private Town town;

    private Street street;

    public Trim(){
        this.person=new Person();
    }

    public Person getPerson() {
        return this.person;
    }


    public String getNewInformation() {
        return newInformation;
    }

    public void setNewInformation(String newInformation) {
        this.newInformation = newInformation.substring(0,newInformation.length()-1);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level)  {
        this.level = level;
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


    public void trimPhoneNumber() {
        String regexPhone = "\\d{11}";
        Pattern pattern = Pattern.compile(regexPhone);
        Matcher matcher = pattern.matcher(this.newInformation);
        //System.out.println(newInformation);
        if (matcher.find()) {
            //System.out.println(matcher.group());
            this.phoneNumber=matcher.group();
            //person.setPhoneNumber(matcher.group());
        }
        this.newInformation = matcher.replaceAll("").trim();
        // System.out.println(newInformation);
    }

    public void trimLevel() {

        String regexLevel = "[!,]";

        String[] split = this.newInformation.split(regexLevel);

        this.level = Integer.parseInt(split[0]);

        ///System.out.println(level);

        personName = split[1];

        //person.setName(personName);

        this.newInformation = split[2];
        //System.out.println(newInformation);
    }

    public void trimProvince() {
        try {
            MapData.readMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
        person.setName(this.personName);
        person.setPhoneNumber(this.phoneNumber);
        // 福建福州市鼓楼区鼓西街道湖滨路110号湖滨大厦一层.
        String provinceInformation = this.newInformation.substring(0, 2);//福建

        String provinceName =null;

        for (Province province : MapData.getProvinces()) {
           provinceName = province.getProvinceName().substring(0, 2);//福建
            if (provinceInformation.equals(provinceName)) {

                if (provinceName.equals("北京") || provinceName.equals("重庆") ||
                        provinceName.equals("天津") ||provinceName.equals("上海")){
                    //
                    person.setProvince(province.getProvinceName());
                    //person.setCity(provinceName + "市");
                    //person.getAddress().add(province.getProvinceName());
                    //person.getAddress().add(provinceName + "市");
                } else {
                    person.setProvince(province.getProvinceName());
                    //person.getAddress().add(province.getProvinceName());
                    this.newInformation = trimInformation(this.newInformation, province.getProvinceName());
                }
                this.province=province;
                break;
            }
        }
            trimCity(this.province);
    }

    public void trimCity(Province province){
        try {
            MapData.readMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //福州市鼓楼区鼓西街道湖滨路110号湖滨大厦一层.
        //北京市东城区交道口东大街1号北京市东城区人民法院
        String cityInformation = this.newInformation.substring(0, 2);//福州
        //System.out.println(cityInformation);
        String cityName=null;

        for(City city:province.getCities()){
            cityName=city.getCityName().substring(0,2);

            if(cityInformation.equals(cityName)){
                this.newInformation = trimInformation(this.newInformation, city.getCityName());
                this.city=city;
                person.setCity(city.getCityName());
                //person.getAddress().add(city.getCityName());
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
        String couontyInformation = this.newInformation.substring(0, 2);//

        String countyName=null;

        for(County county:city.getCounties()){
            countyName=county.getCountyName().substring(0,2);
            if(couontyInformation.equals(countyName)){
                this.newInformation = trimInformation(this.newInformation,county.getCountyName());
                this.county=county;
                person.setCounty(county.getCountyName());
              //  person.getAddress().add(county.getCountyName());
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
        //五一北路123号福州鼓楼医院
        String townInformation = this.newInformation.substring(0, 2);//

        String townName=null;
        for(Town town:county.getTowns()){
            townName=town.getTownName().substring(0,2);
            if(townInformation.equals(townName)){
                this.newInformation = trimInformation(this.newInformation, town.getTownName());
                this.town=town;
                person.setTown(town.getTownName());
                //person.getAddress().add(town.getTownName());
               // System.out.println(person.getTown());
                //System.out.println(newInformation);
                break;
            }
        }
            if(this.town==null){
                person.setTown("\"\"");
            }
            trimStreet();
    }

    private void trimStreet() {

        if (this.level == 1) {
            person.setRestAddress(this.newInformation);
            //person.getAddress().add(this.newInformation);
        } else if (this.level == 2 || level == 3) {
            String regex1 = "(\\D+)(\\d+号)";
            Pattern pattern = Pattern.compile(regex1);
            Matcher matcher = pattern.matcher(newInformation);
            //System.out.println(newInformation);
            String restAddress = null;
            if (matcher.find()) {
                restAddress = matcher.replaceFirst("");
                this.newInformation = matcher.group();
                String regex2 = "\\d+号";
                String gateNumber = null;
                String roadName = null;
                Pattern pattern1 = Pattern.compile(regex2);
                Matcher matcher1 = pattern1.matcher(this.newInformation);
                if (matcher1.find()) {
                    gateNumber = matcher1.group(0);
                    roadName = matcher1.replaceAll("");
//                    System.out.println(roadName);
//                    System.out.println(gateNumber);
//                    System.out.println(restAddress);
                }
                if(roadName!=null){
                    person.setRoadName(roadName);
                }else {
                    person.setRoadName("\"\"");
                }
                if(gateNumber!=null){
                    person.setGateNumber(gateNumber);
                }else{
                    person.setGateNumber("\"\"");
                }
                if(restAddress!=null){
                    person.setRestAddress(restAddress);
                }else{
                    person.setRestAddress("\"\"");
                }
            }
        }
    }



    public String trimInformation(String information, String Name) {
        //福建福州市鼓楼区鼓西街道湖滨路110号湖滨大厦一层
        //福建省
       // System.out.println(information);
        //System.out.println(Name);
        int length=Math.min(information.length(), Name.length());
       // System.out.println(length);
        int index=length;
        for(int i=0;i<length;i++){
            if(information.charAt(i)!=Name.charAt(i)){
                index=i;
                break;
            }
        }

       // System.out.println(index);
       // System.out.println(information.substring(index));
       // System.out.println(information.substring(index));
        return information.substring(index);
    }
    public String sout(){
        //String message=JSON.toJSONString(person);
        //System.out.println(message);
//        System.out.println("{");
//        System.out.println("[");
//        System.out.println("    "+"\"姓名\":"+"\""+person.getName()+"\",");
//        System.out.println("    "+"\"手机\":"+"\""+person.getPhoneNumber()+"\",");
//        System.out.println("    "+"\"地址\":"+"["+"\r\n"+"    "+"\""+person.getProvince()+"\",");
//        System.out.println("    "+"\""+person.getCity()+"\",");
//        System.out.println("    "+"\""+person.getCounty()+"\",");
//        System.out.println("    "+"\""+person.getTown() +"\",");

       // System.out.println("    "+"\""+person.getRestAddress()+"\""+"\r\n"+"]"+"\r\n"+"},");

        return JSON.toJSONString(person);
        //System.out.println(JSON.toJSONString(person));
    }

}