package com.team.chatproject.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.chatproject.domain.Rq;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class BeforeActionInterceptor implements HandlerInterceptor {
	private Rq rq;

	public BeforeActionInterceptor (Rq rq) {
		this.rq = rq;
	}
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		// 이제는 Rq 객체가 자동으로 만들어지기 때문에 필요 없음
		rq.initOnBeforeActionInterceptor();


		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
}