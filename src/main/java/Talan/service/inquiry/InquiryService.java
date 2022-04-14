package Talan.service.inquiry;

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

import Talan.DTO.InquiryDTO;

@Service
public class InquiryService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired(required = true)
	@Qualifier("sqlSession_sample")
	private SqlSession sqlSession;

	@Autowired(required = true)
	@Qualifier("transactionManager_sample")
	private DataSourceTransactionManager transactionManager_sample;

	// 문의 등록 
	public int registInquiry(Map<String, Object> param) {
		// 트랜잭션 구현
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager_sample.getTransaction(def);
		
		int result = 0;
		try {

			result = sqlSession.insert("inquiry.registInquiry", param);

			transactionManager_sample.commit(status);
			logger.info("========== 문의 등록 완료 : {}", result);

		} catch (Exception e) {
			logger.error("[ERROR] registPro() Fail : e : {}", e.getMessage());
			e.printStackTrace();
			transactionManager_sample.rollback(status);
		}
		return result;

	}

	// 문의글 전체 조회
	public List<Object> inquiryListAll(Map<String, Object> param) {
		List<InquiryDTO> inquiry = new ArrayList<InquiryDTO>();

		if (param.get("lastInquiryNumber").equals("0")) {
			param.replace("lastInquiryNumber", "0", sqlSession.selectOne("inquiry.getLastInquiryNumber"));
		} else {
			String strLastInquiryNumber = param.get("lastInquiryNumber").toString();
			int lastInquiryNumber = Integer.parseInt(strLastInquiryNumber.substring(7)) - 1;
			param.replace("lastInquiryNumber", "INQUIRY" + lastInquiryNumber);
		}

		int InquiryCnt = sqlSession.selectOne("inquiry.getInquiryCnt");
		if ((Integer.parseInt(param.get("cnt").toString()) > InquiryCnt)
				|| Integer.parseInt(param.get("cnt").toString()) == 0) {
			param.replace("cnt", InquiryCnt);
		}

		inquiry = sqlSession.selectList("inquiry.getInquiryList", param);

		int cnt = Integer.parseInt(param.get("cnt").toString()) - 1;

		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i <= cnt; i++) {
			list.add(inquiry.get(i).getInquiryList());
		}
		return list;
	}

}
