package com.ds.dy_xhs_monitor.mapper;

import com.ds.dy_xhs_monitor.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends JpaRepository<User,Long> {
}
