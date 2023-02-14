package todolist.todo.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class TodoPostDto {
    @NotNull(message = "todo is necessary")
    private String title;
    private int todo_order;
    private boolean completed;
}
