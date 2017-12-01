package com.iyunr.entity.message.res;

/**
 * @Description: TODO 语音消息  
 * <xml>
<ToUserName><![CDATA[toUser]]></ToUserName>
<FromUserName><![CDATA[fromUser]]></FromUserName>
<CreateTime>12345678</CreateTime>
<MsgType><![CDATA[voice]]></MsgType>
<Voice>
<MediaId><![CDATA[media_id]]></MediaId>
</Voice>
</xml>
 * @author Administrator  
 * @date 2017年11月8日
 */
public class VoiceMessage extends BaseMessage{
	private Voice Voice = new Voice();

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		this.Voice = voice;
	}
	
}

