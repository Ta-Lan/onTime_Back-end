package Talan.service.request;

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

import Talan.DTO.FeedDTO;
import Talan.DTO.RequestDTO;

@Service
public class RequestService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired(required = true)
	@Qualifier("sqlSession_sample")
	private SqlSession sqlSession;

	@Autowired(required = true)
	@Qualifier("transactionManager_sample")
	private DataSourceTransactionManager transactionManager_sample;

	// 요청서 등록
	public int registRequest(Map<String, Object> param) {
		// 트랜잭션 구현
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager_sample.getTransaction(def);

		int result = 0;
		try {
			result = sqlSession.insert("request.registRequest", param);

			transactionManager_sample.commit(status);
			logger.info("========== 요청서 등록 완료 : {}", result);

		} catch (Exception e) {
			logger.error("[ERROR] registRequest() Fail : e : {}", e.getMessage());
			e.printStackTrace();
			transactionManager_sample.rollback(status);
		}
		return result;
	}

	// 요청서 리스트
	public List<Object> requestList(Map<String, Object> param) {
		List<RequestDTO> request = new ArrayList<RequestDTO>();

		if (param.get("lastRequestNumber").equals("0")) {
			param.replace("lastRequestNumber", "0", sqlSession.selectOne("request.getLastRequestNumber"));
		} else {
			String strlastRequestNumber = param.get("lastRequestNumber").toString();
			int lastRequestNumber = Integer.parseInt(strlastRequestNumber.substring(7)) - 1;
			param.replace("lastRequestNumber", "REQUEST" + lastRequestNumber);
		}

		int requestCnt = sqlSession.selectOne("request.getRequestCnt");
		if ((Integer.parseInt(param.get("cnt").toString()) > requestCnt)
				|| Integer.parseInt(param.get("cnt").toString()) == 0) {
			param.replace("cnt", requestCnt);
		}

		request = sqlSession.selectList("request.getRequestList", param);

		int cnt = Integer.parseInt(param.get("cnt").toString()) - 1;

		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i <= cnt; i++) {
			list.add(request.get(i).getRequestList());
		}
		return list;
	}

	// 요청서 검색
	public List<Object> requestListSearch(Map<String, Object> param) {
		List<RequestDTO> request = new ArrayList<RequestDTO>();

		if (param.get("lastRequestNumber").equals("0")) {
			param.replace("lastRequestNumber", "0", sqlSession.selectOne("request.getSearchLastRequestNumber", param));
		} else {
			String strlastRequestNumber = param.get("lastRequestNumber").toString();
			int lastRequestNumber = Integer.parseInt(strlastRequestNumber.substring(7)) - 1;
			param.replace("lastRequestNumber", "REQUEST" + lastRequestNumber);
		}

		int requestCnt = sqlSession.selectOne("request.getRequestSearchCnt", param);
		if ((Integer.parseInt(param.get("cnt").toString()) > requestCnt)
				|| Integer.parseInt(param.get("cnt").toString()) == 0) {
			param.replace("cnt", requestCnt);
		}

		request = sqlSession.selectList("request.getRequestSearchList", param);

		int cnt = Integer.parseInt(param.get("cnt").toString()) - 1;

		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i <= cnt; i++) {
			list.add(request.get(i).getRequestList());
		}
		return list;
	}
	
	// 요청서 상세 조회
	public RequestDTO detailReqeust(Map<String, Object> param) {
		RequestDTO request = sqlSession.selectOne("request.getRequest", param);
		return request;
	}
	

	// 요청서 마감
	public int closedRequest(Map<String, Object> param) {
		// 트랜잭션 구현
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager_sample.getTransaction(def);

		int result = 0;
		try {
			result = sqlSession.update("request.setRequestClosed", param);

			transactionManager_sample.commit(status);
			logger.info("========== 요청서 마감 완료 : {}", result);

		} catch (Exception e) {
			logger.error("[ERROR] closedRequest() Fail : e : {}", e.getMessage());
			e.printStackTrace();
			transactionManager_sample.rollback(status);
		}
		return result;
	}



}
