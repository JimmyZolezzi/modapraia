package moda.praia.modulo.produtos.bean.serialize;

import java.io.IOException;



import moda.praia.modulo.produtos.bean.Categoria;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CategoriaSerialize extends JsonSerializer<Categoria> {

	@Override
	public void serialize(Categoria categoria, JsonGenerator generator,
			SerializerProvider serializer) throws IOException,
			JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		generator.writeObject(mapper.writer().writeValueAsString(categoria));
	}
	
}
