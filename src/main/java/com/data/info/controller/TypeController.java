package com.data.info.controller;

import com.data.info.dao.OpenDao;
import com.data.info.util.Type2023062201;
import com.data.info.vo.enums.TypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class TypeController extends BaseController{

    @Autowired
    OpenDao openDao;

    @RequestMapping("/get")
    public void getData(){
        String code = request.getParameter("code");
        int start = Integer.parseInt(request.getParameter("start"));
        if(code.equals(TypeEnum.type2023062201.getCode())){
            type2023062201(start);
        }
    }

    public void type2023062201(int start){
        try {
            boolean flag = true;
            int i = start;
            while (flag){
                String rs = Type2023062201.get(i);
                if(rs == null){
                    log.info("进度: {}, 地址: {}", i, "null");
                }else {
                    String str [] = rs.split("\\|");

                    Map<String, Object> map = new HashMap<>();
                    map.put("live_address", str[0]);
                    map.put("title", str[1]);
                    map.put("num", i);
                    map.put("sqlMapId", "sql.insert");
                    openDao.insert(map);

                    log.info("进度: {}, 地址: {}", i, str[0]);
                }

                i++;

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
