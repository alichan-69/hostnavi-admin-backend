package com.alichan.hostnavi.admin.infrastracture.model.generated;

import java.util.Date;

public class ReservationReview {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column reservation_review.id
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column reservation_review.reservation_id
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    private Long reservationId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column reservation_review.review
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    private String review;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column reservation_review.clean_score
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    private Integer cleanScore;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column reservation_review.service_score
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    private Integer serviceScore;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column reservation_review.facility_score
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    private Integer facilityScore;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column reservation_review.location_score
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    private Integer locationScore;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column reservation_review.fee_score
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    private Integer feeScore;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column reservation_review.create_time
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column reservation_review.update_time
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column reservation_review.delete_flag
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    private Boolean deleteFlag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column reservation_review.id
     *
     * @return the value of reservation_review.id
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column reservation_review.id
     *
     * @param id the value for reservation_review.id
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column reservation_review.reservation_id
     *
     * @return the value of reservation_review.reservation_id
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public Long getReservationId() {
        return reservationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column reservation_review.reservation_id
     *
     * @param reservationId the value for reservation_review.reservation_id
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column reservation_review.review
     *
     * @return the value of reservation_review.review
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public String getReview() {
        return review;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column reservation_review.review
     *
     * @param review the value for reservation_review.review
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public void setReview(String review) {
        this.review = review;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column reservation_review.clean_score
     *
     * @return the value of reservation_review.clean_score
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public Integer getCleanScore() {
        return cleanScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column reservation_review.clean_score
     *
     * @param cleanScore the value for reservation_review.clean_score
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public void setCleanScore(Integer cleanScore) {
        this.cleanScore = cleanScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column reservation_review.service_score
     *
     * @return the value of reservation_review.service_score
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public Integer getServiceScore() {
        return serviceScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column reservation_review.service_score
     *
     * @param serviceScore the value for reservation_review.service_score
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public void setServiceScore(Integer serviceScore) {
        this.serviceScore = serviceScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column reservation_review.facility_score
     *
     * @return the value of reservation_review.facility_score
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public Integer getFacilityScore() {
        return facilityScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column reservation_review.facility_score
     *
     * @param facilityScore the value for reservation_review.facility_score
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public void setFacilityScore(Integer facilityScore) {
        this.facilityScore = facilityScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column reservation_review.location_score
     *
     * @return the value of reservation_review.location_score
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public Integer getLocationScore() {
        return locationScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column reservation_review.location_score
     *
     * @param locationScore the value for reservation_review.location_score
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public void setLocationScore(Integer locationScore) {
        this.locationScore = locationScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column reservation_review.fee_score
     *
     * @return the value of reservation_review.fee_score
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public Integer getFeeScore() {
        return feeScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column reservation_review.fee_score
     *
     * @param feeScore the value for reservation_review.fee_score
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public void setFeeScore(Integer feeScore) {
        this.feeScore = feeScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column reservation_review.create_time
     *
     * @return the value of reservation_review.create_time
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column reservation_review.create_time
     *
     * @param createTime the value for reservation_review.create_time
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column reservation_review.update_time
     *
     * @return the value of reservation_review.update_time
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column reservation_review.update_time
     *
     * @param updateTime the value for reservation_review.update_time
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column reservation_review.delete_flag
     *
     * @return the value of reservation_review.delete_flag
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column reservation_review.delete_flag
     *
     * @param deleteFlag the value for reservation_review.delete_flag
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}