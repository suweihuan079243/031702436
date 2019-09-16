import Work.Person;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("----------test------------");
//        Trim trim = new Trim();
        //Person person = trim.getPerson("3!小美,北京市东15822153326城区交道口东大街1号北京市东城区人民法院.");
       // Person person = trim.getPerson("2!李四,福建省福州13756899511市鼓楼区鼓西街道湖滨路110号湖滨大厦一层.");
      //  Person person = trim.getPerson("1!张三,福建福州闽13599622362侯县上街镇福州大学10#111.");
        //trim.setNewInformation("1!张三,福建福州闽13599622362侯县上街镇福州大学10#111.");
       // trim.setNewInformation("2!李四,福建省福州13756899511市鼓楼区鼓西街道湖滨路110号湖滨大厦一层.");
//        BufferedReader bufferedReader=new BufferedReader(new FileReader("1.txt"));
//        BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter("2.txt"));
        BufferedReader bufferedReader=new BufferedReader(new FileReader(args[0]));
        BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(args[1]));
        String line=null;

        while((line=bufferedReader.readLine())!=null){
            Trim trim=new Trim();
            trim.setNewInformation(line);
            trim.trimPhoneNumber();
            trim.trimLevel();
            trim.trimProvince();
            String jsonArray=trim.sout();
            bufferedWriter.write(jsonArray);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
            bufferedReader.close();
            bufferedWriter.close();

//        Trim trim=new Trim();
//        trim.setNewInformation("2!王五,福建省福州市鼓楼18960221533区五一北路123号福州鼓楼医院.");
//       // trim.setNewInformation("2!李四,福建省福州13756899511市鼓楼区鼓西街道湖滨路110号湖滨大厦一层.");
//        trim.trimPhone();
//        trim.trimLevel();
//        trim.trimProvince();
//        System.out.println(trim.sout());

//            Trim trim=new Trim();
//            trim.setNewInformation("3!小美,北京市东15822153326城区交道口东大街1号北京市东城区人民法院.");
//            trim.trimPhone();
//            trim.trimLevel();
//            trim.trimProvince();
//            System.out.println(trim.sout());

        //trim.trimPhone();
        //System.out.println(person.getPhone());
        //System.out.println(person.getInformation());
        //trim.trimLevel();
        //System.out.println(person.getName());
         //trim.trimProvince();

         //trim.sout();

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
