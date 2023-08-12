package com.puhj.rye;

import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RyeApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 生成数据库用户加密密码和公钥
     */
    @Test
    void encrypt() throws Exception {
        // 数据库明文密码
        String password = "root123456";
        ConfigTools.main(new String[]{password});
    }

}
