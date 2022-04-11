package Talan.service.people;

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

import Talan.DTO.PeopleDTO;

@Service
public class PeopleService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired(required=true)
    @Qualifier("sqlSession_sample")
    private SqlSession sqlSession;

    @Autowired(required=true)
    @Qualifier("transactionManager_sample")
    private DataSourceTransactionManager transactionManager_sample;
	
    //회원정보
	public PeopleDTO getPeopleInfo( Map<String,Object> param ) {
		return sqlSession.selectOne("people.getPeopleInfo", param);
	}
	
	//회원가입
	public int insertPeople( Map<String,Object> param ) {
		//트랜잭션 구현
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager_sample.getTransaction(def);

        int result = 0;
        try{
           
            result = sqlSession.insert("people.insertPeople", param);
            
            transactionManager_sample.commit(status);
            logger.info("========== 유저 등록 완료 : {}", result);
            
        }catch(Exception e){
        	logger.error("[ERROR] insertPeople() Fail : e : {}", e.getMessage());
        	e.printStackTrace();
        	transactionManager_sample.rollback(status);    	
        }
		return result;
	}
	
	//회원가입 (+프로필사진)
	public int insertPeopleWithImage(Map<String, Object> param) {
		//트랜잭션 구현
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager_sample.getTransaction(def);

        int result = 0;
        try{
           
            result = sqlSession.insert("people.insertPeopleWithImage", param);
            
            transactionManager_sample.commit(status);
            logger.info("========== 유저 등록 완료 : {}", result);
            
        }catch(Exception e){
        	logger.error("[ERROR] insertPeople() Fail : e : {}", e.getMessage());
        	e.printStackTrace();
        	transactionManager_sample.rollback(status);    	
        }
		return result;
	}
	
	//회원정보 수정
	public int updatePeople( Map<String,Object> param ) {
		//�듃�젋�젥�뀡 援ы쁽
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager_sample.getTransaction(def);

        int result = 0;
        try{
           
        	PeopleDTO info = sqlSession.selectOne("people.getPeopleInfo", param);
        	
        	if( param.get("password").equals(info.getPassword()) ) {
        		result = sqlSession.update("people.updatePeople", param);
        	}
            
            transactionManager_sample.commit(status);
            logger.info("========== 유저 수정 완료 : {}", result);
            
        }catch(Exception e){
        	logger.error("[ERROR] updatePeople() Fail : e : {}", e.getMessage());
        	e.printStackTrace();
        	transactionManager_sample.rollback(status);    	
        }
		return result;
	}
	
	//회원탈퇴
	public int deletePeople(Map<String, Object> param) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager_sample.getTransaction(def);
		
		int result = 0;
		try {
			result = sqlSession.delete("people.deletePeople", param);
			transactionManager_sample.commit(status);
	        logger.info("========== 유저 삭제 완료 : {}", result);
		}
		catch(Exception e) {
			logger.error("[ERROR] deletePeople() Fail : e : {}", e.getMessage());
        	e.printStackTrace();
        	transactionManager_sample.rollback(status);    	
		}
		return result;
	}
	
	//로그인
	public int loginPeople(Map<String, Object> param) {
        int result = 0;
        try{        
        	PeopleDTO info = sqlSession.selectOne("people.getSession", param);
        	if( param.get("password").equals(info.getPassword()) ) {
        		result = 1;
        	}
        	else if(!param.get("password").equals(info.getPassword())) {
        		result = -1;
        	}
        }catch(Exception e){
        	logger.error("[ERROR] login() Fail : e : {}", e.getMessage());
        	e.printStackTrace(); 	
        }
		return result;
	}
//	로그아웃	
//	public int logoutpeople(Map<String, Object> param) {
//		int result = 0;
//		return result = sqlSession.selectOne("people.loginpeople", param);
//		
//	}
	
	
	//아이디찾기
	public String findId(Map<String, Object> param) {
		return sqlSession.selectOne("people.findId", param);
	}

	//본인인증
	public int isPeople(Map<String, Object> param) {
		if(sqlSession.selectOne("people.isPeople", param) != null) {
			return 1;
		}
		else {
			return 0;
		}
	}

	//비밀번호변경
	public int changePWD(Map<String, Object> param) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager_sample.getTransaction(def);

        int result = 0;
        try{
        	result = sqlSession.update("people.changePWD", param);
            transactionManager_sample.commit(status);
            
        }catch(Exception e){
        	logger.error("[ERROR] changePWD() Fail : e : {}", e.getMessage());
        	e.printStackTrace();
        	transactionManager_sample.rollback(status);    	
        }
		return result;
	}

	//아이디중복확인
	public int duplicatePeople1(Map<String, Object> param) {
		if(sqlSession.selectOne("people.duplicatePeople1", param) != null) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	//이메일 중복확인
	public int duplicatePeople2(Map<String, Object> param) {
		if(sqlSession.selectOne("people.duplicatePeople2", param) != null) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	//닉네임 중복확인
	public int duplicatePeople3(Map<String, Object> param) {
		if(sqlSession.selectOne("people.duplicatePeople3", param) != null) {
			return 1;
		}
		else {
			return 0;
		}
	}


	

	
}
