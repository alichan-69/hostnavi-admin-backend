package com.alichan.hostnavi.admin.dto.requestparam;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.alichan.hostnavi.admin.application.validation.annotation.IntegerType;
import com.alichan.hostnavi.admin.application.validation.annotation.LongType;
import com.alichan.hostnavi.admin.application.validation.annotation.StringType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationReviewRequestParam {
  @NotNull(message = "reservationIdを入力してください。")
  @LongType(message = "reservationIdをLong型で入力してください。")
  @Min(value = 1, message = "reservationIdを1以上で入力してください。")
  private Long reservationId;
  @NotNull(message = "reviewを入力してください。")
  @StringType(message = "reviewを文字列型で入力してください。")
  @Length(min = 1, max = 1000, message = "reviewを1文字以上1000文字以下で入力してください。")
  private String review;
  @NotNull(message = "cleanScoreを入力してください。")
  @IntegerType(message = "cleanScoreをInteger型で入力してください。")
  @Min(value = 1, message = "cleanScoreを1以上で入力してください。")
  @Max(value = 5, message = "cleanScoreを5以上で入力してください。")
  private Integer cleanScore;
  @NotNull(message = "serviceScoreを入力してください。")
  @IntegerType(message = "serviceScoreをInteger型で入力してください。")
  @Min(value = 1, message = "serviceScoreを1以上で入力してください。")
  @Max(value = 5, message = "serviceScoreを5以上で入力してください。")
  private Integer serviceScore;
  @NotNull(message = "facilityScoreを入力してください。")
  @IntegerType(message = "facilityScoreをInteger型で入力してください。")
  @Min(value = 1, message = "facilityScoreを1以上で入力してください。")
  @Max(value = 5, message = "facilityScoreを5以上で入力してください。")
  private Integer facilityScore;
  @NotNull(message = "locationScoreを入力してください。")
  @IntegerType(message = "locationScoreをInteger型で入力してください。")
  @Min(value = 1, message = "locationScoreを1以上で入力してください。")
  @Max(value = 5, message = "locationScoreを5以上で入力してください。")
  private Integer locationScore;
  @NotNull(message = "feeScoreを入力してください。")
  @IntegerType(message = "feeScoreをInteger型で入力してください。")
  @Min(value = 1, message = "feeScoreを1以上で入力してください。")
  @Max(value = 5, message = "feeScoreを5以上で入力してください。")
  private Integer feeScore;
}
