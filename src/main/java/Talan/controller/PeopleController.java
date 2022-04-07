package Talan.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Talan.DTO.PeopleDTO;
import kr.msp.constant.Const;
import Talan.service.people.PeopleService;

@Controller
public class PeopleController {

	private Logger logger = LoggerFactory.getLogger(PeopleController.class);
	
	
	@Autowired(required=true)
	private PeopleService service;
	
	//회원정보 
	@RequestMapping( method = RequestMethod.POST, value = "/api/people/info" )
	public ModelAndView getpeopleInfo( HttpServletRequest request, HttpServletResponse response ) {
		
        Map<String,Object> reqHeadMap =  (Map<String,Object>)request.getAttribute(Const.HEAD);
        Map<String,Object> reqBodyMap =  (Map<String,Object>)request.getAttribute(Const.BODY);
        Map<String, Object> responseBodyMap= new HashMap<String, Object>();
        
        if(reqHeadMap==null){
            reqHeadMap = new HashMap<String, Object>();
        }
        
        reqHeadMap.put(Const.RESULT_CODE, Const.OK);
        reqHeadMap.put(Const.RESULT_MESSAGE, Const.SUCCESS);
		
        logger.info("======================= reqBodyMap : {}", reqBodyMap.toString());
        
        PeopleDTO info = service.getPeopleInfo( reqBodyMap );
        
        if( !StringUtils.isEmpty(info) ) {
        	responseBodyMap.put("rsltCode", "0000");
            responseBodyMap.put("rsltMsg", "Success");
            responseBodyMap.put("name", info.getName());
            responseBodyMap.put("birth", info.getBirth());
            responseBodyMap.put("nickname", info.getNickname());
            responseBodyMap.put("address", info.getAddress());
            responseBodyMap.put("phone", info.getPhone());
            responseBodyMap.put("intro", info.getIntro());
            responseBodyMap.put("gender", info.getGender());
            responseBodyMap.put("email", info.getEmail());
            responseBodyMap.put("account", info.getImagePath());
            responseBodyMap.put("originImageName", info.getOriginImageName());
            responseBodyMap.put("storeImageName", info.getStoreImageName());
            responseBodyMap.put("imagePath", info.getImagePath());
            
        } else {
        	responseBodyMap.put("rsltCode", "2003");
            responseBodyMap.put("rsltMsg", "Data not found.");
        }
		
        ModelAndView mv = new ModelAndView("defaultJsonView");
        mv.addObject(Const.HEAD,reqHeadMap);
        mv.addObject(Const.BODY,responseBodyMap);

        return mv;
	}
	
	//회원가입
	@RequestMapping( method = RequestMethod.POST, value = "/api/people/join" )
	public ModelAndView regPeople( HttpServletRequest request, HttpServletResponse response ) {
		
        Map<String,Object> reqHeadMap =  (Map<String,Object>)request.getAttribute(Const.HEAD);
        Map<String,Object> reqBodyMap =  (Map<String,Object>)request.getAttribute(Const.BODY);
        Map<String, Object> responseBodyMap= new HashMap<String, Object>();
        
        if(reqHeadMap==null){
            reqHeadMap = new HashMap<String, Object>();
        }
        
        reqHeadMap.put(Const.RESULT_CODE, Const.OK);
        reqHeadMap.put(Const.RESULT_MESSAGE, Const.SUCCESS);
		
        logger.info("======================= reqBodyMap : {}", reqBodyMap.toString());
        
        int result = service.insertPeople( reqBodyMap );
        
        if( result > 0 ) {
        	responseBodyMap.put("rsltCode", "0000");
            responseBodyMap.put("rsltMsg", "Success");
        } else {
        	responseBodyMap.put("rsltCode", "2003");
            responseBodyMap.put("rsltMsg", "Data not found.");
        }
		
        ModelAndView mv = new ModelAndView("defaultJsonView");
        mv.addObject(Const.HEAD,reqHeadMap);
        mv.addObject(Const.BODY,responseBodyMap);

        return mv;
	}
	
