package com.study.task.scheduledtask.constant;



import com.study.task.scheduledtask.utils.ComUtil;

import java.util.Map;


public class BusinessConstant {

	
	  /***
	   *   0 待审核
	   */
	  public static final String AUDIT_RESULT_STAY = "0";
	  /***
	   * 1 审核通过
	   */
	  public static final String AUDIT_RESULT_PASS = "1";
	  
	  /***
	   * -1 审核不过
	   */
	  public static final String AUDIT_RESULT_JUST = "-1";
	  
	  
	  
	  /***
	   * 正常状态
	   */
	  public static final String COMMON_STATE_STAY = "0";
	  
	  /***
	   * 删除状态
	   */
	  public static final String COMMON_STATE_JUST = "-1";
	  /***
	   * 锁定
	   */
	  public static final String COMMON_STATE_LOCK = "1";
	  
	  
/*********************************************** 分享 start************************************************/  
	  /** 分享状态 正常 */
	  public static final String SHARE_SATAE_NORMAL = "0";
	  /** 分享状态 停用 */
	  public static final String SHARE_SATAE_DISABLE= "-1";
	  /** 分享状态 锁定 */
	  public static final String SHARE_SATAE_LOCK = "0";
	  /** 是否可以继续分享 针对 一级 yes */
	  public static final String SHARE_IS_CONTINUE_YES = "true";
	  /*** 是否可以继续分享 针对 一级 no*/
	  public static final String SHARE_IS_CONTINUE_NO = "false";
	  /*** 是否撤回 针对 一级 yes */
	  public static final String SHARE_IS_REVOKE_YES = "true";
	  /*** 是否撤回 针对 一级 no */
	  public static final String SHARE_IS_REVOKE_NO = "false";  
	  /** 单个 图表 */
	  public static final String SHARE_RESTYPE_SINGLE = "single";
	  /** 组合图表 */
	  public static final String SHARE_RESTYPE_GROUP = "group";
	  /** 分享类型(个人) */
	  public static final String SHARE_TYPE_USER = "USER";
	  /** 分享类型(组) */
	  public static final String SHARE_TYPE_GROUP = "GROUP";
/*********************************************** 分享 end ************************************************/ 
	  
	  /** 黑名单 类型 用户 */
	  public static final String BLACK_TYPE_USER = "USER";
	  /** 黑名单类型 组 */
	  public static final String BLACK_TYPE_GROUP = "GROUP";
	  
	  
	  
	  
	  
	  /**
	   *  加法运算
	   */
	  private static final Integer ADDITION = 1;
	  /**
	   * 减
	   */
	  private static final Integer SUBTRACTION = 2;
	  /**
	   * 乘
	   */
	  private static final Integer MULTIPLICATION = 3;
	  /***
	   * 除
	   */
	  private static final Integer DIVSION = 4;
	  
	  /***
	   * ID in 查询
	   */
	  public static final String IN_SELECT_IDS = "ids";
	  /***
	   * 接口执行状态(默认0)
	   */
	  public static final String RESULT_STATUS = "RetStatus";
	  /***
	   * 返回码(true|false)
	   */
	  public static final String RESULT_CODE = "resCode";
	  /***
	   *  返回信息
	   */
	  public static final String RESULT_MSG = "resMsg";
	  /***
	   *  返回数据
	   */
	  public static final String RESULT_DATA = "resData";
	  /***  方法内部执行码：成功 */
	  public static final boolean RESULT_CODE_TRUE = true;
	  /***  方法内部执行码：失败 */
	  public static final boolean RESULT_CODE_FALSE = false;
	  /*** 方法执行 成功  */
	  public static final Integer RESULT_STATUS_TRUE = 0;
	  /*** 方法执行 失败 */
	  public static final Integer RESULT_STATUS_FALSE = -1;
	  
	  
	  
	  /****************************** 排序 *****************************/
	  public static final String ORDER = "order";
	  public static final String SORT = "sorting";
	  public static final String PAGENUM = "pageNum";
	  public static final String PAGESIZE = "pageSize";
	  
	  /****
	   * 
	   * 业务默认状态
	   * @param object
	   * @param params
	   * @param field
	   * @return
	   */
	   @SuppressWarnings({"null","rawtypes"})
	   public static Map<String, Object> defaultState(Class object,Map<String, Object> params,String field){
			  if(params != null){
					if(params.get(field) != null){
						String state = String.valueOf(params.get(field));
						if(!BusinessConstant.COMMON_STATE_JUST.equals(state) && !BusinessConstant.COMMON_STATE_LOCK.equals(state)){
							params.put(field, BusinessConstant.COMMON_STATE_STAY);
						}
					}else{
						params.put(field, BusinessConstant.COMMON_STATE_STAY);
					}
			  }else{
			     params.put(field, BusinessConstant.COMMON_STATE_STAY);
			  }
			  params = defaultSort(object,params);
			  return params;
	    }
	  
	  
	   /***
	    * 默认排序
	    * @param object
	    * @param params
	    * @return
	    */
	    @SuppressWarnings("rawtypes") 
		private static Map<String, Object> defaultSort(Class object,Map<String, Object> params){
			  if(params != null){
				if(params.get(ORDER) != null){
					String column = ComUtil.getCheckColumn(object, params.get(ORDER).toString().trim());
					params.put(ORDER, column);
				}
				if(params.get(SORT) != null){
					String S = params.get(SORT).toString().trim();
					if(!S.toUpperCase().equals("DESC") || !S.toUpperCase().equals("ASC")){
						params.put(SORT, "DESC");	
					}
				 }
		      }
			  return params;
		 }
	
}
