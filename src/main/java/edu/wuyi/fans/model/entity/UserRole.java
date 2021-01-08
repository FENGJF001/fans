package edu.wuyi.fans.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author fans <wuyi_edu@163.com>
 * @since 2020-11-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user_role")
@ApiModel(value="UserRole对象")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private String userId;

    private Integer roleId;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm",timezone = "GMT+8")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm",timezone = "GMT+8")
    private Date updateTime;


}
