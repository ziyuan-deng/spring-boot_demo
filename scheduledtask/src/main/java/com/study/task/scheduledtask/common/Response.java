package com.study.task.scheduledtask.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 3625483007513143901L;

	/** 返回结果集 */
    private Object resData;

    /** 返回消息 */
    private String resMsg;

    /** 响应码 */
    private Integer resCode;
}