import org.junit.Test;

import java.util.List;

public class UnitTest {

    @Test
    public void testMain(){
        String[] strings = new String[]{"in.txt","out.json"};
        Main.main(strings);
    }

    @Test
    public void testTrimPhoneNumber(){
        Trim trim=new Trim();
        trim.setNewInformation("1!宗衬缝,湖南省长沙市浏阳市古港镇024乡道古港镇梅田湖村村民委员15590409121会.");
        trim.trimPhoneNumber();
        System.out.println(trim.getPerson().getPhoneNumber());
    }

    @Test
    public void testTrimLevel(){
        Trim trim=new Trim();
        trim.setNewInformation("1!宗衬缝,湖南省长沙市浏阳市古港镇024乡道古港镇梅田湖村村民委员15590409121会.");
        trim.trimLevel();
        System.out.println(trim.getLevel());
    }

    @Test
    public void test(){
        Trim trim=new Trim();
        //trim.setNewInformation("2!李四,福建省福州13756899511市鼓楼区鼓西街道湖滨路110号湖滨大厦一层.\n");
        //trim.setNewInformation("2!李四,福建省福州01013756899511市鼓楼区鼓西街道湖滨路110号湖滨大厦一层.");
        trim.setNewInformation("1!刘湖,吉林省白山市六道江镇西村药店18694520738.");
        //trim.setNewInformation("1!咸陡隐,江苏省苏州市吴江13184142847区平望镇新业织造有限公司吴江区平望镇双浜村村民委员会.");
        trim.trimPhoneNumber();
        trim.trimLevel();
        trim.trimProvince();
        trim.sout();
        List<String>list =trim.getPerson().getAddress();
        System.out.println(list);
    }
}