	//회원정보수정
	@RequestMapping( method = RequestMethod.POST, value = "/api/people/update" )
	public ModelAndView updatePeople( HttpServletRequest request, HttpServletResponse response ) {
		
        Map<String,Object> reqHeadMap =  (Map<String,Object>)request.getAttribute(Const.HEAD);
        Map<String,Object> reqBodyMap =  (Map<String,Object>)request.getAttribute(Const.BODY);
        Map<String, Object> responseBodyMap= new HashMap<String, Object>();
        
        if(reqHeadMap==null){
            reqHeadMap = new HashMap<String, Object>();
        }
        
        reqHeadMap.put(Const.RESULT_CODE, Const.OK);
        reqHeadMap.put(Const.RESULT_MESSAGE, Const.SUCCESS);
		
        logger.info("======================= reqBodyMap : {}", reqBodyMap.toString());
        
        int result = service.updatePeople( reqBodyMap );
        
        if( result > 0 ) {
        	responseBodyMap.put("rsltCode", "0000");
            responseBodyMap.put("rsltMsg", "Success");
        } else {
        	responseBodyMap.put("rsltCode", "2003");
            responseBodyMap.put("rsltMsg", "Data not found.");
        }
		
        ModelAndView mv = new ModelAndView("defaultJsonView");
        mv.addObject(Const.HEAD,reqHeadMap);
        mv.addObject(Const.BODY,responseBodyMap);

        return mv;
	}
	
	//회원탈퇴
	@RequestMapping( method = RequestMethod.POST, value = "/api/people/out" )
	public ModelAndView deletepeople( HttpServletRequest request, HttpServletResponse response ) {
		Map<String,Object> reqHeadMap =  (Map<String,Object>)request.getAttribute(Const.HEAD);
        Map<String,Object> reqBodyMap =  (Map<String,Object>)request.getAttribute(Const.BODY);
        Map<String, Object> responseBodyMap= new HashMap<String, Object>();
        
        if(reqHeadMap==null){
            reqHeadMap = new HashMap<String, Object>();
        }
        
        reqHeadMap.put(Const.RESULT_CODE, Const.OK);
        reqHeadMap.put(Const.RESULT_MESSAGE, Const.SUCCESS);
		
        logger.info("======================= reqBodyMap : {}", reqBodyMap.toString());
        
        int result = service.deletePeople( reqBodyMap );
        
        if( result > 0 ) {
        	responseBodyMap.put("rsltCode", "0000");
            responseBodyMap.put("rsltMsg", "Success");
        } else {
        	responseBodyMap.put("rsltCode", "2003");
            responseBodyMap.put("rsltMsg", "Data not found.");
        }
		
        ModelAndView mv = new ModelAndView("defaultJsonView");
        mv.addObject(Const.HEAD,reqHeadMap);
        mv.addObject(Const.BODY,responseBodyMap);

        return mv;
	}
	
