package com.mkl.gmall.vo.ums;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginResponseVo {

    private Long memberLevelId;

    private String username;

    private String nickname;

    private String phone;

    private String accessToken;//访问令牌
}
