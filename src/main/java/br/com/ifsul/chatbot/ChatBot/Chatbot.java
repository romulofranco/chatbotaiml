package br.com.ifsul.chatbot.ChatBot;

import java.io.File;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;

public class Chatbot {

    private static final boolean TRACE_MODE = false;
    static String botName = "super";
    private String resourcesPath;
    private Chat chatSession;
    private Bot bot;

    public Chatbot() {
        resourcesPath = getResourcesPath();
        MagicBooleans.trace_mode = TRACE_MODE;

        bot = new Bot("super", resourcesPath);

        bot.writeAIMLIFFiles();
        chatSession = new Chat(bot);
        bot.brain.nodeStats();
    }

    public String respostaInteligente(String textLine) {
        try {
            System.out.println(resourcesPath);

            if ((textLine == null) || (textLine.length() < 1)) {
                textLine = MagicStrings.null_input;
            }
            if (textLine.equals("q")) {
                System.exit(0);
                return "Bot foi encerrado";
            } else if (textLine.equals("wq")) {
                bot.writeQuit();
                System.exit(0);
                return "Bot foi encerrado";
            } else {
                String request = textLine;
                if (MagicBooleans.trace_mode) {
                    System.out.println(
                            "STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0)
                            + ":TOPIC=" + chatSession.predicates.get("topic"));
                }
                String response = chatSession.multisentenceRespond(request);
                while (response.contains("&lt;")) {
                    response = response.replace("&lt;", "<");
                }
                while (response.contains("&gt;")) {
                    response = response.replace("&gt;", ">");
                }
                System.out.println("Robot : " + response);

                return response;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Falha";
    }

    private String getResourcesPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        System.out.println(path);
        return path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
    }
}
