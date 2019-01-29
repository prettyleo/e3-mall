package cn.e3mall.enums;

/**
 * FileName: CloseStatusEnum
 * DESCRIPTION: EasyUi的tree关闭状态枚举
 *
 * @author: SLY
 * @create: 2019/1/19
 */
public enum CloseStatusEnum {

    CLOSED("closed"),
    OPEN("open")
    ;

    public String status;

    private CloseStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
