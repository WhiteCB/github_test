package todolist.todo.mapper;

import org.mapstruct.Mapper;
import todolist.todo.dto.TodoPatchDto;
import todolist.todo.dto.TodoPostDto;
import todolist.todo.dto.TodoResponseDto;
import todolist.todo.entity.Todo;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    Todo todoPostDtoToTodo(TodoPostDto todoPostDto);
    Todo todoPatchDtoToTodo(TodoPatchDto todoPatchDto);
    TodoResponseDto todoToTodoResponseDto(Todo todo);
    List<TodoResponseDto> todosToTodoResponseDtos(List<Todo> todos);
}
