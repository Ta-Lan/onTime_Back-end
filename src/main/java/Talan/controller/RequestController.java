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

import Talan.DTO.PeopleDTO;
import Talan.service.request.RequestService;
import kr.msp.constant.Const;

@Controller
public class RequestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired(required = true)
	@Qualifier("sqlSession_sample")
	private SqlSession sqlSession;
	
	@Autowired(required = true)
	private RequestService service;
	
	// 요청서 등록
	@RequestMapping(method = RequestMethod.POST, value = "/api/request/regist")
	public ModelAndView registRequest (HttpServletRequest request, HttpServletResponse response, HttpSession session) {
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
	          
	            reqBodyMap.put("peopleId", user.get("loginId"));
	            
//	            PeopleDTO peopleDTO = sqlSession.selectOne("people.getPeopleInfo", user);
//	            
//	            String address[] = peopleDTO.getAddress().split("`");
//	            
//	            reqBodyMap.put("town", address[0]);
//	            reqBodyMap.put("district", address[1]);
	            
	            logger.info("======================= reqBodyMap : {}", reqBodyMap.toString());
	            
	            int result = service.registRequest(reqBodyMap);
	            
	            if( result > 0 ) {
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