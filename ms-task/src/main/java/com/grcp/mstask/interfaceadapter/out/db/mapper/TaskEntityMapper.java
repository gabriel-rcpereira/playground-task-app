package com.grcp.mstask.interfaceadapter.out.db.mapper;

import com.grcp.mstask.core.domain.task.NewTask;
import com.grcp.mstask.interfaceadapter.out.db.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class TaskEntityMapper {

    public static final TaskEntityMapper INSTANCE = Mappers.getMapper(TaskEntityMapper.class);

    public abstract TaskEntity mapToTaskEntity(NewTask newTask);
}
