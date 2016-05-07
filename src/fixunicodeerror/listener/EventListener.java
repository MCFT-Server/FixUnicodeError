package fixunicodeerror.listener;

import java.util.LinkedHashMap;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.event.server.DataPacketSendEvent;
import cn.nukkit.network.protocol.LoginPacket;
import cn.nukkit.network.protocol.TextPacket;
import cn.nukkit.utils.TextFormat;
import fixunicodeerror.Main;

public class EventListener extends BaseListener<Main> {
	
	//private LinkedHashMap<String, Integer> protocol;

	public EventListener(Main plugin) {
		super(plugin);
		//protocol = new LinkedHashMap<String, Integer>();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return false;
	}
	/*
	@EventHandler
	public void onPacketRecieve(DataPacketReceiveEvent event) {
		if (! (event.getPacket() instanceof LoginPacket)) return;
		LoginPacket packet = (LoginPacket) event.getPacket();
		protocol.put(event.getPlayer().getName().toLowerCase(), packet.protocol1);
	}
	*/
	
	@EventHandler
	public void onPacketSend(DataPacketSendEvent event) {
		if (! (event.getPacket() instanceof TextPacket)) return;
		TextPacket packet = (TextPacket) event.getPacket();
		//if (protocol.get(event.getPlayer().getName().toLowerCase()) == 60)
			packet.message = fixUnicode(packet.message);
	}
	
	private String fixUnicode(String str) {
		String fixstr = str;
		char uni = 'ａ';
		
		for (int i = 'a'; i < 'z'; i++) {
			fixstr = fixstr.replace((char)i, uni++);
		}
		
		uni = 'Ａ';
		for (int i = 'A'; i < 'Z'; i++) {
			fixstr = fixstr.replace((char)i, uni++);
		}
		
		uni = '０';
		for (int i = '0'; i < '9'; i++) {
			fixstr = fixstr.replace((char)i, uni++);
		}
		
		uni = '！';
		for (int i = '!'; i < 127; i++) {
			if (i == '%') {
				uni++;
				continue;
			}
			fixstr = fixstr.replace((char)i, uni++);
		}
		
		uni = 'ａ';
		for (int i = 'a'; i < 'z'; i++) {
			fixstr = fixstr.replace("§" + uni++, "§" + (char)i);
		}
		
		uni = '０';
		for (int i = '0'; i <= '9'; i++) {
			fixstr = fixstr.replace("§" + uni++, "§" + (char)i);
		}
		
		return fixstr;
	}
}
