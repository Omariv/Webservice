package com.WS_Routing;

import java.io.FileReader;
import java.net.URL;
import java.nio.file.Paths;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.WebServiceToolBox.Connection;
import com.WebServiceToolBox.Utils.Response;

public class RoutingManager {
	
	static final Logger logger = LogManager.getLogger(RoutingManager.class);
	Connection con;

	public RoutingManager(String user, String securityContext) throws Exception {
		try {
			this.con = new Connection(user, securityContext);
		} catch (Exception e) {
			logger.catching(e);
			throw e;
		}
	}
	
	
	/*
	 * CreateRouting method
	 */
	public Response CreateRouting() throws Exception {
		URL resource = getClass().getResource("InputRouting.json");
		String pathLien = Paths.get(resource.toURI()).toFile().getAbsolutePath();
		JSONParser jsonParser = new JSONParser();

		JSONObject obj = new JSONObject();
		try (FileReader reader = new FileReader(pathLien)) {
			obj = (JSONObject) jsonParser.parse(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		StringBuffer sbNewDocumentURL = new StringBuffer();
		sbNewDocumentURL.append(con.getUrl3dpaceEnv());
		sbNewDocumentURL.append(Connection.SERVICE_3DSPACE);
		sbNewDocumentURL.append(Utils.SPACE_ROUTING);
		
		Response response = con.getResultFromWebService(
				sbNewDocumentURL.toString(), 
				"POST", 
				obj.toString());
		
		logger.log(Level.DEBUG, response.toString());
		System.out.println(response);
		
		//TODO check response OK/SUCCESS
		// return ID
		return response;
	}

}