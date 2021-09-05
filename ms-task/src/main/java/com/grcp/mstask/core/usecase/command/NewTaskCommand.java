package com.grcp.mstask.core.usecase.command;

import com.grcp.mstask.core.domain.task.NewTask;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NewTaskCommand {

    private final String userId;
    private final String description;

    public NewTask newTask() {
        return new NewTask(this.userId, this.description);
    }
}
