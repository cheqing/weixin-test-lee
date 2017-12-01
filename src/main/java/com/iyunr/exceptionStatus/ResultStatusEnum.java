package com.iyunr.exceptionStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
    * @ClassName: ResultStatusEnum  
    * @Description: TODO 返回结果枚举类  
    * 100 到199 的状态码代表信息，描述对于请求的处理。
    * 200 到 299 的状态码表示客户端发来的请求已经被接收并正确处理。
    * 300 到 399 的状态码表示客户端需要进一步的处理才能完成请求，比如重定向到另一个地址。
    * 400 到 499 的状态码表示客户端的请求有错误，需要修正。404就是这种情况。
    * 500 到 599 的状态码表示服务器在处理客户端请求时发生了内部错误。
    * @author Administrator  
    * @date 2017年10月31日  
    *
 */
public enum ResultStatusEnum {
	// -1为通用失败（根据ApiResult.java中的构造方法注释而来）
    FAIL(-1, "common fail"),
    // 通用成功
    OK(0, "common OK"),
    // [GET]：服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）
    SUCCESS(200, "查询成功！"),
    // [POST/PUT/PATCH]：用户新建或修改数据成功。
    CREATED(201, "操作成功！"),
    // [*]：表示一个请求已经进入后台排队（异步任务）
    ACCEPTED(202, "排队中~~"),
    // [DELETE]：用户删除数据成功。
    NO_CONTENT(204, "删除成功！"),
    // [POST/PUT/PATCH]：用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的。
    INVALID_REQUEST(400, "请求错误！"),
    // [*]：表示用户没有权限（令牌、用户名、密码错误）。
    UNAUTHORIZED(401, "无权限！"),
    // [*] 表示用户得到授权（与401错误相对），但是访问是被禁止的。
    FORBIDDEN(403, "禁止访问！"),
    // [*]：用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的。
    NOT_FOUND(404, "找不到记录！"),
    // [GET]：用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）。
    NOT_ACCEPTABLE(406, "请求的格式不可得！"),
    // [GET]：用户请求的资源被永久删除，且不会再得到的。
    GONE(410, "资源被永久删除！"),
    // [POST/PUT/PATCH] 当创建一个对象时，发生一个验证错误。
    UNPROCESABLE_ENTITY(422, "创建时发生错误！"),
    // [*]：服务器发生错误，用户将无法判断发出的请求是否成功。
    INTERNAL_SERVER_ERROR(500, "服务器异常！"),
    
    
    BY_ZERO(50001, "分母为零错误！"),
    NULLPOINTEXCEPTION(50002, "空指针错误"),
    ERROR_CREATE_FAILED(50003,"新增失败"),
    ERROR_UPDATE_FAILED(50004,"修改失败"),
    ERROR_DELETE_FAILED(50005,"删除失败"),
    ERROR_SEARCH_FAILED(50006,"查询失败"),

    error_pic_file(3,"非法图片文件"),
    error_pic_upload(4,"图片上传失败"),
    error_record_not_found(5, "没有找到对应的数据"),
    error_max_page_size(6, "请求记录数超出每次请求最大允许值"),
    error_count_failed(11,"查询数据总数失败"),
    error_string_to_obj(12,"字符串转java对象失败"),
    error_invalid_argument(13,"参数不合法"),
    error_update_not_allowed(14,"更新失败：%s"),
    error_duplicated_data(15,"数据已存在"),
    error_unknown_database_operation(16,"未知数据库操作失败，请联系管理员解决"),
    error_column_unique(17,"字段s%违反唯一约束性条件"),
    error_file_download(18,"文件下载失败"),
    error_file_upload(19,"文件上传失败"),

