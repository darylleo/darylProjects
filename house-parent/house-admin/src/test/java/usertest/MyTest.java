package usertest;

import com.daryl.domain.dtos.ValidOptions;
import com.daryl.domain.entity.user.RoleMenuEntity;
import com.daryl.domain.entity.user.UserEntity;
import com.daryl.mapper.RoleMenuMapper;
import com.daryl.utils.EntityUtil;
import lombok.val;
import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.List;

/**
 * @author wl
 * @create 2022-02-18
 */
@SpringBootTest
public class MyTest {
    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Test
    public void bindRoleMenu(){
        List<Long> list = Arrays.asList(1493503690747674624L, 1493503834629079040L, 1493503952489021440L, 1494561962548264960L);
        for (Long aLong : list) {
            RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
            roleMenuEntity.setRoleId(1492016300376055808L);
            roleMenuEntity.setMenuId(aLong);
            roleMenuMapper.insert(EntityUtil.addCreateInfo(roleMenuEntity));
            UserEntity userEntity = new UserEntity();
        }
    }
}
