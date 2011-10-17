package com.asp.bot;

import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

import com.asp.bot.listners.TheRobotChatListener;

public class TheRobot {
	public static void main(String[] args) {
		ConnectionConfiguration cc = new ConnectionConfiguration("talk.google.com", 5222,
				"talk.google.com");
		// Assumes you have the SRV records setup in DNS
		XMPPConnection conn = new XMPPConnection(cc);
		try {
			conn.connect();

			// You have to put this code before you login
			//SASLAuthentication.supportSASLMechanism("PLAIN", 0);

			//conn.login("TheRobot", "passwd");
			conn.login("arunshankerprasad@gmail.com", "p@sswd");
			ChatManager chatManager = conn.getChatManager();
			chatManager.addChatListener(new TheRobotChatListener());

			System.out.println("Connected");
			while (conn.isConnected()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			conn.disconnect();
		} catch (XMPPException e) {
			e.printStackTrace();
		}
	}
}
