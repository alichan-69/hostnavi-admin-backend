package com.alichan.hostnavi.admin.domain.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.alichan.hostnavi.admin.dto.requestparam.ImagesRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.ImageResponseData;
import com.alichan.hostnavi.admin.dto.responsedata.InnImageResponseData;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.InnImageMapper;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnImage;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnImageExample;
import com.alichan.hostnavi.admin.util.ModelMapperUtil;

@Component
public class InnImageLogic {
  @Autowired
  private ImageLogic imageLogic;
  @Autowired
  private InnImageMapper innImageMapper;

  private ModelMapper modelMapper = new ModelMapperUtil().getModelMapper();

  public List<ImageResponseData> createImage(long id, ImagesRequestParam imagesRequestParam) {
    List<ImageResponseData> imageResponseDatas = new ArrayList<ImageResponseData>();

    List<MultipartFile> images = imagesRequestParam.getImages();

    // データベースから以前保存した画像のurlを全て削除する処理
    deleteInnImageByInnId(id);

    for (int index = 0; index < images.size(); index++) {
      MultipartFile image = images.get(index);

      // MinIOで画像を格納するパスを作成する処理
      String fileName = image.getOriginalFilename();
      String objectName = "inn/" + id + "/" + index + "_" + fileName;

      // MinIOに画像を保存する処理
      ImageResponseData innImageResponseData = imageLogic.createImage(image, objectName).get();
      imageResponseDatas.add(innImageResponseData);

      // データベースに画像の情報を保存する処理
      InnImage innImage =
          convertRequestParamToInsertedModel(id, fileName, innImageResponseData.getUrl());
      innImageMapper.insertSelective(innImage);
    }

    return imageResponseDatas;
  }

  public List<InnImageResponseData> getInnImageByInnId(long innId) {
    InnImageExample innImageExample = new InnImageExample();
    innImageExample.createCriteria().andInnIdEqualTo(innId).andDeleteFlagEqualTo(false);
    return convertListModelToListResponseData(innImageMapper.selectByExample(innImageExample));
  }

  public void deleteInnImageByInnId(long innId) {
    List<InnImageResponseData> innImageResponseDatas = getInnImageByInnId(innId);

    innImageResponseDatas.forEach(innImageResponseData -> {
      InnImage deletedInnImage = createDeletedModel(innImageResponseData.getId());
      innImageMapper.updateByPrimaryKeySelective(deletedInnImage);
    });
  }

  private InnImage convertRequestParamToInsertedModel(long innId, String name, String imageUrl) {
    InnImage innImage = new InnImage();

    innImage.setInnId(innId);
    innImage.setName(name);
    innImage.setImageUrl(imageUrl);
    innImage.setCreateTime(new Date());
    innImage.setUpdateTime(new Date());
    innImage.setDeleteFlag(false);

    return innImage;
  }

  private InnImageResponseData convertModelToResponseData(InnImage innImage) {
    InnImageResponseData innImageResponseData =
        modelMapper.map(innImage, InnImageResponseData.class);

    return innImageResponseData;
  }

  private List<InnImageResponseData> convertListModelToListResponseData(List<InnImage> innImages) {
    List<InnImageResponseData> innImageResponseDatas = innImages.stream()
        .map(image -> convertModelToResponseData(image)).collect(Collectors.toList());
    return innImageResponseDatas;
  }

  private InnImage createDeletedModel(long id) {
    InnImage innImage = new InnImage();

    innImage.setId(id);
    innImage.setDeleteFlag(true);

    return innImage;
  }
}
