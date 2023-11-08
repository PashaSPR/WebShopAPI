package com.example.webshopdip.dtos;

import java.time.LocalDate;

public class CommentsAndEvaluationDTO {

    private Long id;
    private LocalDate date_comment; // Дата створення коменту до товару
    private Long usersListsId;

    private Long goodsId;
    private String comment; // Коментар до товару

    private  Integer evaluation;

    public CommentsAndEvaluationDTO() {
    }

    public CommentsAndEvaluationDTO(Long id,LocalDate date_comment, Long usersListsId, Long goodsId, String comment, Integer evaluation) {
        this.id = id;
        this.date_comment = date_comment;
        this.usersListsId = usersListsId;
        this.goodsId = goodsId;
        this.comment = comment;
        this.evaluation = evaluation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate_comment() {
        return date_comment;
    }

    public void setDate_comment(LocalDate date_comment) {
        this.date_comment = date_comment;
    }

    public Long getUsersListsId() {
        return usersListsId;
    }

    public void setUsersListsId(Long usersListsId) {
        this.usersListsId = usersListsId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
    }
}