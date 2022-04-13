package Talan.service.pro;

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

import Talan.DTO.ProDTO;

@Service
public class ProService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired(required = true)
    @Qualifier("sqlSession_sample")
    private SqlSession sqlSession;
	
	@Autowired(required = true)
	@Qualifier("transactionManager_sample")
	private DataSourceTransactionManager transactionManager_sample;

	// PRO 등록
	public int registPro(Map<String, Object> param) {
		//트랜잭션 구현
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager_sample.getTransaction(def);

        int result = 0;
        try{
           
            result = sqlSession.insert("pro.registPro", param);
            
            transactionManager_sample.commit(status);
            logger.info("========== 고수 등록 완료 : {}", result);
            
        }catch(Exception e){
        	logger.error("[ERROR] insertPeople() Fail : e : {}", e.getMessage());
        	e.printStackTrace();
        	transactionManager_sample.rollback(status);    	
        }
		return result;
	}

	// PRO 확인
	public int isProRegisted(String peopleId) {
		int result = sqlSession.selectOne("pro.isProRegisted", peopleId);
		return result;
	}

	public ProDTO getProInfo(Map<String, Object> param) {
		ProDTO info = sqlSession.selectOne("pro.getProInfo",param);
		
		return info;
	}
	
	

}
