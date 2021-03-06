package com.newland.boss.export.cdr.bean;



import com.newland.boss.export.cdr.util.Tool;

import java.util.List;

public class Constants {

	public final static String LOGIN_USER_ID = "ngjf_web.login_user_id";
	public final static String VERIFICATION_CODE = "ngjf_web.verification_code";
	public final static String SYSFUNC_ID_KEY = "sysfuncid";

	public final static String o_detail_field = "o_amount|o_deduct_time|o_fee_type_desc|o_bill_type|o_subtotalbill_item|o_invoice_billitem|o_sub_inv_billitem|o_sum_billitem|o_detail_billitem|o_sub_inv_name|batch_id";
	public final static String p_detail_field = "p_other_home_city|p_other_user_id|p_user_id|p_subtotal_billitem|p_pay_product_id|p_fee|p_start_time|p_cdr_type|p_pay_type|p_msisdn|p_invoice_billitem|p_sub_inv_billitem|p_sum_billitem|p_detail_billitem|p_sub_inv_name|batch_id";
	public final static String c_detail_field = "c_price|c_fee|c_type|c_subtotal_billitem|c_deal_id|c_rate_type|c_start_time|c_sub_rate_type|c_service_start_time|c_service_end_time|c_original_price|c_subscription_id|c_invoice_billitem|c_sub_inv_billitem|c_sum_billitem|c_detail_billitem|c_sub_inv_name|c_deal_name|batch_id";
	public final static String r_detail_field = "r_call_type|r_msisdn|r_other_number|r_fwdcall_number|r_start_time|r_fee_type|r_roam_type|r_cell_id|r_call_duration|r_data_down|r_data_up|r_fee|r_cfee|r_cfee_add|r_lfee|r_lfee_add|r_visit_code|r_other_lac|r_guild_other_number|r_cdr_type|r_lac|r_other_home_code|r_msrn|r_msc|r_original_sn|r_system_type|r_service_code|r_cycle_sub_seq|r_original_cfee|r_original_lfee|r_original_fee_add|r_communication_type|r_usage_mode|r_cost_type|r_info_type|r_rate_type_1|r_subtotal_bill_item_1|r_deal_id_1|r_service_code_1|r_fee_1|r_rate_type_2|r_subtotal_bill_item_2|r_deal_id_2|r_service_code_2|r_fee_2|r_rate_type_3|r_subtotal_bill_item_3|r_deal_id_3|r_service_code_3|r_fee_3|r_rate_type_4|r_subtotal_bill_item_4|r_deal_id_4|r_service_code_4|r_fee_4|r_extend_rated_item|r_second_rating_time|r_service_type|r_trunk_groupin|r_other_cell_id|r_hot_flag|r_invoice_billitem_1|r_sub_inv_billitem_1|r_sum_billitem_1|r_detail_billitem_1|r_sub_inv_name_1|r_invoice_billitem_2|r_sub_inv_billitem_2|r_sum_billitem_2|r_detail_billitem_2|r_sub_inv_name_2|r_invoice_billitem_3|r_sub_inv_billitem_3|r_sum_billitem_3|r_detail_billitem_3|r_sub_inv_name_3|r_invoice_billitem_4|r_sub_inv_billitem_4|r_sum_billitem_4|r_detail_billitem_4|r_sub_inv_name_4|r_deal_name_1|r_deal_name_2|r_deal_name_3|r_deal_name_4|r_cell_name|r_sp_short_name|r_biz_name|r_biz_desc|r_subtotal_bill_item_name_1|r_subtotal_bill_item_name_2|r_subtotal_bill_item_name_3|r_subtotal_bill_item_name_4";
	public final static String r_other_filed = "r_home_city|r_ratedcdr_seq|r_guiding_time|r_imsi|r_called_number|r_end_time|r_trunk_groupout|r_stop_flag|r_home_code|r_other_visit_code|r_user_type|r_prefix_type|r_term_type|r_lfee_2|r_ldc_type|r_consolidation_result|r_source_type|r_file_name|r_user_id|r_home_county|r_user_brand_id|r_other_user_id|r_other_home_county|r_other_brand_id|r_first_rating_time|r_bill_summing_time|r_detail_bill_id|r_mon_day|r_deal_ids|r_new_brand_id|r_prod_ids|r_multimedia_type|r_team_id|r_team_type|r_partial_id|r_call_reference|r_cdr_id|r_session_id| r_extend_field_1| r_extend_field_2| r_extend_field_3| r_extend_field_4|batch_id";

