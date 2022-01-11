package com.todo;


import com.todo.pojo.Task;
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

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootTest
@MapperScan
class TodoApplicationTests {
    @Autowired
    TaskServiceImpl TaskServiceImpl;
    @Autowired
    UserServiceImpl UserServiceImpl;
    @Test
    void contextLoads() {
        SqlSession sqlSession = GetSqlSession.createSqlSession();
//        Task task = TaskServiceImpl.getTasks(1);
//        System.out.println(task.getTask());
    }

}
