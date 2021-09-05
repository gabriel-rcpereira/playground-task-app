package com.grcp.mstask.core.usecase.command;

import com.github.javafaker.Faker;
import java.util.stream.Stream;
import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NewTaskCommandTest {

    private final Faker faker = new Faker();

    @Test
    public void shouldCreateSuccessfulTaskGivenValidInput() {
        String userId = faker.idNumber().valid();
        String description = faker.gameOfThrones().house();
        var newTaskCommand = new NewTaskCommand(userId, description);

        var newTask = newTaskCommand.newTask();
        assertThat(newTask.getUserId()).isEqualTo(userId);
        assertThat(newTask.getDescription()).isEqualTo(description);
    }

    @ParameterizedTest
    @MethodSource("invalidTasks")
    public void shouldNotCreateGivenInvalidInput(Executable newTaskToExecute) {
        assertThrows(ConstraintViolationException.class, newTaskToExecute);
    }

    static Stream<Executable> invalidTasks() {
        Executable invalidUserIdNull = () -> new NewTaskCommand(null, "new task description").newTask();
        Executable invalidUserIdEmpty = () -> new NewTaskCommand("", "new task description").newTask();
        Executable invalidUserIdSpaces = () -> new NewTaskCommand("  ", "new task description").newTask();
        Executable invalidDescriptionNull = () -> new NewTaskCommand("userId", null).newTask();
        Executable invalidDescriptionEmpty = () -> new NewTaskCommand("userId", "").newTask();
        Executable invalidDescriptionSpaces = () -> new NewTaskCommand("userId", "   ").newTask();

        Faker faker = new Faker();
        Executable invalidDescriptionMaximum = () -> new NewTaskCommand("userId", faker.lorem().characters(151)).newTask();

        return Stream.of(
                invalidUserIdNull,
                invalidUserIdEmpty,
                invalidUserIdSpaces,
                invalidDescriptionNull,
                invalidDescriptionEmpty,
                invalidDescriptionSpaces,
                invalidDescriptionMaximum
        );
    }
}