package com.grcp.mstask.interfaceadapter.out.db;

import com.grcp.mstask.core.domain.task.NewTask;
import com.grcp.mstask.core.port.CreateTaskAdapter;
import com.grcp.mstask.interfaceadapter.out.db.entity.TaskEntity;
import com.grcp.mstask.interfaceadapter.out.db.mapper.TaskEntityMapper;
import com.grcp.mstask.interfaceadapter.out.db.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateTaskAdapterImpl implements CreateTaskAdapter {

    private final TaskRepository taskRepository;

    @Override
    public void execute(NewTask newTask) {
        TaskEntity taskEntity = TaskEntityMapper.INSTANCE.mapToTaskEntity(newTask);
        this.taskRepository.save(taskEntity);
    }
}
