package moda.praia.util;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moda.praia.modulo.produtos.ProdutoBusiness;
import moda.praia.modulo.produtos.bean.ImagemProduto;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


@Component
public class ImageServlet extends HttpServlet {

	private final static Logger log = Logger.getLogger(ImageServlet.class);
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.

	@Autowired
	private ProdutoBusiness produtoBusiness;

	public void init(ServletConfig config) {
		 SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

			// Get ID from request.
			String imageId = request.getParameter("id");
			String tamanhoImagemString = TAMANHO_PEQUENO;

			if (request.getParameter("tamanhoImagem") != null) {
				tamanhoImagemString = request.getParameter("tamanhoImagem");

			}

			byte[] imagemProdutoOut;


			// Check if ID is supplied to the request.
			if (imageId == null || imageId.equals("")) {
				// Do your thing if the ID is not supplied to the request.
				// Throw an exception, or send 404, or show default/warning
				// image,
				// or just ignore it.
				response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
				return;
			}
			ImagemProduto imagemProduto = null;
			if(imageId != null && imageId.matches("[0-9]*")){
				imagemProduto = produtoBusiness.pesquisaImagemProdutoPorID(Long.valueOf(imageId));
			}

			// Lookup Image by ImageId in database.
			// Do your "SELECT * FROM Image WHERE ImageID" thing.

			// Check if image is actually retrieved from database.
			if (imagemProduto == null) {
				// Do your thing if the image does not exist in database.
				// Throw an exception, or send 404, or show default/warning
				// image,
				// or just ignore it.
				response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
				return;
			}

			
			imagemProdutoOut = null;
			if(tamanhoImagemString.equals(TAMANHO_NORMAL)){
				imagemProdutoOut = imagemProduto.getImagem();
			}else{
				if(tamanhoImagemString.equals(TAMANHO_MEDIO)){
					imagemProdutoOut = imagemProduto.getImagemMedia();
				}else{
				
					if(tamanhoImagemString.equals(TAMANHO_MEDIO_PEQUENO)){
						imagemProdutoOut = imagemProduto.getImagemMediaPequena();
					}else{
						imagemProdutoOut = imagemProduto.getImagemPequena();
					}
					
				}
				
			}
			
			// Init servlet response.
			response.reset();
			response.setBufferSize(DEFAULT_BUFFER_SIZE);
			response.setContentType(imagemProduto.getContentType());
			response.setContentLength(imagemProdutoOut.length);
			response.setHeader("Content-Disposition", "inline; filename=\""	+ imagemProduto.getNomeImagem() + "\"");

			// Prepare streams.
			BufferedOutputStream output = null;

			try {
				// Open streams.
				output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

				// Write file contents to response.
				output.write(imagemProdutoOut);
			} finally {
				// Gently close streams.
				close(output);
			}
	}

	
	private static void close(Closeable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (IOException e) {
				// Do your thing with the exception. Print it, log it or mail
				// it.
				log.error("erro ao fechar leitura da imagem" + e );
			}
		}
	}
	
	private String TAMANHO_NORMAL = "normal";
	private String TAMANHO_MEDIO = "medio";
	private String TAMANHO_PEQUENO = "pequeno";
	private String TAMANHO_MEDIO_PEQUENO = "medio_pequeno";
}
