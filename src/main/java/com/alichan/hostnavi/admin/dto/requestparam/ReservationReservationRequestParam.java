package com.alichan.hostnavi.admin.dto.requestparam;

import java.util.Date;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import com.alichan.hostnavi.admin.application.validation.annotation.DateType;
import com.alichan.hostnavi.admin.application.validation.annotation.IntegerType;
import com.alichan.hostnavi.admin.application.validation.annotation.LongType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationReservationRequestParam {
  @NotNull(message = "innIdを入力してください。")
  @LongType(message = "innIdをLong型で入力してください。")
  @Min(value = 1, message = "innIdを1以上で入力してください。")
  private Long innId;
  @NotNull(message = "statusIdを入力してください。")
  @IntegerType(message = "statusIdをInteger型で入力してください。")
  @Min(value = 1, message = "statusIdを1以上で入力してください。")
  private Integer statusId;
  @NotNull(message = "reserverIdを入力してください。")
  @LongType(message = "reserverIdをLong型で入力してください。")
  @Min(value = 1, message = "reserverIdを1以上で入力してください。")
  private Long reserverId;
  @NotNull(message = "checkInTimeを入力してください。")
  @DateType(message = "checkInTimeを日付型で入力してください。")
  @Future(message = "checkInTimeに未来の日付を入力してください。")
  private Date checkInTime;
  @NotNull(message = "checkOutTimeを入力してください。")
  @DateType(message = "checkOutTimeを日付型で入力してください。")
  @Future(message = "checkOutTimeに未来の日付を入力してください。")
  private Date checkOutTime;
  @NotNull(message = "guestNumberを入力してください。")
  @IntegerType(message = "guestNumberをInteger型で入力してください。")
  @Range(min = 0, max = 99, message = "guestNumberを0以上99以下で入力してください。")
  private Integer guestNumber;
  @NotNull(message = "feeを入力してください。")
  @IntegerType(message = "feeをInteger型で入力してください。")
  @Range(min = 0, max = 99999999, message = "feeを0以上99999999以下で入力してください。")
  private Integer fee;
}
