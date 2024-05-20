package com.jibro.shop.data.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ljy
 * @since 2024.05.19
 * Base entity 코드
 * **/
@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

	/* 생성 날짜 */
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    /* 갱신 날짜 */
    @LastModifiedDate
    @Column
    private LocalDateTime updatedAt;

}