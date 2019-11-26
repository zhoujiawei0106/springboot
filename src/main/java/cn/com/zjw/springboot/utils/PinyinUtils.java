package cn.com.zjw.springboot.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

/**
 * 将输入的中文转换成拼音
 * @author zhoujiawei
 */
public class PinyinUtils {

    /**
     *
     * @author zhoujiawei
     * @param str
     * @return
     * @throws Exception
     */
    public static final String toPinyin(String str) throws Exception{
        char[] cs = str.toCharArray();
        String value = "";
        for (char c : cs) {
            HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
            hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
            hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

            // 获取
            String[] strs = PinyinHelper.toHanyuPinyinStringArray(c, hanyuPinyinOutputFormat);
            if (strs == null || strs.length == 0) {
                value += c;
            } else {
                value += strs[0];
            }
        }
        return value;
    }
}
