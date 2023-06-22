package com.alichan.hostnavi.admin.application.validation;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class Util {
  public static boolean isValidFileExtension(MultipartFile file) {
    String filename = file.getOriginalFilename();
    int dotIndex = filename.lastIndexOf(".");
    String extension = filename.substring(dotIndex + 1);

    List<String> validExtensions = Arrays.asList("png", "jpg", "jpeg");

    return validExtensions.contains(extension);
  }

  public static boolean isValidFileSize(MultipartFile file) {
    return file.getSize() <= 2097152;
  }
}
