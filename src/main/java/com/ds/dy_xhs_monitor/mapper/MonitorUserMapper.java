package com.ds.dy_xhs_monitor.mapper;

import com.ds.dy_xhs_monitor.entity.MonitorUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorUserMapper extends JpaRepository<MonitorUser,Long> {

}
