package cn.e3mall.util;

import cn.e3mall.enums.ResultCode;
import cn.e3mall.vo.resp.Result;

/**
 * FileName: ResultUtil
 * DESCRIPTION: 响应结果工具类
 *
 * @author: Liyou Shen
 * @create: 2019/1/13
 */
public class ResultUtil {

    /**
     * 创建无data的成功Result
     * @return
     */
    public static Result success() {
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 创建成功的Result, 有data
     * @param object
     * @return
     */
    public static Result success(Object object) {
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(object);
        return result;
    }

    /**
     * 创建错误的Result
     * @return
     */
    public static Result error() {
        return new Result(ResultCode.ERROR);
    }

}
