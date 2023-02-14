package todolist.todo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Auditable;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Todo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;
    @Column(length = 100, nullable = false)
    private String title;
    @Column
    private Integer todo_order;
    @Column
    private Boolean completed;

}
