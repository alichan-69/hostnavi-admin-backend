package com.alichan.hostnavi.admin.application.response;

import lombok.Getter;

@Getter
public enum ResponseMessageEnums {
  SUCCESS("成功"), UNAUTHORIZED("まだログインしていないか、トークンの有効期限が切れています"), FORBIDDEN(
      "認証はされていますが、対象のリソースへのアクセスが許可されていません"), NOT_FOUND("リソースが存在しません"), FAIL_VALIDATION(
          "パラメーターでバリデーションエラーが発生しました"), SERVER_FAIL("サーバーでエラーが発生しました");

  private String message;

  private ResponseMessageEnums(String message) {
    this.message = message;
  }
}
