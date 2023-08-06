package dbquery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.tomcat.jdbc.pool.DataSource;

public class DbQuery {

    private DataSource dataSource;
    
    public DbQuery(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public int count() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection(); // 풀에서 구함. -->  connection 활성 상태.
            
            /*
             * try () : statement, stream 타입의 클래스들이 동작후 자동으로 close() 메서드를 실행.
             */
            try (Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("select count(*) from MEMBER");) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close(); // pool에 반환. connection 유휴 상태.
                } catch (SQLException e) {
                    
                }
            }
        }
    }
}
