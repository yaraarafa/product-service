package com.mytoys.product.controller;

import com.mytoys.product.properties.ErrorProperties;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    private ErrorProperties errorProperties;
    private final static String PATH = "/error";

    @Override
    @RequestMapping(PATH)
    @ResponseBody
    public String getErrorPath() {
        return errorProperties.getPathNotFound();
    }

}