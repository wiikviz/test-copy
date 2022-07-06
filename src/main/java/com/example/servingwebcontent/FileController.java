package com.example.servingwebcontent;

import net.glxn.qrgen.javase.QRCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class FileController {
    @Autowired
    private YAMLConfig myConfig;

    @RequestMapping(value = "/files/{file}", method = GET, produces = MediaType.IMAGE_PNG_VALUE )
    @ResponseBody
    public byte[] getImage(
            @PathVariable String file) throws IOException {

        Path path = Paths.get(myConfig.getFilePath() + file);
        byte[] data = Files.readAllBytes(path);

        String encodedString = Base64.getEncoder().encodeToString(data);

        ByteArrayOutputStream stream = QRCode
                .from(encodedString)
                //.withErrorCorrection(ErrorCorrectionLevel.L)
                .withSize(1024, 1024)
                .stream();

        return stream.toByteArray();
    }
}