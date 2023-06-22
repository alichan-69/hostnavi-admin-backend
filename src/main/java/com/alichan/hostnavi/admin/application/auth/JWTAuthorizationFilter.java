package com.alichan.hostnavi.admin.application.auth;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    // headersのkeyを指定して値(トークン)を取得する
    String value = request.getHeader("X-AUTH-TOKEN");
    if (value == null || !value.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }
    String token = value.substring(7);
    // tokenの検証と認証を行う
    DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("secret")).build().verify(token);
    // usernameの取得
    String mail = decodedJWT.getClaim("mail").toString();
    // ログイン状態の設定
    SecurityContextHolder.getContext()
        .setAuthentication(new UsernamePasswordAuthenticationToken(mail, null, new ArrayList<>()));
    filterChain.doFilter(request, response);
  }
}
