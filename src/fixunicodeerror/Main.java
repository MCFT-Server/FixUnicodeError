package fixunicodeerror;

import cn.nukkit.plugin.PluginBase;
import fixunicodeerror.listener.EventListener;

public class Main extends PluginBase {
	private EventListener listener;
	
	@Override
	public void onEnable() {
		listener = new EventListener(this);
		
		getServer().getPluginManager().registerEvents(listener, this);
	}
}
