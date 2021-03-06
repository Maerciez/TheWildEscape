package teamdjg.wildescape.worldborderCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import teamdjg.wildescape.main.Main;

public class WorldborderSetupcommand implements CommandExecutor {

	Main mainclass;
	
	public WorldborderSetupcommand(Main plugin)
	{
		this.mainclass = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {		
		
		if(sender instanceof Player)
		{
			if(!sender.hasPermission(mainclass.bordersetupPermission))
			{
				sender.sendMessage(mainclass.pluginPrefix + ChatColor.DARK_RED + "ERROR: you don't have permission for this plugin.");
				return true;
			}
		}
		
		if(sender instanceof Player)
		{
			Player p = (Player)sender;
			
			//check if there are 4 args
			if(args.length != 4)
			{
				WrongArgsMessage(p);
				return true;
			}
			else
			{
				int minborder;
				int maxborder;
				int distanceborder;
				long speedborder;
				
				//check if all types are the right one
				try 
				{
					minborder = Integer.parseInt(args[0]);
					maxborder = Integer.parseInt(args[1]);
					distanceborder = Integer.parseInt(args[2]);
					speedborder = Long.parseLong(args[3]); 
				}
				catch(Exception e)
				{
					p.sendMessage(mainclass.ChatLine());
					p.sendMessage(mainclass.pluginPrefix + ChatColor.DARK_RED + "ERROR:" + ChatColor.GOLD + " You need to put numbers, not letters in the arguments.");
					p.sendMessage(mainclass.ChatLine());
					return true;
				}
				
				//check if variables from the minimal, max and distance
				if(distanceborder <= 0 || minborder < 0 || maxborder < minborder || speedborder < 0)
				{
					p.sendMessage(mainclass.ChatLine());
					p.sendMessage(mainclass.pluginPrefix + ChatColor.DARK_RED + "ERROR:" + ChatColor.GOLD + "Your minimal border can't be below 0.");
					p.sendMessage(mainclass.pluginPrefix + ChatColor.GOLD + "Also your maximum border can't be below the minimal border.");
					p.sendMessage(mainclass.pluginPrefix + ChatColor.GOLD + "And your distance for the border to move must be bigger than 0, also your speed from the border to move must be bigger then -1.");
					p.sendMessage(mainclass.ChatLine());
					return true;
				}
				
				//change variables
				mainclass.WorldBorderMin = minborder;
				mainclass.WorldBorderMax = maxborder;
				mainclass.WordBorderDistance = distanceborder;
				mainclass.WorldBorderSpeed = speedborder;
				mainclass.ContactPlayerForWorldBorder = p;
				mainclass.WorldBorderWorldName = p.getWorld().getName();
				mainclass.WorldborderSetupCheck = true;
								
				//instantiation message
				p.sendMessage(mainclass.ChatLine());
				p.sendMessage(mainclass.pluginPrefix + ChatColor.GOLD + "Your variables where saved! :");
				p.sendMessage(mainclass.pluginPrefix + ChatColor.GOLD + "Minimal border                   = " + String.valueOf(minborder));
				p.sendMessage(mainclass.pluginPrefix + ChatColor.GOLD + "Maximal border                  = " + String.valueOf(maxborder));
				p.sendMessage(mainclass.pluginPrefix + ChatColor.GOLD + "Speed for border to move    = " + String.valueOf(distanceborder));
				p.sendMessage(mainclass.pluginPrefix + ChatColor.GOLD + "distance for border to move = " + String.valueOf(speedborder));
				p.sendMessage(mainclass.pluginPrefix + ChatColor.RED + "To move the center from the border, stand on the block where you want the center and type /borderCenter.");
				p.sendMessage(mainclass.ChatLine());
				
				mainclass._WorldborderMechanics.SetBorderStart();
			}
		}
		else
		{
			System.out.print(mainclass.pluginPrefix + ChatColor.DARK_RED + "Only a player can perform this command.");
		}
		
		return true;
	}
	
	void WrongArgsMessage(Player p)
	{
		p.sendMessage(mainclass.ChatLine());
		p.sendMessage(mainclass.pluginPrefix + ChatColor.DARK_RED + "ERROR:" + ChatColor.GOLD + "You don't have the good arguments!");
		p.sendMessage(mainclass.pluginPrefix + ChatColor.GOLD + "Type - /help borderSetup - for more information.");
		p.sendMessage(mainclass.ChatLine());
	}
}
