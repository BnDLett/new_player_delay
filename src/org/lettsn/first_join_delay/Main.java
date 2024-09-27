package org.lettsn.first_join_delay;

import mindustry.mod.Plugin;
import org.lettsn.first_join_delay.server.Configs;
import org.lettsn.first_join_delay.server.Events;

import java.util.HashMap;
import java.util.Map;

public class Main extends Plugin {
    public static Map<String, Long> firstTimePlayerMap = new HashMap<>();
    public static Configs configs = new Configs();

//  public static Player pluginDummyPlayer = new PluginDummyPlayer(); // Experimental code.

    @Override
    public void init() {
//      pluginDummyPlayer.name(pluginMessageName);
        Events.load();
    }
}
