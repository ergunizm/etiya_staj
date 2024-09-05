package com.etiya.staj.utility.results;

public class DataResult<T> extends Result{
    private T data;
    public DataResult(boolean s1, T d1 ) {
        super(s1);
        data = d1;
    }
    public DataResult(String message, boolean s1, T d1 ) {
        super(message, s1);
        data = d1;
    }

    public T getData() {
        return data;
    }
}
