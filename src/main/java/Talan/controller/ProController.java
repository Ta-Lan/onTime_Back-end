package Talan.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Talan.DTO.ProDTO;
import Talan.service.pro.ProService;
import kr.msp.constant.Const;

@Controller
public class ProController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired(required = true)
	private ProService service;

	// PRO 등록
	@RequestMapping(method = RequestMethod.POST, value = "/api/pro/regist")
	public ModelAndView registPro(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		Map<String, Object> reqHeadMap = (Map<String, Object>) request.getAttribute(Const.HEAD);
		Map<String, Object> reqBodyMap = (Map<String, Object>) request.getAttribute(Const.BODY);
		Map<String, Object> responseBodyMap = new HashMap<String, Object>();

		if (reqHeadMap == null) {
			reqHeadMap = new HashMap<String, Object>();
		}

		reqHeadMap.put(Const.RESULT_CODE, Const.OK);
		reqHeadMap.put(Const.RESULT_MESSAGE, Const.SUCCESS);

		if (session.getAttribute("user") != null) {
			Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");

			reqBodyMap.put("proId", user.get("loginId"));
			reqBodyMap.put("kindScore", 0);

			logger.info("======================= reqBodyMap : {}", reqBodyMap.toString());

			int result = service.registPro(reqBodyMap);

			if (result > 0) {
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
		mv.addObject(Const.HEAD, reqHeadMap);
		mv.addObject(Const.BODY, responseBodyMap);

		return mv;
	}

	// PRO 조회
	@RequestMapping(method = RequestMethod.POST, value = "/api/pro/info")
	public ModelAndView getpeopleInfo(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> reqHeadMap = (Map<String, Object>) request.getAttribute(Const.HEAD);
		Map<String, Object> reqBodyMap = (Map<String, Object>) request.getAttribute(Const.BODY);
		Map<String, Object> responseBodyMap = new HashMap<String, Object>();

		if (reqHeadMap == null) {
			reqHeadMap = new HashMap<String, Object>();
		}

		reqHeadMap.put(Const.RESULT_CODE, Const.OK);
		reqHeadMap.put(Const.RESULT_MESSAGE, Const.SUCCESS);

		logger.info("======================= reqBodyMap : {}", reqBodyMap.toString());

		ProDTO info = service.getProInfo(reqBodyMap);

		if (!StringUtils.isEmpty(info)) {
			responseBodyMap.put("rsltCode", "0000");
			responseBodyMap.put("rsltMsg", "Success");
			responseBodyMap.put("proId", info.getProId());
			responseBodyMap.put("category", info.getCategory());
			responseBodyMap.put("license", info.getLicense());
			responseBodyMap.put("kindScore", info.getKindScore());
		} else {
			responseBodyMap.put("rsltCode", "2003");
			responseBodyMap.put("rsltMsg", "Data not found.");
		}

		ModelAndView mv = new ModelAndView("defaultJsonView");
		mv.addObject(Const.HEAD, reqHeadMap);
		mv.addObject(Const.BODY, responseBodyMap);

		return mv;
	}

	// 자격증 검색
	@RequestMapping(method = RequestMethod.POST, value = "/api/pro/searchLicense")
	public ModelAndView searchLicense(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		Map<String, Object> reqHeadMap = (Map<String, Object>) request.getAttribute(Const.HEAD);
		Map<String, Object> reqBodyMap = (Map<String, Object>) request.getAttribute(Const.BODY);
		Map<String, Object> responseBodyMap = new HashMap<String, Object>();

		if (reqHeadMap == null) {
			reqHeadMap = new HashMap<String, Object>();
		}

		reqHeadMap.put(Const.RESULT_CODE, Const.OK);
		reqHeadMap.put(Const.RESULT_MESSAGE, Const.SUCCESS);

		logger.info("======================= reqBodyMap : {}", reqBodyMap.toString());

		List<Object> list = service.searchLicense(reqBodyMap);
		
		if (!StringUtils.isEmpty(list)) {
			responseBodyMap.put("rsltCode", "0000");
			responseBodyMap.put("rsltMsg", "Success");
			responseBodyMap.put("licenseList", list);
		} else {
			responseBodyMap.put("rsltCode", "2003");
			responseBodyMap.put("rsltMsg", "Data not found.");
		}

		ModelAndView mv = new ModelAndView("defaultJsonView");
		mv.addObject(Const.HEAD, reqHeadMap);
		mv.addObject(Const.BODY, responseBodyMap);

		return mv;
	}
}
