package todolist.todo.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import todolist.dto.MultiResponseDto;
import todolist.dto.SingleResponseDto;
import todolist.todo.dto.TodoPatchDto;
import todolist.todo.dto.TodoPostDto;
import todolist.todo.entity.Todo;
import todolist.todo.mapper.TodoMapper;
import todolist.todo.service.TodoService;
import todolist.utils.UriCreator;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/")
@Validated
public class TodoController {
    private final static String TODO_DEFAULT_URL = "/";
    private TodoService todoService;
    private TodoMapper mapper;

    public TodoController(TodoService todoService, TodoMapper mapper) {
        this.todoService = todoService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postTodo(@Valid @RequestBody TodoPostDto todoPostDto){
        Todo todo = todoService.createTodo(mapper.todoPostDtoToTodo(todoPostDto));
        URI location = UriCreator.createUri(TODO_DEFAULT_URL, todo.getTodoId());
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{todo-id}")
    public ResponseEntity PatchTodo(@PathVariable("todo-id") long todoId,
                                    @Valid @RequestBody TodoPatchDto todoPatchDto){
        todoPatchDto.setTodoId(todoId);
        Todo todo = todoService.updateTodo(mapper.todoPatchDtoToTodo(todoPatchDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.todoToTodoResponseDto(todo)),
                HttpStatus.OK);
    }

    @GetMapping("/{todo-id}")
    public ResponseEntity getTodo(@PathVariable("todo-id") long todoId){
        Todo todo = todoService.findTodo(todoId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.todoToTodoResponseDto(todo)),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTodos(@Positive @RequestParam int page,
                                   @Positive @RequestParam int size){
        Page<Todo> todoPage = todoService.findTodos(page - 1, size);
        List<Todo> todos = todoPage.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.todosToTodoResponseDtos(todos),
                        todoPage),
                HttpStatus.OK);
    }

    @DeleteMapping("/{todo-id}")
    public ResponseEntity deleteTodo(@PathVariable("todo-id") long todoId){
        todoService.deleteTodo(todoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
