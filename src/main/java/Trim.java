import Work.*;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Trim {
    private Person person;

    private String phoneNumber;

    private String newInformation;

    private String level;

    private String personName;

    private Province province;

    private City city;

    private County county;

    private Town town;

    private Street street;

    public Trim() {
        this.person = new Person();
    }

    public void setPerson(Person person) {
        this.person = person;
    }

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
        return this.person;
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
        String regexPhone = "\\d{11}";
        Pattern pattern = Pattern.compile(regexPhone);
        Matcher matcher = pattern.matcher(this.newInformation);
        if (matcher.find()) {
            this.phoneNumber = matcher.group();
        }
        person.setPhoneNumber(this.phoneNumber);
        this.newInformation = matcher.replaceAll("").trim();
    }

    public void trimLevel() {

        String regexLevel = "[!,]";

        String[] split = this.newInformation.split(regexLevel);

        this.level = split[0];

        personName = split[1];

        this.newInformation = split[2];
        person.setName(this.personName);
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
                } else {
                    this.newInformation = trimInformation(this.newInformation, province.getProvinceName());
                }
                person.setProvince(province.getProvinceName());
                this.province = province;
                break;
            }
        }
        if (province != null)
            trimCity(this.province);
    }

    public void trimCity(Province province) {
        try {
            MapData.readMap();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String cityInformation = this.newInformation.substring(0, 2);
        String cityName = null;
        for (City city : province.getCities()) {
            cityName = city.getCityName().substring(0, 2);

            if (cityInformation.equals(cityName)) {
                this.newInformation = trimInformation(this.newInformation, city.getCityName());
                this.city = city;
                person.setCity(city.getCityName());
                break;
            }
        }
        if (this.city != null) {
            trimCounty(this.city);
        } else {
            person.setCity("\"\"");
            if (this.province != null) {
                List<City> cityList = this.province.getCities();
                for (City city : cityList) {
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
        String couontyInformation = this.newInformation.substring(0, 2);//

        String countyName = null;

        for (County county : city.getCounties()) {
            countyName = county.getCountyName().substring(0, 2);
            if (couontyInformation.equals(countyName)) {
                this.newInformation = trimInformation(this.newInformation, county.getCountyName());
                this.county = county;
                person.setCounty(county.getCountyName());
                break;
            }
        }
        if (this.county != null) {
            trimTown(this.county);
        } else {
            person.setCounty("\"\"");
            if (this.city != null) {
                List<County> countyList = this.city.getCounties();
                for (County county : countyList) {
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
        String townInformation = this.newInformation.substring(0, 2);//

        String townName = null;
        for (Town town : county.getTowns()) {
            townName = town.getTownName().substring(0, 2);
            if (townInformation.equals(townName)) {
                this.newInformation = trimInformation(this.newInformation, town.getTownName());
                this.town = town;
                person.setTown(town.getTownName());
                break;
            }
        }
        if (this.town == null) {
            person.setTown("\"\"");
        }
        trimStreet();
    }

    private void trimStreet() {

        if (this.level.equals("1")) {
            person.setRestAddress(this.newInformation);
        } else if (this.level.equals("2") || level.equals("3")) {
            String regex1 = "(\\D+)(\\d+号)";
            Pattern pattern = Pattern.compile(regex1);
            Matcher matcher = pattern.matcher(newInformation);
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
                }
                if (roadName != null) {
                    person.setRoadName(roadName);
                } else {
                    person.setRoadName("\"\"");
                }
                if (gateNumber != null) {
                    person.setGateNumber(gateNumber);
                } else {
                    person.setGateNumber("\"\"");
                }
                if (restAddress != null) {
                    person.setRestAddress(restAddress);
                } else {
                    person.setRestAddress("\"\"");
                }
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

    public String sout() {
        return JSON.toJSONString(person);
    }

}
