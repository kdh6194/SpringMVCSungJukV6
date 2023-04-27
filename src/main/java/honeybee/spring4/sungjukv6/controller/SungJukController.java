package honeybee.spring4.sungjukv6.controller;

import honeybee.spring4.sungjukv6.model.SungJukVO;
import honeybee.spring4.sungjukv6.service.SungJukV6Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    // 성적 입력 폼 표시
    @GetMapping("/add")
    public String add() {
        return "sungjuk";
    }
    // 성적 입력 처리
    @PostMapping("/add")
    public ModelAndView addok(SungJukVO sj) {
        ModelAndView mv = new ModelAndView();
        String view = "sungjukfail";

        if (sjsrv.newSungJuk(sj)) {
            mv.addObject("sj", sj);
            view = "sungjukok";
        }
        mv.setViewName(view);

        return mv;
    }

    @GetMapping("/select")
    public ModelAndView select(@RequestParam int sjno) {
        ModelAndView mv = new ModelAndView();
        String select = "sugnjukfail";

        SungJukVO sj = sjsrv.readOneSungJuk(sjno);
        if (sj != null) {
            mv.addObject("sjv", sj);
            select = "sungjukselect";
        }
        mv.setViewName(select);

        return mv;
    }

    @GetMapping("/update")
    public String update(SungJukVO sj) {
        return "sungjukupdate";
    }
    @PostMapping("/update/{sjno}")
    public ModelAndView updateok(@PathVariable int sjno, SungJukVO sj){
        ModelAndView mv = new ModelAndView();
        String update = "sungjukfail";
        if(sjsrv.modifySungJuk(sj)) {
            mv.addObject("sjup", sj);
            update = "sungjukupdate";
        }
        mv.setViewName(update);
        return mv;
    }

    @GetMapping("/delete")
    public String delete(int sjno) {
        sjsrv.removeSungJuk(sjno);

        // 클라이언트에게 /list를 서버에 요청하도록 지시
        return "redirect:/list";
    }
}
