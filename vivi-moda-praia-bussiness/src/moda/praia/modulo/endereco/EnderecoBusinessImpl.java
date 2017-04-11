package moda.praia.modulo.endereco;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class EnderecoBusinessImpl implements EnderecoBusiness{
	
	private final Logger log = Logger.getLogger(EnderecoBusinessImpl.class);

	@Override
	public Endereco buscarEndereco(String cep) {

		Endereco endereco = new Endereco();
		// a string da url
		String urlString = "http://cep.republicavirtual.com.br/web_cep.php";

		// os parametros a serem enviados
		Properties parameters = new Properties();
		parameters.setProperty("cep", cep);
		parameters.setProperty("formato", "xml");

		// o iterador, para criar a URL
		Iterator i = parameters.keySet().iterator();
		// o contador
		int counter = 0;

		// enquanto ainda existir parametros
		while (i.hasNext()) {

			// pega o nome
			String name = (String) i.next();
			// pega o valor
			String value = parameters.getProperty(name);

			// adiciona com um conector (? ou &)
			// o primeiro � ?, depois s�o &
			urlString += (++counter == 1 ? "?" : "&") + name + "=" + value;

		}

		try {
			// cria o objeto url
			URL url = new URL(urlString);

			// cria o objeto httpurlconnection
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// seta o metodo
			connection.setRequestProperty("Request-Method", "GET");

			// seta a variavel para ler o resultado
			connection.setDoInput(true);
			connection.setDoOutput(false);

			// conecta com a url destino
			connection.connect();

			// abre a conex�o pra input
			BufferedReader br = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			// le ate o final
			StringBuffer newData = new StringBuffer();
			String s = "";
			while (null != ((s = br.readLine()))) {
				newData.append(s);
			}
			br.close();

			// Controi classe a partir do XML
			XStream xstream = new XStream(new DomDriver());
			//Annotations.configureAliases(xstream, CepServiceVO.class);
			xstream.alias("webservicecep", CepServiceVO.class);
			CepServiceVO cepServiceVO = (CepServiceVO) xstream.fromXML(newData.toString());

			
			endereco.setBairro(cepServiceVO.getBairro());
			endereco.setCep(cep);
			endereco.setCidade(cepServiceVO.getCidade());
			endereco.setEndereco(cepServiceVO.getTipo_logradouro() + " " + cepServiceVO.getLogradouro());
			endereco.setEstado(cepServiceVO.getUf());
			
			// Imprime Resuntado Final
		//	System.out.println(new String(newData));

		} catch (Exception e) {
			log.error("Erro ao tentar consultar o endereco "+ e.getMessage());
		}

		return endereco;
	}

}
