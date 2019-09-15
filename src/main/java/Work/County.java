package Work;

import com.alibaba.fastjson.annotation.JSONField;


import java.util.List;

public class County {
    @JSONField(name = "name")
    private String countyName;

    @JSONField(name = "children")
    List<Town>towns;

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public List<Town> getTowns() {
        return towns;
    }

    public void setTowns(List<Town> towns) {
        this.towns = towns;
    }
}
