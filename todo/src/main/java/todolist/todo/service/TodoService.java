package todolist.todo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todolist.exception.BusinessLogicException;
import todolist.exception.ExceptionCode;
import todolist.todo.entity.Todo;
import todolist.todo.repository.TodoRepository;

import java.util.Optional;
import java.util.OptionalInt;

@Transactional
@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todo){

        return todoRepository.save(todo);
    }

    public Todo updateTodo(Todo todo){
        Todo findTodo = findVerifiedTodo(todo.getTodoId());
        Optional.ofNullable(todo.getTodo_order())
                .ifPresent(todo_Order -> findTodo.setTodo_order(todo_Order));
        Optional.ofNullable(todo.getCompleted())
                .ifPresent(Completed -> findTodo.setCompleted(Completed));
        Optional.ofNullable(todo.getTitle())
                .ifPresent(title -> findTodo.setTitle(title));
        return todoRepository.save(findTodo);
    }
    public Todo findTodo(long todoId){
        return findVerifiedTodo(todoId);
    }
    public Page<Todo> findTodos(int page, int size){
        return todoRepository.findAll(PageRequest.of(page, size,
                Sort.by("todoId").descending()));
    }
    public void deleteTodo(long todoId){
        Todo todo = findVerifiedTodo(todoId);
        todoRepository.delete(todo);
    }


    private Todo findVerifiedTodo(long todoId){
        Optional<Todo> optionalTodo = todoRepository.findByTodo(todoId);
        Todo findTodo =
                optionalTodo.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.TODO_NOT_FOUND));
        return findTodo;
    }

}
