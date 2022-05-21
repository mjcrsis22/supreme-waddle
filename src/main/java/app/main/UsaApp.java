package app.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import app.services.interfaces.ServicioCuenta;
import app.services.interfaces.ServicioTransferencia;

// desde la ubicación del jar: /c/Users/A129057/Downloads/h2-2022-04-09/h2/bin
// ejecutar: java -jar h2-2.1.212.jar

public class UsaApp {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/app-context.xml");

		ServicioCuenta servicioCuenta = ctx.getBean("servicioCuenta", ServicioCuenta.class);
		ServicioTransferencia servicioTransferencia = ctx.getBean("servicioTransferencia", ServicioTransferencia.class);

		servicioCuenta.loadData();

		try {
			//servicioCuenta.agregarCotitular(2L, 3L);
			servicioTransferencia.realizarTransferencia(3L, 250.0, 4L);
		} catch (Exception e) {
			e.printStackTrace();
		}

		((ConfigurableApplicationContext) ctx).close();
	}
}
