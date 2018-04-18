package com.wxmp.gather.constant;

/**
 * Created by shenwenjun on 2018/4/13.
 */
public class GatherMessage {
    public static final int SUCCESS = 0;
    public static final String SUCCESS_MSG = "成功";
    public static final int FAIL = -1;
    public static final String FAIL_NAME = "失败";
    public static final int PASSWORD_WRONG = 1;
    public static final String PASSWORD_WRONG_NAME = "用户名或密码错误";
    public static final int VERIFICATION_CODE_WRONG = 3;
    public static final String VERIFICATION_CODE_WRONG_NAME = "验证码错误";
    public static final int USER_PHONE_EXIST = 4;
    public static final String USER_PHONE_EXIST_NAME = "手机号已存在";
    public static final int USER_NOT_EXIST = 5;
    public static final String USER_NOT_EXIST_NAME = "用户不存在";
    public static final int UPLOAD_FAIL = 6;
    public static final String UPLOAD_FAIL_NAME = "上传失败，为选择任何文件";
    public static final int SOURCE_END_LIMIT = 7;
    public static final String SOURCE_END_LIMIT_NAME = "最多添加20条房源";
}
