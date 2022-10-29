package com.todo;


import com.todo.pojo.Task;
import com.todo.service.TaskService;
import com.todo.service.UserService;
import com.todo.service.impl.TaskServiceImpl;
import com.todo.service.impl.UserServiceImpl;
import com.todo.util.GetSqlSession;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootTest
//@MapperScan("com.todo.*")
class TodoApplicationTests {
    @Autowired
    TaskService taskService;
    @Test
    void contextLoads() {
//        System.out.println(taskService.getTaskId(1));
//        taskService.newTask(2,"Second task");
        List<Task> taskList = taskService.getTasks(1);
        for (Task t:
             taskList) {
            System.out.println(t.getTaskId());
            System.out.println(t.getTask());
            System.out.println("-----");
        }

    }

}
