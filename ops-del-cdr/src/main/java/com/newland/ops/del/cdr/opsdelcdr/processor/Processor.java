package com.newland.ops.del.cdr.opsdelcdr.processor;

import com.newland.ops.del.cdr.opsdelcdr.service.serviceImpl.cdrServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * @author ${linfengpeng}
 * @Title: processor
 * @ProjectName TempProject
 * @Description: TODO
 * @date 2020/11/2 16:22
 */
@Component
public class Processor{


    @Autowired
    private cdrServiceImpl impl;


    public void process(String operationId){
        try {
            impl.delCdr(operationId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
