package com.grcp.mstask.interfaceadapter.out.db.repository;

import com.grcp.mstask.interfaceadapter.out.db.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
