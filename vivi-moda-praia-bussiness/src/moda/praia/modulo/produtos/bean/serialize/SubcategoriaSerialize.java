package moda.praia.modulo.produtos.bean.serialize;

import java.io.IOException;

import moda.praia.modulo.produtos.bean.Subcategoria;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

public class SubcategoriaSerialize extends JsonSerializer<Subcategoria> {

	@Override
	public void serialize(Subcategoria subcategoria, JsonGenerator generator,
			SerializerProvider arg2) throws IOException,
			JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		generator.writeObject(mapper.writer().writeValueAsString(subcategoria));
	}

}
