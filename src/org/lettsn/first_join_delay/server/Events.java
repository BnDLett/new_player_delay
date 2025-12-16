package org.lettsn.first_join_delay.server;

import mindustry.game.EventType;
import mindustry.gen.Player;
import mindustry.net.Administration;
import org.lettsn.first_join_delay.Main;
import org.lettsn.first_join_delay.Utilities.PluginMessage;

import static mindustry.Vars.netServer;

public class Events {
    /**
     * Loads all events for the plugin.
     */
    public static void load() {
        arc.Events.on(EventType.PlayerConnect.class, Events::handleFirstJoin);
        netServer.admins.addActionFilter(Events::checkAction);
        netServer.admins.addChatFilter(Events::checkChat);
    }

    /**
     * Checks if the player is a first time player.
     * @param uuid The UUID of the player..
     * @return A boolean value. `false` if the player is new, and `true` if the player isn't new.
     */
    public static boolean hasPlayedBefore(String uuid) {
        if (Main.firstTimePlayerMap.containsKey(uuid)) {
            Long firstJoinTime = Main.firstTimePlayerMap.get(uuid);
            Integer delayMinutes = Main.configs.getDelayMinutes();

            return (System.currentTimeMillis() - firstJoinTime) >= (delayMinutes * 60_000);
        }

        if (netServer.admins.getInfo(uuid).timesJoined > 1) {
            return true;
        }

        Main.firstTimePlayerMap.put(uuid, System.currentTimeMillis());
        return false;
    }

    /**
     * A method that handles the connection of a player. Primarily revolves around their first join.
     * @param event The PlayerConnect event.
     */
    public static void handleFirstJoin(EventType.PlayerConnect event) {
        if (hasPlayedBefore(event.player.uuid())) {
            return;
        }

        PluginMessage.send(event.player, String.format("It appears as if you're a new player. Do understand that " +
                "you will not be able to do actions for %d minutes. This is to help ensure the safety and security of" +
                " this server.", Main.configs.getDelayMinutes()));
    }

    /**
     * Ensures that new players cannot do non-permitted actions.
     * @param action A PlayerAction object.
     * @return `true` if the player is allowed to do the action; otherwise, false.
     */
    public static boolean checkAction(Administration.PlayerAction action) {
        if (hasPlayedBefore(action.player.uuid())) {
            return true;
        }

        if (action.type != Administration.ActionType.respawn) {
            PluginMessage.send(action.player, "You are not allowed to do much yet!");
            return false;
        }

        return true;
    }

    public static String checkChat(Player player, String message) {
        if (hasPlayedBefore(player.uuid())) {
            return message;
        }

        PluginMessage.send(player, "You are not allowed to chat yet!");
        return null;
    }
}