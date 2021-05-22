package com.newland.boss.export.cdr.bean;

import java.util.HashMap;

/**
 * @author ${linfengpeng}
 * @Title: StatusConstant
 * @ProjectName TempProject
 * @Description: 常量
 * @date 2020/8/10 9:50
 */
public  class StatusConstant {
    public final static int QUERY_STATUS = 1;
    public final static int PUT_ORACLE_STATUS = 2;
    public final static int END_STATUS = 9;
    public final static int FAiLURE_STATUS = 8;

    //
    public final static int OPERATION_EXPORT_TYPE_FIX = 3;

    //
    public final static String INFO_HOME_CITY = "info:home_city";
    public final static String INFO_DETAIL = "info:detail";
    public final static String TYPE = "type";
    public final static int INIT_COUNT = 0;
    //
    public static final String DATA_CLASS_O = "o";
    public static final String DATA_CLASS_P = "p";
    public static final String DATA_CLASS_C = "c";
    public static final String DATA_CLASS_R = "r";
    //
    public static final String SEPARARTOR = "|";
    public final static int LENGTH = 136;
    public static final String NEWLINE_SEPARATOR = "\n";
    public static final String ROW_KEY = "rowKey";
    //
    public static final int MAX_COMMIT = 500;
    public static final String UPLOAD_PATH = "/bossapp1/web/webapp/BossWeb.war/";
    public static final String LINE="/";
    public static HashMap<String,String> sqlMap = new HashMap<>();
    static {
     //   String rateCdrSql = "INSERT INTO QUERY_CDR_RATED (ROWKEY,R_HOME_CITY,R_RATEDCDR_SEQ,R_GUIDING_TIME,R_IMSI,R_CALLED_NUMBER,R_END_TIME,R_TRUNK_GROUPOUT,R_STOP_FLAG,R_HOME_CODE,R_OTHER_VISIT_CODE,R_USER_TYPE,R_PREFIX_TYPE,R_TERM_TYPE,R_LFEE_2,R_LDC_TYPE,R_CONSOLIDATION_RESULT,R_SOURCE_TYPE,R_FILE_NAME,R_USER_ID,R_HOME_COUNTY,R_USER_BRAND_ID,R_OTHER_USER_ID,R_OTHER_HOME_COUNTY,R_OTHER_BRAND_ID,R_FIRST_RATING_TIME,R_BILL_SUMMING_TIME,R_DETAIL_BILL_ID,R_MON_DAY,R_DEAL_IDS,R_NEW_BRAND_ID,R_PROD_IDS,R_MULTIMEDIA_TYPE,R_TEAM_ID,R_TEAM_TYPE,R_PARTIAL_ID,R_CALL_REFERENCE,R_CDR_ID,R_SESSION_ID,R_EXTEND_FIELD_1,R_EXTEND_FIELD_2,R_EXTEND_FIELD_3,R_EXTEND_FIELD_4,R_CALL_TYPE,R_MSISDN,R_OTHER_NUMBER,R_FWDCALL_NUMBER,R_START_TIME,R_FEE_TYPE,R_ROAM_TYPE,R_CELL_ID,R_CALL_DURATION,R_DATA_DOWN,R_DATA_UP,R_FEE,R_CFEE,R_CFEE_ADD,R_LFEE,R_LFEE_ADD,R_VISIT_CODE,R_OTHER_LAC,R_GUILD_OTHER_NUMBER,R_CDR_TYPE,R_LAC,R_OTHER_HOME_CODE,R_MSRN,R_MSC,R_ORIGINAL_SN,R_SYSTEM_TYPE,R_SERVICE_CODE,R_CYCLE_SUB_SEQ,R_ORIGINAL_CFEE,R_ORIGINAL_LFEE,R_ORIGINAL_FEE_ADD,R_COMMUNICATION_TYPE,R_USAGE_MODE,R_COST_TYPE,R_INFO_TYPE,R_RATE_TYPE_1,R_SUBTOTAL_BILL_ITEM_1,R_DEAL_ID_1,R_SERVICE_CODE_1,R_FEE_1,R_RATE_TYPE_2,R_SUBTOTAL_BILL_ITEM_2,R_DEAL_ID_2,R_SERVICE_CODE_2,R_FEE_2,R_RATE_TYPE_3,R_SUBTOTAL_BILL_ITEM_3,R_DEAL_ID_3,R_SERVICE_CODE_3,R_FEE_3,R_RATE_TYPE_4,R_SUBTOTAL_BILL_ITEM_4,R_DEAL_ID_4,R_SERVICE_CODE_4,R_FEE_4,R_EXTEND_RATED_ITEM,R_SECOND_RATING_TIME,R_SERVICE_TYPE,R_TRUNK_GROUPIN,R_OTHER_CELL_ID,R_HOT_FLAG,R_INVOICE_BILLITEM_1,R_SUB_INV_BILLITEM_1,R_SUM_BILLITEM_1,R_DETAIL_BILLITEM_1,R_SUB_INV_NAME_1,R_INVOICE_BILLITEM_2,R_SUB_INV_BILLITEM_2,R_SUM_BILLITEM_2,R_DETAIL_BILLITEM_2,R_SUB_INV_NAME_2,R_INVOICE_BILLITEM_3,R_SUB_INV_BILLITEM_3,R_SUM_BILLITEM_3,R_DETAIL_BILLITEM_3,R_SUB_INV_NAME_3,R_INVOICE_BILLITEM_4,R_SUB_INV_BILLITEM_4,R_SUM_BILLITEM_4,R_DETAIL_BILLITEM_4,R_SUB_INV_NAME_4,R_DEAL_NAME_1,R_DEAL_NAME_2,R_DEAL_NAME_3,R_DEAL_NAME_4,R_CELL_NAME,R_SP_SHORT_NAME,R_BIZ_NAME,R_BIZ_DESC,R_SUBTOTAL_BILL_ITEM_NAME_1,R_SUBTOTAL_BILL_ITEM_NAME_2,R_SUBTOTAL_BILL_ITEM_NAME_3,R_SUBTOTAL_BILL_ITEM_NAME_4,OPERATION_ID,OPERATOR_ID,EXPORT_TIME) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
        String rateCdrSql = "INSERT INTO QUERY_CDR_RATED (ROWKEY,R_HOME_CITY,R_RATEDCDR_SEQ,R_GUIDING_TIME,R_IMSI,R_CALLED_NUMBER,R_END_TIME,R_TRUNK_GROUPOUT,R_STOP_FLAG,R_HOME_CODE,R_OTHER_VISIT_CODE,R_USER_TYPE,R_PREFIX_TYPE,R_TERM_TYPE,R_LFEE_2,R_LDC_TYPE,R_CONSOLIDATION_RESULT,R_SOURCE_TYPE,R_FILE_NAME,R_USER_ID,R_HOME_COUNTY,R_USER_BRAND_ID,R_OTHER_USER_ID,R_OTHER_HOME_COUNTY,R_OTHER_BRAND_ID,R_FIRST_RATING_TIME,R_BILL_SUMMING_TIME,R_DETAIL_BILL_ID,R_MON_DAY,R_DEAL_IDS,R_NEW_BRAND_ID,R_PROD_IDS,R_MULTIMEDIA_TYPE,R_TEAM_ID,R_TEAM_TYPE,R_PARTIAL_ID,R_CALL_REFERENCE,R_CDR_ID,R_SESSION_ID,R_EXTEND_FIELD_1,R_EXTEND_FIELD_2,R_EXTEND_FIELD_3,R_EXTEND_FIELD_4,R_CALL_TYPE,R_MSISDN,R_OTHER_NUMBER,R_FWDCALL_NUMBER,R_START_TIME,R_FEE_TYPE,R_ROAM_TYPE,R_CELL_ID,R_CALL_DURATION,R_DATA_DOWN,R_DATA_UP,R_FEE,R_CFEE,R_CFEE_ADD,R_LFEE,R_LFEE_ADD,R_VISIT_CODE,R_OTHER_LAC,R_GUILD_OTHER_NUMBER,R_CDR_TYPE,R_LAC,R_OTHER_HOME_CODE,R_MSRN,R_MSC,R_ORIGINAL_SN,R_SYSTEM_TYPE,R_SERVICE_CODE,R_CYCLE_SUB_SEQ,R_ORIGINAL_CFEE,R_ORIGINAL_LFEE,R_ORIGINAL_FEE_ADD,R_COMMUNICATION_TYPE,R_USAGE_MODE,R_COST_TYPE,R_INFO_TYPE,R_RATE_TYPE_1,R_SUBTOTAL_BILL_ITEM_1,R_DEAL_ID_1,R_SERVICE_CODE_1,R_FEE_1,R_RATE_TYPE_2,R_SUBTOTAL_BILL_ITEM_2,R_DEAL_ID_2,R_SERVICE_CODE_2,R_FEE_2,R_RATE_TYPE_3,R_SUBTOTAL_BILL_ITEM_3,R_DEAL_ID_3,R_SERVICE_CODE_3,R_FEE_3,R_RATE_TYPE_4,R_SUBTOTAL_BILL_ITEM_4,R_DEAL_ID_4,R_SERVICE_CODE_4,R_FEE_4,R_EXTEND_RATED_ITEM,R_SECOND_RATING_TIME,R_SERVICE_TYPE,R_TRUNK_GROUPIN,R_OTHER_CELL_ID,R_HOT_FLAG,R_INVOICE_BILLITEM_1,R_SUB_INV_BILLITEM_1,R_SUM_BILLITEM_1,R_DETAIL_BILLITEM_1,R_SUB_INV_NAME_1,R_INVOICE_BILLITEM_2,R_SUB_INV_BILLITEM_2,R_SUM_BILLITEM_2,R_DETAIL_BILLITEM_2,R_SUB_INV_NAME_2,R_INVOICE_BILLITEM_3,R_SUB_INV_BILLITEM_3,R_SUM_BILLITEM_3,R_DETAIL_BILLITEM_3,R_SUB_INV_NAME_3,R_INVOICE_BILLITEM_4,R_SUB_INV_BILLITEM_4,R_SUM_BILLITEM_4,R_DETAIL_BILLITEM_4,R_SUB_INV_NAME_4,R_DEAL_NAME_1,R_DEAL_NAME_2,R_DEAL_NAME_3,R_DEAL_NAME_4,R_CELL_NAME,R_SP_SHORT_NAME,R_BIZ_NAME,R_BIZ_DESC,R_SUBTOTAL_BILL_ITEM_NAME_1,R_SUBTOTAL_BILL_ITEM_NAME_2,R_SUBTOTAL_BILL_ITEM_NAME_3,R_SUBTOTAL_BILL_ITEM_NAME_4,OPERATION_ID,OPERATOR_ID,EXPORT_TIME) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
        String cycleSql = "INSERT INTO QUERY_CDR_CYCLE (ROWKEY, HOME_CITY, C_PRICE,C_FEE,C_TYPE,C_SUBTOTAL_BILLITEM,C_DEAL_ID,C_RATE_TYPE,C_START_TIME,C_SUB_RATE_TYPE,C_SERVICE_START_TIME,C_SERVICE_END_TIME,C_ORIGINAL_PRICE,C_SUBSCRIPTION_ID,C_INVOICE_BILLITEM,C_SUB_INV_BILLITEM,C_SUM_BILLITEM,C_DETAIL_BILLITEM,C_SUB_INV_NAME,C_DEAL_NAME,OPERATION_ID,OPERATOR_ID,EXPORT_TIME) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
        String paySql = "INSERT INTO QUERY_CDR_PAYUSER (ROWKEY, HOME_CITY, P_OTHER_HOME_CITY,P_OTHER_USER_ID,P_USER_ID,P_SUBTOTAL_BILLITEM,P_PAY_PRODUCT_ID,P_FEE,P_START_TIME,P_CDR_TYPE,P_PAY_TYPE,P_MSISDN,P_INVOICE_BILLITEM,P_SUB_INV_BILLITEM,P_SUM_BILLITEM,P_DETAIL_BILLITEM,P_SUB_INV_NAME,OPERATION_ID,OPERATOR_ID,EXPORT_TIME) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
        String otherSql = "INSERT INTO QUERY_CDR_OTHERACCOUNT (ROWKEY, HOME_CITY, O_AMOUNT,O_DEDUCT_TIME,O_FEE_TYPE_DESC,O_BILL_TYPE,O_SUBTOTALBILL_ITEM,O_INVOICE_BILLITEM,O_SUB_INV_BILLITEM,O_SUM_BILLITEM,O_DETAIL_BILLITEM,O_SUB_INV_NAME,OPERATION_ID,OPERATOR_ID,EXPORT_TIME) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";

//       String rateCdrSql = "INSERT INTO QUERY_CDR_RATED (rowkey,r_home_city,r_ratedcdr_seq,r_guiding_time,r_imsi,r_called_number,r_end_time,r_trunk_groupout,r_stop_flag,r_home_code,r_other_visit_code,r_user_type,r_prefix_type,r_term_type,r_lfee_2,r_ldc_type,r_consolidation_result,r_source_type,r_file_name,r_user_id,r_home_county,r_user_brand_id,r_other_user_id,r_other_home_county,r_other_brand_id,r_first_rating_time,r_bill_summing_time,r_detail_bill_id,r_mon_day,r_deal_ids,r_new_brand_id,r_prod_ids,r_multimedia_type,r_team_id,r_team_type,r_partial_id,r_call_reference,r_cdr_id,r_session_id,r_extend_field_1,r_extend_field_2,r_extend_field_3,r_extend_field_4,r_call_type,r_msisdn,r_other_number,r_fwdcall_number,r_start_time,r_fee_type,r_roam_type,r_cell_id,r_call_duration,r_data_down,r_data_up,r_fee,r_cfee,r_cfee_add,r_lfee,r_lfee_add,r_visit_code,r_other_lac,r_guild_other_number,r_cdr_type,r_lac,r_other_home_code,r_msrn,r_msc,r_original_sn,r_system_type,r_service_code,r_cycle_sub_seq,r_original_cfee,r_original_lfee,r_original_fee_add,r_communication_type,r_usage_mode,r_cost_type,r_info_type,r_rate_type_1,r_subtotal_bill_item_1,r_deal_id_1,r_service_code_1,r_fee_1,r_rate_type_2,r_subtotal_bill_item_2,r_deal_id_2,r_service_code_2,r_fee_2,r_rate_type_3,r_subtotal_bill_item_3,r_deal_id_3,r_service_code_3,r_fee_3,r_rate_type_4,r_subtotal_bill_item_4,r_deal_id_4,r_service_code_4,r_fee_4,r_extend_rated_item,r_second_rating_time,r_service_type,r_trunk_groupin,r_other_cell_id,r_hot_flag,r_invoice_billitem_1,r_sub_inv_billitem_1,r_sum_billitem_1,r_detail_billitem_1,r_sub_inv_name_1,r_invoice_billitem_2,r_sub_inv_billitem_2,r_sum_billitem_2,r_detail_billitem_2,r_sub_inv_name_2,r_invoice_billitem_3,r_sub_inv_billitem_3,r_sum_billitem_3,r_detail_billitem_3,r_sub_inv_name_3,r_invoice_billitem_4,r_sub_inv_billitem_4,r_sum_billitem_4,r_detail_billitem_4,r_sub_inv_name_4,r_deal_name_1,r_deal_name_2,r_deal_name_3,r_deal_name_4,r_cell_name,r_sp_short_name,r_biz_name,r_biz_desc,r_subtotal_bill_item_name_1,r_subtotal_bill_item_name_2,r_subtotal_bill_item_name_3,r_subtotal_bill_item_name_4,operation_id,operator_id,export_time) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
//       String cycleSql = "INSERT INTO QUERY_CDR_CYCLE (rowkey, home_city, c_price,c_fee,c_type,c_subtotal_billitem,c_deal_id,c_rate_type,c_start_time,c_sub_rate_type,c_service_start_time,c_service_end_time,c_original_price,c_subscription_id,c_invoice_billitem,c_sub_inv_billitem,c_sum_billitem,c_detail_billitem,c_sub_inv_name,c_deal_name,operation_id,operator_id,export_time) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
//       String paySql = "INSERT INTO QUERY_CDR_PAYUSER (rowkey, home_city, p_other_home_city,p_other_user_id,p_user_id,p_subtotal_billitem,p_pay_product_id,p_fee,p_start_time,p_cdr_type,p_pay_type,p_msisdn,p_invoice_billitem,p_sub_inv_billitem,p_sum_billitem,p_detail_billitem,p_sub_inv_name,operation_id,operator_id,export_time) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
//       String otherSql = "INSERT INTO QUERY_CDR_OTHERACCOUNT (rowkey, home_city, o_amount,o_deduct_time,o_fee_type_desc,o_bill_type,o_subtotalbill_item,o_invoice_billitem,o_sub_inv_billitem,o_sum_billitem,o_detail_billitem,o_sub_inv_name,operation_id,operator_id,export_time) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
        sqlMap.put(StatusConstant.DATA_CLASS_R,rateCdrSql);
        sqlMap.put(StatusConstant.DATA_CLASS_C,cycleSql);
        sqlMap.put(StatusConstant.DATA_CLASS_P,paySql);
        sqlMap.put(StatusConstant.DATA_CLASS_O,otherSql);

    }
}