	//로그인
	@RequestMapping( method = RequestMethod.POST, value = "/api/people/login" )
	public ModelAndView loginpeople ( HttpServletRequest request, HttpServletResponse response, HttpSession session ) {
		Map<String,Object> reqHeadMap =  (Map<String,Object>)request.getAttribute(Const.HEAD);
        Map<String,Object> reqBodyMap =  (Map<String,Object>)request.getAttribute(Const.BODY);
        Map<String, Object> responseBodyMap= new HashMap<String, Object>();
        
        if(reqHeadMap==null){
            reqHeadMap = new HashMap<String, Object>();
        }
        
        reqHeadMap.put(Const.RESULT_CODE, Const.OK);
        reqHeadMap.put(Const.RESULT_MESSAGE, Const.SUCCESS);
		
        logger.info("======================= reqBodyMap : {}", reqBodyMap.toString());
        
        PeopleDTO DTO = new PeopleDTO();
        
        int result = service.loginPeople( reqBodyMap );
        
        if( result == 1 ) {
        	responseBodyMap.put("rsltCode", "0000");
            responseBodyMap.put("rsltMsg", "Success");
            session.setAttribute("DTO", DTO);
        }
        else if(result == -1) {
        	responseBodyMap.put("rsltCode", "2001");
            responseBodyMap.put("rsltMsg", "ID or PWD not correct");
        }
        else {
        	responseBodyMap.put("rsltCode", "2003");
            responseBodyMap.put("rsltMsg", "Data not found.");
        }
		
        ModelAndView mv = new ModelAndView("defaultJsonView");
        mv.addObject(Const.HEAD,reqHeadMap);
        mv.addObject(Const.BODY,responseBodyMap);

        return mv;
	}
	
//	@RequestMapping( method = RequestMethod.POST, value = "/api/people/loginout" )
//	public ModelAndView logoutPeople ( HttpServletRequest request, HttpServletResponse response, HttpSession session ) {
//		Map<String,Object> reqHeadMap =  (Map<String,Object>)request.getAttribute(Const.HEAD);
//        Map<String,Object> reqBodyMap =  (Map<String,Object>)request.getAttribute(Const.BODY);
//        Map<String, Object> responseBodyMap= new HashMap<String, Object>();
//        
//        if(reqHeadMap==null){
//            reqHeadMap = new HashMap<String, Object>();
//        }
//        
//        reqHeadMap.put(Const.RESULT_CODE, Const.OK);
//        reqHeadMap.put(Const.RESULT_MESSAGE, Const.SUCCESS);
//		
//        logger.info("======================= reqBodyMap : {}", reqBodyMap.toString());
//        
//        peopleDTO DTO = new peopleDTO();
//        
//        int result = service.logoutpeople( reqBodyMap );
//        
//        if( result > 1 ) {
//        	responseBodyMap.put("rsltCode", "0000");
//            responseBodyMap.put("rsltMsg", "Success");
//            session.removeAttribute("DTO");
//            session.invalidate();
//        }
//        else {
//        	responseBodyMap.put("rsltCode", "2003");
//            responseBodyMap.put("rsltMsg", "Data not found.");
//        }
//		
//        ModelAndView mv = new ModelAndView("defaultJsonView");
//        mv.addObject(Const.HEAD,reqHeadMap);
//        mv.addObject(Const.BODY,responseBodyMap);
//
//        return mv;
//	}
	
	//아이디찾기
	@RequestMapping( method = RequestMethod.POST, value = "/api/people/findId")
	public ModelAndView findId (HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> reqHeadMap =  (Map<String,Object>)request.getAttribute(Const.HEAD);
        Map<String,Object> reqBodyMap =  (Map<String,Object>)request.getAttribute(Const.BODY);
        Map<String, Object> responseBodyMap= new HashMap<String, Object>();
        
        if(reqHeadMap==null){
            reqHeadMap = new HashMap<String, Object>();
        }
        
        reqHeadMap.put(Const.RESULT_CODE, Const.OK);
        reqHeadMap.put(Const.RESULT_MESSAGE, Const.SUCCESS);
		
        logger.info("======================= reqBodyMap : {}", reqBodyMap.toString());
        
        String info = service.findId( reqBodyMap );
        
        if( !StringUtils.isEmpty(info) ) {
        	responseBodyMap.put("rsltCode", "0000");
            responseBodyMap.put("rsltMsg", "Success");
            responseBodyMap.put("loginId", info);
        } else {
        	responseBodyMap.put("rsltCode", "2003");
            responseBodyMap.put("rsltMsg", "Data not found.");
        }
		
        ModelAndView mv = new ModelAndView("defaultJsonView");
        mv.addObject(Const.HEAD,reqHeadMap);
        mv.addObject(Const.BODY,responseBodyMap);

        return mv;
	}
	
