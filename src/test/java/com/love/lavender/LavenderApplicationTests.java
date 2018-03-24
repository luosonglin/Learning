package com.love.lavender;

import com.love.lavender.domain.User;
import com.love.lavender.domain.UserMapper;
import com.love.lavender.service.PersonProperties;
import com.love.lavender.web.HelloController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LavenderApplicationTests {

    private MockMvc mvc;

    @Test
    public void contextLoads() {
    }


    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World!")));
    }


    /**
     * 验证PersonProperties中的属性是否已经根据配置文件加载
     */
    @Autowired
    private PersonProperties personProperties;

    @Test
    public void getValue() throws Exception {
        Assert.assertEquals(personProperties.getName(), "Luo Songlin");
        Assert.assertEquals(personProperties.getDear(), "Liu Qing");
    }

    /**
     * 对该UserController编写测试用例验证正确性
     */
    @Test
    public void testUserController() throws Exception {


        //1. get
        RequestBuilder requestBuilder = get("/users/");
        mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

        //2. post
        mvc.perform(post("/users/").param("id", "0").param("name", "Songlin"))
                .andExpect(content().string(equalTo("success")));

        //3. get
        mvc.perform(get("/users/"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":0,\"name\":\"Songlin\"}]")));

        //4. get
        mvc.perform(get("/users/0"))
                .andExpect(content().string(equalTo("[{\"id\":0,\"name\":\"Songlin\"}]")));
    }

    /**
     * 测试User
     */
    @Autowired
    private UserMapper userMapper;

    @Test
    @Rollback
    public void findByName() throws Exception {
//        userMapper.insert(10, "哈哈");
        User u = userMapper.findByName("丫头");
        Assert.assertEquals(2, u.getId());
    }

}
