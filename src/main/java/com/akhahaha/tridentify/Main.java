package com.akhahaha.tridentify;

import com.akhahaha.tridentify.model.Triangle;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.Properties;

/**
 * Main executable
 * Created by Alan on 9/1/2016.
 */
public class Main {
    private static final String RESOURCE_APP_PROPERTIES = "app.properties";
    private static final String APPLICATION_PROP_NAME = "application.name";
    private static final String APPLICATION_PROP_VERSION = "application.version";
    private static final String APPLICATION_PROP_DESC = "application.desc";

    public static void main(String[] args) {
        String APP_NAME = "";
        String APP_VERSION = "";
        String APP_DESC = "";

        // Get application properties
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            Properties applicationProperties = new Properties();
            applicationProperties.load(loader.getResourceAsStream(RESOURCE_APP_PROPERTIES));
            APP_NAME = applicationProperties.getProperty(APPLICATION_PROP_NAME);
            APP_VERSION = applicationProperties.getProperty(APPLICATION_PROP_VERSION);
            APP_DESC = applicationProperties.getProperty(APPLICATION_PROP_DESC);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create options
        Options options = new Options();

        Option optHelp = Option
                .builder("h")
                .longOpt("help")
                .desc("")
                .build();
        options.addOption(optHelp);

        Option optVersion = Option
                .builder("v")
                .longOpt("version")
                .desc("")
                .build();
        options.addOption(optVersion);

        String[] argNames = new String[]{"length1", "length2", "length3"};

        // Parse command line arguments
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            printHelp(APP_NAME, APP_DESC, options, argNames);
            return;
        }

        // Process arguments
        if (cmd.hasOption(optHelp.getOpt())) {
            printHelp(APP_NAME, APP_DESC, options, argNames);
            return;
        }

        if (cmd.hasOption(optVersion.getOpt())) {
            System.out.println(APP_NAME + " " + APP_VERSION);
            return;
        }

        // Ensure there are 3 lengths
        if (cmd.getArgList().size() == 3) {
            // Execute
            String length1 = cmd.getArgList().get(0);
            String length2 = cmd.getArgList().get(1);
            String length3 = cmd.getArgList().get(2);
            try {
                Triangle triangle = new Triangle(
                        Double.parseDouble(length1),
                        Double.parseDouble(length2),
                        Double.parseDouble(length3));
                System.out.println(triangle.getType().name());
            } catch (NumberFormatException e) {
                System.err.println("Triangle lengths must be numerical");
            } catch (Exception e) {
                System.err.print(e.getMessage());
            }
        } else {
            printHelp(APP_NAME, APP_DESC, options, argNames);
        }
    }

    private static void printHelp(String appName, String appDesc, Options options, String[] argNames) {
        StringBuilder builder = new StringBuilder();
        // Construct usage
        builder.append("usage: ").append(appName);

        if (!options.getOptions().isEmpty()) {
            builder.append(" ");
            for (Option option : options.getOptions()) {
                builder.append(" ");

                if (option.isRequired()) {
                    builder.append("<-");
                } else {
                    builder.append("[-");
                }

                builder.append(option.getOpt());

                if (option.isRequired()) {
                    builder.append(">");
                } else {
                    builder.append("]");
                }
            }
        }

        if (argNames != null && argNames.length > 0) {
            for (String argName : argNames) {
                builder.append(" ");
                builder.append(argName);
            }
        }

        builder.append("\n");
        builder.append(appDesc);
        builder.append("\n\n");

        // List options
        for (Option option : options.getOptions()) {
            builder.append("  -");
            builder.append(option.getOpt());
            builder.append(" --");
            builder.append(option.getLongOpt());
            if (option.getDescription() != null && option.getDescription().length() > 0) {
                builder.append(" ");
                builder.append(option.getDescription());
            }
            builder.append("\n");
        }

        // Print
        System.out.print(builder);
    }
}
