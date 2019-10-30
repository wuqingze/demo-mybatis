package com.example.demo.controller;

import com.example.demo.entity.People;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Wu Qingze
 * Created by IntelliJ IDEA.
 * User: Wu Qingze
 * Date: 2019/10/30
 * Time: 11:07
 */
@RestController
@Slf4j
public class RedisController {

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("/redis/peopleList")
    public List<People> index(String name) {
        return (List<People>)redisTemplate.opsForValue().get("peopleList");
    }

    @RequestMapping(value="/redis/insert", method= RequestMethod.POST)
    public void insert(@ModelAttribute People people){
        log.info("=======people:"+people);
        redisTemplate.opsForList().rightPush("peopleList", people);
    }

    @RequestMapping(value = "/redis/insertPeopleList", method = RequestMethod.POST)
    public void insertPeopleList(@RequestBody List<People> peopleList){
        log.info(peopleList.toString());
        redisTemplate.opsForValue().set("peopleList", peopleList);
    }
}
