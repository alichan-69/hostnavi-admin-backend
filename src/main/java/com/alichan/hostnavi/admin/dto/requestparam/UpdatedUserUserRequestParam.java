package com.alichan.hostnavi.admin.dto.requestparam;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import com.alichan.hostnavi.admin.application.validation.annotation.StringType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatedUserUserRequestParam {
  @StringType(message = "imageUrlを文字列型で入力してください。")
  @URL(message = "imageUrlをURLの形式で入力してください。")
  @Length(min = 1, max = 500, message = "imageUrlを1文字以上500文字以下で入力してください。")
  private String imageUrl;
  @NotBlank(message = "nameを入力してください。")
  @StringType(message = "nameを文字列型で入力してください。")
  @Length(min = 1, max = 100, message = "nameを1文字以上100文字以下で入力してください。")
  private String name;
  @StringType(message = "descriptionを文字列型で入力してください。")
  @Length(min = 1, max = 500, message = "descriptionを1文字以上500文字以下で入力してください。")
  private String description;
  @StringType(message = "addressを文字列型で入力してください。")
  @Length(min = 1, max = 100, message = "addressを1文字以上100文字以下で入力してください。")
  private String address;
  @StringType(message = "occupationを文字列型で入力してください。")
  @Length(min = 1, max = 100, message = "occupationを1文字以上100文字以下で入力してください。")
  private String occupation;
  @StringType(message = "phoneNumberを文字列型で入力してください。")
  @Length(min = 1, max = 100, message = "phoneNumberを1文字以上100文字以下で入力してください。")
  private String phoneNumber;
  @NotBlank(message = "mailを入力してください。")
  @StringType(message = "mailを文字列型で入力してください。")
  @Email(message = "mailをemailの形式で入力してください。")
  @Length(min = 1, max = 100, message = "mailを1文字以上100文字以下で入力してください。")
  private String mail;
  @StringType(message = "facebookUrlを文字列型で入力してください。")
  @URL(message = "facebookUrlをURLの形式で入力してください。")
  @Length(min = 1, max = 500, message = "facebookUrlを1文字以上500文字以下で入力してください。")
  private String facebookUrl;
  @StringType(message = "instagramUrlを文字列型で入力してください。")
  @URL(message = "instagramUrlをURLの形式で入力してください。")
  @Length(min = 1, max = 500, message = "instagramUrlを1文字以上500文字以下で入力してください。")
  private String instagramUrl;
  @StringType(message = "twitterUrlを文字列型で入力してください。")
  @URL(message = "twitterUrlをURLの形式で入力してください。")
  @Length(min = 1, max = 500, message = "twitterUrlを1文字以上500文字以下で入力してください。")
  private String twitterUrl;
  private UpdatedUserCreditCardRequestParam creditCard;
}