    //100-511为http 状态码
    // --- 4xx Client Error ---
    http_status_bad_request(400, "Bad Request"),
    http_status_unauthorized(401, "Unauthorized"),
    http_status_payment_required(402, "Payment Required"),
    http_status_forbidden(403, "Forbidden"),
    http_status_not_found(404, "Not Found"),
    http_status_method_not_allowed(405, "Method Not Allowed"),
    http_status_not_acceptable(406, "Not Acceptable"),
    http_status_proxy_authentication_required(407, "Proxy Authentication Required"),
    http_status_request_timeout(408, "Request Timeout"),
    http_status_conflict(409, "Conflict"),
    http_status_gone(410, "Gone"),
    http_status_length_required(411, "Length Required"),
    http_status_precondition_failed(412, "Precondition Failed"),
    http_status_payload_too_large(413, "Payload Too Large"),
    http_status_uri_too_long(414, "URI Too Long"),
    http_status_unsupported_media_type(415, "Unsupported Media Type"),
    http_status_requested_range_not_satisfiable(416, "Requested range not satisfiable"),
    http_status_expectation_failed(417, "Expectation Failed"),
    http_status_im_a_teapot(418, "I'm a teapot"),
    http_status_unprocessable_entity(422, "Unprocessable Entity"),
    http_status_locked(423, "Locked"),
    http_status_failed_dependency(424, "Failed Dependency"),
    http_status_upgrade_required(426, "Upgrade Required"),
    http_status_precondition_required(428, "Precondition Required"),
    http_status_too_many_requests(429, "Too Many Requests"),
    http_status_request_header_fields_too_large(431, "Request Header Fields Too Large"),

    // --- 5xx Server Error ---
    http_status_internal_server_error(500, "系统错误"),
    http_status_not_implemented(501, "Not Implemented"),
    http_status_bad_gateway(502, "Bad Gateway"),
    http_status_service_unavailable(503, "Service Unavailable"),
    http_status_gateway_timeout(504, "Gateway Timeout"),
    http_status_http_version_not_supported(505, "HTTP Version not supported"),
    http_status_variant_also_negotiates(506, "Variant Also Negotiates"),
    http_status_insufficient_storage(507, "Insufficient Storage"),
    http_status_loop_detected(508, "Loop Detected"),
    http_status_bandwidth_limit_exceeded(509, "Bandwidth Limit Exceeded"),
    http_status_not_extended(510, "Not Extended"),
    http_status_network_authentication_required(511, "Network Authentication Required"),

    // --- 8xx common error ---
    EXCEPTION(800, "exception"),
    INVALID_PARAM(801, "invalid.param"),
    INVALID_PRIVI(802, "invalid.privi"),

    //1000以内是系统错误，
    NO_LOGIN(1000,"没有登录"),
    CONFIG_ERROR(1001,"参数配置表错误"),
    USER_EXIST(1002,"用户名已存在"),
    USERPWD_NOT_EXIST(1003,"用户名不存在或者密码错误"),
    ;
    private static final Logger LOGGER = LoggerFactory.getLogger(ResultStatusEnum.class);

    private int code;
    private String msg;

    ResultStatusEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public static int getCode(String define){
        try {
            return ResultStatusEnum.valueOf(define).code;
        } catch (IllegalArgumentException e) {
            LOGGER.error("undefined error code: {}", define);
            return FAIL.getErrorCode();
        }
    }

    public static String getMsg(String define){
        try {
            return ResultStatusEnum.valueOf(define).msg;
        } catch (IllegalArgumentException e) {
            LOGGER.error("undefined error code: {}", define);
          //如果没有找到枚举类中给出的错误描述，则使用用户传过来的自定义描述
            return define;
//            return FAIL.getErrorMsg();
        }
    }

    public static String getMsg(int code){
        for(ResultStatusEnum err : ResultStatusEnum.values()){
            if(err.code==code){
                return err.msg;
            }
        }
        return "errorCode not defined ";
    }
    
//    public static void main(String[] args) {
//		System.out.println(ResultStatusEnum.getCode("no_login"));
//		System.out.println(ResultStatusEnum.getMsg(1000));
//	}

    public int getErrorCode(){
        return code;
    }

    public String getErrorMsg(){
        return msg;
    }
    
    @Override
    public String toString() {
    	return "["+this.code+"]"+this.msg;
    }
     
}
