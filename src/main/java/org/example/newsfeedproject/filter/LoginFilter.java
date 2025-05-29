package org.example.newsfeedproject.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.newsfeedproject.constant.Const;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class LoginFilter implements Filter {

    // 로그인 안해도 접근가능한 url
    private static final String[] WHITE_LIST = {"/", "/users", "/users/login"};

    boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST,requestURI);
    }

    // 로그인을 해야 feeds 에 접근가능
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // 다운 캐스팅
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        // 요청 url 받아오기
        String requestURI = httpRequest.getRequestURI();

        // 로그인한 유저만 모든 url 에 접근가능
        if(!isWhiteList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);

            // 로그인 안한 유저는 WHITE_LIST 에 있는 url 만 접근가능
            // 접근 시도시 401, 에러메세지 반환
            if (session == null || session.getAttribute(Const.USER) == null) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.setContentType("application/json");
                httpResponse.getWriter().write("{\"message\" : \"로그인이 필요합니다.\"}");

                // 조건문 동작시 필터체인 중단
                return;
            }

        }

        // 다음 필터로 넘겨주기
        filterChain.doFilter(httpRequest, httpResponse);

    }

}
