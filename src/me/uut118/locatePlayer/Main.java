package me.uut118.locatePlayer;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin implements CommandExecutor {

	@Override
	public void onEnable() {
		PluginCommand locatePlayerCommand = super.getCommand("locateplayer");
		locatePlayerCommand.setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandName, String[] arguments) {
		if (arguments.length != 1) {
			sender.sendMessage("§cWrong number of arguments, §lUsage:§r§c /locateplayer <player>");
			return true;
		}
		
		Player target = getServer().getPlayer(arguments[0]);
		if (target == null) {
			sender.sendMessage("§cPlayer "+arguments[0]+" is not online or doesn't exist.");
			return true;
		}
		
		Location pos = target.getLocation();
		
		sender.sendMessage(
			"Player "+target.getDisplayName()+" is at §a§l["+
			pos.getBlockX()+" "+
			pos.getBlockY()+" "+
			pos.getBlockZ()+"]§r in dimension §a§l"+
			pos.getWorld().getName()
		);
		
		target.addPotionEffect(PotionEffectType.GLOWING.createEffect(100,1));
		return true;
	}

}
