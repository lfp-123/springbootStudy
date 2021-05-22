package com.newland.boss.export.cdr.util;


import java.io.*;
import java.lang.management.ManagementFactory;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


public class StringUtils {
	private static final Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
	
	/**
	 * @Description: 左填充
	 * @param num
	 * @param value
	 * @return
	 * @return String
	 */
	public static String padding(int num, int value) {
		String result = (new Integer(value)).toString();
		while (num > result.length()) {
			result = "0" + result;
		}
		return result;
	}
	
	public static String padding(int len, String value) {
		while( len > value.length()) {
			value = "0"+value;
		}
		return value;
	}
	
	/**
	 * 
	 * @Description: 右填充
	 * @param num
	 * @param value
	 * @return String
	 */
	public static String rpadding(int num, int value) {
		String result = (new Integer(value)).toString();
		while (num > result.length()) {
			result = result+"0";
		}
		return result;
	}

	/**
	 * @Description: 数据分拆,分割字符串
	 * @param sValue
	 * @param separator
	 * @return
	 * @return List<String>
	 */
	public static List<String> split(String sValue, String separator) {
		List<String> dataList = new ArrayList<String>();
		int preIndex = 0;
		boolean bFirstCell = true;
		for (int i = 0; i < sValue.length(); i++) {
			if (separator.equals(String.valueOf(sValue.charAt(i)))) {
				if (preIndex == 0 && bFirstCell) {
					dataList.add(sValue.substring(0, i));
					bFirstCell = false;
				} else {
					dataList.add(sValue.substring(preIndex + 1, i));
				}
				preIndex = i;
			}
		}
		if (preIndex == 0) {
			dataList.add(sValue.substring(0, sValue.length()));
		} else {
			dataList.add(sValue.substring(preIndex + 1, sValue.length()));
		}
		return dataList;
	}

	/**
	 * @Description: 获取字段的位置
	 * @param fieldList
	 * @param fieldName
	 * @return int
	 */
	public static int getFieldPos(List<String> fieldList, String fieldName) {
		int pos = -1;
		if (null != fieldName) {
			for (int i = 0; i < fieldList.size(); i++) {
				if (fieldName.equalsIgnoreCase(fieldList.get(i))) {
					pos = i;
					break;
				}
			}
		}
		return pos;
	}

	/**
	 * @Description: 获取子List数据在父List数据中的位置
	 * @param parentList
	 * @param subList
	 * @return List<Integer>
	 */
	public static List<Integer> getSubPosList(List<String> parentList, List<String> subList) {
		List<Integer> posList = new ArrayList<Integer>();
		String selectField;
		for (int i = 0; i < subList.size(); i++) {
			selectField = subList.get(i);
			posList.add(new Integer(getFieldPos(parentList, selectField)));
		}
		return posList;
	}

	/**
	 * @Description: 获取子数据
	 * @param parentData
	 * @param parentSeparator
	 * @param subSeparator
	 * @param posList
	 * @return String
	 */
	public static String getSubData(String parentData, String parentSeparator, String subSeparator, List<Integer> posList) {
		StringBuffer sbSubData = new StringBuffer();
		List<String> parentDataList = split(parentData, parentSeparator);
		for (int i = 0; i < posList.size(); i++) {
			if (-1 != posList.get(i).intValue()) {
				sbSubData.append(parentDataList.get(posList.get(i).intValue()));
			}
			if (i != posList.size() - 1) {
				sbSubData.append(subSeparator);
			}
		}
		return sbSubData.toString();
	}

	/**
	 * @Description: 获取子数据
	 * @param parentDataList
	 * @param
	 * @param subSeparator
	 * @param posList
	 * @return String
	 */
	public static String getSubData(List<String> parentDataList, String subSeparator, List<Integer> posList) {
		StringBuffer sbSubData = new StringBuffer();
		for (int i = 0; i < posList.size(); i++) {
			if (-1 != posList.get(i).intValue()) {
				sbSubData.append(parentDataList.get(posList.get(i).intValue()));
			}
			if (i != posList.size() - 1) {
				sbSubData.append(subSeparator);
			}
		}
		return sbSubData.toString();
	}



