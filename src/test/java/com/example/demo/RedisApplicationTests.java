package com.example.demo;

import com.example.demo.entity.People;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@SpringBootTest
@Slf4j
class RedisApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;
//    @Autowired
//    private RedisTemplate<String, People> peopleRedisTemplate;

    @Test
    public void contextLoads() {
        //这里相当于redis对String类型的set操作
        redisTemplate.opsForValue().set("test","helloworld");
        //这里相当于redis对String类型的get操作
        String test = (String)redisTemplate.opsForValue().get("test");
        System.out.println(test);
    }

//    @Test
//    public void mytest(){
//        log.info("======"+redisTemplate.opsForValue().get);
//    }

    @Test
    public void insertRedisObjet(){
        People people = new People(1, "wuqingze", 22, "男");
        redisTemplate.opsForValue().set("people"+people.getId(), people);

    }

    @Test
    public void insertRedisObjetList(){
        Random r = new Random();
        List<People> peopleList = new LinkedList<>();
        for(int i =0;i<10;i++){
            peopleList.add(new People(i, "wuqingze", r.nextInt(100), r.nextInt(100)%2==0?"男":"女"));
        }
//        redisTemplate.opsForValue().set("people"+people.getId(), people);
        redisTemplate.opsForValue().set("peopleList", peopleList);
    }

    @Test
    public void getRedisObjetList(){
        List<People> peopleList = (List<People>)redisTemplate.opsForValue().get("peopleList");
        log.info(peopleList.toString());
    }

    /**
     * 测试插入1000条数据花费多长时间
     * 目前知道用mysql插入1000条数据，单线程27秒，多线程50秒
     * 结果：花费65毫秒
     */
    @Test
    public void insert100people(){
        Random r = new Random();
        List<People> peopleList = new LinkedList<>();
        for(int i =0;i<1000;i++){
            peopleList.add(new People(i, "wuqingze", r.nextInt(100), r.nextInt(100)%2==0?"男":"女"));
        }
        log.info("=====start insert");
        redisTemplate.opsForValue().set("peopleList", peopleList);
        log.info("=====end insert");
    }

    /**
     * 测试插入10000条数据花费多长时间
     * 目前知道用mysql插入10000条数据，单线程27秒，多线程50秒
     * 结果：花费81毫秒
     */
    @Test
    public void insert1000people(){
        Random r = new Random();
        List<People> peopleList = new LinkedList<>();
        for(int i =0;i<10000;i++){
            peopleList.add(new People(i, "wuqingze", r.nextInt(100), r.nextInt(100)%2==0?"男":"女"));
        }
        log.info("=====start insert");
        redisTemplate.opsForValue().set("peopleList", peopleList);
        log.info("=====end insert");
    }

    /**
     * 测试插入100000条数据花费多长时间
     * 目前知道用mysql插入100000条数据，单线程27秒，多线程50秒
     * 结果：花费161毫秒
     */
    @Test
    public void insert10000people(){
        Random r = new Random();
        List<People> peopleList = new LinkedList<>();
        for(int i =0;i<100000;i++){
            peopleList.add(new People(i, "wuqingze", r.nextInt(100), r.nextInt(100)%2==0?"男":"女"));
        }
        log.info("=====start insert");
        redisTemplate.opsForValue().set("peopleList", peopleList);
        log.info("=====end insert");
    }

    /**
     * list追加数据
     */
    @Test
    public void addRedisObjet(){
        List<People> peopleList = new LinkedList<People>(){};
        peopleList.add(new People(100000, "wuqingze", 22, "男"));
        redisTemplate.opsForList().rightPush("peopleList", peopleList);
//        redisTemplate.opsForValue().set("people"+people.getId(), people);

    }
}
