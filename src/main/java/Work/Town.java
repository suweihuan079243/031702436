package Work;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class Town {
    @JSONField(name = "name")
    private String townName;

    @JSONField(name = "children")
    List<Street>streets;

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public List<Street> getStreets() {
        return streets;
    }

    public void setStreets(List<Street> streets) {
        this.streets = streets;
    }
}
