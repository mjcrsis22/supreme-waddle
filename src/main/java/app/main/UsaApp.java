package app.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import app.services.interfaces.ServicioCuenta;
import app.services.interfaces.ServicioMonedaExtranjera;
import app.services.interfaces.ServicioTransferencia;

public class UsaApp {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/app-context.xml");

		ServicioCuenta servicioCuenta = ctx.getBean("servicioCuenta", ServicioCuenta.class);
		ServicioTransferencia servicioTransferencia = ctx.getBean("servicioTransferencia", ServicioTransferencia.class);
		ServicioMonedaExtranjera servicioMonedaExtranjera = ctx.getBean("servicioMonedaExtranjera",
				ServicioMonedaExtranjera.class);

		try {
			servicioCuenta.agregarCotitular(2L, 3L);
			servicioTransferencia.realizarTransferencia(3L, 250.0, 4L);
			servicioMonedaExtranjera.venderMonedaExtranjera(1L, 5L, 3L, 25.0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		((ConfigurableApplicationContext) ctx).close();
	}
}