	public final static String o_detail_field_import = "o_amount|o_deduct_time|o_fee_type_desc|o_bill_type|o_subtotalbill_item|o_invoice_billitem|o_sub_inv_billitem|o_sum_billitem|o_detail_billitem|o_sub_inv_name";
	public final static String p_detail_field_import = "p_other_home_city|p_other_user_id|p_user_id|p_subtotal_billitem|p_pay_product_id|p_fee|p_start_time|p_cdr_type|p_pay_type|p_msisdn|p_invoice_billitem|p_sub_inv_billitem|p_sum_billitem|p_detail_billitem|p_sub_inv_name";
	public final static String c_detail_field_import = "c_price|c_fee|c_type|c_subtotal_billitem|c_deal_id|c_rate_type|c_start_time|c_sub_rate_type|c_service_start_time|c_service_end_time|c_original_price|c_subscription_id|c_invoice_billitem|c_sub_inv_billitem|c_sum_billitem|c_detail_billitem|c_sub_inv_name|c_deal_name";
;
	public final static String r_detail_field_import = "r_call_type|r_msisdn|r_other_number|r_fwdcall_number|r_start_time|r_fee_type|r_roam_type|r_cell_id|r_call_duration|r_data_down|r_data_up|r_fee|r_cfee|r_cfee_add|r_lfee|r_lfee_add|r_visit_code|r_other_lac|r_guild_other_number|r_cdr_type|r_lac|r_other_home_code|r_msrn|r_msc|r_original_sn|r_system_type|r_service_code|r_cycle_sub_seq|r_original_cfee|r_original_lfee|r_original_fee_add|r_communication_type|r_usage_mode|r_cost_type|r_info_type|r_rate_type_1|r_subtotal_bill_item_1|r_deal_id_1|r_service_code_1|r_fee_1|r_rate_type_2|r_subtotal_bill_item_2|r_deal_id_2|r_service_code_2|r_fee_2|r_rate_type_3|r_subtotal_bill_item_3|r_deal_id_3|r_service_code_3|r_fee_3|r_rate_type_4|r_subtotal_bill_item_4|r_deal_id_4|r_service_code_4|r_fee_4|r_extend_rated_item|r_second_rating_time|r_service_type|r_trunk_groupin|r_other_cell_id|r_hot_flag|r_invoice_billitem_1|r_sub_inv_billitem_1|r_sum_billitem_1|r_detail_billitem_1|r_sub_inv_name_1|r_invoice_billitem_2|r_sub_inv_billitem_2|r_sum_billitem_2|r_detail_billitem_2|r_sub_inv_name_2|r_invoice_billitem_3|r_sub_inv_billitem_3|r_sum_billitem_3|r_detail_billitem_3|r_sub_inv_name_3|r_invoice_billitem_4|r_sub_inv_billitem_4|r_sum_billitem_4|r_detail_billitem_4|r_sub_inv_name_4|r_deal_name_1|r_deal_name_2|r_deal_name_3|r_deal_name_4|r_cell_name|r_sp_short_name|r_biz_name|r_biz_desc";
	public final static String r_other_field_import = "r_home_city|r_ratedcdr_seq|r_guiding_time|r_imsi|r_called_number|r_end_time|r_trunk_groupout|r_stop_flag|r_home_code|r_other_visit_code|r_user_type|r_prefix_type|r_term_type|r_lfee_2|r_ldc_type|r_consolidation_result|r_source_type|r_file_name|r_user_id|r_home_county|r_user_brand_id|r_other_user_id|r_other_home_county|r_other_brand_id|r_first_rating_time|r_bill_summing_time|r_detail_bill_id|r_mon_day|r_deal_ids|r_new_brand_id|r_prod_ids|r_multimedia_type|r_team_id|r_team_type|r_partial_id|r_call_reference|r_cdr_id|r_session_id|r_extend_field_1|r_extend_field_2|r_extend_field_3|r_extend_field_4|batch_id";
	public final static String separator = "|";
	public final static int iPreSplitNum = 250;

	public static List<String> data_format_o;
	public static List<String> data_format_p;
	public static List<String> data_format_c;
	public static List<String> data_format_r;
	public static List<String> data_format_other_r;

	public static List<String> import_data_format_o;
	public static List<String> import_data_format_p;
	public static List<String> import_data_format_c;
	public static List<String> import_data_format_r;
	public static List<String> import_data_format_r_other;

	static {
		data_format_o = Tool.split(o_detail_field, separator);
		data_format_p = Tool.split(p_detail_field, separator);
		data_format_c = Tool.split(c_detail_field, separator);
		data_format_r = Tool.split(r_detail_field, separator);
		data_format_other_r = Tool.split(r_other_filed, separator);
		import_data_format_o = Tool.split(o_detail_field_import, separator);
		import_data_format_p = Tool.split(p_detail_field_import, separator);
		import_data_format_c = Tool.split(c_detail_field_import, separator);
		import_data_format_r = Tool.split(r_detail_field_import, separator);
		import_data_format_r_other = Tool.split(r_other_field_import, separator);
	}
}
