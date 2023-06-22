package com.alichan.hostnavi.admin.application.auth;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.alichan.hostnavi.admin.application.response.ResponseMessageEnums;
import com.alichan.hostnavi.admin.dto.requestparam.UserUserRequestParam;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private AuthenticationManager authenticationManager;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
    // ログインパスの指定
    setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/auth/login", "POST"));

    // ログイン成功時にtokenを発行してレスポンスヘッダーにセットする
    this.setAuthenticationSuccessHandler((request, response, chain) -> {
      AuthUser authUser = (AuthUser) chain.getPrincipal();
      // JWTトークンの作成
      String token = JWT.create().withIssuer("alichan").withClaim("id", authUser.getId())
          .sign(Algorithm.HMAC256("secret"));
      // HeaderにX-AUTH-TOKENというKEYで生成したトークンを付与する
      response.setHeader("X-AUTH-TOKEN", token);
      response.setHeader("Access-Control-Expose-Headers", "X-AUTH-TOKEN");
      response.setStatus(HttpServletResponse.SC_OK);
      // responsebodyへの書き込み
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      response.getWriter().write("{\n" + "    \"message\": \""
          + ResponseMessageEnums.SUCCESS.getMessage() + "\",\n" + "    \"data\": null\n" + "}");
    });

    // ログイン失敗時
    this.setAuthenticationFailureHandler((request, response, chain) -> {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      // responsebodyへの書き込み
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      response.getWriter()
          .write("{\n" + "    \"message\": \"" + ResponseMessageEnums.UNAUTHORIZED.getMessage()
              + "\",\n" + "    \"data\": null\n" + "}");
    });
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {
    // リクエストされたユーザーを読み取る処理
    UserUserRequestParam userRequestParam = null;
    try {
      userRequestParam =
          new ObjectMapper().readValue(request.getInputStream(), UserUserRequestParam.class);

      return this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
          userRequestParam.getMail(), userRequestParam.getPassword()));
    } catch (IOException error) {
      throw new RuntimeException(error);
    }
  }
}
