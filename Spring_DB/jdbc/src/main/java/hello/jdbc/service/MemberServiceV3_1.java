package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV2;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


/*
* 트랜잭션 - 트랜잭션 매니저
* */

@Slf4j
@RequiredArgsConstructor
public class MemberServiceV3_1 {

    // DataSource를 직접 받지 않고, Transaction manager라는 추상화된 인터페이스를 받는다.
//    private final DataSource dataSource;
    private final PlatformTransactionManager transactionManager;
    private final MemberRepositoryV3 memberRepository;

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {

        // 트랜잭션 시작
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
//        Connection con = dataSource.getConnection();

        try {
            // 트랜잭션 시작
            bizLogic(fromId, toId, money);
            transactionManager.commit(status);
//            con.commit();   // 성공시 커밋
        } catch (Exception e) {
            transactionManager.rollback(status);
//            con.rollback(); // 실패시 롤백
            throw new IllegalStateException();
        }
//        finally {
//            release(con);
//        }


    }

    private void bizLogic(String fromId, String toId, int money) throws SQLException {
        // ------ 비즈니스 로직 시작점 ---------
        Member fromMember = memberRepository.findById(fromId);
        Member toMember = memberRepository.findById(toId);

        memberRepository.update(fromId, fromMember.getMoney() - money);
        validation(toMember);
        memberRepository.update(toId, toMember.getMoney() + money);
    }

    private void release(Connection con) {
        if (con != null) {
            try {
                // 닫기 전에 AutoCommit false를 풀어준다.
                con.setAutoCommit(true);
                con.close();
            } catch (Exception e) {
                log.info("error", e);
            }
        }
    }

    private void validation(Member toMember) {
        if (toMember.getMemberId().equals("ex")) {
            throw new IllegalStateException("이체중 예외가 발생함");
        }
    }
}