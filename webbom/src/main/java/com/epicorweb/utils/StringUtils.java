package com.epicorweb.utils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	public static SimpleDateFormat fyyyy_mm_dd = new SimpleDateFormat("yyyy-MM-dd"); 
	/**
	 * 转换为int
	 *
	 * @param obj          Object
	 * @param defaultValue int
	 * @return int
	 */
	public static int getInt(Object obj, int defaultValue) {
        if (obj == null) return defaultValue;
        try {
            if (obj instanceof Integer) return ((Integer) obj).intValue();
            if (obj instanceof String) return Integer.parseInt((String) obj);
            return Integer.parseInt(obj.toString());
        } catch (Throwable e) {
            return defaultValue;
        }
	}

	/**
	 * 转换为long
	 *
	 * @param obj          Ojbect
	 * @param defaultValue long
	 * @return long
	 */
	public static long getLong(Object obj, long defaultValue) {
		   if (obj == null) return defaultValue;
        try {
            if (obj instanceof Long) return ((Long) obj).longValue();
            if (obj instanceof String) return Long.parseLong((String) obj);
            return Long.parseLong(obj.toString());
        } catch (Throwable e) {
            return defaultValue;
        }
	}

    /**
     * 字符串参数转化成sql in('','')
     * @param str
     * @return
     */
    public static String toSqlStrInParam(String str){
        if(isEmpty(str)) {
            return "";
        } else {
            String states = "";
            if(str.endsWith(",")) str = str.substring(0,str.length());
            if(str.contains(",")) {
                String[] sp = str.split(",");
                StringBuffer sb = new StringBuffer();
                for(int i = 0; i < sp.length; i++) {
                    sb.append("'"+sp[i]+"'");
                }
                return sb.toString();
            } else {
               return "'"+str+"'";
            }
        }
    }

	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}
    public static String getStrFromMap(Map map,String key) {
        if(map == null || isEmpty(key)) return null;
        if(map.get(key) != null) {
            return map.get(key).toString();
        }
        return  null;
    }
    public static String getStrFromMap(Map map,String key,String defalutVal) {
        if(map == null || isEmpty(key)) return null;
        try{
            return map.get(key).toString();
        } catch (Exception e) {
            return defalutVal;
        }
    }
    public static Map getMapFromList(List dataList,String key,String value) {
        if(dataList == null || isEmpty(key)) return null;
        for(int i = 0; i < dataList.size(); i++) {
             Map map = (Map)dataList.get(i);
             String keyValue = getStrFromMap(map,key);
             if(keyValue != null && keyValue.equals(value)) {
                 return map;
             }
        }
        return null;
    }
    public static String toUnicode(String source){
        String str = "";
        for(int i = 0; i < source.length(); i++){
            char c = source.charAt(i);
            if(c < 256){
                str += "\\u00" + Integer.toHexString(c);
            }else if(c < 4096){
                str += "\\u0" + Integer.toHexString(c);
            }else{
                str += "\\u" + Integer.toHexString(c);
            }
        }
        return str;
    }
    /**
     *转化sql参数的模糊查询中含有特殊字符% _等 在语句里必须使用 and like '%%' escape '\' and
     * @param str
     * @return
     */
    public static String escapeSqlStr(String str) {
        if(isEmpty(str)) {
            return "";
        } else {
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < str.length(); i++) {
                char pos = str.charAt(i);
                if(pos == '%') {
                    sb.append("\\%");
                    //retStr = retStr.replaceAll("\u0025","\\u005c\\u0025");
                } else if(pos == '_') {
                    sb.append("\\_");
                } else {
                    sb.append(pos);
                }
            }
            return sb.toString();
        }
    }
    public static String toJSON(Object obj) {
        if (obj == null) return "null";
        StringBuffer sb = new StringBuffer();
        if (obj instanceof String) {
            return StringUtils.toJSONQuoteString((String) obj);
//        }else if (obj instanceof Array) {
        } else if (obj instanceof List) {
            List collection = (List) obj;
            sb.append("[");
            for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
                Object o = iterator.next();
                if (sb.length() > 1) sb.append(",");
                sb.append(toJSON(o));
            }
            return sb.append("]").toString();
        } else if (obj instanceof Map) {
            Map m = (Map) obj;
            sb.append("{");
            Set keys = m.keySet();
            for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
                String key = (String) iterator.next();
                if (sb.length() > 1) sb.append(",");
                sb.append(StringUtils.toJSONQuoteString(key));
                sb.append(":");
                sb.append(toJSON(m.get(key)));
            }
            return sb.append("}").toString();
        } else if (obj instanceof Number) {
            return "" + obj;
        } else if (obj instanceof Timestamp) {
            return toJSON(StringUtils.getDateStringByFormat("yyyy-MM-dd HH:mm:ss", (Date) obj));
        } else if (obj instanceof Date) {
            return toJSON(StringUtils.getDateStringByFormat("yyyy-MM-dd HH:mm:ss", (Date) obj));
        } else if (obj instanceof Object[]) {
            Object[] collection = (Object[]) obj;
            sb.append("[");
            for (int i = 0; i < collection.length; i++) {
                Object o = collection[i];
                if (sb.length() > 1) sb.append(",");
                sb.append(toJSON(o));
            }
            return sb.append("]").toString();
        }  else { //default string
            return StringUtils.toJSONQuoteString("" + obj);
        }
    }
    public static String getDateStringByFormat(String format) {
        return getDateStringByFormat(format, new Date());
    }

    public static String getDateStringByFormat(String format, Date data) {
        return new SimpleDateFormat(format).format(data);
    }

    public static String getDateStringByFormat(String dateSrc, String srcFormat, String toFormat) {
        try {
            return new SimpleDateFormat(toFormat).format(new SimpleDateFormat(srcFormat).parse(dateSrc));
        } catch (Exception e) {
            return dateSrc;
        }
    }
    public static String toJSONQuoteString(String string) {
        if (string == null || string.length() == 0) {
            return "\"\"";
        }
        if (string.toLowerCase().equals("true")) return "true";
        if (string.toLowerCase().equals("false")) return "false";

        char c;
        int i;
        int len = string.length();
        StringBuffer sb = new StringBuffer(len + 4);
        String t;

        sb.append('"');
        for (i = 0; i < len; i += 1) {
            c = string.charAt(i);
            switch (c) {
                case '\\':
                case '"':
                case '/':
                    sb.append('\\');
                    sb.append(c);
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                default:
                    if (c < ' ' || c >= 128) {
                        t = "000" + Integer.toHexString(c);
                        sb.append("\\u");
                        sb.append(t.substring(t.length() - 4));
                    } else {
                        sb.append(c);
                    }
            }
        }
        sb.append('"');
        return sb.toString();
    }

    public static String getToken(String key) throws Exception{
        if(StringUtils.isEmpty(key)) return null;
        //初始化加密环境
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(512);
        KeyPair kp = keyGen.genKeyPair();
        //加密token
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, kp.getPublic());
        byte[] x = cipher.doFinal(key.getBytes());
        return (new BASE64Encoder()).encode(x);

        /*cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] y = cipher.doFinal(decryptByte);
        log.debug(new String(y));*/
    }
    public static String getTokenKey(String key) throws Exception{
        if(StringUtils.isEmpty(key)) return null;
        //初始化加密环境
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(512);
        KeyPair kp = keyGen.genKeyPair();
        //解密toke
        byte[] decryptByte = new BASE64Decoder().decodeBuffer(key);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, kp.getPrivate());
        byte[] y = cipher.doFinal(decryptByte);
        return  new String(y);
    }

    /**
     * 功能：身份证的有效验证
     *
     * @param IDStr
     *            身份证号
     * @return 有效：true 无效：false
     * @throws ParseException
     */
    public static boolean isIdentityCard(String IDStr) throws ParseException {
        String errorInfo = "";// 记录错误信息
        String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4",
                "3", "2" };
        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
                "9", "10", "5", "8", "4", "2" };
        String Ai = "";
        // ================ 号码的长度 15位或18位 ================
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            return false;
        }
        // =======================(end)========================

        // ================ 数字 除最后以为都为数字 ================
        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);
        } else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (isNumber(Ai) == false) {
            return false;
        }
        // =======================(end)========================

        // ================ 出生年月是否有效 ================
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 月份
        if (isDataFormat(strYear + "-" + strMonth + "-" + strDay) == false) {
            return false;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                || (gc.getTime().getTime() - s.parse(
                strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
            return false;
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            return false;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            return false;
        }
        // =====================(end)=====================

        // ================ 地区码时候有效 ================
        Hashtable h = GetAreaCode();
        if (h.get(Ai.substring(0, 2)) == null) {
            return false;
        }
        // =====================(end)=====================
        return true;
    }

    /**
     * 功能：设置地区编码
     *
     * @return Hashtable 对象
     */
    @SuppressWarnings("unchecked")
    private static Hashtable GetAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    // 验证数字
    public static boolean isNumber(String str) {
        Pattern p = Pattern.compile("^[0-9]*$");
        Matcher m = p.matcher(str);
        return m.matches();
    }
    /**
     * 验证日期字符串是否是YYYY-MM-DD格式
     *
     * @param str
     * @return
     */
    public static boolean isDataFormat(String str) {
        boolean flag = false;
        String regxStr = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
        Pattern pattern1 = Pattern.compile(regxStr);
        Matcher isNo = pattern1.matcher(str);
        if (isNo.matches()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 是否是电话号码
     * @param mobiles
     * @return
     */
    public static boolean isPhone(String mobiles) {
        Pattern p = Pattern.compile("^[0][1-9]{2,3}[0-9]{5,8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 是否是手机号
     * @param mobiles
     * @return
     */
    public static boolean isMobile(String mobiles) {
        Pattern p = Pattern
                .compile("^0?(13[0-9]|15[012356789]|18[0-9]|14[57])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 向stringbuffer添加字段
     * @param obj
     * @param val
     */
    public static void appednField(StringBuffer obj,Object val){
        if(obj.length() > 0) obj.append(","+val);
        else obj.append(val);
    }
    private static Map getMapFromNotes(List node) {
        Map retMap = new HashMap();
        for (Iterator it = node.iterator(); it.hasNext();) {
            Element elm = (Element) it.next();
            List childList = elm.elements();
            if (childList.size() == 0) {
                retMap.put(elm.getName(), elm.getText());
            } else {
                Map childNoteMap = getMapFromNotes(childList);
                retMap.put(elm.getName(),childNoteMap);
            }
        }
        return retMap;
    }
    public static Map xmlToMap(String xml,String xpathStr) {
        try {
            if (xpathStr == null) {
                return null;
            }
            Document document= DocumentHelper.parseText(xml);
            Element rootEle = document.getRootElement();
            List nodeList = null;
            nodeList = rootEle.selectNodes(xpathStr);
            return getMapFromNotes(nodeList);
        } catch (Exception e) {
            System.out.println(" xmlToMap->: msg:" + e.toString());
            return null;
        }
    }
    /**
     * xml 转化成Map数据
     * <Manager>
     <Status>Success</Status>
     <Message>0</Message>
     </Manager>
     *
     * @param xml
     * @return
     */
    public static Map xmlToMap(String xml){
        try {
            if (xml == null) {
                return null;
            }
            Document document= DocumentHelper.parseText(xml);
            Element rootEle = document.getRootElement();
            List nodeList = rootEle.elements();
            return getMapFromNotes(nodeList);
        } catch (Exception e) {
            System.out.println(" xmlToMap->: msg:" + e.toString());
            return null;
        }
    }

    /**
     * 获取xml 节点值
     * @param xml
     * @param nodeName
     * @return
     */
    public static String getNodeTextFromXml(String xml,String nodeName){
        try {
            if (xml == null) {
                return null;
            }
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(false);//不进行验证
            DocumentBuilder db = dbf.newDocumentBuilder();
            org.w3c.dom.Document doc = db.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));
            NodeList nodeList = doc.getElementsByTagName(nodeName);
            if(nodeList == null || nodeList.getLength() == 0) {
                return null;
            }
            Node node = nodeList.item(0).getFirstChild();
            if(node == null) return null;
            return node.getTextContent();
        } catch (Exception e) {
            System.out.println(" getNodeTextFromXml->: msg:" + e.toString());
            return null;
        }
    }

    /**
     *逗号隔开的字符串，转换成long[]数组
     * @param ids
     * @return
     */
    public static Long[] strSplitLongArray(String ids) {
        Long[] b = null;
        if (ids.indexOf(",") >= 0) {
            String[] a = ids.split(",");
            b = new Long[a.length];
            for (int i = 0; i < a.length; i++) {
                try {
                    b[i] = Long.parseLong(a[i]);
                } catch (Exception e) {e.printStackTrace();}
            }
        } else {
            try {
                b = new Long[]{Long.parseLong(ids)};
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return b;
    }

    public static void main(String[] args) throws Exception{
        //System.out.println(java.net.URLEncoder.encode("aa%_","utf-8"));
        Object a = null;
        System.out.println((String)a);
    }
}
