package Talan.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Talan.service.review.ReviewService;

@Controller
public class ReviewController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired(required = true)
	@Qualifier("sqlSession_sample")
	private SqlSession sqlSession;

	@Autowired(required = true)
	private ReviewService service;
	
	// 리뷰 등록
//	@RequestMapping(method = RequestMethod.POST, value = "/api/review/regist")
//	public ModelAndView registReview(HttpServletRequest request, HttpServletResponse response) {
//		
//	}
}
