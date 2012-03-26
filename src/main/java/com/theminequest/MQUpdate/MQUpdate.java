package com.theminequest.MQUpdate;

/*
    This file is part of MQUpdate

    MQUpdate is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    MQUpdate is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with MQUpdate.  If not, see <http://www.gnu.org/licenses/>.
 */

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public class MQUpdate extends JavaPlugin {

	//ClassListeners
	private final MQUpdateCommandExecutor commandExecutor = new MQUpdateCommandExecutor(this);
	//ClassListeners

	Logger log = Logger.getLogger("Minecraft");//Define your logger


	public void onDisable() {
		log.info("Bye! MQUpdate is going to sleep.");
	}

	public void onEnable() {
		log.info("MQUpdate up and checking for stuff in the background.");

		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new MQUpdateListener(this), this);
		getCommand("mqupdate").setExecutor(commandExecutor);

	}

	public final String checkforUpdate(){
		try {
			// Create a URL for the desired page
			URL url = new URL("http://build.lincomlinux.org/jenkins/job/MineQuest-1-Core/lastSuccessfulBuild/buildNumber");

			// Read all the text returned by the server
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String str;
			String thisversion = "NOT AVAILABLE";
			while ((str = in.readLine()) != null) {
				thisversion=str;
			}
			in.close();
			return "The latest build of MineQuest is " + thisversion + ": http://goo.gl/3kBXr";
		} catch (MalformedURLException e) {
			return "Can't check for updates :(";
		} catch (IOException e) {
			return "Wtf? Can't do any IO operations? Does I haz perms or an internet connection?";
		}
	}
}
