package cn.e3mall.vo.resp;

import cn.e3mall.enums.ResultCode;
import lombok.Data;

/**
 * FileName: Result
 * DESCRIPTION: 普通响应结果
 *
 * @author: Liyou Shen
 * @create: 2019/1/13
 */
@Data
public class Result<T> {

    private String code;
    private String message;
    private T data;

    public Result() {
    }

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMsg();
    }
}
