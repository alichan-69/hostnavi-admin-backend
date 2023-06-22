package com.alichan.hostnavi.admin.error;

import org.apache.ibatis.jdbc.Null;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.alichan.hostnavi.admin.application.response.Response;

@Controller
@RequestMapping("/error")
public class ErrorControllerImplement implements ErrorController {
  @RequestMapping("")
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  @ResponseBody
  public Response<Null> getError() {
    return Response.notFound();
  }
}
