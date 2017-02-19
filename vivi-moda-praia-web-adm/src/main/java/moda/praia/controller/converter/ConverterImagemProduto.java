package moda.praia.controller.converter;

import java.io.IOException;

import moda.praia.modulo.produtos.bean.ImagemProduto;
import moda.praia.util.ResizeImage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ConverterImagemProduto {

	private final Logger log = Logger.getLogger(ConverterImagemProduto.class);
	
	public ImagemProduto coverter(MultipartFile multiPartFile){
		ImagemProduto imagemProduto = new ImagemProduto();
		imagemProduto.setContentType(multiPartFile.getContentType());
		
		try {
			
			imagemProduto.setImagem(ResizeImage.scaleImage(multiPartFile.getBytes(), 600, 0));
			imagemProduto.setImagemMedia(ResizeImage.scaleImage(multiPartFile.getBytes(),200, 0));
			imagemProduto.setImagemMediaPequena(ResizeImage.scaleImage(multiPartFile.getBytes(),100, 0));
			imagemProduto.setImagemPequena(ResizeImage.scaleImage(multiPartFile.getBytes(),60, 0));
			
			imagemProduto.setNomeImagem(multiPartFile.getOriginalFilename());
			
			return imagemProduto;
			
		} catch (IOException e) {
			log.equals("Erro ao converter ImagemProduto");
		}
		
		return null;
		
	}
}
