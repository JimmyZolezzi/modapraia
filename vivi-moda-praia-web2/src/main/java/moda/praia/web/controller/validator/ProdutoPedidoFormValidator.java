package moda.praia.web.controller.validator;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import moda.praia.modulo.pedido.bean.ItemPedidoTamanho;
import moda.praia.modulo.pedido.bean.ProdutoPedido;
import moda.praia.web.controller.form.FormProdutoPedido;

@Component
public class ProdutoPedidoFormValidator implements Validator {


	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return FormProdutoPedido.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		FormProdutoPedido formProdutoPedido = (FormProdutoPedido) target;

		if (formProdutoPedido != null) {
			if (formProdutoPedido.getQuantidade() <= 0) {
				errors.rejectValue("quantidade", "NotEmpty.formProdutoPedido.quantidade");
			}

			List<ProdutoPedido> produtosPedido = formProdutoPedido.getListaProdutoPedido();
			int contadorProdutoPedido = 0;
			for (ProdutoPedido produtoPedido : produtosPedido) {
				List<ItemPedidoTamanho> listaPedidoTamanho = produtoPedido.getItensPedidoTamanho();
				int contadorItemPedidoTamanho = 0;
				for (ItemPedidoTamanho itemPedidoTamanho : listaPedidoTamanho) {
					if (itemPedidoTamanho.getTamanho() == null || itemPedidoTamanho.getTamanho().equals("")) {
						errors.rejectValue("listaProdutoPedido[" + contadorProdutoPedido + "].itensPedidoTamanho["+ contadorItemPedidoTamanho +"].tamanho", "NotEmpty.formProdutoPedido.listaItemPedido.tamanho");
					}
					contadorItemPedidoTamanho++;
				}
				contadorProdutoPedido++;
			}

		}
	}

}
