package com.p2AE.pdf.api;

import com.p2AE.pdf.domain.Linkareer;
import com.p2AE.pdf.service.LinkareerService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;


@RestController
@RequiredArgsConstructor
public class LinkareerApi {
    private final LinkareerService linkareerService;


    @GetMapping("/api/linkareer")
    public void reader() throws IOException, ParseException {
        String filePath = "/Users/choejong-geun/Desktop/jsonLinkareer/2022_10_22/dailyUpdateData.json";

        JSONParser parser = new JSONParser();
        Reader reader = new FileReader(filePath);
        JSONArray jsonObject = (JSONArray) parser.parse(reader);

        if (jsonObject.size() > 0) {
            for (int i = 0; i < jsonObject.size(); i++) {
                JSONObject jsonObj = (JSONObject) jsonObject.get(i);
                Linkareer linkareer = new Linkareer();

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

                linkareerService.join(linkareer);

                System.out.println(linkareer);
            }
        }

    }

}
