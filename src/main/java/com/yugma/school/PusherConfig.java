package com.yugma.school;

import com.pusher.rest.Pusher;

public class PusherConfig {
	
	//String url = "http://"+223986+":"+"29d744d48234624cf3a4"+"@api.pusherapp.com:80/apps/"+"a536e732c4b7dc4471dc";
	//@Autowired
	static final Pusher pusher = new Pusher("223986","29d744d48234624cf3a4","a536e732c4b7dc4471dc");
	
	//Pusher pusher1 = new Pusher("","","");
	
	public void triggerEvent(String channel,String event, Object object){
		pusher.trigger(channel, event, object);
	}
}
