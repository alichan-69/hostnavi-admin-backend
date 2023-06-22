package com.alichan.hostnavi.admin.dto.requestparam;

import javax.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;
import com.alichan.hostnavi.admin.application.validation.annotation.FileExtension;
import com.alichan.hostnavi.admin.application.validation.annotation.FileSize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageRequestParam {
  @NotNull(message = "imageを入力してください。")
  @FileExtension(message = "imageの拡張子を有効な拡張子で入力してください。")
  @FileSize(message = "imageのサイズを2MB以下で入力してください。")
  MultipartFile image;
}
