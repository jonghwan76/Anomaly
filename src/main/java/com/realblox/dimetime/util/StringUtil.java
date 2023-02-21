package com.realblox.dimetime.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 공통 문자열 처리 유틸리티 <br>
 * @author wimy
 *
 */
public class StringUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);
	
	private static long dummy = 0l;
	
	public static final Locale locale = Locale.KOREA;
	
	public static final String DEFAULT_DATE_FORMAT = "yyyyMMdd";

	public static final String DEFAULT_HYPHEN_DATE_FORMAT = "yyyy-MM-dd";

	public static final String DEFAULT_DATETIME_FORMAT = "yyyyMMddHHmmss";

	public static final String DEFAULT_HYPHEN_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String DEFAULT_HYPHEN_DATETIME_MILLI_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

	public static final String DEFAULT_FORMAT_HH_MM_SS = "HH:mm:ss";

	public static final String DEFAULT_FORMAT_HH_MM = "HH:mm";
	private Object GenType;

	//16진수 스트링을 바이트 배열로 변환
	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
					+ Character.digit(s.charAt(i+1), 16));
		}
		return data;
	}

	//short형을 바이트 배열로 변환
	public static byte[] shortToByte(short a) {
		byte[] shortToByte = new byte[2];
		shortToByte[0] |= (byte)(a & 0xFF & 0xff);
		shortToByte[1] |= (byte)((a & 0xFF00) >>> 8);
		return shortToByte;
	}

	//바이트 배열을 16진수 스트링으로 변환
	public static String byteArrayToHexString(byte[] bytes){
		StringBuilder sb = new StringBuilder();
		for(byte b : bytes){
			sb.append(String.format("%02X", b&0xff));
		}
		return sb.toString();
	}

	//16진수 스트링을 10진수 스트링으로 변환
	public static String getHexToDec(String hex) {
		long v = Long.parseLong(hex, 16);
		return String.valueOf(v);
	}

	//16진수 스트링을 10진수 스트링으로 변환 : 자리수가 2보다 작으면 앞에 0추가
	public static String getHexToDecZero(String hex) {
		String retStr = "";

		long v = Long.parseLong(hex, 16);
		retStr = String.valueOf(v);

		if(retStr.length() < 2) {
			retStr = "0" + retStr;
		}
		return retStr;
	}

	//Hex 문자열을 10진수 문자열로 바꾸준다.
	public static String convertHexToString(String hex){

		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();

		//49204c6f7665204a617661 split into two characters 49, 20, 4c...
		for( int i=0; i<hex.length()-1; i+=2 ){

			//grab the hex in pairs
			String output = hex.substring(i, (i + 2));
			//convert hex to decimal
			int decimal = Integer.parseInt(output, 16);
			//convert the decimal to character
			sb.append((char)decimal);

			temp.append(decimal);
		}
//		System.out.println("Decimal : " + temp.toString());

		return sb.toString();
	}



	/**
	 * 문자열 빈 값 여부 - null 포함 
	 * @param str
	 * @return true: 입력받은 String 이 빈 문자열 또는 null인 경우
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
	
	/**
	 * 숫자 여부 판단 - 실수 가능
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str.replaceAll("\\,", ""));
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 엑셀 업로드시 오류특수문자 제거 제거
	 *
	 * @param string
	 * @return
	 * @throws SQLException
	 */
	public static String removeExcelChar(String str) {
		str = str.replace("","");
		str = str.replace("\\","");
		str = str.replace("","");
		str = str.replace("\r", "");
		str = str.replace("\n", "");
		str = getString(str).trim();		
		
		return str; 
	}
	
			
	
	/**
	 * 모든 태그 제거
	 *
	 * @param content
	 * @return
	 * @throws SQLException
	 */
	public static String removeTag(String content) {
		Pattern SCRIPTS = Pattern.compile("<(no)?script[^>]*>.*?</(no)?script>", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
		
		Pattern STYLE = Pattern.compile("<style[^>]*>.*</style>", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
		
		content = content.replaceAll("&lt;", "<");
		content = content.replaceAll("&gt;", ">");
		
		Pattern TAGS = Pattern.compile("<(\"[^\"]*\"|\'[^\']*\'|[^\'\">])*>");
		// Pattern nTAGS = Pattern.compile("<\\w+\\s+[^<]*\\s*>");
		Pattern ENTITY_REFS = Pattern.compile("&[^;]+;");
		Pattern WHITESPACE = Pattern.compile("\\s\\s+");
		Pattern ETC = Pattern.compile("nbsp;");
		
		Matcher m;
		
		m = SCRIPTS.matcher(content);
		content = m.replaceAll("");
		
		m = STYLE.matcher(content);
		content = m.replaceAll("");
		
		m = TAGS.matcher(content);
		content = m.replaceAll("");
		m = ENTITY_REFS.matcher(content);
		content = m.replaceAll("");
		m = WHITESPACE.matcher(content);
		content = m.replaceAll(" ");
		
		m = ETC.matcher(content);
		content = m.replaceAll(" ");
		
		return content;
	}

	
	/**
	 * 날짜 형식 문자열 비교
	 * 
	 * @param Object
	 * @return boolean 
	 */
	public static boolean diffOfDate(String begin, String end) throws Exception {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date thisdate = formatter.parse(formatter.format(new Date()));
			
			Date beginDate = formatter.parse(begin);
			Date endDate = formatter.parse(end);
			
			if ((thisdate.equals(beginDate) || thisdate.after(beginDate)) && (thisdate.equals(endDate) || thisdate.before(endDate))) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 파라미터를 String 타입으로 가져옵니다.<br>
	 *  - null일 경우 빈 문자열을 가져옵니다.<br>
	 * Get String(if object is null, return empty string). 
	 * @param Object
	 * @return String
	 */
	public static String getString(Object obj) {
		if (obj == null)
			return "";
		else
			return String.valueOf(obj);
	}
	
	/**
	 * Get int
	 * @param obj
	 * @return
	 */
	public static int getInt(Object obj) {
		String sRtn = getString(obj).trim();
		if (sRtn.equals(""))
			return 0;
		else
			return Integer.parseInt(sRtn);
	}	
	
	/**
	 * escaped html 태그 변경
	 * 
	 * @param i
	 * @return
	 */
	public static String replaceEditorTag(String content) {
		String str = content;
		if (str != null && !str.equals("")) {
			str = str.replaceAll("&amp;", "&");
			str = str.replaceAll("&lt;", "<");
			str = str.replaceAll("&gt;", ">");
			
			str = str.replaceAll("&quot;", "\"");
			str = str.replaceAll("&apos;", "'");
		}
		else {
			str = "";
		}
		return str;
	}
	
	
	/**
	 * 오늘로부터 원하는 기간을 설정하여 Date로 반환
	 * <br><i>문자열로 가져오고 싶은 경우 getDateToString(Date date, String format) 함수에 적용하여 사용하면 됨.</i>
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date getDate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		
		cal.add(Calendar.YEAR, year);
		cal.add(Calendar.MONTH, month); 
		cal.add(Calendar.DATE, day);
		
		return cal.getTime();
	}
	
	/**
	 * yyyy-MM-dd 형식의 문자열을 Date 객체로 가져옵니다.
	 * 
	 * @param input
	 * @return
	 */
	public static Date getDate(String input) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(input);
	}
	
	/**
	 * 특정 날짜 기준에서 특정 값을 더하거나 뺀 java.util.Date 형식의 날짜를 가져옵니다.
	 * @param date 특정 날짜입니다. null일 경우 오늘입니다.
	 * @param calendarField Calendar 상수값
	 * @param amount 더하거나 뺄 값
	 * @return
	 */
	public static Date getDate(Date date, int calendarField, int amount) {
		Calendar cal = Calendar.getInstance();
		if (date == null)
			date = new Date();
			
		cal.setTime(date);
		
		cal.add(calendarField, amount);
		
		return cal.getTime();
	}
	
	/**
	 * 입력된 문자열과 지정한 날짜 포맷으로 날짜 객체를 가져옵니다.
	 * @param dateTime
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static Date getDate(String dateTime, String format) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(dateTime);
	}
	
	/**
	 * 오늘 날짜를 형식에 맞는 문자열로 가져옵니다.
	 * 
	 * @param format
	 * @return
	 */
	public static String getDateToString(String format) {
		Date date = new Date();
		return getDateToString(date, format);
	}
	
	/**
	 * 날짜 객체를 형식에 맞는 문자열로 가져옵니다.
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getDateToString(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	
	/**
	 * 입력된 일시에서 분을 더하거나 빼는 함수
	 * 예) 현재 일자에서 1시간 이전을 구할 경우
	 *     addMinuteDate(null, -1, "yyyyMMddHHmmss")
	 * @param date 대상 날짜
	 * @param hour 계산할 시간
	 * @param format 날자형식
	 * @return 계산된 일시를 Format시킨후 문자열
	 */	
	public static String addMinuteDate(Calendar date, int minute, String format)
	{
		if ( date == null )
		{
			date = Calendar.getInstance(locale);
		}

		date.add(Calendar.MINUTE,minute);

		if ( format == null || ("").equals(format) )
		{
			format = DEFAULT_DATE_FORMAT;
		}

		SimpleDateFormat formatter = new SimpleDateFormat(format, locale);

		return formatter.format(date.getTime());
	}
	
	/**
	 * date1에 대한 date2의 일수 차이를 가져옵니다. - date2가 date1보다 미래일 경우 음수가 나옵니다.
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	public static long compareTwoDate(Date date1, Date date2) throws Exception {
		// logger.debug(">>>>> date1 : {}, date2 : {}", date1, date2);
		long interval = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000);
		return interval;
	}
	
	/**
	 * 타임스탬프 형식 문자열
	 * @return
	 */
	public static String getTimestamp() {
		String rtnStr = null;
		
		// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
		String pattern = "yyyyMMddhhmmssSSS";
		SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		
		rtnStr = sdfCurrent.format(ts.getTime());
		return rtnStr;
	}
	
	/**
	 * 시간 값을 이용하여 20자리 고유 문자열 반환
	 * 
	 * @return 숫자형태의 문자
	 */
	public static synchronized String getUniqueNumber() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS"); // 17 characters
		String sReturn = sdf.format(Calendar.getInstance().getTime());
		try {
			++dummy;
		}
		catch (Exception e) {
			dummy = 0;
		}
		sReturn += padLeft(String.valueOf(dummy % 1000), 3, '0');
		
		return sReturn;
	}
	
	/**
	 * java.util.UUID를 이용한 32자리 랜덤 문자열 : 하이픈 제거
	 * 
	 * @return
	 */
	public static String getRandomStringByUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	// @Deprecated
	// public static String getWeekName(String wk) {
	// int tmp = Integer.parseInt(wk);
	// if (getString(wk).equals("")) {
	// tmp = 0;
	// }
	// String[] str = { "*", "일", "월", "화", "수", "목", "금", "토" };
	// return str[tmp];
	// }
	
	/**
	 * 16진 문자열 -&gt; byte 배열 변환 <br>
	 * 	- for RSA encrypt/decrypt <br>
	 */
	public static byte[] hexToByteArray(String hex) {
		if (hex == null || hex.length() % 2 != 0) {
			return new byte[] {};
		}
		
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < hex.length(); i += 2) {
			byte value = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
			bytes[(int) Math.floor(i / 2)] = value;
		}
		return bytes;
	}
	
	/**
	 * 휴대폰 국번 리스트
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<String> getMobileList() throws Exception {
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("010");
		list.add("011");
		list.add("016");
		list.add("017");
		list.add("018");
		list.add("019");
		
		return list;
	}
	
	/**
	 * 전화번호 지역번호 기타 국번 리스트
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<String> getPhoneList() throws Exception {
		ArrayList<String> list = new ArrayList<String>();
		
		// list.add("010");
		// list.add("011");
		// list.add("016");
		// list.add("017");
		// list.add("018");
		// list.add("019");
		list.add("02");
		list.add("031");
		list.add("032");
		list.add("033");
		list.add("041");
		list.add("042");
		list.add("043");
		list.add("051");
		list.add("052");
		list.add("053");
		list.add("054");
		list.add("055");
		list.add("061");
		list.add("062");
		list.add("063");
		list.add("064");
		list.add("050");
		list.add("0502");
		list.add("0505");
		list.add("070");
		
		return list;
	}
	
	/**
	 * 핸드폰 국번/일반 지역번호, 기타 국번 가져오기
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<String> getMobilePhoneList() throws Exception {
		List<String> list = getMobileList();
		List<String> list2 = getPhoneList();
		list.addAll(list2);
		return list;
	}
	
	/**
	 * IPIN용 특수 문자 변환
	 * @param paramValue
	 * @param gubun
	 * @return
	 */
	public static String requestReplace(String paramValue, String gubun) {
		String result = "";
		
		if (paramValue != null) {
			
			paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
			
			paramValue = paramValue.replaceAll("\\*", "");
			paramValue = paramValue.replaceAll("\\?", "");
			paramValue = paramValue.replaceAll("\\[", "");
			paramValue = paramValue.replaceAll("\\{", "");
			paramValue = paramValue.replaceAll("\\(", "");
			paramValue = paramValue.replaceAll("\\)", "");
			paramValue = paramValue.replaceAll("\\^", "");
			paramValue = paramValue.replaceAll("\\$", "");
			paramValue = paramValue.replaceAll("'", "");
			paramValue = paramValue.replaceAll("@", "");
			paramValue = paramValue.replaceAll("%", "");
			paramValue = paramValue.replaceAll(";", "");
			paramValue = paramValue.replaceAll(":", "");
			paramValue = paramValue.replaceAll("-", "");
			paramValue = paramValue.replaceAll("#", "");
			paramValue = paramValue.replaceAll("--", "");
			paramValue = paramValue.replaceAll("-", "");
			paramValue = paramValue.replaceAll(",", "");
			
			if (gubun != "encodeData") {
				paramValue = paramValue.replaceAll("\\+", "");
				paramValue = paramValue.replaceAll("/", "");
				paramValue = paramValue.replaceAll("=", "");
			}
			
			result = paramValue;
			
		}
		return result;
	}
	
	/**
	 * 문자수 단위로 문자열을 자른다.<br>
	 *
	 * @param s : 자를 문자열
	 * @param i : 자를 수
	 * @param plusStr : 플러스될 문자열
	 */
	public static String cutStringPlus(String s, int i, String plusStr) {
		String str = "";
		if (s.length() <= i)
			return s;
		str = s.substring(0, i);
		return str + plusStr;
	}
	
	/**
	 * 바이트 단위로 문자열을 자른다.<br>
	 *
	 * @param s : 자를 문자열
	 * @param i : 자를 수
	 * @param plusStr : 플러스될 문자열
	 */
	public static String cutStringBytesPlus(String s, int i, String plusStr) {
		if (getString(s).equals(""))
			return "";
		
		byte abyte0[] = s.getBytes();
		int j = abyte0.length;
		int k = 0;
		if (i >= j)
			return s;
		for (int l = i - 1; l >= 0; l--)
			if ((abyte0[l] & 0x80) != 0)
				k++;
		String str = new String(abyte0, 0, i - k % 3);
		return str + plusStr;
	}
	
	/**
	 * 바이트 기준 문자열 길이 가져오기 <br><br>
	 * 출처: http://cofs.tistory.com/257 [CofS]<br> 
	 * 		- 소스 일부 변경<br>
	 * @param value
	 * @return
	 */
	public static int getByteLength(String value) {
		if (isEmpty(value))
			return 0;
		
		// 바이트 체크 (영문 1, 한글 2, 특문 1)
		int en = 0;
		int ko = 0;
		int etc = 0;
		
		char[] txtChar = value.toCharArray();
		for (int j = 0; j < txtChar.length; j++) {
			if (txtChar[j] >= 'A' && txtChar[j] <= 'z') {
				en++;
			}
			else if (txtChar[j] >= '\uAC00' && txtChar[j] <= '\uD7A3') {
				ko++;
				ko++;
			}
			else {
				etc++;
			}
		}
		return (en + ko + etc);
		
	}


	/**
	 * 문자열 자르기 - 한글 깨지지 않도록<br><br>
	 * 출처 : 박쿤's Blog<br>
	 * @param value 대상 문자열
	 * @param startStr 기준 인덱스 정의할 문자열 (null)
	 * @param length 자를 길이
	 * @param nPrev (0)
	 * @param isNotag 태그 불가 : true
	 * @param isAddDot : 문자열 끝 ... 추가 : false
	 * @return
	 */
	public static String cutString(String value, String startStr, int length, int nPrev, boolean isNotag, boolean isAddDot) {
		
		String r_val = value;
		int oF = 0, oL = 0, rF = 0, rL = 0;
		int nLengthPrev = 0;
		Pattern p = Pattern.compile("<(/?)([^<>]*)?>", Pattern.CASE_INSENSITIVE); // 태그제거
																					// 패턴
		
		if (isNotag) {
			r_val = p.matcher(r_val).replaceAll("");
		} // 태그 제거
		r_val = r_val.replaceAll("&amp;", "&");
		r_val = r_val.replaceAll("(!/|\r|\n|&nbsp;)", ""); // 공백제거
		
		try {
			byte[] bytes = r_val.getBytes("UTF-8"); // 바이트로 보관
			if (startStr != null && !startStr.equals("")) {
				nLengthPrev = (r_val.indexOf(startStr) == -1) ? 0 : r_val.indexOf(startStr); // 일단 위치찾고
				nLengthPrev = r_val.substring(0, nLengthPrev).getBytes("MS949").length; // 위치까지길이를 byte로 다시 구한다
				nLengthPrev = (nLengthPrev - nPrev >= 0) ? nLengthPrev - nPrev : 0; // 좀 앞부분부터 가져오도록한다.
			}
			
			// x부터 y길이만큼 잘라낸다. 한글안깨지게.
			int j = 0;
			
			if (nLengthPrev > 0)
				while (j < bytes.length) {
					if ((bytes[j] & 0x80) != 0) {
						oF += 2;
						rF += 3;
						if (oF + 2 > nLengthPrev) {
							break;
						}
						j += 3;
					}
					else {
						if (oF + 1 > nLengthPrev) {
							break;
						}
						++oF;
						++rF;
						++j;
					}
				}
			
			j = rF;
			
			while (j < bytes.length) {
				if ((bytes[j] & 0x80) != 0) {
					if (oL + 2 > length) {
						break;
					}
					oL += 2;
					rL += 3;
					j += 3;
				}
				else {
					if (oL + 1 > length) {
						break;
					}
					++oL;
					++rL;
					++j;
				}
			}
			
			r_val = new String(bytes, rF, rL, "UTF-8"); // charset 옵션
			
			if (isAddDot && rF + rL + 3 <= bytes.length) {
				r_val += "...";
			} // ...을 붙일지말지 옵션
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return r_val;
	}
	
	/**
	 * 지정한 문자열 반복 결합 후 리턴 <br>
	 * @param character 반복할 문자열
	 * @param repeatCount 반복 수
	 * @return
	 */
	public static String repeatChar(char character, int repeatCount) {
		StringBuffer sb = new StringBuffer(repeatCount);
		
		while (sb.length() < repeatCount)
			sb.append(character);
		
		return sb.toString();
	}
	
	/**
	 * 문자열 오른쪽 패딩<br>
	 * @param value 적용시킬 문자열
	 * @param width 패딩 길이
	 * @param paddingChar 채울 문자
	 * @return 오른쪽으로 패딩된 문자열
	 */
	public static String padRight(String value, int width, char paddingChar) {
		try {
			if (value.length() >= width)
				return value;
			
			String sReturn = value;
			while (sReturn.length() < width)
				sReturn = sReturn + String.valueOf(paddingChar);
			
			return sReturn;
		}
		catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 문자열 오른쪽 패딩<br>
	 * JSTL 커스텀 function 용
	 * @param value
	 * @param width
	 * @param paddingChar
	 * @return
	 */
	public static String padRight(Object value, int width, char paddingChar) {
		return padRight(String.valueOf(value), width, paddingChar);
	}
	
	/**
	 * 문자열 왼쪽 패딩<br>
	 * @param value 적용시킬 문자열
	 * @param width 패딩 길이
	 * @param paddingChar 채울 문자
	 * @return 왼쪽으로 패딩된 문자열
	 */
	public static String padLeft(String value, int width, char paddingChar) {
		try {
			if (value.length() >= width)
				return value;
			
			String sReturn = value;
			while (sReturn.length() < width)
				sReturn = String.valueOf(paddingChar) + sReturn;
			
			return sReturn;
		}
		catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 문자열 왼쪽 패딩<br>
	 * JSTL 커스텀 function 용 
	 * @param value
	 * @param width
	 * @param paddingChar
	 * @return
	 */
	public static String padLeft(Object value, int width, char paddingChar) {
		return padLeft(String.valueOf(value), width, paddingChar);
	}
	
	
	/**
	 * 마스킹 : 지정 문자열 별표 처리 
	 * @param value 별표 처리할 문자열
	 * @param startIndex 별표 처리 시작할 문자열 인덱스
	 * @param length 별표 길이
	 * @return
	 */
	public static String maskingChar(String value, int startIndex, int length) {
		try {
			if (value.length() < (startIndex + 1))
				return value;
			
			char[] chars = value.toCharArray();
			for (int i = 0; i < chars.length ; i++) {
				if (i >= startIndex && i <= startIndex + length - 1)
					chars[i] = '*';
			}
			
			return String.valueOf(chars);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "null";
		}
	}
	
	/**
	 * 마스킹 : 지정문자열 별표 처리 - 인덱스 이상 문자열 모두 별표 처리
	 * @param value 별표 처리할 문자열
	 * @param startIndex 별표 처리 시작할 문자열 인덱스
	 * @return
	 */
	public static String maskingChar(String value, int startIndex) {
		return maskingChar(value, startIndex, value.length() - startIndex);
	}
	
	/**
	 * 파일 용량 읽기 쉬운 형태로 가져오기 - KB, MB, GB 단위로 가져옴
	 * 
	 * @param fileSize
	 * @return
	 */
	public static String getFileSize(long fileSize) {
		try {
			String sRtn = "0KB";
			
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(2);
			// GB
			if (fileSize >= Math.pow(2, 30))
				sRtn = nf.format((double) (fileSize / Math.pow(2, 30))) + "GB";
			else if (fileSize >= Math.pow(2, 20))
				sRtn = nf.format((double) (fileSize / Math.pow(2, 20))) + "MB";
			else if (fileSize > 0)
				sRtn = nf.format((double) (fileSize / Math.pow(2, 10))) + "KB";
			
			return sRtn;
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "0KB";
		}
	}

	/**
	 * 랜덤 토큰 생성
	 * @param byteLength
	 * @return
	 */
	public static String generateRandomBase64Token(int byteLength) {
		SecureRandom secureRandom = new SecureRandom();
		byte[] token = new byte[byteLength];
		secureRandom.nextBytes(token);
		return Base64.getUrlEncoder().withoutPadding().encodeToString(token); // base64 인코딩
	}
}