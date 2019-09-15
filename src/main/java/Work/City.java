package Work;

import com.alibaba.fastjson.annotation.JSONField;


import java.util.List;

public class City {
    @JSONField(name = "name")
    private String cityName;

    @JSONField(name ="children")
    List<County>counties;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<County> getCounties() {
        return counties;
    }

    public void setCounties(List<County> counties) {
        this.counties = counties;
    }
}
