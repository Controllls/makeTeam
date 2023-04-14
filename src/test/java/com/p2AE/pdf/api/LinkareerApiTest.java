package com.p2AE.pdf.api;

import com.p2AE.pdf.repository.MemberRepository;
import com.p2AE.pdf.service.LinkareerService;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LinkareerApiTest {

    @Autowired
    LinkareerService linkareerService;
    @Test
    void reader() {
        LinkareerApi linkareerApi = new LinkareerApi(linkareerService);
        try {
            linkareerApi.reader();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

//        try {
//            linkareerApi.readerText();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}