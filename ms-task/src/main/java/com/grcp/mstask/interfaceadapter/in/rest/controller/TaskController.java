package com.grcp.mstask.interfaceadapter.in.rest.controller;

import com.grcp.mstask.core.usecase.CreateTask;
import com.grcp.mstask.core.usecase.command.NewTaskCommand;
import com.grcp.mstask.interfaceadapter.in.rest.model.request.NewTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TaskController {

    private final CreateTask createTask;

    @PostMapping(value = "/v1/tasks", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> post(@RequestBody NewTaskRequest newTaskRequest) {
        NewTaskCommand newTaskCommand = new NewTaskCommand(newTaskRequest.getUserId(), newTaskRequest.getDescription());
        this.createTask.execute(newTaskCommand);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
