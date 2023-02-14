package todolist.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import todolist.todo.entity.Todo;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findByTitle(String title);
    @Query(value = "SELECT t FROM Todo t WHERE t.todoId = :todoId")
    Optional<Todo> findByTodo(long todoId);
}
