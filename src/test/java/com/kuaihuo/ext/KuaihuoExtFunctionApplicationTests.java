package com.kuaihuo.ext;

import com.kuaihuo.ext.utils.files.FilePathUtil;
import com.kuaihuo.ext.utils.PrintUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KuaihuoExtFunctionApplicationTests {

    @Test
    void contextLoads() {
        String uploadPath = FilePathUtil.getUploadAbsBasePath();
        PrintUtil.println("上传路径为：" + uploadPath);
    }

}
