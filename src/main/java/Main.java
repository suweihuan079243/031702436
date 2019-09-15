import Work.Person;

public class Main {
    public static void main(String[] args) {
        System.out.println("----------test------------");
        Trim trim = new Trim();
        //Person person = trim.getPerson("3!小美,北京市东15822153326城区交道口东大街1号北京市东城区人民法院.");
       // Person person = trim.getPerson("2!李四,福建省福州13756899511市鼓楼区鼓西街道湖滨路110号湖滨大厦一层.");
        Person person = trim.getPerson("1!张三,福建福州闽13599622362侯县上街镇福州大学10#111.");
        trim.trimPhone();
        //System.out.println(person.getPhone());
        //System.out.println(person.getInformation());
        trim.trimLevel();
        //System.out.println(person.getName());
         trim.trimProvince();

         trim.sout();

         //System.out.println(person.getProvince());
        //System.out.println(person.getCity());
//        try {
//            Test.readMap();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        for(Province province:Test.getProvinces()){
//           System.out.println(province.getProvinceName());
//       }
   }
}
