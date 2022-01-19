package com.todo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{
    private int UsrId;
    private String UsrName;
    private String UsrPwd;
    private String UsrEmail;
    private List<Task> tasks;
    private Date RegisterDate;
}
