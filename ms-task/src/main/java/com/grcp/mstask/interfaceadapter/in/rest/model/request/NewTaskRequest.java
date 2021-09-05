package com.grcp.mstask.interfaceadapter.in.rest.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class NewTaskRequest {

    private final String userId;
    private final String description;
}
