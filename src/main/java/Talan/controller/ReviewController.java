package Talan.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Talan.DTO.PaymentDetailDTO;
import Talan.service.review.ReviewService;
import kr.msp.constant.Const;

@Controller
public class ReviewController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired(required = true)
	@Qualifier("sqlSession_sample")
	private SqlSession sqlSession;

	@Autowired(required = true)
	private ReviewService service;
	
	// 리뷰 등록
	@RequestMapping(method = RequestMethod.POST, value = "/api/review/regist")
	public ModelAndView registReview(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        Map<String, Object> reqHeadMap = (Map<String,Object>)request.getAttribute(Const.HEAD);
        Map<String, Object> reqBodyMap = (Map<String,Object>)request.getAttribute(Const.BODY);
        Map<String, Object> responseBodyMap = new HashMap<String, Object>();
        
        if(reqHeadMap==null){
            reqHeadMap = new HashMap<String, Object>();
        }
        
        reqHeadMap.put(Const.RESULT_CODE, Const.OK);
        reqHeadMap.put(Const.RESULT_MESSAGE, Const.SUCCESS);
        
        if (session.getAttribute("user") != null) {
    		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
    		
    		PaymentDetailDTO payment = sqlSession.selectOne("payment.getPayment", reqBodyMap.get("paymentNumber"));
            reqBodyMap.put("peopleId", user.get("loginId"));
            reqBodyMap.put("proId", payment.getProId());
            
            
            logger.info("======================= reqBodyMap : {}", reqBodyMap.toString());
            
            int result = service.registReview(reqBodyMap);
           
            if( result > 0 ) {
            	 sqlSession.update("pro.updateProKindScore");
            	responseBodyMap.put("rsltCode", "0000");
                responseBodyMap.put("rsltMsg", "Success");
            } else {
            	responseBodyMap.put("rsltCode", "2003");
                responseBodyMap.put("rsltMsg", "Data not found.");
            }
        } else if (session.getAttribute("user") == null) {
        	responseBodyMap.put("rsltCode", "1003");
            responseBodyMap.put("rsltMsg", "Login required.");
        }
	
        ModelAndView mv = new ModelAndView("defaultJsonView");
        mv.addObject(Const.HEAD,reqHeadMap);
        mv.addObject(Const.BODY,responseBodyMap);

        return mv;
	}
}
