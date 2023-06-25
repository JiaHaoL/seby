package com.data.info.vo;

public class OutputVo extends BaseVo{
	
	private static final long serialVersionUID = 515620690273051439L;
	
	private String code;
	private String msg;
	private Object data;
	private PageVo page;
	
	
	public OutputVo(Object data){
		this.code="0000";
		this.data=data;
	}
	
	public OutputVo(Object data,PageVo pageVo){
		this.code="0000";
		this.data=data;
		this.page = pageVo;
	}
	
	public OutputVo(String code,Object data,PageVo pageVo){
		this.code=code;
		this.data=data;
		this.page = pageVo;
	}
	
	public OutputVo(String code,String msg){
		this.code=code;
		this.msg=msg;
	}
	
	public OutputVo(String code,String msg,Object data){
		this.code=code;
		this.msg=msg;
		this.data=data;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	public PageVo getPage() {
		return page;
	}

	public void setPage(PageVo page) {
		this.page = page;
	}

}

