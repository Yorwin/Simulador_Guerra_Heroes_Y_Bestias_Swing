package Ejercito;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import Excepciones.ReclutaNoValidoException;
import Personajes.Heroe;
import Personajes.Personaje;
import TiposYInterfaces.tipoEjercito;

class EjercitoTest {

	Ejercito<Heroe> ejercito;
	Heroe heroe;

	@BeforeEach
	void creacionEjercito() {
		ejercito = new Ejercito<Heroe>();
		heroe = (Heroe) new Personaje.Builder().nombre("Legolas").puntosDeVida(100).nivelResistencia(35)
				.tipoPersonaje("Elfo").tipoRecluta(tipoEjercito.HEROES).build();
	}

	@Tag("modificacion-ejercito")
	@DisplayName("Añadir nuevos reclutas")
	@Test
	void añadirReclutasTest() throws ReclutaNoValidoException {
		int lengthAntesDeAnadir = ejercito.length();

		Assertions.assertThrows(IllegalArgumentException.class, () -> ejercito.anadirRecluta(null));
		// Un intento fallido no debe modificar el ejército.
		Assertions.assertEquals(lengthAntesDeAnadir, ejercito.length());

		ejercito.anadirRecluta(heroe);
		Assertions.assertTrue(ejercito.getReclutas().contains(heroe));
		Assertions.assertEquals(lengthAntesDeAnadir + 1, ejercito.length());
	}

	@Tag("modificacion-ejercito")
	@DisplayName("Un recluta inválido lanza ReclutaNoValidoException")
	@Test
	void reclutaInvalidoLanzaExcepcionTest() {
		// Ajusta los valores a lo que tu lógica considere "no válido".
		Assertions.assertThrows(ReclutaNoValidoException.class,
				() -> ejercito.anadirRecluta((Heroe) new Personaje.Builder().nombre("Debil").puntosDeVida(-5)
						.nivelResistencia(1).tipoPersonaje("Elfo").tipoRecluta(tipoEjercito.HEROES).build()));
		// El ejército no debe haber cambiado.
		Assertions.assertEquals(0, ejercito.length());
	}

	@Tag("modificacion-ejercito")
	@DisplayName("Retirar reclutas existentes")
	@Test
	void retirarReclutasTest() throws ReclutaNoValidoException {
		ejercito.anadirRecluta(heroe);
		int lengthAntesDeRetirar = ejercito.length();

		Assertions.assertThrows(IllegalArgumentException.class, () -> ejercito.retirarRecluta(null));
		// Un intento fallido no debe modificar el ejército.
		Assertions.assertEquals(lengthAntesDeRetirar, ejercito.length());

		ejercito.retirarRecluta(heroe);
		Assertions.assertEquals(lengthAntesDeRetirar - 1, ejercito.length());

		// Retirar un recluta que ya no está devuelve false.
		Assertions.assertFalse(ejercito.retirarRecluta(heroe));
	}

	@Tag("modificacion-ejercito")
	@DisplayName("Cambiar orden reclutas")
	@Test
	void cambiarOrdenReclutasTest() throws ReclutaNoValidoException {
		Heroe segundoHeroe = (Heroe) new Personaje.Builder().nombre("Aragorn").puntosDeVida(150).nivelResistencia(50)
				.tipoPersonaje("Humano").tipoRecluta(tipoEjercito.HEROES).build();

		ejercito.anadirRecluta(heroe);
		ejercito.anadirRecluta(segundoHeroe);

		Assertions.assertEquals(heroe, ejercito.getReclutas().get(0));

		ejercito.cambiarOrden(0, 1);
		Assertions.assertEquals(segundoHeroe, ejercito.getReclutas().get(0));
		Assertions.assertEquals(heroe, ejercito.getReclutas().get(1));

		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> ejercito.cambiarOrden(0, 4));
	}

	@Tag("comprobacion-estado-ejercito")
	@DisplayName("Comprobar longitud ejercito")
	@Test
	void comprobarLengthTest() throws ReclutaNoValidoException {
		ejercito.anadirRecluta(heroe);
		Assertions.assertEquals(1, ejercito.length());

		ejercito.retirarRecluta(heroe);
		Assertions.assertEquals(0, ejercito.length());
	}

	@Tag("comprobacion-estado-ejercito")
	@DisplayName("Obtener reclutas")
	@Test
	void obtenerReclutasTest() throws ReclutaNoValidoException {
		ArrayList<Heroe> reclutasEjercitoHeroeObtenido = ejercito.getReclutas();
		Assertions.assertEquals(0, reclutasEjercitoHeroeObtenido.size());

		ejercito.anadirRecluta(heroe);
		reclutasEjercitoHeroeObtenido = ejercito.getReclutas();
		Assertions.assertEquals(1, reclutasEjercitoHeroeObtenido.size());

		Assertions.assertEquals(heroe, reclutasEjercitoHeroeObtenido.get(0));
	}

	@Tag("copiar-ejercito")
	@DisplayName("Realizar copia ejercito")
	@Test
	void copiaEjercitoTest() throws ReclutaNoValidoException {
		Ejercito<Heroe> copiaEjercitoVacio = ejercito.copiar();
		ejercito.anadirRecluta(heroe);
		Ejercito<Heroe> copiaTrasAnadirUnHeroe = ejercito.copiar();

		Assertions.assertEquals(0, copiaEjercitoVacio.length());
		Assertions.assertEquals(1, copiaTrasAnadirUnHeroe.length());
	}
}