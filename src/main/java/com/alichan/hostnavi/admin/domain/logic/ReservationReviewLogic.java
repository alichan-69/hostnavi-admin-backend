package com.alichan.hostnavi.admin.domain.logic;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.ReservationReviewMapper;
import com.alichan.hostnavi.admin.infrastracture.model.generated.ReservationReview;
import com.alichan.hostnavi.admin.infrastracture.model.generated.ReservationReviewExample;

@Component
public class ReservationReviewLogic {
  @Autowired
  ReservationReviewMapper reservationReviewMapper;

  public void deleteReviewsByReservationId(long reservationId) {
    List<ReservationReview> reviews = getReviewByReservationId(reservationId);

    reviews.forEach(review -> {
      deleteReviewWithoutResponseData(review.getId());
    });
  }

  public List<ReservationReview> getReviewByReservationId(long reservationId) {
    ReservationReviewExample reservationReviewExample = new ReservationReviewExample();
    reservationReviewExample.createCriteria().andReservationIdEqualTo(reservationId)
        .andDeleteFlagEqualTo(false);
    return reservationReviewMapper.selectByExample(reservationReviewExample);
  }

  public void deleteReviewWithoutResponseData(long id) {
    ReservationReview deletedReview = createDeletedModel(id);
    reservationReviewMapper.updateByPrimaryKeySelective(deletedReview);
  }

  public ReservationReview createDeletedModel(long id) {
    ReservationReview reservationReview = new ReservationReview();

    reservationReview.setId(id);
    reservationReview.setDeleteFlag(true);

    return reservationReview;
  }
}
