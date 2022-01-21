package com.WS_Routing;

public class CreateRouting {

	public static void main(String[] args) {
		try {
			RoutingManager cam = new RoutingManager("user00", "ctx::VPLMProjectLeader.Company Name.Common Space");
			cam.CreateRouting();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
