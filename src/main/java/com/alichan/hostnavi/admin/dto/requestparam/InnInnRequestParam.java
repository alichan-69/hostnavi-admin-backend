package com.alichan.hostnavi.admin.dto.requestparam;

import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import com.alichan.hostnavi.admin.application.validation.annotation.IntegerType;
import com.alichan.hostnavi.admin.application.validation.annotation.ListIntegerType;
import com.alichan.hostnavi.admin.application.validation.annotation.LongType;
import com.alichan.hostnavi.admin.application.validation.annotation.StringType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InnInnRequestParam {
  @NotNull(message = "userIdを入力してください。")
  @LongType(message = "userIdをLong型で入力してください。")
  @Min(value = 1, message = "userIdを1以上で入力してください。")
  private Long userId;
  @NotBlank(message = "nameを入力してください。")
  @StringType(message = "nameを文字列型で入力してください。")
  @Length(min = 1, max = 100, message = "nameを1文字以上100文字以下で入力してください。")
  private String name;
  @StringType(message = "descriptionを文字列型で入力してください。")
  @Length(min = 1, max = 500, message = "descriptionを1文字以上500文字以下で入力してください。")
  private String description;
  @NotNull(message = "feeを入力してください。")
  @IntegerType(message = "feeをInteger型で入力してください。")
  @Range(min = 0, max = 9999999, message = "feeを0以上9999999以下で入力してください。")
  private Integer fee;
  @NotNull(message = "statusIdを入力してください。")
  @IntegerType(message = "statusIdをInteger型で入力してください。")
  @Min(value = 1, message = "statusIdを1以上で入力してください。")
  private Integer statusId;
  @NotNull(message = "typeIdを入力してください。")
  @IntegerType(message = "typeIdをInteger型で入力してください。")
  @Min(value = 1, message = "typeIdを1以上で入力してください。")
  private Integer typeId;
  @NotBlank(message = "addressを入力してください。")
  @StringType(message = "addressを文字列型で入力してください。")
  @Length(min = 1, max = 100, message = "addressを1文字以上100文字以下で入力してください。")
  private String address;
  @NotNull(message = "guestNumberを入力してください。")
  @IntegerType(message = "guestNumberをInteger型で入力してください。")
  @Range(min = 0, max = 99, message = "feeを0以上99以下で入力してください。")
  private Integer guestNumber;
  @NotNull(message = "bedroomNumberを入力してください。")
  @IntegerType(message = "bedroomNumberをInteger型で入力してください。")
  @Range(min = 0, max = 99, message = "bedroomNumberを0以上99以下で入力してください。")
  private Integer bedroomNumber;
  @NotNull(message = "bedNumberを入力してください。")
  @IntegerType(message = "bedNumberをInteger型で入力してください。")
  @Range(min = 0, max = 99, message = "bedNumberを0以上99以下で入力してください。")
  private Integer bedNumber;
  @NotNull(message = "bathroomNumberを入力してください。")
  @IntegerType(message = "bathroomNumberをInteger型で入力してください。")
  @Range(min = 0, max = 99, message = "bathroomNumberを0以上99以下で入力してください。")
  private Integer bathroomNumber;
  @ListIntegerType(message = "amenityIdListの要素をInteger型で入力してください。")
  private List<Integer> amenityIdList;
  @ListIntegerType(message = "facilityIdListの要素をInteger型で入力してください。")
  private List<Integer> facilityIdList;
}
