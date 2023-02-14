package todolist.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponseDto {
    private long todoId;
    private String title;
    private int todo_order;
    private boolean completed;
}
