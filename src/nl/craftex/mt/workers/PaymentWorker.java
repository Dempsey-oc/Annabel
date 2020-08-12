package nl.craftex.mt.workers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import nl.craftex.mt.Main;

public class PaymentWorker implements Runnable{

	public void run() {
		for(Player pls : Bukkit.getOnlinePlayers()) {
			Main.econ.depositPlayer(pls, 2000);
			double val = Double.valueOf(Bukkit.getOnlinePlayers().size());
			double res = val*10;
			Main.econ.depositPlayer(pls, res);
			pls.sendMessage("§8[§aLoon§8] §aJe hebt $2000 ontvangen als uitkering!");
			pls.sendMessage("§8[§aLoon§8] §aJe ontvieng €" + String.valueOf(res) + " als bonus!");
		}
	}
	
}
