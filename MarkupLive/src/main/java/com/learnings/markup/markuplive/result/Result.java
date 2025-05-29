package com.learnings.markup.markuplive.result;

// This class is a placeholder for the result of an operation.
public class Result<T> {
    private int code; // The status code of the result
    private String message; // A message describing the result
    private T data; // The data returned by the operation

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
