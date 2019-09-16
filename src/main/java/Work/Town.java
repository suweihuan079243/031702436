package Work;

import com.alibaba.fastjson.annotation.JSONField;

public class Town {
    @JSONField(name = "name")
    private String townName;

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

}
