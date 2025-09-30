package com.mcp.host.mcp_host;

import com.google.gson.Gson;
import com.mcp.host.mcp_host.core.MCPHost;
import com.mcp.host.mcp_host.model.MCPRequest;
import com.mcp.host.mcp_host.model.MCPResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class McpHostApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(McpHostApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		String jsonInput= """
				{
				"tool":"Browser",
				"action":"openIncognito",
				"parameters":{
					"url":"www.google.com"
				}
				}
				""";
		Gson gson=new Gson();
		MCPRequest request=gson.fromJson(jsonInput, MCPRequest.class);
		MCPHost host=new MCPHost();
		MCPResponse response=host.handle(request);
		System.out.println("Status:"+response.status);
		System.out.println("Message:"+response.message);
	}
}
