package tr.bel.gebze.springcourse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;

/**
 * <p>This bean is only available on development and shows a desktop notification when the application is started or reloaded(by spring devtools)</p>
 * <p>You should give @{code -Djava.awt.headless=false} JVM argument for the notification work.</p>
 * <p>Icon source: https://www.iconfinder.com/icons/52668/java_icon</p>
 * Created on August, 2017
 *
 * @author destan
 */
@Profile("dev")
@Component
@Slf4j
class DesktopNotification {

	private final String appName;

	private TrayIcon trayIcon;

	public DesktopNotification(@Value("${spring.application.name:Spring Boot Application}") String appName) {
		this.appName = appName;
	}


	@EventListener
	private void onContextRefreshedEvent(ContextRefreshedEvent event) {
		displayTray();
	}

	@EventListener
	private void onContextClosedEvent(ContextClosedEvent event) {
		removeIcon();
	}

	/**
	 * Thanks: https://stackoverflow.com/a/34490485/8405129
	 */
	private void displayTray() {

		try {
			System.setProperty("java.awt.headless", "false");
			if (SystemTray.isSupported()) {
				final Image image = ImageIO.read(ClassLoader.getSystemResource("systemTrayIcon.png"));

				trayIcon = new TrayIcon(image, "Desktop Notification for development");
				trayIcon.setImageAutoSize(true);
				SystemTray.getSystemTray().add(trayIcon);
				trayIcon.displayMessage(appName, "Application started!", TrayIcon.MessageType.INFO);
				trayIcon.addActionListener(e -> removeIcon());
			}
			else {
				log.error("System Tray is not supported. Try giving '-Djava.awt.headless=false' as a VM option.");
			}
		}
		catch (Exception e) {
			log.error("Unknown error occurred while trying to show system tray icon: " + e.getMessage(), e);
		}
	}

	private void removeIcon() {
		if (SystemTray.isSupported() && trayIcon != null) {
			SystemTray.getSystemTray().remove(trayIcon);
			trayIcon = null;
		}
	}
}