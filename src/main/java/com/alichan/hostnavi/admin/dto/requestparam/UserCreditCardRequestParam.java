package com.alichan.hostnavi.admin.dto.requestparam;

import java.util.Date;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import com.alichan.hostnavi.admin.application.validation.annotation.DateType;
import com.alichan.hostnavi.admin.application.validation.annotation.StringType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreditCardRequestParam {
  @NotBlank(message = "cardNumberを入力してください。")
  @StringType(message = "cardNumberを文字列型で入力してください。")
  @CreditCardNumber(message = "cardNumberをクレジットカード番号の形式で入力してください。")
  @Length(min = 14, max = 16, message = "cardNumberを14桁以上16桁以下で入力してください。")
  private String cardNumber;
  @NotNull(message = "expirationDateを入力してください。")
  @DateType(message = "expirationDateを日付型で入力してください。")
  @Future(message = "expirationDateに未来の日付を入力してください。")
  private Date expirationDate;
  @NotBlank(message = "cvvを入力してください。")
  @StringType(message = "cvvを文字列型で入力してください。")
  @Length(min = 3, max = 3, message = "cvvを3桁で入力してください。")
  private String cvv;
}
