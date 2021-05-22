package cn.nit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ${林锋鹏}
 * @Title: FIleController
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/2/8 16:33
 */
@Controller
@RequestMapping("/file")
public class FIleController {

    @RequestMapping("/fileUpload")
    public String fileUpload(MultipartFile upload,HttpServletRequest req){

        //指定上传的位置
        String realPath = req.getServletContext().getRealPath("/upload");
        return "redirect:/success.jsp";
    }
}
