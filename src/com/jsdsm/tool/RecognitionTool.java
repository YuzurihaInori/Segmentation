package com.jsdsm.tool;

/**
 * �ִ��������ʶ�𹤾� ��Ӣ�ĵ��ʡ����֡�ʱ���
 */
public class RecognitionTool {
    // '��'�����ã��ŵ����
    private static final char[] chineseNumbers = { 'һ', '��', '��', '��', '��', '��', '��', '��', '��', 'ʮ', '��', 'ǧ', '��',
            '��', '��', 'Ҽ', '��', '��', '��', '��', '½', '��', '��', '��', 'ʰ', '��', 'Ǫ', '��' };

    /**
     * ʶ���ı���Ӣ�ĵ��ʡ����֡�ʱ��ȣ�
     * 
     * @param text
     *            ʶ���ı�
     * @return �Ƿ�ʶ��
     */
    public static boolean recog(final String text) {
        return recog(text, 0, text.length());
    }

    /**
     * ʶ���ı���Ӣ�ĵ��ʡ����֡�ʱ��ȣ�
     * 
     * @param text
     *            ʶ���ı�
     * @param start
     *            ��ʶ���ı���ʼ����
     * @param len
     *            ʶ�𳤶�
     * @return �Ƿ�ʶ��
     */
    public static boolean recog(final String text, final int start, final int len) {
        return isEnglishAndNumberMix(text, start, len) || isFraction(text, start, len)
                || isQuantifier(text, start, len) || isChineseNumber(text, start, len);
    }

    /**
     * С���ͷ���ʶ��
     * 
     * @param text
     *            ʶ���ı�
     * @param start
     *            ��ʶ���ı���ʼ����
     * @param len
     *            ʶ�𳤶�
     * @return �Ƿ�ʶ��
     */
    public static boolean isFraction(final String text, final int start, final int len) {
        if (len < 3) {
            return false;
        }
        int index = -1;
        for (int i = start; i < start + len; i++) {
            char c = text.charAt(i);
            if (c == '.' || c == '/' || c == '��' || c == '��' || c == '��') {
                index = i;
                break;
            }
        }
        if (index == -1 || index == start || index == start + len - 1) {
            return false;
        }
        int beforeLen = index - start;
        return isNumber(text, start, beforeLen) && isNumber(text, index + 1, len - (beforeLen + 1));
    }

    /**
     * Ӣ����ĸ�����ֻ��ʶ����ʶ�����֡���Ӣ�ĵ����Լ���ϵ����
     * 
     * @param text
     *            ʶ���ı�
     * @param start
     *            ��ʶ���ı���ʼ����
     * @param len
     *            ʶ�𳤶�
     * @return �Ƿ�ʶ��
     */
    public static boolean isEnglishAndNumberMix(final String text, final int start, final int len) {
        for (int i = start; i < start + len; i++) {
            char c = text.charAt(i);
            if (!(isEnglish(c) || isNumber(c))) {
                return false;
            }
        }
        // ָ�����ַ����Ѿ�ʶ��ΪӢ����ĸ�����ֻ�ϴ�
        // ����Ҫ�ж�Ӣ����ĸ�����ֻ�ϴ��Ƿ�����
        if (start > 0) {
            // �ж�ǰһ���ַ������ΪӢ���ַ���������ʶ��ʧ��
            char c = text.charAt(start - 1);
            if (isEnglish(c) || isNumber(c)) {
                return false;
            }
        }
        if (start + len < text.length()) {
            // �жϺ�һ���ַ������ΪӢ���ַ���������ʶ��ʧ��
            char c = text.charAt(start + len);
            if (isEnglish(c) || isNumber(c)) {
                return false;
            }
        }
        System.out.println("ʶ���Ӣ����ĸ�����ֻ�ϴ���" + text.substring(start, start + len));
        return true;
    }

    /**
     * Ӣ�ĵ���ʶ��
     * 
     * @param text
     *            ʶ���ı�
     * @param start
     *            ��ʶ���ı���ʼ����
     * @param len
     *            ʶ�𳤶�
     * @return �Ƿ�ʶ��
     */
    public static boolean isEnglish(final String text, final int start, final int len) {
        for (int i = start; i < start + len; i++) {
            char c = text.charAt(i);
            if (!isEnglish(c)) {
                return false;
            }
        }
        // ָ�����ַ����Ѿ�ʶ��ΪӢ�Ĵ�
        // ����Ҫ�ж�Ӣ�Ĵ��Ƿ�����
        if (start > 0) {
            // �ж�ǰһ���ַ������ΪӢ���ַ���ʶ��ʧ��
            char c = text.charAt(start - 1);
            if (isEnglish(c)) {
                return false;
            }
        }
        if (start + len < text.length()) {
            // �жϺ�һ���ַ������ΪӢ���ַ���ʶ��ʧ��
            char c = text.charAt(start + len);
            if (isEnglish(c)) {
                return false;
            }
        }
        System.out.println("ʶ���Ӣ�ĵ��ʣ�" + text.substring(start, start + len));
        return true;
    }

