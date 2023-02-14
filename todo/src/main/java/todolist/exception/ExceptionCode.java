package todolist.exception;

import lombok.Getter;

public enum ExceptionCode {
    TODO_NOT_FOUND(404, "Todo not found"),
    TODO_EXISTS(409, "Todo exists"),
    CANNOT_CHANGE_TODO(403, "Todo can not change"),
    NOT_IMPLEMENTATION(501, "Not Implementation"),
    INVALID_TODO_STATUS(400, "Invalid todo status");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
