package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class CarrinhoCompras  {


	
	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();
	
	public Collection<CarrinhoItem> getItens(){
		return itens.keySet();
	}
	
	public void add(CarrinhoItem item) {
		itens.put(item, getQuantidade(item)  + 1);
	}

	public int getQuantidade(CarrinhoItem item) {
		if(!itens.containsKey(item)) {
		itens.put(item, 0);
		}
		return itens.get(item);
	}
	
	public int getQuantidade() {
		return itens.values().stream().reduce(0, (proximo , acomulador) -> proximo + acomulador);
	}
	
	public BigDecimal getTotal(CarrinhoItem item){
		BigDecimal item1 = item.getTotal(getQuantidade(item));
		return item1;
				
	}
	
	public BigDecimal getTotalDoCarrinho() {
		BigDecimal total = BigDecimal.ZERO;
		for (CarrinhoItem item : itens.keySet()) {
			total = total.add(getTotal(item));
		}
		return total;
	}
}
