package apresentacao;

import java.util.Iterator;

public interface ContasBuilder {
	abstract String gerarCabecalho();
	abstract String gerarListagemContas(Iterator<Conta> iterator);
	abstract String gerarSumario();

}
