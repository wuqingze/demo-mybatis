package com.example.demo;

import com.example.demo.entity.People;
import com.example.demo.mapper.PeopleMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

	@Autowired
	PeopleMapper peopleMapper;
	private static ExecutorService CDCRatingReportDownloadService;
	
	@Test
	void contextLoads() {
		System.out.println(peopleMapper.selectByPrimaryKey(1));
	}

	@Test
	void insertPeopleList(){
		People people = new People();
		Random r = new Random();

		for(int i=0;i<10000;i++){
			people.setAge(r.nextInt(100));
			people.setGender(r.nextInt()%2==0?"男":"女");
			people.setName("lkasjdf");
			log.info("======"+people);
			peopleMapper.insert(people);
		}
	}

	@Test
	void insertPeopleListByThreadPool() throws Exception{
		People people = new People();
		Random r = new Random();
		CDCRatingReportDownloadService = Executors.newFixedThreadPool(5);

		for(int i=0;i<1000;i++){
			CDCRatingReportDownloadService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					people.setAge(r.nextInt(100));
					people.setGender(r.nextInt()%2==0?"男":"女");
					people.setName("lkasjdf");
					log.info("======"+people);
					return peopleMapper.insert(people);
				}
			}).get();
		}
	}
}
