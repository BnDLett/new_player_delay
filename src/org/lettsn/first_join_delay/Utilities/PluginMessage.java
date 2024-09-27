package org.lettsn.first_join_delay.Utilities;

import mindustry.gen.Player;

/**
 * A wrapper to send messages in the name of the plugin. This shouldn't be used for external projects.
 */
public class PluginMessage {
    @SuppressWarnings("FieldCanBeLocal")
    private static final String pluginMessageName = "[accent]{[gray]New Player Action Delay[accent]}[white]";

    public static void send(Player player, String text) {
        player.sendMessage(String.format("%s: %s", pluginMessageName, text));
    }
}
