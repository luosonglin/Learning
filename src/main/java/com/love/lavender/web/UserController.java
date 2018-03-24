package com.love.lavender.web;

import com.love.lavender.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by luosonglin on 24/03/2018.
 */
@RestController     //创建http请求的对象
@RequestMapping(value = "/users")   //url映射
public class UserController {

    // 创建线程安全的Map
    static Map<Long, User> userMap = Collections.synchronizedMap(new HashMap<Long, User>());


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getUsers() {
        List<User> users = new ArrayList<>(userMap.values());
        return users;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {
        userMap.put(user.getId(), user);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return userMap.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ModelAttribute User user) {
        User u = userMap.get(id);
        u.setName(user.getName());

        userMap.put(id, u);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        userMap.remove(id);
        return "success";
    }

}
