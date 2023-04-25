package honeybee.spring4.sungjukv6.service;

import honeybee.spring4.sungjukv6.dao.SungJukV4DAO;
import honeybee.spring4.sungjukv6.model.SungJukVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("sjsrv")
public class SungJukV6ServiceImpl implements SungJukV6Service {
    private List<SungJukVO> sjs = null;
    private SungJukV4DAO sjdao = null;


    @Autowired
    public SungJukV6ServiceImpl(SungJukV4DAO sjdao) {
        this.sjdao = sjdao;
    }


    public void removeSungJuk() {

    }

    public void modifySungJuk() {

    }

    public void readOneSungJuk() {

    }

    // 성적 리스트 받아옴
    public List<SungJukVO> readSungJuk() {

        return sjdao.selectSungJuk();
    }

    public void newSungJuk() {

    }

    public void computeSungJuk(SungJukVO sj) {
    }

}