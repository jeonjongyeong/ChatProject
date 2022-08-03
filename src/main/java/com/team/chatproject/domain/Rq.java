package com.team.chatproject.domain;

import com.team.chatproject.service.MemberService;
import com.team.chatproject.util.Ut;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Rq {
	@Getter
	private boolean isLogin;
	@Getter
	private int loginMemberId;
	@Getter
	private Member loginMember;
	
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HttpSession session;
	

	public Rq(HttpServletRequest req, HttpServletResponse resp, MemberService memberService) {
		this.req =req;
		this.resp =resp;
		
		this.session = req.getSession();
		
		boolean isLogin = false;
		int loginMemberId = 0;
		Member loginMember = null;

		if (session.getAttribute("loginMemberId") != null) {
			isLogin = true;
			loginMemberId = (int) session.getAttribute("loginMemberId");
			loginMember = memberService.getMemberById(loginMemberId);
		}

		this.isLogin = isLogin;
		this.loginMemberId = loginMemberId;
		this.loginMember = loginMember;

		this.req.setAttribute("rq", this);

	}

	public void printHistoryBackJs(String msg) {
		resp.setContentType("text/html; charset=UTF-8");
		print(Ut.jsHistoryBack(msg));
	}

	public void print(String str) {
		try {
			resp.getWriter().append(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void println(String str) {
		print(str + "\n");
	}
	
	public void login(Member member) {
		session.setAttribute("loginMemberId", member.getId());

	}

	public void logout() {
		session.removeAttribute("loginMemberId");
	}

	public String historyBackJsOnView(String msg) {
		req.setAttribute("msg", msg);
		req.setAttribute("historyBack",true );
		return "common/js";
	}

	public String jsHistoryBack(String msg) {
		
		return Ut.jsHistoryBack(msg);
	}

	public String jsReplace(String msg,String uri) {
		return Ut.jsReplace(msg,uri); 
	}
}