package com.alichan.hostnavi.admin.util;

import org.modelmapper.ModelMapper;

public class ModelMapperUtil {
  private ModelMapper modelMapper;

  public ModelMapper getModelMapper() {
    modelMapper = new ModelMapper();
    // mapper関数使用時にmap先が曖昧なプロパティをmapしない設定をする処理
    modelMapper.getConfiguration().setAmbiguityIgnored(true);
    return modelMapper;
  }
}
