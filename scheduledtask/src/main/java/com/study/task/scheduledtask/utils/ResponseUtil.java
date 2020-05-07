package com.study.task.scheduledtask.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.study.task.scheduledtask.common.Response;
import com.study.task.scheduledtask.constant.EduConstant;
import com.study.task.scheduledtask.enums.ResponseEnum;
import org.springframework.ui.ModelMap;

import java.util.Map;

/***
 * 接口返回标准
 * @author ziyuan_deng
 */
public class ResponseUtil {


    //成功
    private final static Integer SUCCESS = 0;
    //统一错误
    private final static Integer ERROR = 500;
    private final static String REQUEST_SUCCESS = "Request success!";

    /***
     * 请求数据成功、正确信息返回
     * @param data
     * @return
     */
    public static ModelMap retCorrectModel(Object data) {
        return retInfo(SUCCESS, data, REQUEST_SUCCESS);
    }

    /***
     * 请求数据成功、正确信息返回
     * @param data
     * @return
     */
    public static String retCorrectJson(Object data) {
        return retInfoJson(SUCCESS, data, REQUEST_SUCCESS);
    }

    /***
     * 请求数据成功、正确信息返回
     * @param msg
     * @return
     */
    public static Map<String, Object> retCorrectModel(Object data, String msg) {
        return retInfo(SUCCESS, data, msg);
    }

    /***
     * 请求数据成功、正确信息返回
     * @param data
     * @return
     */
    public static String retCorrectJson(Object data, String msg) {
        return retInfoJson(SUCCESS, data, msg);
    }

    /***
     * 请求数据成功、正确信息返回
     * @param msg
     * @return
     */
    public static ModelMap retCorrectInfo(String msg) {
        return retInfo(SUCCESS, null, msg);
    }

    /***
     * 请求数据成功、正确信息返回
     * @param msg
     * @return
     */
    public static String retCorrectInfoJson(String msg) {
        return retInfoJson(SUCCESS, null, msg);
    }

    /***
     * 校验错误信息返回
     * @param msg
     * @return
     */
    public static ModelMap retErrorInfo(String msg) {
        return retInfo(ERROR, null, msg);
    }

    /***
     * 校验错误信息返回
     * @param msg
     * @return
     */
    public static String retErrorJson(String msg) {
        return retInfoJson(ERROR, null, msg);
    }


    /***
     * 校验错误信息返回
     * @param msg
     * @return
     */
    public static ModelMap retErrorInfo(Integer errCode, String msg) {
        return retInfo(errCode, null, msg);
    }

    /***
     * 校验错误信息返回
     * @param msg
     * @return
     */
    public static String retErrorInfoJson(Integer errCode, String msg) {
        return retInfoJson(errCode, null, msg);
    }

    /**
     * @param code
     * @param data
     * @param msg
     * @return
     * @notes: 返回格式标准
     * @author: wubin
     */
    private static ModelMap retInfo(Integer code, Object data, String msg) {
        ModelMap result = new ModelMap();
        result.put(EduConstant.RESULT_CODE, code);
        result.put(EduConstant.RESULT_DATA, data);
        result.put(EduConstant.RESULT_MSG, msg);
        return result;
    }

    /**
     * @param code
     * @param data
     * @param msg
     * @return
     * @notes: 返回格式标准, json
     * @author: wubin
     */
    public static String retInfoJson(Integer code, Object data, String msg) {
        return JSON.toJSONString(retInfo(code, data, msg), SerializerFeature.WriteMapNullValue);
    }
    
    /**
     * 通用格式
     *
     * @param resCode 返回代码
     * @param resData 返回数据
     * @param resMsg 返回信息
     * @return com.maiyue.authenticate.platform.base.common.Response
     * @author wubin
     * @date 2019/8/28
     **/
   public static Response result(Integer resCode, Object resData, String resMsg){
        return Response.builder()
                .resCode(resCode)
                .resData(resData)
                .resMsg(resMsg)
                .build();
   }

    /**
     * 通用格式：enum
     *
     * @param responseEnum 返回枚举
     * @param resData 返回代码
     * @return com.maiyue.authenticate.platform.base.common.Response
     * @author wubin
     * @date 2019/8/28
     **/
    public static Response result(ResponseEnum responseEnum, Object resData){
        return Response.builder()
                .resCode(responseEnum.getCode())
                .resData(resData)
                .resMsg(responseEnum.getMsg())
                .build();
    }

    /**
     * 通用格式：success
     *
     * @param resData 返回数据
     * @return com.maiyue.authenticate.platform.base.common.Response
     * @author wubin
     * @date 2019/8/28
     **/
   public static Response success(Object resData){
       return Response.builder()
               .resCode(ResponseEnum.SUCCESS.getCode())
               .resData(resData)
               .resMsg(ResponseEnum.SUCCESS.getMsg())
               .build();
   }

    /**
     * 通用格式：exception
     * @return
     */
    public static Response exception(){
        return Response.builder()
                .resCode(ResponseEnum.FAILED.getCode())
                .resData(null)
                .resMsg(ResponseEnum.FAILED.getMsg())
                .build();
    }
}
