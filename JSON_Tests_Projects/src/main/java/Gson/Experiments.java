package Gson;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NonNull;

@Data
class AuthResponse {
	@NonNull
	public String access_token;
	
	@NonNull
	public String token_type;
	
	@NonNull
	@SerializedName("expires_inn")
	public int expires_in;
	
	@NonNull
	public String refresh_token;
	
	@NonNull
	public int refresh_token_expires_in;
	
	@NonNull
	public String scope;
	
	@NonNull
	public String owner_id;
	
	@NonNull
	public String endpoint_id;
}

@Data
class Subscription {
	
	@Data
	final static class DeliveryMode {
		@NonNull
		@SerializedName("transportType")
		public String transportType;
		
		@NonNull
		@SerializedName("encryption")
		public String encryption;
		
		@NonNull
		@SerializedName("address")
		public String address;
	}
	
	@NonNull
	@SerializedName("uri")
	public String uri;
	
	@NonNull
	@SerializedName("id")
	public String id;
	
	@NonNull
	@SerializedName("creationTime")
	public String creationTime;
	
	@NonNull
	@SerializedName("status")
	public String status;
	
	@NonNull
	@SerializedName("eventFilters")
	public List<String> eventFilters;
	
	@NonNull
	@SerializedName("expirationTime")
	public String expirationTime;
	
	@NonNull
	@SerializedName("expiresIn")
	public int expiresIn;
	
	@NonNull
	@SerializedName("deliveryMode")
	public DeliveryMode deliveryMode;
}

@Data
class AGWErrorResponse {
	@NonNull
	public String errorCode;
	
	@NonNull
	public String message;
	
	@NonNull
	public List<String> errors;
}



public class Experiments {
	/** **/
	private final static Gson gson = new Gson();
	
	private static void Parser_AuthResponse() {
		final String json = "{\r\n"
				+ "	\"access_token\": \"QU1TMDJQMDFQQVMwMHxBQUJYcWNOdzhaZ0E1YzhtenRPRm9GWmNNRDdkQ0M2eWJGT1FYQmdsUlZIZ2hfRk95VllfTXhtMGQxWFkyeXd1RjRUbUR4a2tkLU9waXpNVU9DbkthcnpRREQ4QmNMTzhJSmc2cW5MNWp5anpJeDR5Y1lzUXdCMjVCMktkWTExbWdvQjFzV21GZ3Q5Z2tvT3UwTVVoMUtHZVFOdVREQmtTSzdIMUtUb3Q5cmJzUkpDX0EyQUhIWElWNjZYdzlRfDFydHBRd3xoUU5JUWktZ2RQa1NpLTNhYkM2ZFVRfEFR\",\r\n"
				+ "	\"token_type\": \"bearer\",\r\n"
				+ "	\"expires_inn\": 12345,\r\n"
				+ "	\"refresh_token\": \"QU1TMDJQMDFQQVMwMHxBQUI2VjNaTVJMOUl3TTV3RE1INVFCRXhIZllwaEpMZm1KSzNZOGNuaDV4WVJKNlg5c2xEeC0zTEwzRENyUG9sSHNxR191TW1FbmJhOWhxTmZnbWc2OFJCbXhiaFczUjlYMGtIOWhaVlZzZEhuWF8tY1BKeGN6VGdmTmFNSWhpMC1ydktxbmxjR1lHdXY5d0RXX2VQUC13NG51bXhycFlCRGU4OVJ4UXg4LUdzdHR6VFRpejNRMkVoaG1LbXZnfDFydHBRd3xvZzRMOFJ6T253Mlp4S2RRdTdFMFlBfEFR\",\r\n"
				+ "	\"refresh_token_expires_in\": 604800,\r\n"
				+ "	\"scope\": \"EditReportingSettings CallControl EditMeetingsPresence Meetings EditILMData EditLiveReports Voicemail VoipCalling ReadClientInfo SubscriptionAPNS Glip ReadILMData Interoperability DirectRingOut ReadContacts RoomsManagement ReadAccounts SubscriptionGCM GlipLegacy SubscriptionWebhook EditPaymentInfo EditPresence SendNotifications EditAccounts ReadMessages Faxes GlipInternal ReadPresence EditCallLog ReadCallRecording A2PSMS WebSocket EditCustomData ReadLiveReports SubscriptionWebSocket Contacts EditExtensions RoleManagement TelephonySessions RingOut NumberLookup SMS InternalMessages ReadCallLog Accounts EditMessages SendUsageInfo\",\r\n"
				+ "	\"owner_id\": \"402726228127\",\r\n"
				+ "	\"endpoint_id\": \"qvcvhAqEQb2TipMNVWFbBQ\"\r\n"
				+ "}";
		
		AuthResponse authResponse = gson.fromJson(json, AuthResponse.class);
		System.out.println(authResponse.getExpires_in());
	}
	
	private static void Parser_CreateExtensionResponse() {
		final String json = "{\r\n"
				+ "	\"creationTime\": \"2021-01-21T12:49:09.266Z\",\r\n"
				+ "	\"status\": \"Active\",\r\n"
				+ "	\"eventFilters\": [\r\n"
				+ "		\"/restapi/v1.0/account/402730421127/extension/402730423127/presence\"\r\n"
				+ "	],\r\n"
				+ "	\"expirationTime\": \"2021-03-20T09:42:29.266Z\",\r\n"
				+ "	\"expiresIn\": 4999999,\r\n"
				+ "	\"deliveryMode\": {\r\n"
				+ "		\"transportType\": \"WebHook\",\r\n"
				+ "		\"encryption\": false,\r\n"
				+ "		\"address\": \"http://fun01-c01-rce01.lab.nordigy.ru:6790/webhooks/raw/hook/FakeAddress_4806596726873066142\"\r\n"
				+ "	}\r\n"
				+ "}";
		
		Subscription authResponse = gson.fromJson(json, Subscription.class);
		System.out.println("getExpiresIn = " + authResponse.getExpiresIn());
		System.out.println("getEncryption = " + authResponse.getDeliveryMode().getEncryption());
	}
	
	private static void Parser_CreateAGW_Error() {
		final String json = "{\r\n"
				+ "  \"errorCode\" : \"AGW-111\",\r\n"
				+ "  \"message\" : \"Internal Server Error. Consult RC Support.\",\r\n"
				+ "  \"errors\" : [ ]\r\n"
				+ "}";
		
		AGWErrorResponse agwError = gson.fromJson(json, AGWErrorResponse.class);
		System.out.println("Error code: " + agwError.getErrorCode());
		System.out.println("Message: " + agwError.getMessage());
		
		System.out.println(agwError.getErrorCode().equals("AGW-111"));
			
		
	}

	public static void main(String... args) {
		// Parser_AuthResponse();
		// Parser_CreateExtensionResponse();
		Parser_CreateAGW_Error();
	}
}
