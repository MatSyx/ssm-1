package com.syx.web.controller;

import com.syx.domain.Score;
import com.syx.domain.ScoreCriteria;
import com.syx.domain.ScoreCriteria.Criteria;
import com.syx.mapper.ScoreMapper;
import com.syx.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by shiyongxiang on 16/9/22.
 */
@RestController
public class SiteController {

    @Autowired
    ScoreMapper scoreMapper;

    @GetMapping(value = "hello",produces = {"application/xml;charset=UTF-8"})
    @ResponseBody
    public User hello() {
        return new User(1, "syx");
    }

    @RequestMapping(value = "add")
    public void add(User user) {
        System.out.print(user);
        System.out.print("时永祥");
    }

    @RequestMapping(value = "all",produces = {"application/json;charset=UTF-8"})
    public List<Score> all(){
        ScoreCriteria example=new ScoreCriteria();
        example.setOffset(0);
        example.setLimit(5);
        return scoreMapper.selectByExample(example);
    }
}
