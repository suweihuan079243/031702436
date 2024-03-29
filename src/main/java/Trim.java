import Work.*;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Trim {
    private String phoneNumber;

    private String newInformation;

    private String level;

    private String personName;

    private Province province;

    private City city;

    private County county;

    private Town town;

    private Street street;

    private List<String>address=new ArrayList<>();



    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Person getPerson() {
        Person person=new Person();
        person.setName(personName);
        person.setPhoneNumber(phoneNumber);
        person.setAddress(address);
        return person;
    }


    public String getNewInformation() {
        return newInformation;
    }

    public void setNewInformation(String newInformation) {
        this.newInformation = newInformation.substring(0, newInformation.length() - 1);
    }


    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
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
        String regexPhone = "\\d{11,}";
        Pattern pattern = Pattern.compile(regexPhone);
        Matcher matcher = pattern.matcher(this.newInformation);
        if (matcher.find()) {
            this.phoneNumber = matcher.group();
        }
        this.newInformation = matcher.replaceAll("").trim();
    }

    public void trimLevel() {

        String regexLevel = "[!,]";

        String[] split = this.newInformation.split(regexLevel);

        this.level = split[0];

        personName = split[1];

        this.newInformation = split[2];
    }

    public void trimProvince() {
        try {
            MapData.readMap();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String provinceInformation = this.newInformation.substring(0, 2);
        List<Province> provinceList = MapData.getProvinces();
        for (Province province : provinceList) {
            String provinceName = province.getProvinceName().substring(0, 2);
            if (provinceInformation.equals(provinceName)) {
                if (provinceName.equals("北京") || provinceName.equals("重庆") ||
                        provinceName.equals("天津") || provinceName.equals("上海")) {
                    this.newInformation=provinceName+this.newInformation;
                    this.newInformation = trimInformation(this.newInformation, province.getProvinceName());
                } else {
                    this.newInformation = trimInformation(this.newInformation, province.getProvinceName());
                }
                this.province = province;
                break;
            }
        }
          if (this.province != null) {
            address.add(province.getProvinceName());
            trimCity(this.province);
        } else {
            address.add("");
                List<Province> provinceList1 = MapData.getProvinces();
                for (Province province : provinceList1) {
                    trimCity(province);
                    if (this.city != null) {
                        break;
                    }
                }
        }
    }

    public void trimCity(Province province) {
        try {
            MapData.readMap();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String cityInformation =null;
        try{
            cityInformation = this.newInformation.substring(0, 2);
        }catch (StringIndexOutOfBoundsException e){
            this.city=null;
        }

        String cityName = null;
        if(cityInformation!=null){
            for (City city : province.getCities()) {
                try{
                    cityName = city.getCityName().substring(0, 2);

                }catch (StringIndexOutOfBoundsException e){
                    cityName = city.getCityName().substring(0, 1);
                }
                if (cityInformation.equals(cityName)) {
                    this.newInformation = trimInformation(this.newInformation, city.getCityName());
                    this.city = city;
                    address.add(city.getCityName());
                    break;
                }
            }
        }

        if (this.city != null) {
            trimCounty(this.city);
        } else {
            address.add("");
            if (this.province != null) {
                List<City> cityList = this.province.getCities();
                for (City city : cityList) {
                    trimCounty(city);
                    if(this.county!=null){
                        break;
                    }
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


        String countyInformation =null;
        try{
             countyInformation = this.newInformation.substring(0, 2);
        }catch (StringIndexOutOfBoundsException e){
            this.county=null;
        }

        String countyName = null;
        if(countyInformation!=null){
            for (County county : city.getCounties()) {
                try{
                    countyName = county.getCountyName().substring(0, 2);
                }catch (StringIndexOutOfBoundsException e){
                    countyName = county.getCountyName().substring(0, 1);
                }
                if (countyInformation.equals(countyName)) {
                    this.newInformation = trimInformation(this.newInformation, county.getCountyName());
                    this.county = county;
                    address.add(this.county.getCountyName());
                    break;
                }
            }
        }

        if (this.county != null) {
            trimTown(this.county);
        } else {
            address.add("");
            if (this.city != null) {
                List<County> countyList = this.city.getCounties();
                for (County county : countyList) {
                    trimTown(county);
                    if(this.town!=null) {
                        break;
                    }
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
        String townInformation =null;
        try{
                townInformation = this.newInformation.substring(0, 2);
            }catch (StringIndexOutOfBoundsException e){
                this.town=null;
            }

        if(townInformation!=null){
            String townName = null;
            for (Town town : county.getTowns()) {
                try{
                    townName = town.getTownName().substring(0, 2);
                } catch (StringIndexOutOfBoundsException e){
                    townName=town.getTownName().substring(0, 1);
                }
                if (townInformation.equals(townName)) {
                this.newInformation = trimInformation(this.newInformation, town.getTownName());
                this.town = town;
                break;
            }
                  }
        }

        if (this.town != null) {
            address.add(town.getTownName());
        }else {
            address.add("");
        }
        trimStreet();
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    private void trimStreet() {
        if (this.level.contains("1")&&(this.newInformation.length()>0)) {
            address.add(this.newInformation);
        }else if(this.level.contains("1")&&(this.newInformation.length()<=0)){
            address.add("");
        } else if ((this.level.contains("2") || level.contains("3"))) {
            //只有路名和详细地址
            //只有详细地址
            //只有路名和门牌号
            //只有路名
            //只有门牌号
           // System.out.println(this.newInformation);
            String roadName="";
            String gateNumber="";
            String restAddress="";
            String regexRoadName="(.*[路道街巷里])";
            Pattern pattern=Pattern.compile(regexRoadName);
            Matcher matcher=pattern.matcher(this.newInformation);
            if(matcher.find()){
                roadName=matcher.group();
               // System.out.println(roadName);
                this.newInformation=trimInformation(this.newInformation, roadName);
            }
            String regexGateNumber="(\\d+号)";
            pattern=Pattern.compile(regexGateNumber);
            matcher=pattern.matcher(this.newInformation);
            if(matcher.find()){
                gateNumber=matcher.group();
                //System.out.println(gateNumber);
                this.newInformation=trimInformation(this.newInformation, gateNumber);
                restAddress=this.newInformation;
                //System.out.println(restAddress);
            }
            address.add(roadName);
            address.add(gateNumber);
            if(this.newInformation.length()==0){
                address.add("");
            }else{
                address.add(restAddress);
            }
        }
    }


    public String trimInformation(String information, String Name) {
        int length = Math.min(information.length(), Name.length());
        int index = length;
        for (int i = 0; i < length; i++) {
            if (information.charAt(i) != Name.charAt(i)) {
                index = i;
                break;
            }
        }
        return information.substring(index);

    }

    public void sout() {
        System.out.println(address);
    }

}