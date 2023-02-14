package todolist.todo.dto;

import lombok.Getter;
import todolist.todo.validator.NotSpace;

@Getter
public class TodoPatchDto {
    private long todoId;
    private String title;
    private int todo_order;
    private boolean completed;
    public void setTodoId(long todoId){
        this.todoId = todoId;
    }
}
