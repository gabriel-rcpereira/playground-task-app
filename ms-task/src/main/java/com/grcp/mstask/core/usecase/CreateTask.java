package com.grcp.mstask.core.usecase;

import com.grcp.mstask.core.port.CreateTaskAdapter;
import com.grcp.mstask.core.usecase.command.NewTaskCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateTask {

    private final CreateTaskAdapter createTaskAdapter;

    public void execute(NewTaskCommand newTaskCommand) {
        this.createTaskAdapter.execute(newTaskCommand.newTask());
    }
}
