package com.mytoys.product.controller;

import com.mytoys.product.config.ErrorConfig;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requested URLs with no mapping in the restful webservice
 */
@Controller
@AllArgsConstructor
public class ErrorRequestMappingController implements ErrorController {

    private ErrorConfig errorConfig;
    private final static String PATH = "/error";

    @Override
    @RequestMapping(PATH)
    @ResponseBody
    public String getErrorPath() {
        return errorConfig.getPathNotFound();
    }

}