package org.blue.demoframework.bean;

import lombok.Data;

/**
 * @author 郑煜
 * @Title: ResponseEntity
 * @ProjectName demoframework
 * @Description: 返回的数据类
 * @date 2019/3/13上午 01:22
 */

@Data
public class ResponseEntity<T> {
    int code;
    String info;
    T data;

    public ResponseEntity(int code,String info){
        this.code = code;
        this.info = info;
    }

    public ResponseEntity(int code,String info,T data){
        this.code = code;
        this.info = info;
        this.data = data;
    }
}
