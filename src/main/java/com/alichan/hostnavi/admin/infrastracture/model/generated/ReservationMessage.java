package com.alichan.hostnavi.admin.infrastracture.model.generated;

import java.util.Date;

public class ReservationMessage {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column reservation_message.id
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column reservation_message.reservation_id
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    private Long reservationId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column reservation_message.sender_id
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    private Long senderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column reservation_message.receiver_id
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    private Long receiverId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column reservation_message.message
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    private String message;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column reservation_message.send_time
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    private Date sendTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column reservation_message.delete_flag
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    private Boolean deleteFlag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column reservation_message.id
     *
     * @return the value of reservation_message.id
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column reservation_message.id
     *
     * @param id the value for reservation_message.id
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column reservation_message.reservation_id
     *
     * @return the value of reservation_message.reservation_id
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public Long getReservationId() {
        return reservationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column reservation_message.reservation_id
     *
     * @param reservationId the value for reservation_message.reservation_id
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column reservation_message.sender_id
     *
     * @return the value of reservation_message.sender_id
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public Long getSenderId() {
        return senderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column reservation_message.sender_id
     *
     * @param senderId the value for reservation_message.sender_id
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column reservation_message.receiver_id
     *
     * @return the value of reservation_message.receiver_id
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public Long getReceiverId() {
        return receiverId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column reservation_message.receiver_id
     *
     * @param receiverId the value for reservation_message.receiver_id
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column reservation_message.message
     *
     * @return the value of reservation_message.message
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column reservation_message.message
     *
     * @param message the value for reservation_message.message
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column reservation_message.send_time
     *
     * @return the value of reservation_message.send_time
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column reservation_message.send_time
     *
     * @param sendTime the value for reservation_message.send_time
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column reservation_message.delete_flag
     *
     * @return the value of reservation_message.delete_flag
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column reservation_message.delete_flag
     *
     * @param deleteFlag the value for reservation_message.delete_flag
     *
     * @mbg.generated Wed Jun 14 18:10:19 JST 2023
     */
    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}