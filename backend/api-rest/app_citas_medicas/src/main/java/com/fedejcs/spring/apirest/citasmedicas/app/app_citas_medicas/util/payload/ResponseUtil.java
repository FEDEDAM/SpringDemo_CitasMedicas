package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.util.payload;


public class ResponseUtil 
{
    private String msg;
    private String msgType;
    private String msgExtendedInfo;
    private Object entity;


    public ResponseUtil(){}
    public ResponseUtil( String msg , String msgExtendedInfo , String msgType)
    {
        this.entity = null;
        this.msg = msg;
        this.msgType = msgType;
        this.msgExtendedInfo = msgExtendedInfo;
    }

    public ResponseUtil( String msg , String msgExtendedInfo , String msgType , Object entity)
    {
        this.entity = entity;
        this.msg = msg;
        this.msgType = msgType;
        this.msgExtendedInfo = msgExtendedInfo;
    }

    public String getMsg(){ return msg; }
    public void setMsg( String msg ){ this.msg = msg; }

    public String getMsgType(){ return msgType; }
    public void setMsgType( String msgType ){ this.msgType = msgType; }

    public String getMsgInfo(){ return msgExtendedInfo; }
    public void setMsgExtendedInfo( String msgExtendedInfo ){ this.msgExtendedInfo = msgExtendedInfo; }

    public Object getEntity(){ return entity; }
    public void setEntity( Object entity ){ this.entity = entity; }
    

    
}
