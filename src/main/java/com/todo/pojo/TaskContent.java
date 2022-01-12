package com.todo.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskContent {
    private String Content;
    private Date createTime;
    private Date finishTime = null;
    private Date alertTime = null;
    private boolean isCompleted = false;
}
