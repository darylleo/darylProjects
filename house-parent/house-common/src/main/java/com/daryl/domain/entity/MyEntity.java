package com.daryl.domain.entity;

import com.daryl.config.MyGenId;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wl
 * @create 2022-02-07
 */
@Getter
@Setter
@MappedSuperclass
public class MyEntity implements Serializable {
    @Id
    @KeySql(genId = MyGenId.class)
    //@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "com.daryl.utils.IdWorker")
    @Column(name = "id")
    private Long id;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "modify_time")
    private Date modifyTime;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "modify_by")
    private String modifyBy;

    /**
     * 是否逻辑删除（0否1是）
     */
    @Column(name = "del_tag")
    private Integer delTag = 0;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
