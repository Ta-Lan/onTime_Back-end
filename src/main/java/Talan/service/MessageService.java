package Talan.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import Talan.DTO.MessageDTO;
import Talan.DTO.RequestDTO;

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
    public List<Object> getMessageInfo(Map<String, Object> param) {
    	List<MessageDTO> dto = sqlSession.selectList("message.getMessageInfo", param);
		
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < dto.size(); i++) {
			list.add(dto.get(i).getChatList());
		}
		
		return list;
    }

    //메세지 보내기
    public int insertMessage(Map<String, Object> param) {
        //트랜잭션 구현
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager_sample.getTransaction(def);
        
        String chatNumber = null;
    	chatNumber = sqlSession.selectOne("message.getChatNumber", param);
        int result = 0;
        try {
        	if (chatNumber == null) {
        		result = sqlSession.insert("message.insertNewMessage", param);
        	} else {
        		param.put("chatNumber", chatNumber);
        		result = sqlSession.insert("message.insertMessage", param);
        	}
            
            transactionManager_sample.commit(status);
            logger.info("========== 메시지 등록 완료 : {}", result);

        } catch (Exception e) {
            logger.error("[ERROR] insertMessage() Fail : e : {}", e.getMessage());
            e.printStackTrace();
            transactionManager_sample.rollback(status);
        }
        return result;
    }
    
    // 견적서 메시지 보내기
    public int insertEstimateMessage(Map<String, Object> param) {
        //트랜잭션 구현
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager_sample.getTransaction(def);
        
        param.put("messageSender", param.get("proId"));
        param.put("messageContent", sqlSession.selectOne("estimate.getLastEstimateNumber"));
        
        RequestDTO requestInfo = sqlSession.selectOne("request.getRequestInfo", param.get("requestNumber"));
        param.put("messageReceiver", requestInfo.getPeopleId());
        
        String chatNumber = null;
    	chatNumber = sqlSession.selectOne("message.getChatNumber", param);
        int result = 0;
        try {
        	if (chatNumber == null) {
        		result = sqlSession.insert("message.insertNewEstimateMessage", param);
        	} else {
        		param.put("chatNumber", chatNumber);
        		result = sqlSession.insert("message.insertEstimateMessage", param);
        	}
            
            transactionManager_sample.commit(status);
            logger.info("========== 견적서 메시지 등록 완료 : {}", result);

        } catch (Exception e) {
            logger.error("[ERROR] insertMessage() Fail : e : {}", e.getMessage());
            e.printStackTrace();
            transactionManager_sample.rollback(status);
        }
        return result;
    }
    
    // 채팅방 리스트
	public List<Object> getChatList(Map<String, Object> param) {
		List<MessageDTO> dto = sqlSession.selectList("message.getChatList", param);
		
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < dto.size(); i++) {
			System.out.println("TIME::::::::::::"+dto.get(i).getMessageTime()); 
			list.add(dto.get(i).getChatList());
		}
		
		return list;
	}
}