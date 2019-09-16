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
    private List<String> address=new ArrayList<>();


    private transient String province;

    private transient String city;

    private transient String county;

    private transient String town;

    private transient String street;

    private transient String restAddress;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
        if(province.equals("\"\"")){
            address.add("");
        }else{
            address.add(province);
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        if(city.equals("\"\"")){
            address.add("");
        }else{
            address.add(city);
        }
    }

    public String getCounty() {
        return county;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public void setCounty(String county) {
        this.county = county;

       if(county.equals("\"\"")){
           address.add("");
       }else{
           address.add(county);
       }
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        if(town.equals("\"\"")){
            address.add("");
        }else{
            address.add(town);
        }
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
        if(street.equals("\"\"")){
            address.add("");
        }else{
            address.add(street);
        }
    }

    public String getRestAddress() {
        return restAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(String phone) {
        this.phoneNumber = phone;
    }

    public void setRoadName(String roadName) {
        if(roadName.equals("\"\"")){
            address.add("");
        }else{
            address.add(roadName);
        }
    }

    public void setGateNumber(String gateNumber){
        if(gateNumber.equals("\"\"")){
            address.add("");
        }else{
            address.add(gateNumber);
        }
    }

    public void setRestAddress(String restAddress) {
        this.restAddress = restAddress;
        if(restAddress.equals("\"\"")){
            address.add("");
        }else{
            address.add(restAddress);
        }
    }

}
