package com.newland.ops.del.cdr.opsdelcdr.bean;

/**
 * @author ${linfengpeng}
 * @Title: Constant
 * @ProjectName TempProject
 * @Description: TODO
 * @date 2020/11/2 18:12
 */
public class    Constant
{
    public final static String sqlO = "delete from query_cdr_otheraccount where rowkey=?";
    public final static String sqlP = "delete from query_cdr_payuser where rowkey=?";

    public final static String sqlC = "delete from query_cdr_cycle where rowkey=?";
    public final static String sqlOA = "truncate table query_cdr_otheraccount";
    public final static String sqlPA = "truncate table query_cdr_payuser";
    public final static String sqlRA = "truncate table query_cdr_rated";
    public final static String sqlCA = "truncate table query_cdr_cycle";

    public final static String sqlQO = "select ROWKEY from QUERY_CDR_OTHERACCOUNT  where OPERATION_ID=? and rownum <=500";
    public final static String sqlQP = "select ROWKEY from QUERY_CDR_PAYUSER where  OPERATION_ID=? and rownum <=500";
    public final static String sqlQC = "select ROWKEY from QUERY_CDR_CYCLE where OPERATION_ID=? and rownum <=500";


    public final static String sqlCount ="select count(*) as count from query_cdr_rated";
    public final static String sqlR = "delete from query_cdr_rated  where rowkey=?";
    public final static String sqlQR = "select ROWKEY from QUERY_CDR_RATED where OPERATION_ID=? and rownum<=5000";


}
