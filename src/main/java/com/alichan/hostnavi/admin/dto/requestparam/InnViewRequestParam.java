package com.alichan.hostnavi.admin.dto.requestparam;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.alichan.hostnavi.admin.application.validation.annotation.LongType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InnViewRequestParam {
  @NotNull(message = "innIdを入力してください。")
  @LongType(message = "innIdをLong型で入力してください。")
  @Min(value = 1, message = "innIdを1以上で入力してください。")
  private Long innId;
  @NotNull(message = "viewerIdを入力してください。")
  @LongType(message = "viewerIdをLong型で入力してください。")
  @Min(value = 1, message = "viewerIdを1以上で入力してください。")
  private Long viewerId;
  @NotNull(message = "pageIdを入力してください。")
  @LongType(message = "pageIdをLong型で入力してください。")
  @Min(value = 1, message = "pageIdを1以上で入力してください。")
  private Long pageId;
}