	//본인인증
	@RequestMapping(method = RequestMethod.POST, value = "/api/people/find")
	public ModelAndView ispeople (HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> reqHeadMap =  (Map<String,Object>)request.getAttribute(Const.HEAD);
        Map<String,Object> reqBodyMap =  (Map<String,Object>)request.getAttribute(Const.BODY);
        Map<String, Object> responseBodyMap= new HashMap<String, Object>();
       
        if(reqHeadMap==null){
            reqHeadMap = new HashMap<String, Object>();
        }
        
        Integer info = service.isPeople(reqBodyMap);
        
        if( info == 1 ) {
        	responseBodyMap.put("rsltCode", "0000");
            responseBodyMap.put("rsltMsg", "Success");
            responseBodyMap.put("existYn", "Y");
        }
        else {
        	responseBodyMap.put("rsltCode", "0000");
            responseBodyMap.put("rsltMsg", "Success");
            responseBodyMap.put("existYn", "N");
        }
        
        
        reqHeadMap.put(Const.RESULT_CODE, Const.OK);
        reqHeadMap.put(Const.RESULT_MESSAGE, Const.SUCCESS);
		
        logger.info("======================= reqBodyMap : {}", reqBodyMap.toString());
        
        ModelAndView mv = new ModelAndView("defaultJsonView");
        mv.addObject(Const.HEAD,reqHeadMap);
        mv.addObject(Const.BODY,responseBodyMap);

        return mv;
        
	}

	//비밀번호변경
	@RequestMapping(method = RequestMethod.POST, value = "/api/people/password")
	public ModelAndView changePWD (HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> reqHeadMap =  (Map<String,Object>)request.getAttribute(Const.HEAD);
        Map<String,Object> reqBodyMap =  (Map<String,Object>)request.getAttribute(Const.BODY);
        Map<String, Object> responseBodyMap= new HashMap<String, Object>();
        
        if(reqHeadMap==null){
            reqHeadMap = new HashMap<String, Object>();
        }
        
        reqHeadMap.put(Const.RESULT_CODE, Const.OK);
        reqHeadMap.put(Const.RESULT_MESSAGE, Const.SUCCESS);
		
        logger.info("======================= reqBodyMap : {}", reqBodyMap.toString());
        
        int result = service.changePWD( reqBodyMap );
        
        if( result > 0 ) {
        	responseBodyMap.put("rsltCode", "0000");
            responseBodyMap.put("rsltMsg", "Success");
        } else {
        	responseBodyMap.put("rsltCode", "2003");
            responseBodyMap.put("rsltMsg", "Data not found.");
        }
		
        ModelAndView mv = new ModelAndView("defaultJsonView");
        mv.addObject(Const.HEAD,reqHeadMap);
        mv.addObject(Const.BODY,responseBodyMap);

        return mv;  
	}
	
	//아이디중복확인
	@RequestMapping(method = RequestMethod.POST, value = "/api/people/duplicate")
	public ModelAndView duplicatepeople (HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> reqHeadMap =  (Map<String,Object>)request.getAttribute(Const.HEAD);
        Map<String,Object> reqBodyMap =  (Map<String,Object>)request.getAttribute(Const.BODY);
        Map<String, Object> responseBodyMap= new HashMap<String, Object>();
        
        if(reqHeadMap==null){
            reqHeadMap = new HashMap<String, Object>();
        }
        
        reqHeadMap.put(Const.RESULT_CODE, Const.OK);
        reqHeadMap.put(Const.RESULT_MESSAGE, Const.SUCCESS);
		
        logger.info("======================= reqBodyMap : {}", reqBodyMap.toString());
        
        int result = service.duplicatePeople1(reqBodyMap);
        
        if( result > 0 ) {
        	responseBodyMap.put("rsltCode", "0000");
            responseBodyMap.put("rsltMsg", "Success");
            responseBodyMap.put("existYn", "Y");
        } else {
        	responseBodyMap.put("rsltCode", "0000");
            responseBodyMap.put("rsltMsg", "Success");
            responseBodyMap.put("existYn", "N");
        }
		
        ModelAndView mv = new ModelAndView("defaultJsonView");
        mv.addObject(Const.HEAD,reqHeadMap);
        mv.addObject(Const.BODY,responseBodyMap);

        return mv;  
	}
	
	
	
}
