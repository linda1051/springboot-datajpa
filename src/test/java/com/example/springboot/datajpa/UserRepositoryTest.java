package com.example.springboot.datajpa;

import com.example.springboot.datajpa.entity.User;
import com.example.springboot.datajpa.repository.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class UserRepositoryTest {
    @Autowired()
    UserRepository userRepository;

    // 打印出class com.sun.proxy.$Proxy66表示spring注入通过jdk动态代理获取接口的子类
    @Test
    public void proxy() throws Exception {
        System.out.println("----1-----------------------------------------proxy:"+userRepository.getClass());
    }

    @Test
    public void save() throws Exception {
        for (int i = 0; i < 10; i++) {
            User user = new User("jege" + i, 25 + i);
            userRepository.save(user);
        }
        System.out.println("--2----------------------------------------------------------------save");
    }

    @Test
    public void all() throws Exception {
        save();
        assertThat(userRepository.findAll()).hasSize(10);
        System.out.println("--3----------------------------------------------------------------all");
    }

    @Test
    public void findByName() throws Exception {
        save();
        assertThat(userRepository.findByNameLike("jege%")).hasSize(10);
        System.out.println("--4----------------------------------------------------------------findByName");
    }

    @After
    public void destroy() throws Exception {
        userRepository.deleteAll();
        System.out.println("--5----------------------------------------------------------------destroy");
    }

}
