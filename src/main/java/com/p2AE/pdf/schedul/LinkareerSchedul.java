package com.p2AE.pdf.schedul;

import com.p2AE.pdf.service.LinkareerService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;


@Component
@RequiredArgsConstructor
public class LinkareerSchedul {

    String filePath = "/Users/choejong-geun/Desktop/jsonLinkareer/2022_10_22/dailyUpdateData.json";
    String fileText = "/Users/choejong-geun/Desktop/jsonLinkareer/2022_10_21/dailyUpdateData.txt";

    @Scheduled(cron = "10 18 16 * * *", zone = "Asia/Seoul")
    public void bulkUpdate() {
//            throws JsonParseException, JsonMappingException, IOException {

        String fileName = "";
        File rw = new File(filePath);

        System.out.println("fileName : " + rw.getName());

        //해당 Logic...
    }

    public void readerText() throws IOException {
        FileReader reader = new FileReader(fileText);
        int ch;
        while ((ch = reader.read()) != -1) {
            System.out.print((char) ch);
        }
    }

    public void reader() throws IOException, ParseException {
        JSONParser parser = new JSONParser();         // JSON 파일 읽기
        Reader reader = new FileReader(filePath);
        JSONArray jsonObject = (JSONArray) parser.parse(reader);

        if (jsonObject.size() > 0) {
            for (int i = 0; i < jsonObject.size(); i++) {
                JSONObject jsonObj = (JSONObject) jsonObject.get(i);
                com.p2AE.pdf.domain.Linkareer linkareer = new com.p2AE.pdf.domain.Linkareer();

                linkareer.setImageUrl((String)jsonObj.get("imageUrl"));
                linkareer.setName((String)jsonObj.get("name"));
                linkareer.setType((String)jsonObj.get("type"));
                linkareer.setTarget((String)jsonObj.get("target"));
                linkareer.setScale((String)jsonObj.get("scale"));
                linkareer.setPeriod((String)jsonObj.get("period"));
                linkareer.setHomePageUrl((String)jsonObj.get("homePageUrl"));
                linkareer.setBenefit((String)jsonObj.get("benefit"));
                linkareer.setField((String)jsonObj.get("field"));
                linkareer.setExtraBenefit((String)jsonObj.get("extraBenefit"));


                System.out.println(linkareer);
            }
        }

    }

//
//    @Scheduled(fixedDelay = 1000)
//    public void scheduleFixedRateTask() {
//        System.out.println(
//                "Fixed rate task - " + System.currentTimeMillis() / 1000);
//    }


}
