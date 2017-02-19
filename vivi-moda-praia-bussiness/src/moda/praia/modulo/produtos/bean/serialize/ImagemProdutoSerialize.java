package moda.praia.modulo.produtos.bean.serialize;

import java.io.IOException;

import moda.praia.modulo.produtos.bean.ImagemProduto;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ImagemProdutoSerialize extends JsonSerializer<ImagemProduto> {


	@Override
	public void serialize(ImagemProduto imagemProduto, JsonGenerator generator,
			SerializerProvider arg2) throws IOException,
			JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		generator.writeObject(mapper.writer().writeValueAsString(imagemProduto));
		
	}

}
