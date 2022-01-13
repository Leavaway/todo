package com.todo.pojo;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.Cookie;
import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task implements Serializable{
    private int TaskId;
    private int UsrId;
    private int IsImportant=0;
    private int IsComplete=0;
    private int IsDaily=0;
    private Date AlertTime=null;
    private String Task;

}
