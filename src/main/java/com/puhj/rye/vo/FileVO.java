package com.puhj.rye.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author puhanjie
 * @description 文件VO
 * @create 2022/3/29 21:28
 * @modify 2022/3/29 21:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileVO {

    private String fileName;

    private String uuid;

    private String filePath;

}
