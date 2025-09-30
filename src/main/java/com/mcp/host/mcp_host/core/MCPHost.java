package com.mcp.host.mcp_host.core;

import com.mcp.host.mcp_host.model.MCPRequest;
import com.mcp.host.mcp_host.model.MCPResponse;
import com.mcp.host.mcp_host.tools.*;

public class MCPHost {
    public MCPResponse handle(MCPRequest request){
        switch(request.tool){

            case "FileManager":
                return FileManager.handleFileAction(request.action,request.parameters);

            case "ApplicationLauncher":
                return ApplicationLauncher.handleApplicationAction(request.action,request.parameters);

            case "CommandPrompt":
                return CommandPrompt.handleCommandAction(request.action,request.parameters);

            case "NetworkingCommand" :
                return NetworkingCommands.handleNetworkingAction(request.action,request.parameters);

            case "MediaTool":
                return MediaTool.handleMediaAction(request.action,request.parameters);
            case "Browser":
                return Browser.handleBrowserAction(request.action,request.parameters);

            default:
                return new MCPResponse("error","Unknown tool : "+request.tool);
        }
    }
}
