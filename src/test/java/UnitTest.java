import org.junit.Test;

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

}
