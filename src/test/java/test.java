import honeybee.spring4.sungjukv6.dao.SungJukV5DAOImpl;
import honeybee.spring4.sungjukv6.model.SungJukVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

//단위unit 테스트
// 하나의 모듈을 기준으로 독립적으로 진행되는 가장 작은 단위의 테스트
// 모듈은 애플리케이션에서 작동하는 하나의 기능 또는 메소드를 의미
// 애플리케이션을 구성하는 하나의 기능이 올바르게 동작하는지를 독립적으로 테스트하는 것
// "어떤 기능이 실행되면 어떤 결과가 나온다" 수준으로 테스트 함

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:root-context.xml","classpath:servlet-context.xml"})
public class test {

    @Autowired
    private SungJukV5DAOImpl dao;

//    @Test
//    public void insert() {
//        SungJukVO sjv = new SungJukVO(0,"김도현", 99, 99, 99);
//
//        int cnt = dao.insertSungJuk(sjv);
//
//        assertThat(cnt,is(1));
//    }


    @Test
    public void test2(){
        int sjno = 4;
        SungJukVO sj = dao.selectOneSungJuk(sjno);
        System.out.println(sj);

    }

}