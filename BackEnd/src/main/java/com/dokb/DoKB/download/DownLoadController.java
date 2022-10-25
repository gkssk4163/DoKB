package com.dokb.DoKB.download;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;


@Controller
public class DownLoadController {

    @RequestMapping("/download")
    public void fileDownLoad(HttpServletResponse response) throws IOException {
        String path = "\\file\\headtracking_window.zip";
        File file = new File(path);
        byte[] fileByte = Files.readAllBytes(file.toPath());

        response.setContentType(("application/octet-stream"));
        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode("headtracking_window.zip", "UTF-8")+"\";");
        response.setHeader("Content-Transfer-Encoding", "binary");

        response.getOutputStream().write(fileByte);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
}
