package com.grcp.mstask.core.usecase;

import com.grcp.mstask.core.domain.task.NewTask;
import com.grcp.mstask.core.port.CreateTaskAdapter;
import com.grcp.mstask.core.usecase.command.NewTaskCommand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class CreateTaskTest {

    private final CreateTask createTask;
    private final CreateTaskAdapter createTaskAdapter;

    public CreateTaskTest() {
        this.createTaskAdapter = Mockito.mock(CreateTaskAdapter.class);
        this.createTask = new CreateTask(this.createTaskAdapter);
    }

    @Test
    public void shouldCreateSuccessfulTaskGivenValidInput() {
        var newTaskCommand = new NewTaskCommand("UserId", "n");

        this.createTask.execute(newTaskCommand);

        ArgumentCaptor<NewTask> newTaskArgumentCaptor = ArgumentCaptor.forClass(NewTask.class);
        verify(this.createTaskAdapter, times(1)).execute(newTaskArgumentCaptor.capture());

        Assertions.assertThat(newTaskArgumentCaptor.getValue().getUserId()).isEqualTo("UserId");
        Assertions.assertThat(newTaskArgumentCaptor.getValue().getDescription()).isEqualTo("n");
    }
}