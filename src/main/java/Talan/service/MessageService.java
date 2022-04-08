package Talan.service;

import Talan.DTO.MessageDTO;
import Talan.DTO.PeopleDTO;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired(required = true)
    private MessageService service;

    @Autowired(required = true)
    @Qualifier("sqlSession_sample")
    private SqlSession sqlSession;

    @Autowired(required = true)
    @Qualifier("transactionManager_sample")
    private DataSourceTransactionManager transactionManager_sample;

    //메시지 가져오기
    public List<MessageDTO> getMessageInfo(Map<String, Object> param) {
        return sqlSession.selectList("message.getMessageInfo", param);
    }

    //메세지 보내기
    public int insertMessage(Map<String, Object> param) {
        //트랜잭션 구현
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager_sample.getTransaction(def);
        
        int result = 0;
        try {

            result = sqlSession.insert("message.insertMessage", param);

            transactionManager_sample.commit(status);
            logger.info("========== 메시지 등록 완료 : {}", result);

        } catch (Exception e) {
            logger.error("[ERROR] insertMessage() Fail : e : {}", e.getMessage());
            e.printStackTrace();
            transactionManager_sample.rollback(status);
        }
        return result;
    }

//    //회원정보 수정
//    public int updatePeople(Map<String, Object> param) {
//        //
//        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//        TransactionStatus status = transactionManager_sample.getTransaction(def);
//
//        int result = 0;
//        try {
//
//            PeopleDTO info = sqlSession.selectOne("people.getPeopleInfo", param);
//
//            if (param.get("password").equals(info.getPassword())) {
//                result = sqlSession.update("people.updatePeople", param);
//            }
//
//            transactionManager_sample.commit(status);
//            logger.info("========== 유저 수정 완료 : {}", result);
//
//        } catch (Exception e) {
//            logger.error("[ERROR] updatePeople() Fail : e : {}", e.getMessage());
//            e.printStackTrace();
//            transactionManager_sample.rollback(status);
//        }
//        return result;
//    }
//
//    //회원탈퇴
//    public int deletePeople(Map<String, Object> param) {
//        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//        TransactionStatus status = transactionManager_sample.getTransaction(def);
//
//        int result = 0;
//        try {
//            result = sqlSession.delete("people.deletePeople", param);
//            transactionManager_sample.commit(status);
//            logger.info("========== 유저 삭제 완료 : {}", result);
//        } catch (Exception e) {
//            logger.error("[ERROR] deletePeople() Fail : e : {}", e.getMessage());
//            e.printStackTrace();
//            transactionManager_sample.rollback(status);
//        }
//        return result;
//    }
//
//    //로그인
//    public int loginPeople(Map<String, Object> param) {
//        int result = 0;
//        try {
//            PeopleDTO info = sqlSession.selectOne("people.loginPeople", param);
//            if (param.get("password").equals(info.getPassword())) {
//                result = 1;
//            } else if (!param.get("password").equals(info.getPassword())) {
//                result = -1;
//            }
//        } catch (Exception e) {
//            logger.error("[ERROR] login() Fail : e : {}", e.getMessage());
//            e.printStackTrace();
//        }
//        return result;
//    }
//

}