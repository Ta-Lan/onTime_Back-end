package Talan.controller;

import Talan.DTO.MessageDTO;
import Talan.service.MessageService;
import com.google.gson.Gson;
import kr.msp.constant.Const;
import oracle.net.ns.Message;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MessageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired(required = true)
	@Qualifier("sqlSession_sample")
	private SqlSession sqlSession;
	
    @Autowired(required=true)
    private MessageService service;

    private Gson gson = new Gson();
    // 한 채팅방 대화가져오기
    @RequestMapping(method = RequestMethod.POST, value = "/api/message/info")
    public ModelAndView getMessageInfo(HttpServletRequest request, HttpServletResponse response){

        Map<String,Object> reqHeadMap =  (Map<String,Object>)request.getAttribute(Const.HEAD);
        Map<String,Object> reqBodyMap =  (Map<String,Object>)request.getAttribute(Const.BODY);
        Map<String, Object> responseBodyMap= new HashMap<String, Object>();

        if(reqHeadMap==null){
            reqHeadMap = new HashMap<String, Object>();
        }

        reqHeadMap.put(Const.RESULT_CODE, Const.OK);
        reqHeadMap.put(Const.RESULT_MESSAGE, Const.SUCCESS);

        logger.info("======================= reqBo  dyMap : {}", reqBodyMap.toString());

        List<MessageDTO> list = service.getMessageInfo(reqBodyMap);

        logger.info("======================= list  : {}", list.toString());

        responseBodyMap.put("rsltCode", "0000");
        responseBodyMap.put("rsltMsg", "Success");
        responseBodyMap.put("Data",list);
        ModelAndView mv = new ModelAndView("defaultJsonView");
        mv.addObject(Const.HEAD,reqHeadMap);
        mv.addObject(Const.BODY,responseBodyMap);

        return mv;
    }

    //message send
    @RequestMapping( method = RequestMethod.POST, value = "/api/message/send" )
    public ModelAndView regPeople( HttpServletRequest request, HttpServletResponse response ){

        Map<String,Object> reqHeadMap =  (Map<String,Object>)request.getAttribute(Const.HEAD);
        Map<String,Object> reqBodyMap =  (Map<String,Object>)request.getAttribute(Const.BODY);
        Map<String, Object> responseBodyMap= new HashMap<String, Object>();

        if(reqHeadMap==null){
            reqHeadMap = new HashMap<String, Object>();
        }

        reqHeadMap.put(Const.RESULT_CODE, Const.OK);
        reqHeadMap.put(Const.RESULT_MESSAGE, Const.SUCCESS);
        

        logger.info("======================= reqBodyMap : {}", reqBodyMap.toString());

        int result = service.insertMessage( reqBodyMap );

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
}
