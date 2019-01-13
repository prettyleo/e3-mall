package cn.e3mall.enums;

/**
 * FileName: ResultCode
 * DESCRIPTION: 响应结果枚举
 *
 * @author: Liyou Shen
 * @create: 2019/1/13
 */
public enum ResultCode {

    SUCCESS("0000", "成功"),
    ERROR("9999", "系统异常"),
    ;

    private String code;
    private String msg;

    private ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
