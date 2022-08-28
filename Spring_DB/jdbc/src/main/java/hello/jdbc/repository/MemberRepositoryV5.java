package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.ex.MyDbException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;

import javax.sql.DataSource;
import java.sql.*;
import java.util.NoSuchElementException;


/*
JdbcTemplate사용한다.
* */
@Slf4j
public class MemberRepositoryV5 implements MemberRepository{

    private final JdbcTemplate template;

    public MemberRepositoryV5(DataSource dataSource) {

        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        String sql = "insert into member(member_id, money) values (?, ?)";
        template.update(sql, member.getMemberId(), member.getMoney());
        return member;

//        Connection con = null;
//        PreparedStatement pstmt = null;
//
//        try {
//            con = getConnection();
//            pstmt = con.prepareStatement(sql);
//            pstmt.setString(1, member.getMemberId());
//            pstmt.setInt(2, member.getMoney());
//            pstmt.executeUpdate();
//            return member;
//        } catch (SQLException e) {
//            throw exTranslator.translate("save", sql, e);
//        } finally {
//            close(con, pstmt, null);
//        }
    }


    public Member findById(String memberId) {
        String sql = "select * from member where member_id = ?";
        Member member = template.queryForObject(sql, memberRowMapper(), memberId);
        return member;

    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setMemberId(rs.getString("member_id"));
            member.setMemberId(rs.getInt("money"));
            return member;
        };
    }

    public void update(String memberId, int money)  {
        String sql = "update member set money = ? where member_id = ?";
        template.update(sql, money, memberId);
    }


    public void delete(String memberId)  {
        String sql = "delete from member where member_id=?";
        template.update(sql, memberId);
    }
}
