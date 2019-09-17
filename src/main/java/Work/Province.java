package Work;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class Province {
    @JSONField(name = "name")
    String provinceName;

    @JSONField(name = "children")
    private List<City> cities;

    public String getProvinceName() {
        return provinceName;
    }


    public List<City> getCities() {
        return cities;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "Province{" +
                "provinceName='" + provinceName + '\'' +
                ", cities=" + cities +
                '}';
    }
}
