package app.models;

import javax.persistence.AttributeConverter;

public class MonedaExtranjeraConverter implements AttributeConverter<MonedaExtranjera, Character> {

	@Override
	public Character convertToDatabaseColumn(MonedaExtranjera monedaExtranjera) {
		switch (monedaExtranjera) {
		case USD:
			return 'U';
		case EUR:
			return 'E';
		default:
			throw new IllegalArgumentException(monedaExtranjera + " Desconocido");
		}
	}

	@Override
	public MonedaExtranjera convertToEntityAttribute(Character character) {
		switch (character) {
		case 'U':
			return MonedaExtranjera.USD;
		case 'E':
			return MonedaExtranjera.EUR;
		default:
			throw new IllegalArgumentException(character + " Desconocida");
		}
	}

}
