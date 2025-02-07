package com.renzhikeji.pdfviewer.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author 认知科技技术团队
 * 微信公众号：认知科技技术团队
 */
@Controller
@RequestMapping("/media")
public class PdfviewerPdfjsController {

  @GetMapping
  public String home() {
    return "home";
  }

  @GetMapping("preview")
  public void preview(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
//    File file = new File("/home/<username>/e95433e8-fb08-4c63-ba57-70331903562f.pdf");
    File file = ResourceUtils.getFile("classpath:doc/1996_PODC_queues.pdf");
    if (file.exists()) {
      byte[] data = null;
      FileInputStream input = null;

      try {
        input = new FileInputStream(file);
        data = new byte[input.available()];
        input.read(data);

        response.setContentType("application/pdf");
        response.getOutputStream().write(data);
        response.flushBuffer();

      } catch (Exception e) {
        System.out.println(e.getMessage());
      } finally {
        try {
          if (input != null) {
            input.close();
          }
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }

    }
  }
}




















