	/**
	 * @Description: 获取日期中的月份
	 * @param date
	 * @return String
	 */
	public static String getMonthOfDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return padding(2, c.get(Calendar.MONTH) + 1);
	}

	/**
	 * @Description: 获取日期中的日
	 * @param date
	 * @return String
	 */
	public static String getDayOfDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return padding(2, c.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * @Description: 获取当前月份
	 * @return String
	 */
	public static String getCurrMonth() {
		return getMonthOfDate(new Date());
	}

	/**
	 * 
	 * @Description: 获取当日
	 * @return String
	 */
	public static String getCurrDay() {
		return getDayOfDate(new Date());
	}
	
	// 增加或减少天数  
    public static Date addDay(Date date, int num) {  
        Calendar startDT = Calendar.getInstance();  
        startDT.setTime(date);  
        startDT.add(Calendar.DAY_OF_MONTH, num);  
        return startDT.getTime();  
    }  

	/**
	 * @Description: 生成默认表名
	 * @param
	 * @return String
	 */
	public static String genTableName(String tableNameRegex) {
		return genTableName(tableNameRegex, getCurrMonth(), getCurrDay());
	}

	/**
	 * @Description: 按月、日生成表名
	 * @param tableNameRegex
	 * @param month
	 * @param day
	 * @return
	 * @return String
	 */
	public static String genTableName(String tableNameRegex, String month, String day) {
		StringBuffer sbTableName = new StringBuffer();
		// CDR_MM_DD
		if (tableNameRegex.endsWith("_MM_DD")) {
			sbTableName.append(tableNameRegex.substring(0, tableNameRegex.indexOf("_MM_DD")));
			sbTableName.append("_");
			sbTableName.append(padding(2, Integer.parseInt(month)));
			sbTableName.append("_");
			sbTableName.append(padding(2, Integer.parseInt(day)));
		}
		// CDR_MM
		else if (tableNameRegex.endsWith("_MM")) {
			sbTableName.append(tableNameRegex.substring(0, tableNameRegex.indexOf("_MM")));
			sbTableName.append("_");
			sbTableName.append(padding(2, Integer.parseInt(month)));
		} else {
			sbTableName.append(tableNameRegex);
		}
		return sbTableName.toString();
	}

	public static String genNullValue(String valueField, String structSeparator, String dataSeparator) {
		StringBuffer sbNullValue = new StringBuffer();
		List<String> list = split(valueField, structSeparator);
		for (int i = 0; i < list.size() - 1; i++) {
			sbNullValue.append(dataSeparator);
		}
		return sbNullValue.toString();
	}

	public static List<String> genNullValueList(String valueField, String structSeparator) {
		List<String> list = new ArrayList<String>();
		List<String> valueFieldList = split(valueField, structSeparator);
		for (int i = 0; i < valueFieldList.size(); i++) {
			list.add("");
		}
		return list;
	}

	public static String getJarPath(Class<?> clazz) {
		String jarPath = "";
		try {
			String path = clazz.getProtectionDomain().getCodeSource().getLocation().getPath();
			String filePath = URLDecoder.decode(path, "UTF-8");
			if (filePath.endsWith(".jar")) { // jar path
				File jarFile = new File(filePath);
				File parent = jarFile.getParentFile();
				if (null != parent) {
					jarPath = parent.getAbsolutePath() + "/";
				}
			} else { // class path
				jarPath = filePath;
			}
		} catch (IOException e) {
			jarPath = "";
		}
		return jarPath;
	}

	public static String getClassPath(Class<?> clazz) {
		String absolutePath = clazz.getResource("").getPath();
		if (absolutePath.startsWith("thread:")) {
			absolutePath = absolutePath.substring(5);
		}
		return absolutePath.substring(0, absolutePath.indexOf("streamsapp"));
	}

	public static String getAppPath(Class<?> clazz) {
		String appPath = "";
		appPath = getJarPath(clazz);
		if ("".equals(appPath)) {
			appPath = getClassPath(clazz);
		} else {
			appPath = appPath + "../";
		}
		return appPath;
	}

	public static int getMonthLastDay(String yearMonth) {
		String str1 = yearMonth.substring(0, 4);
		String str2 = yearMonth.substring(4, yearMonth.length());
		Calendar cal = Calendar.getInstance();
		int i = Integer.parseInt(str1);
		int j = Integer.parseInt(str2);
		cal.set(i, j - 1, 1);
		return cal.getActualMaximum(5);
	}
	
	public static String getCurrYearMonth() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return String.valueOf(cal.get(Calendar.YEAR))+padding(2, (cal.get(Calendar.MONTH)+1));
	}
	
	public static int getCurrMonthLastDay() {
		return getMonthLastDay(getCurrYearMonth());
	}
	
	public static String getNextYearMonth() {
		Calendar cal=Calendar.getInstance(); 
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, 1);
        return String.valueOf(cal.get(Calendar.YEAR))+padding(2, (cal.get(Calendar.MONTH)+1));
	}
	
	public static String List2String(List<String> list, String separator) {
		StringBuffer sbContent = new StringBuffer();
		for(String sValue:list) {
			sbContent.append(sValue);
			sbContent.append(separator);
		}
		return sbContent.substring(0, sbContent.length()-1);
	}
	
	public static byte[] append(byte[] org, byte[] to) {
		byte[] newByte = new byte[org.length + to.length];
		System.arraycopy(org, 0, newByte, 0, org.length);
		System.arraycopy(to, 0, newByte, org.length, to.length);
		return newByte;
	}
	
	public static long parseLong(String s) {
		try {
			return Long.parseLong(s);
		}catch(NumberFormatException e) {
			return 0;
		}
	}
	
	/**
	 * 字符串转为时间
	 * @Description:  
	 * @param datetime yyyyMMddHHmmss
	 * @return Timestamp
	 */
	public static Timestamp string2Timestamp(String datetime) {
		StringBuffer sbDate = new StringBuffer();
		sbDate.append(datetime.substring(0, 4));
		sbDate.append("-");
		sbDate.append(datetime.substring(4, 6));
		sbDate.append("-");
		sbDate.append(datetime.substring(6, 8));
		sbDate.append(" ");
		sbDate.append(datetime.substring(8, 10));
		sbDate.append(":");
		sbDate.append(datetime.substring(10, 12));
		sbDate.append(":");
		sbDate.append(datetime.substring(12, 14));
		return Timestamp.valueOf(sbDate.toString());
	}
	
	/**
	 * 两个时间相差天数
	 * @Description:  
	 * @param beginDate
	 * @param endDate
	 * @return int
	 */
	public static int getDifferDays(Date beginDate, Date endDate) {
		return (int) ((endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000));
	}
	
	/**
	 * 获取下一天，MMDD
	 * @param cal
	 * @return
	 */
	public static String getNextDay(Calendar cal) {
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return  String.valueOf(cal.get(Calendar.MONTH)+1)+  padding(2, cal.get(Calendar.DAY_OF_MONTH));
	}
	
	/**
	 * 获取前一天YYYYMMDD
	 * @return
	 */
	public static String getBeforeDay() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return (new SimpleDateFormat("yyyyMMdd")).format(cal.getTime());
	}
	
	/**
	 * 转换字符型为整型
	 * @param sNumber
	 * @param defaultValue
	 * @return
	 */
	public static int parseInt(String sNumber, int defaultValue) {
		try {
			return Integer.parseInt(sNumber);
		} catch(NumberFormatException e) {
			return defaultValue;
		}
	}
	
	/**
	 * 转换字符型为布儿型
	 * @param sMultiTable
	 * @param defaultBoolean
	 * @return
	 */
	public static boolean parseBoolean(String sMultiTable, boolean defaultBoolean) {
		if( null != sMultiTable && !"".equals(sMultiTable)) {
			return Boolean.parseBoolean(sMultiTable);
		} else {
			return defaultBoolean;
		}
	}
	
	public static String getPid() {
		String name = ManagementFactory.getRuntimeMXBean().getName();
		return name.split("@")[0];
	}
	
	public static boolean notIn(int src, int... aTarget) {
		boolean bNotIn = true;
		for(int target : aTarget ) {
			if( src == target ) {
				bNotIn = false;
				break;
			}
		}
		return bNotIn;
	}
	
	public static boolean in (int src, int... aTarget) {
		return !notIn(src, aTarget);
	}
	
	public static boolean isInteger(String str) {  
        return pattern.matcher(str).matches();  
    }
	
	/**
	 * 左消零
	 * @param origStr
	 * @return
	 */
	public static String deleteLeftZero(String origStr) {
		if( null != origStr) {
			int len = origStr.length();
			for(int i = 0; i < len ;i++ ) {
				if( origStr.charAt(i) != '0') {
					return origStr.substring(i);
				}
			}
		}
		return origStr;
	}
	
	/**
	 * 在8位年月日字符串日期加上时分钟
	 * @param date
	 * @return
	 */
	public static String appendStartHHmmss(String date) {
		if( null != date && date.length() == 8) {
			return date+"000000";
		}
		return date;
	}
	
	/**
	 * 在8位年月日字符串日期加上时分钟
	 * @param date
	 * @return
	 */
	public static String appendStopHHmmss(String date) {
		if( null != date && date.length() == 8) {
			return date+"235959";
		}
		return date;
	}

	/**
	 * 写入错误文件
	 * @param
	 * @return
	 */
	public static void writeWrongFile(String file, String conent) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file, true)));
			out.write(conent);
			out.write("\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 判断文件行数
	 * @param
	 * @return
	 */
	public static long getLineNumber(File file) {
		if (file.exists()) {
			try {
				FileReader fileReader = new FileReader(file);
				LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
				lineNumberReader.skip(Long.MAX_VALUE);
				long lines = lineNumberReader.getLineNumber() + 1;
				fileReader.close();
				lineNumberReader.close();
				return lines;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * 判断文件是否超出行数，超出生成新的文件，并写入错误数据
	 * @param
	 * @return
	 */
	public static void creatNewFile(int MAX,String wrongFilePath,String origRowData){
		for (int i = 1; i < MAX; i++) {
			String filename = wrongFilePath + i ;
			File file1 = new File(filename);
			if (!file1.exists()) {
				try {
					file1.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (getLineNumber(file1) < 20) {
				writeWrongFile(filename, origRowData);
				System.out.println("错误数据写入" + filename + "成功");
				break;
			} else {
				System.out.println(filename + "文件行数大于20");
			}
		}
	}

	/**
	 * 生成RowKey
	 * rowKey =  user_id%250,userId,startTime
	 */
	public static int MO = 250;
	public static String getRowKey(Long userId,Date Second){
		StringBuffer bf = new StringBuffer();
		String userIdMo;
		long userIdm = userId % MO;
		if(userIdm < 100){
			userIdMo = "0"+String.valueOf(userIdm);
		}else {
			userIdMo =String.valueOf(userIdm);
		}
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(Second);
		bf.append(userIdMo);
		bf.append(",");
		bf.append(userId);
		bf.append(",");
		bf.append(time);
		return  bf.toString();
	}

	/**
	 * 获取表名 根据月份
	 * @return
	 */
	public static String getTableName(Date time){
		StringBuffer bf = new StringBuffer();
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		int month = cal.get(Calendar.MONTH);
		month++;
		bf.append("query_cdr_");
		if(month<10){
			bf.append(0);
			bf.append(month);
		}else {
			bf.append(month);
		}
		return bf.toString();
	}



}
