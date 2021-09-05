package com.grcp.mstask.interfaceadapter.out.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Getter
@Builder
@Value
@Entity
@Table(name = "TB_TASK")
public class TaskEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false, length = 150)
    private String description;
}
