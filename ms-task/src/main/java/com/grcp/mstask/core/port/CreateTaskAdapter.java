package com.grcp.mstask.core.port;

import com.grcp.mstask.core.domain.task.NewTask;

public interface CreateTaskAdapter {

    void execute(NewTask newTask);
}
