package com.theminequest.MQUpdate;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class MQUpdateListener implements Listener {
	
	private MQUpdate plugin;
	
	public MQUpdateListener(MQUpdate m){
		plugin = m;
	}

	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent e){
    	if (!e.getPlayer().isOp())
    		return;
    	Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable(){
			@Override
			public void run() {
				e.getPlayer().sendMessage(plugin.checkforUpdate());
			}
    	});
	}
	
}
