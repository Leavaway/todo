package com.todo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyTask {
    private int TaskId;
    private int UsrId;
    private int IsImportant=0;
    private int IsComplete=0;
    private int IsDaily=0;
    private String Task;
}
