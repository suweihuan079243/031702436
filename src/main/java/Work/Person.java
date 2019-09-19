package Work;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

public class Person {
    @JSONField(name = "姓名")
    private String name;

    @JSONField(name = "手机")
    private String phoneNumber;

    @JSONField(name = "地址")
    private List<String> address = new ArrayList<>();

    private transient Province province;

    private transient City city;

    private transient County county;

    private transient Town town;

    private transient Street street;

    private transient String restAddress;


    public void setProvince(Province province) {
       if(province!=null){
           address.add(province.getProvinceName());
       }else{
           address.add("");
       }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address=" + address +
                '}';
    }

    public void setCity(City city) {
        if(city!=null){
            address.add(city.getCityName());
        }else{
            address.add("");
        }
    }


    public void setCounty(County county) {
     if(county!=null){
         address.add(county.getCountyName());
     }else{
         address.add("");
     }
    }


    public void setTown(Town town) {
        if(town!=null){
            address.add(town.getTownName());
        }else{
            address.add("");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPhoneNumber(String phone) {
        this.phoneNumber = phone;
    }

    public void setRoadName(String roadName) {
      if(roadName==null){
          address.add("");
      }else{
          address.add(roadName);
      }
    }

    public void setGateNumber(String gateNumber) {
        if(gateNumber==null){
            address.add("");
        }else{
            address.add(gateNumber);
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }


    public String getRestAddress() {
        return restAddress;
    }

    public void setRestAddress(String restAddress) {
        if(restAddress!=null){
            address.add(restAddress);
        }else{
            address.add("");
        }
    }
    public Province getProvince() {
        return province;
    }

    public City getCity() {
        return city;
    }

    public County getCounty() {
        return county;
    }

    public Town getTown() {
        return town;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }
}
