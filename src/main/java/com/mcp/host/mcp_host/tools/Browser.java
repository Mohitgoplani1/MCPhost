package com.mcp.host.mcp_host.tools;

import com.mcp.host.mcp_host.model.MCPResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class Browser {
    private static String defaultBrowser = "chrome";

    public static String getDefaultBrowser() {
        return defaultBrowser;
    }

    public static void setDefaultBrowser(String browser) {
        defaultBrowser = browser;
    }

    // Entry point
    public static MCPResponse handleBrowserAction(String action, Map<String, String> parameters) {
        switch (action) {
            case "openUrl":
                return openUrl(parameters.get("url"));
            case "openNewTab":
                return openNewTab(parameters.get("url"));
            case "openWindow":
                return openWindow(parameters.get("url"));
            case "openIncognito":
                return openIncognito(parameters.get("url"));
            default:
                return new MCPResponse("error", "No such Actions Allowed");
        }
    }

    private static MCPResponse openUrl(String url) {
        return runCommand(defaultBrowser, new String[]{url});
    }

    private static MCPResponse openNewTab(String url) {
        return runCommand(defaultBrowser, new String[]{"--new-tab", url});
    }

    private static MCPResponse openIncognito(String url) {
        return runCommand(defaultBrowser, new String[]{"--incognito", url});
    }

    private static MCPResponse openWindow(String url) {
        return runCommand(defaultBrowser, new String[]{"--new-window", url});
    }

    private static MCPResponse runCommand(String browser, String[] args) {
        try {
            List<String> command = new ArrayList<>();
            command.add("cmd");
            command.add("/c");
            command.add("start");
            command.add(browser);

            if (args != null) {
                for (String arg : args) {
                    command.add(arg);
                }
            }

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.start();
            return new MCPResponse("success", "Task Completed");
        } catch (IOException e) {
            return new MCPResponse("error", e.getMessage());
        }
    }
}
