package com.axis.VoxisService.exception;

public enum ErrorCodeAndMessage {


	INTERNAL_SERVER_ERROR("ER-500","Internal server error. Please try again later.",true),
	UNABLE_TO_PARSE_MESSAGE("ER-500","Internal server error. Please try again later.",false),
	SSL_EXCEPTION("ER-500","Internal server error. Please try again later.",false),
	UNABLE_TO_PARSE_MESSAGE_MAP("ER-500","Internal server error. Please try again later.",false),
	MOCK_FILE_NOT_FOUND("ER-500","Mock File Not Found. Please try again later.",false),
	INVALID_LOCAL_RULE_FILE_PATH("ER-500","Invalid local rule file path.",false),
	INVALID_REQUEST_DATA("ER-1000","Invalid Request.",false),
	DRL_FILE_NOT_FOUND("ER-1001","DRL file not found when creating session.",false),
	NO_WORKFLOW_FOUND("ER-1002","No Workflow found for this configuration.",false),
	NO_POLICY_EVALUATOR_FOUND("ER-1003","No policy evaluator found.",false),
	REQUIRED_ATTRIBUTE_ARE_NOT_PRESENT("ER-1004","Required attribute not present in request.",false),
	NO_IMPLEMENTATION_FOR_DATA_TYPE("ER-1005","Attribute Data type implementation not provided.",false),
	INVALID_CACHE_NAME("ER-1006","Invalid cache name provided.",false),
	UNABLE_TO_PARSE_EXECUTOR_RESPONSE("ER-1007","Unable to parse executor response.",false),
	NO_CACHE_FOUND_FOR_PRODUCT_ATTRIBUTE("ER-1008","No cache found for product attribute",true),
	REQUIRED_ATTRIBUTE_ARE_NOT_PRESENT_FOR_EXECUTOR("ER-1009","Required attribute not present for executor",false),
	SERVICE_CIBIL_EXCEPTION("ER-1010", "Cibil Service down. Please try again later", true),
	SERVICE_FC_VARIANTS_EXCEPTION("ER-1011", "FC Service down. Please try again later", true),
	NO_AGENDA_FOUND_FOR_PRODUCT("ER-1012","No agenda found for product",false),
	ERROR_WHILE_EXECUTING_DROOLS_FILE("ER-1013","Error while executing drools file",false),
	UNABLE_TO_PARSE_DRL_FILE("ER-1014","Unable to parse drl file.",false),
	NO_WORK_FLOW_CONFIG_FOUND("ER-1015","No workflow config found.",false),
	HASH_CALCULATION_ERROR("ER-1016","Unable to calculate Hash",false),
	EXCEPTION_WHILE_READING_FILE("ER-1017","Error while reading file.",false),
    NO_RULE_EXPRESSION_FOUND("ER-1018","No rule expression found for workflow.",false),
	TRANSACTION_ALREADY_IN_PROGRESS("ER-1019","Transaction already in progress with same id",false),
	NO_LATEST_DRL_FOUND("ER-1020","No latest drl found for keys required for workflow.",false),
	NO_IMPLEMENTATION_FOR_GENERATE_REFERNECE_ID("ER-1021","No implementation provided to generate reference id.",false),
	WORKFLOW_IS_NOT_ACTIVE("ER-1022","Workflow not in active state",false),
	NO_ACTIVE_WORKFLOW_RULE_EXPRESSION_TYPE_FOUND("ER-1023","No Active workflow rule expression type found.",false),
	NO_WORKFLOW_ID_FOUND_IN_REQUEST("ER-1024","No workflow id found in dms request.",false),
	NO_WORKFLOW_POLICY_FOUND("ER-1025","No workflowflow policy or scoreCard found.",false),
	NO_WORKFLOW_TRIGGER_MAPPING_FOUND("ER-1026","No trigger mapping exist.",false),
	PRIMITIVE_TYPE_NOT_SUPPORTED("ER-1027","Primitive type not supported",false),
	WORKFLOW_INVALID_PARENT_ID("ER-1028","Invalid parentID.",false),
	WORKFLOW_CONFIG_ID_IS_NULL("ER-AD-1001","Workflow config id is null in request",false),
	START_DATE_NULL("ER-AD-1002","Start date can not be null while creating WorkflowPolicy.",false),
	CLIENT_AUTHORIZATION_ERROR("ER-1029","Client auth Exception.",false);

	private String errorCode;
	private String message;
	private boolean retryable = false;

	public String getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return message;
	}

	public boolean isRetryable() {
		return retryable;
	}

	ErrorCodeAndMessage(String errorCode, String message, boolean retryable) {
		this.errorCode = errorCode;
		this.message = message;
		this.retryable = retryable;
	}
}


