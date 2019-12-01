package com.mkl.gmall.admin.ums.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Null;

/**
 * 用户登录参数
 * Created by atguigu 4/26.
 */
@Getter
@Setter
@Data
public class UmsAdminParam {
    /**
     * 能使用的校验注解
     * 1）、Hibernate   org.hibernate.validator.constraints 里面的所有
     * 2）、JSR303规范规定的都可；
     *      javax.validation.constraints
     *          @Pattern(regexp = "")
     */
    @Length(min = 6,max = 18,message = "用户名长度必须是6-18位")
    @ApiModelProperty(value = "用户名", required = true)
    private String username;



    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @NotEmpty
    @ApiModelProperty(value = "用户头像")
    private String icon;

    @Email(message = "邮箱格式不正确，哈哈哈")
    @ApiModelProperty(value = "邮箱")
    private String email;

    //使用负责正则进行校验
    @ApiModelProperty(value = "用户昵称")
    private String nickName;


    @ApiModelProperty(value = "备注")
    private String note;
}
