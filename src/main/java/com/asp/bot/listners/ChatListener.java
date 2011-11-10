package com.asp.bot.listners;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.muc.MultiUserChat;

public class ChatListener implements PacketListener {

	private MultiUserChat muc;

	public ChatListener(MultiUserChat muc) {
		this.muc = muc;
	}

	public void processPacket(Packet packet) {
		try {
			Message m = (Message) packet;
			System.out.println(m.getBody());
			if (m.getBody().equalsIgnoreCase("$get")) {
				muc.sendMessage("You got my attention!");
				muc.sendMessage("now make me do stuff...");
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

}
