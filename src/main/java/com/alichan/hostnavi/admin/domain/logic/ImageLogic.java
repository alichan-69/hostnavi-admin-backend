package com.alichan.hostnavi.admin.domain.logic;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.alichan.hostnavi.admin.dto.responsedata.ImageResponseData;
import com.alichan.hostnavi.admin.error.Assert;
import com.alichan.hostnavi.admin.util.JsonUtil;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.SetBucketPolicyArgs;

@Component
public class ImageLogic {
  @Value("${minio.endpoint}")
  private String ENDPOINT;
  @Value("${minio.bucketName}")
  private String BUCKET_NAME;
  @Value("${minio.accessKey}")
  private String ACCESS_KEY;
  @Value("${minio.secretKey}")
  private String SECRET_KEY;

  public void deleteObject(String objectName) {
    try {
      MinioClient minioClient =
          MinioClient.builder().endpoint(ENDPOINT).credentials(ACCESS_KEY, SECRET_KEY).build();
      minioClient
          .removeObject(RemoveObjectArgs.builder().bucket(BUCKET_NAME).object(objectName).build());
    } catch (Exception error) {
      Assert.serverFail("ファイルのアップロードに失敗しました。");
      return;
    }
  }

  public Optional<ImageResponseData> createImage(MultipartFile image, String objectName) {
    try {
      MinioClient minioClient =
          MinioClient.builder().endpoint(ENDPOINT).credentials(ACCESS_KEY, SECRET_KEY).build();
      boolean isExists =
          minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build());

      // bucketが存在しなかったらbucketを作成
      if (!isExists) {
        String policy =
            JsonUtil.convertJsonToString("com/alichan/hostnavi/admin/minio/policy.json");

        minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
        minioClient.setBucketPolicy(
            SetBucketPolicyArgs.builder().bucket(BUCKET_NAME).config(policy).build());
      }

      minioClient.putObject(PutObjectArgs.builder().bucket(BUCKET_NAME).object(objectName)
          .stream(image.getInputStream(), image.getSize(), -1).contentType(image.getContentType())
          .build());

      ImageResponseData imageResponseData = new ImageResponseData();
      imageResponseData.setName(image.getOriginalFilename());
      imageResponseData.setUrl(ENDPOINT + "/" + BUCKET_NAME + "/" + objectName);

      return Optional.of(imageResponseData);
    } catch (Exception error) {
      Assert.serverFail("画像のアップロードに失敗しました。");
      return null;
    }
  }

}
