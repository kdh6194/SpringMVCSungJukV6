package honeybee.spring4.sungjukv6.dao;

import honeybee.spring4.sungjukv6.model.SungJukVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;

@Repository("sjdao")
public class SungJukV5DAOImpl implements SungJukV4DAO {
    // 로그를 사용하려면 밑에 logger를 작성해야한다.
    private static final Logger logger = LogManager.getLogger(SungJukV5DAOImpl.class);
    private JdbcTemplate jdbcTemplate; // 타입 지정후 바로 지정한 아이다가 튀어나옴

    // jdbc.properties 에 정의한 SQL 가져오기
    @Value("#{jdbc['insertSQL']}") private String insertSQL;
    @Value("#{jdbc['selectSQL']}") private String selectSQL;
    @Value("#{jdbc['selectOneSQL']}") private String selectOneSQL;
    @Value("#{jdbc['updateSQL']}") private String updateSQL;
    @Value("#{jdbc['deleteSQL']}") private String deleteSQL;

    // 루트 컨텍스트 설정을 가져와서 생성자를 통해 의존성 주입
    @Autowired
    public SungJukV5DAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int insertSungJuk(SungJukVO sj) {
        int cnt = -1;
        //매개 변수 정의
        try {
            Object[] params = new Object[]{
                    sj.getName(), sj.getKor(), sj.getEng(), sj.getMat(), sj.getTot(), sj.getAvg(), sj.getGrd() + ""
            };
            cnt = jdbcTemplate.update(insertSQL, params);
        }catch (Exception e)
        // 전에 작성했던 코드랑 기능에서 차이는 없다 코드는 줄었으나 여전히 달고 있는 것이 있다.
        {
            logger.error("insertSungJuk 에러 발생");
            logger.info(e.getMessage());
        }
            return cnt;
    }

    @Override
    public List<SungJukVO> selectSungJuk() {
        // 콜백 메서드 선언
        // 즉, SQL을 실행하고, 경과집합이 존재하는 경우
        // 스프링컨테이너에 의해 자동으로 호출하는 메서드
        RowMapper<SungJukVO> mapper = new SungJukMapper();

        return jdbcTemplate.query(selectSQL,mapper);
    }

    private class SungJukMapper implements RowMapper<SungJukVO> {
        @Override
        public SungJukVO mapRow(ResultSet rs, int num) throws SQLException {
            SungJukVO sj = new SungJukVO(0,rs.getString(2),rs.getInt(3),
                    rs.getInt(4),rs.getInt(5));
            sj.setSjno(rs.getInt(1)); // add역할을 한다.

            return sj;
        }
    }

    @Override
    public SungJukVO selectOneSungJuk(int sjno) {
        Object[] param = new Object[] { sjno };
        RowMapper<SungJukVO> mapper = new SungJukOneMapper();
        SungJukVO sjo = jdbcTemplate.queryForObject(selectOneSQL,mapper,param);


        return sjo;
    }

    private class SungJukOneMapper implements RowMapper<SungJukVO> {

        public SungJukVO mapRow(ResultSet rs, int num) throws SQLException {
            SungJukVO sj = new SungJukVO(0,rs.getString(2),rs.getInt(3)
                    ,rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getDouble(7),rs.getString(8).charAt(0));
            sj.setSjno(rs.getInt(1));
            sj.setRegdate(rs.getString(9));
            return  sj;
        }

    }

    @Override
    public int updateSungJuk(SungJukVO sj) {
        int cnt = -1;
        try {
            Object[] param = new Object[]{sj.getName(), sj.getKor(), sj.getEng(), sj.getMat(),
            sj.getSjno()};
            cnt = jdbcTemplate.update(updateSQL, param);
        }catch (Exception e){
            logger.error("updateSungJuk 에러 발생");
            logger.info(e.getMessage());
        }
        return cnt;
    }

    @Override
    public int deleteSungJuk(int sjno) {
        Object[] param = new Object[] {sjno};

        return jdbcTemplate.update(deleteSQL,param);
    }



}