package com.example.demo.controller;

import com.example.demo.entity.People;
import com.example.demo.mapper.PeopleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
public class HelloController {

    @Autowired
    PeopleMapper peopleMapper;

    @RequestMapping("/hello")
    public String index(String name) {
        log.info("hello name:"+name);
        return "Hello World";
    }

    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    public String hello1(Integer id) {
        return peopleMapper.selectByPrimaryKey(id).toString();
    }

    @RequestMapping(value="/insert", method= RequestMethod.POST)
    public int insert(@ModelAttribute People people){
        log.info("=======people.gender:"+people.getGender());
        return peopleMapper.insert(people);
    }

    @RequestMapping(value = "/peopleList", method = RequestMethod.GET)
    public List<People> peopleList(){
        return peopleMapper.selectAll();
    }

    @RequestMapping(value = "/getPeopleByID", method = RequestMethod.GET)
    public People hello2(Integer id) {
        return peopleMapper.selectByPrimaryKey(id);
    }
}
