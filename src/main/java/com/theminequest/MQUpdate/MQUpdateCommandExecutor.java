package com.theminequest.MQUpdate;

/*
    This file is part of MQUpdate

    Foobar is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Foobar is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class MQUpdateCommandExecutor implements CommandExecutor {

    private MQUpdate plugin;
    Logger log = Logger.getLogger("Minecraft");//Define your logger

    public MQUpdateCommandExecutor(MQUpdate plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(final CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("mqupdate")) {
        	if (!sender.isOp())
        		return false;
        	Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable(){
				@Override
				public void run() {
					sender.sendMessage(plugin.checkforUpdate());
				}
        	});
        	return true;
        }
        return false;
    }
}