    /**
     * Ӣ���ַ�ʶ�𣬰�����Сд������ȫ�ǺͰ��
     * 
     * @param c
     *            �ַ�
     * @return �Ƿ���Ӣ���ַ�
     */
    public static boolean isEnglish(char c) {
        // �󲿷��ַ��������Χ
        if (c > 'z' && c < '��') {
            return false;
        }
        if (c < 'A') {
            return false;
        }
        if (c > 'Z' && c < 'a') {
            return false;
        }
        if (c > '��' && c < '��') {
            return false;
        }
        if (c > '��') {
            return false;
        }
        return true;
    }

    /**
     * ������ʶ�������ڡ�ʱ�䡢���ȡ�����������������ȵ�
     * 
     * @param text
     *            ʶ���ı�
     * @param start
     *            ��ʶ���ı���ʼ����
     * @param len
     *            ʶ�𳤶�
     * @return �Ƿ�ʶ��
     */
    public static boolean isQuantifier(final String text, final int start, final int len) {
        if (len < 2) {
            return false;
        }
        // �������ʺͲ�����С�����
        // .��ֵ��46,/��ֵ��47
        // �ж�ǰһ���ַ��Ƿ���.��/
        int index = start - 1;
        if (index > -1 && (text.charAt(index) == 46 || text.charAt(index) == 47)) {
            return false;
        }
        char lastChar = text.charAt(start + len - 1);
        if (Quantifier.is(lastChar)
                && (isNumber(text, start, len - 1) || isChineseNumber(text, start, len - 1) || isFraction(text, start,
                        len - 1))) {
            System.out.println("ʶ�������ʣ�" + text.substring(start, start + len));
            return true;
        }
        return false;
    }

    /**
     * ����ʶ��
     * 
     * @param text
     *            ʶ���ı�
     * @param start
     *            ��ʶ���ı���ʼ����
     * @param len
     *            ʶ�𳤶�
     * @return �Ƿ�ʶ��
     */
    public static boolean isNumber(final String text, final int start, final int len) {
        for (int i = start; i < start + len; i++) {
            char c = text.charAt(i);
            if (!isNumber(c)) {
                return false;
            }
        }
        // ָ�����ַ����Ѿ�ʶ��Ϊ���ִ�
        // ����Ҫ�ж����ִ��Ƿ�����
        if (start > 0) {
            // �ж�ǰһ���ַ������Ϊ�����ַ���ʶ��ʧ��
            char c = text.charAt(start - 1);
            if (isNumber(c)) {
                return false;
            }
        }
        if (start + len < text.length()) {
            // �жϺ�һ���ַ������Ϊ�����ַ���ʶ��ʧ��
            char c = text.charAt(start + len);
            if (isNumber(c)) {
                return false;
            }
        }
        System.out.println("ʶ������֣�" + text.substring(start, start + len));
        return true;
    }

    /**
     * ����������ʶ�𣬰���ȫ�ǺͰ��
     * 
     * @param c
     *            �ַ�
     * @return �Ƿ��ǰ���������
     */
    public static boolean isNumber(char c) {
        // �󲿷��ַ��������Χ
        if (c > '9' && c < '��') {
            return false;
        }
        if (c < '0') {
            return false;
        }
        if (c > '��') {
            return false;
        }
        return true;
    }

    /**
     * ��������ʶ�𣬰�����Сд
     * 
     * @param text
     *            ʶ���ı�
     * @param start
     *            ��ʶ���ı���ʼ����
     * @param len
     *            ʶ�𳤶�
     * @return �Ƿ�ʶ��
     */
    public static boolean isChineseNumber(final String text, final int start, final int len) {
        for (int i = start; i < start + len; i++) {
            char c = text.charAt(i);
            boolean isChineseNumber = false;
            for (char chineseNumber : chineseNumbers) {
                if (c == chineseNumber) {
                    isChineseNumber = true;
                    break;
                }
            }
            if (!isChineseNumber) {
                return false;
            }
        }
        // ָ�����ַ����Ѿ�ʶ��Ϊ�������ִ�
        // ����Ҫ�ж��������ִ��Ƿ�����
        if (start > 0) {
            // �ж�ǰһ���ַ������Ϊ���������ַ���ʶ��ʧ��
            char c = text.charAt(start - 1);
            for (char chineseNumber : chineseNumbers) {
                if (c == chineseNumber) {
                    return false;
                }
            }
        }
        if (start + len < text.length()) {
            // �жϺ�һ���ַ������Ϊ���������ַ���ʶ��ʧ��
            char c = text.charAt(start + len);
            for (char chineseNumber : chineseNumbers) {
                if (c == chineseNumber) {
                    return false;
                }
            }
        }
        System.out.println("ʶ����������֣�" + text.substring(start, start + len));
        return true;
    }

    public static void main(String[] args) {
        String t = "0.08%";
        System.out.println("" + recog(t, 0, t.length()));
    }
}
