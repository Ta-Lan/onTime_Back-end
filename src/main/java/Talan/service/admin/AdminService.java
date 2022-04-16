package Talan.service.admin;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mindrot.bcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import Talan.DTO.AdminDTO;

@Service
public class AdminService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired(required = true)
	@Qualifier("sqlSession_sample")
	private SqlSession sqlSession;

	@Autowired(required = true)
	@Qualifier("transactionManager_sample")
	private DataSourceTransactionManager transactionManager_sample;
	
    @Autowired(required = true)
    @Qualifier("bCrypt")
    private BCrypt bCrypt;
	

	public int registAdmin(Map<String, Object> param) {
		//트랜잭션 구현
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager_sample.getTransaction(def);

        param.replace("adminPassword", bCrypt.hashpw(param.get("adminPassword").toString(), bCrypt.gensalt()));
        
        int result = 0;
        try{
            result = sqlSession.insert("admin.registAdmin", param);
            
            transactionManager_sample.commit(status);
            logger.info("========== 관리자 등록 완료 : {}", result);
            
        }catch(Exception e){
        	logger.error("[ERROR] registAdmin() Fail : e : {}", e.getMessage());
        	e.printStackTrace();
        	transactionManager_sample.rollback(status);    	
        }
		return result;
	}

	// 관리자 로그인 
	public int loginAdmin(Map<String, Object> param) {
		int result = 0;
		AdminDTO dto = sqlSession.selectOne("admin.loginAdmin", param.get("peopleId"));
		
        try{        
        	if( bCrypt.checkpw(param.get("password").toString(), dto.getAdminPassword()) ) {
        		result = 1;
        	}
        	else {
        		result = -1;
        	}
        }catch(Exception e){
        	logger.error("[ERROR] loginAdmin() Fail : e : {}", e.getMessage());
        	e.printStackTrace(); 	
        }
		return result;
	}


}
