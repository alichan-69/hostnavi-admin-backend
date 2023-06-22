package com.alichan.hostnavi.admin.dto.requestparam;

import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;
import com.alichan.hostnavi.admin.application.validation.annotation.ListFileExtension;
import com.alichan.hostnavi.admin.application.validation.annotation.ListFileSize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImagesRequestParam {
  @NotNull(message = "imagesを入力してください。")
  @ListFileExtension(message = "imagesの要素の拡張子を有効な拡張子で入力してください。")
  @ListFileSize(message = "imagesの要素のサイズを2MB以下で入力してください。")
  List<MultipartFile> images;
}
