package views;

import java.util.List;

import play.Play;
import play.mvc.Http.Request;

public class AdminChecker {
	public static boolean check(Request request) {
		List<Object> ips = Play.application().configuration()
				.getList("admin.ips");
		for (Object ip : ips) {
			if (request.remoteAddress().equals(ip.toString())) {
				return true;
			}
		}
		return false;
	}
}
