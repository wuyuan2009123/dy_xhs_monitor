package com.ds.dy_xhs_monitor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "monitor_user")
public class MonitorUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parse_url", nullable = false, unique = true, length = 5000)
    private String parseUrl;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "ms_id")
    private String msId;

    @Column(name = "uid")
    private String uid;

    @Column(name = "zp_count",length = 8000)
    private String zpCount;


    @Column(name = "created_at",  columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;



}
