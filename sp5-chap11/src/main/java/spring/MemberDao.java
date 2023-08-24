package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MemberDao {
    
    private JdbcTemplate jdbcTemplate;
    
    /*
     * DataSiyrce를 주입받도록 MemberDao 클래스의 생성자 구현.
     */
    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    /*
     * 혹은 setter 메서드로 주입을 받을 수 있음.
     */
//    public void setDataSource(DataSource dataSource) {
//       this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }

    public Member selectByEmail(String email) {
        List<Member> results = jdbcTemplate.query("select * from MEMBER where EMAIL = ?", new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member(rs.getString("EMAIL"), rs.getString("PASSWORD"), rs.getString("NAME"), rs.getTimestamp("REGDATE").toLocalDateTime());
                member.setId(rs.getLong("ID"));
                
                return member;
            }
        }, email); // 파라미터 (두 개 이상일 경우 콤마로 구분해서 사용)
        
        // query() 메서드는 쿼리를 실행한 결과가 존재하지 않을 경우 길이가 0인 List 리턴.
        return results.isEmpty() ? null : results.get(0);
    }
    
    // lambda
//    public Member selectByEmail2(String email) {
//    List<Member> results = jdbcTemplate.query("select * from MEMBER where EMAIL = ?",
//            (ResultSet rs, int rowNum) -> {
//                Member member = new Member(
//                        rs.getString("EMAIL"),
//                        rs.getString("PASSWORD"),
//                        rs.getString("NAME"),
//                        rs.getTimestamp("REGDATE").toLocalDateTime());
//            member.setId(rs.getLong("ID"));
//            
//            return member;
//            }, email);
//        return results.isEmpty() ? null : results.get(0);
//    }
    
    // MemberRowMapper 객체 생성
//    public Member selectByEmail3(String email, String name) {
//        List<Member> results = jdbcTemplate.query("select * from MEMBER where EMAIL = ? and NAME = ?", new MemberRowMapper(), email, name);
//        
//        return results.isEmpty() ? null : results.get(0);
//    }

    public void insert(final Member member) {
        // 자동 생성된 키 값을 구해주는 keyHolder 구현 클래스 객체 생성.
        KeyHolder keyHolder = new GeneratedKeyHolder();
        // update(PreparaedStatementCreator 객체, KeyHolder 객체)
        jdbcTemplate.update(new PreparedStatementCreator() {
            
            // (sql 구문, String[]: 자동 생성되는 키 카럼 목록을 지정할 때 사용, 시퀀스 칼럼의 이름 작성.)
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement("insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) " + "values (?, ?, ?, ?)", new String[] {"ID"});
                
                // 인덱스 파라미터의 값 설정.
                pstmt.setString(1, member.getEmail());
                pstmt.setString(2, member.getPassword());
                pstmt.setString(3, member.getName());
                pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
                
                // 생성한 PreparedStatement 객체 리턴.
                return pstmt;
            }
        }, keyHolder); // 두 번째 파라미터로 생성한 KeyHolder 객체를 전달.
        Number keyValue = keyHolder.getKey(); // KeyHolder에 보관한 키 값은 getKey() 메서드를 이용해 추출.
        member.setId(keyValue.longValue());
    }
    
    public void update(Member member) {
        jdbcTemplate.update("update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?", member.getName(), member.getPassword(), member.getEmail());
    }
    
    public List<Member> selectAll() {
        List<Member> results = jdbcTemplate.query("select * from MEMBER", new RowMapper<Member>() {
           @Override
           public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member(rs.getString("EMAIL"), rs.getString("PASSWORD"), rs.getString("NAME"), rs.getTimestamp("REGDATE").toLocalDateTime());
            member.setId(rs.getLong("ID"));
            return member;
        } 
        });
        return results;
    }
    
    // 클래스를 이용해서 메서드
//    public List<Member> selectAll2() {
//        List<Member> results = jdbcTemplate.query("select * from MEMBER", new MemberRowMapper());
//        return results;
//    }
    
    public int count() {
        Integer count = jdbcTemplate.queryForObject("select count(*) from MEMBER", Integer.class); // requiredType the type that the result object is expected to match
        return count;
    }

}
