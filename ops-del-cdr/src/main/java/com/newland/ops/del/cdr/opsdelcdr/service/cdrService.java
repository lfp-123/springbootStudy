package com.newland.ops.del.cdr.opsdelcdr.service;

import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * @author ${linfengpeng}
 * @Title: cdrService
 * @ProjectName TempProject
 * @Description: TODO
 * @date 2020/11/2 16:29
 */
@Service
public interface cdrService {

    void delCdr(String operationId) throws SQLException;
}
