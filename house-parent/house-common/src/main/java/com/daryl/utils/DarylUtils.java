package com.daryl.utils;

/**
 * daryl的小工具
 *
 * @author wl
 * @create 2022-02-23
 */
public class DarylUtils {

    /**
     * 十进制进位计算
     *
     * @param str     进位部分
     * @param isCarry 是否进位
     * @return 结果 true进位，false不进位
     */
    private boolean decimalCarry(String str, boolean isCarry) {
        if (str == null || str.length() == 0) {
            return false;
        }
        if ((str.length() == 1 && Integer.parseInt(str) >= 5) || (Integer.parseInt(str.substring(0, 1)) >= 5)) {
            return true;
        }
        if (str.length() == 1 && Integer.parseInt(str) < 5) {
            return false;
        }
        int lastValue = Integer.parseInt(str.substring(str.length() - 1));
        int changeValue = Integer.parseInt(str.substring(str.length() - 2, str.length() - 1));
        if (isCarry) {
            changeValue += 1;
            isCarry = false;
        }
        if (lastValue >= 5) {
            changeValue = changeValue + 1;
        }
        if (changeValue >= 10) {
            changeValue = changeValue - 10;
            isCarry = true;
        }
        return decimalCarry(str.substring(0, str.length() - 2) + changeValue, isCarry);
    }
}
