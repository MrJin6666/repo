package com.goboosoft.common.utils;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * dmxuan
 * 极光推送
 * @Date 2019/3/29 09:30
 **/
@Component
public class PushUtil {
	
	// 推送企业密钥
	private final String COMPANY_APP_KEY = "483ee6191b060c5727641629";
	private final String COMPANY_MASTER_SECRET = "48284be3c98b6dbc6000759f";
	// 推送行业密钥
	private final String INDUSTRY_APP_KEY = "631fd07e2682e4e98b8bd062";
	private final String INDUSTRY_MASTER_SECRET = "f87a5c834201b20291500b07";

	@Value("${jiguang.active}")
	private boolean active;

	private JPushClient companyJPushClient = new JPushClient(COMPANY_MASTER_SECRET,COMPANY_APP_KEY, null,ClientConfig.getInstance());
	private JPushClient industryJPushClient = new JPushClient(INDUSTRY_MASTER_SECRET,INDUSTRY_APP_KEY, null,ClientConfig.getInstance());

	public enum PushMsgType{

		/**
		 * 测试
		 */
		TEST("TEST", "测试行业发送推送消息！！！");

		
		private String type;
		private String message;
		
		PushMsgType(String type, String message) {
			this.type = type;
			this.message = message;
		}
		
		public String getType() {
			return this.type;
		}
		
		public String getMessage(){
			return this.message;
		}
	}



	/**
	 * dmxuan
	 * 行业端单发
	 * @Date 2019/3/28 17:08
	 * @Param [registrationId, message]
	 * @return void
	 **/
	public void industryPushMessage(String registrationId,PushMsgType pushMsgType){
		if(!StringUtils.isBlank(registrationId)){
			PushPayload payload = PushPayload.newBuilder()
					.setPlatform(Platform.android_ios())
					.setAudience(Audience.registrationId(registrationId))
					.setNotification(Notification.newBuilder()
							.setAlert(pushMsgType.getMessage())
//							.addPlatformNotification(AndroidNotification.newBuilder().addExtras(extras).build())
//							.addPlatformNotification(IosNotification.newBuilder().addExtras(extras).build())
							.build())
					.setOptions(Options.newBuilder().setApnsProduction(active).setTimeToLive(86400L).build()).build();

			try {
				PushResult p = industryJPushClient.sendPush(payload);
				System.out.println(p);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * dmxuan
	 * 行业广播
	 * @Date 2019/3/28 17:08
	 * @Param [registrationId, message]
	 * @return void
	 **/
	public void industryPushMessage(PushMsgType pushMsgType){
			PushPayload payload = PushPayload.newBuilder()
					.setPlatform(Platform.android_ios())
					.setAudience(Audience.all())
					.setNotification(Notification.newBuilder()
							.setAlert(pushMsgType.getMessage())
//							.addPlatformNotification(AndroidNotification.newBuilder().addExtras(extras).build())
//							.addPlatformNotification(IosNotification.newBuilder().addExtras(extras).build())
							.build())
					.setOptions(Options.newBuilder().setApnsProduction(active).setTimeToLive(86400L).build()).build();

			try {
				PushResult p = industryJPushClient.sendPush(payload);
				System.out.println(p);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/**
	 * dmxuan
	 * 行业端群发
	 * @Date 2019/3/28 17:08
	 * @Param [registrationId, message]
	 * @return void
	 **/
	public void industryPushMessage(List<String> registrationIds,PushMsgType pushMsgType){
			PushPayload payload = PushPayload.newBuilder()
					.setPlatform(Platform.android_ios())
					.setAudience(Audience.registrationId(registrationIds))
					.setNotification(Notification.newBuilder()
							.setAlert(pushMsgType.getMessage())
//							.addPlatformNotification(AndroidNotification.newBuilder().addExtras(extras).build())
//							.addPlatformNotification(IosNotification.newBuilder().addExtras(extras).build())
							.build())
					.setOptions(Options.newBuilder().setApnsProduction(active).setTimeToLive(86400L).build()).build();

			try {
				PushResult p = industryJPushClient.sendPush(payload);
				System.out.println(p);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	/**
	 * dmxuan
	 * 企业端单发
	 * @Date 2019/3/28 17:08
	 * @Param [registrationId, message]
	 * @return void
	 **/
	public void companyPushMessage(String registrationId,PushMsgType pushMsgType){
		if(!StringUtils.isBlank(registrationId)){
			PushPayload payload = PushPayload.newBuilder()
					.setPlatform(Platform.android_ios())
					.setAudience(Audience.registrationId(registrationId))
					.setNotification(Notification.alert(pushMsgType.getMessage())
//							Notification.newBuilder()
//							.setAlert(pushMsgType.getMessage())
//							.addPlatformNotification(AndroidNotification.newBuilder().addExtras(extras).build())
//							.addPlatformNotification(IosNotification.newBuilder().addExtras(extras).build())
//							.build()
			                       )
					.setOptions(Options.newBuilder().setApnsProduction(active).setTimeToLive(86400L).build()).build();

			try {
				PushResult p = companyJPushClient.sendPush(payload);
				System.out.println(p);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * dmxuan
	 * 企业广播
	 * @Date 2019/3/28 17:08
	 * @Param [registrationId, message]
	 * @return void
	 **/
	public void companyPushMessage(PushMsgType pushMsgType){
			PushPayload payload = PushPayload.newBuilder()
					.setPlatform(Platform.android_ios())
					.setAudience(Audience.all())
					.setNotification(Notification.newBuilder()
							.setAlert(pushMsgType.getMessage())
//							.addPlatformNotification(AndroidNotification.newBuilder().addExtras(extras).build())
//							.addPlatformNotification(IosNotification.newBuilder().addExtras(extras).build())
							.build())
					.setOptions(Options.newBuilder().setApnsProduction(active).setTimeToLive(86400L).build()).build();

			try {
				PushResult p = companyJPushClient.sendPush(payload);
				System.out.println(p);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/**
	 * dmxuan
	 * 企业端群发
	 * @Date 2019/3/28 17:08
	 * @Param [registrationId, message]
	 * @return void
	 **/
	public void companyPushMessage(List<String> registrationIds,PushMsgType pushMsgType){
			PushPayload payload = PushPayload.newBuilder()
					.setPlatform(Platform.android_ios())
					.setAudience(Audience.registrationId(registrationIds))
					.setNotification(Notification.newBuilder()
							.setAlert(pushMsgType.getMessage())
//							.addPlatformNotification(AndroidNotification.newBuilder().addExtras(extras).build())
//							.addPlatformNotification(IosNotification.newBuilder().addExtras(extras).build())
							.build())
					.setOptions(Options.newBuilder().setApnsProduction(active).setTimeToLive(86400L).build()).build();

			try {
				PushResult p = companyJPushClient.sendPush(payload);
				System.out.println(p);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
