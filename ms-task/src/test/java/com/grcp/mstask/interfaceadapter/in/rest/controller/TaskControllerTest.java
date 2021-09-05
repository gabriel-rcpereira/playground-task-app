package com.grcp.mstask.interfaceadapter.in.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grcp.mstask.core.usecase.CreateTask;
import com.grcp.mstask.core.usecase.command.NewTaskCommand;
import com.grcp.mstask.interfaceadapter.in.rest.model.request.NewTaskRequest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = { TaskController.class, CreateTask.class })
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CreateTask createTask;

    //[POST] - /api/v1/tasks
    @Test
    public void shouldPostGivenValidInput() throws Exception {
        var taskRequest = new NewTaskRequest("userId", "new task description");
        var requestBody = mapper.writeValueAsString(taskRequest);

        mockMvc.perform(
                post("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
        .andExpect(status().isCreated());

        ArgumentCaptor<NewTaskCommand> newTaskCommandCaptor = ArgumentCaptor.forClass(NewTaskCommand.class);
        verify(this.createTask, times(1)).execute(newTaskCommandCaptor.capture());

        assertThat(newTaskCommandCaptor.getValue().getUserId()).isEqualTo("userId");
        assertThat(newTaskCommandCaptor.getValue().getDescription()).isEqualTo("new task description");
    }

    //[POST] - /api/v1/tasks
    @Test
    public void shouldPostGivenInvalidInput() throws Exception {
        var taskRequest = new NewTaskRequest(null, null);
        var requestBody = mapper.writeValueAsString(taskRequest);

        mockMvc.perform(
                post("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());
    }
}