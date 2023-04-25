package honeybee.spring4.sungjukv6.controller;

import honeybee.spring4.sungjukv6.service.SungJukV6Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SungJukController {
    private SungJukV6Service sjsrv;

    @Autowired
    public SungJukController(SungJukV6Service sjsrv) {
        this.sjsrv = sjsrv;
    }

    @GetMapping ("/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        //sungjuklist.jsp에 성적조회결과 sjs라는 이름으로 넘김
        mv.addObject("sjs",sjsrv.readSungJuk());
        mv.setViewName("sungjuklist"); // 뷰 이름 지정

        return mv;
    }
}
