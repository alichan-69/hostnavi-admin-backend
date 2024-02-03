package com.alichan.hostnavi.admin.domain.service;

import java.util.List;
import java.util.Optional;
import com.alichan.hostnavi.admin.dto.requestparam.ReservationReviewRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.ReservationReviewResponseData;
import com.alichan.hostnavi.admin.infrastracture.model.custom.ReservationReviewCustom;
import com.alichan.hostnavi.admin.infrastracture.model.generated.ReservationReview;

public interface ReservationReviewService {
  public List<ReservationReviewResponseData> getAllReview(Long ownerId, Long reviewerId);

  public List<ReservationReviewResponseData> getReview(int pageNumber, int pageSize);

  public ReservationReviewResponseData createReview(
      ReservationReviewRequestParam reservationReviewRequestParam);

  public ReservationReviewResponseData updateReview(long id,
      ReservationReviewRequestParam reservationReviewRequestParam);

  public ReservationReviewResponseData deleteReview(long id);

  public List<ReservationReviewResponseData> deleteReviews(List<Long> ids);

  public Optional<ReservationReviewResponseData> getReview(long id);

  public ReservationReview createDeletedModel(long id);

  public ReservationReviewResponseData convertModelToResponseData(
      ReservationReview reservationReview);

  public List<ReservationReviewResponseData> convertListModelToListResponseData(
      List<ReservationReview> reservationReviews);

  public ReservationReviewResponseData convertCustomModelToResponseData(
      ReservationReviewCustom reservationReview);

  public List<ReservationReviewResponseData> convertListCustomModelToListResponseData(
      List<ReservationReviewCustom> gottenReviews);
}
