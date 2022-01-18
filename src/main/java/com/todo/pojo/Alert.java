package com.todo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alert implements Serializable {
    int UsrId;
    Timestamp Alert;
    String Task;
    String UsrEmail;
    int TaskId;
}
