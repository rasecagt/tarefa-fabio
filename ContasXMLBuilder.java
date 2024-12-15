package apresentacao;

import java.util.Iterator;

public class ContasXMLBuilder implements ContasBuilder {
	double saldo_total = 0;
	public String gerarCabecalho() {
		return new String("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	}
	public String gerarListagemContas(Iterator<Conta> iterator) {
		String corpo = "<contas>\n";
		
		Conta c = null;
	    while(iterator.hasNext()) {
	      c = (Conta)iterator.next();
	      corpo = corpo + "\t<conta>\n" +
	    		          "\t\t<numero>" + c.getNumero() + "</numero>\n" + 
	    		          "\t\t<saldo>" + c.getSaldo() + "</saldo>\n" +
	    		          "\t</conta>\n";
	      saldo_total += c.getSaldo();
	    }
		
		corpo = corpo + "</contas>\n";
		return new String(corpo);
		
	}
	public String gerarSumario() {
		
		return new String("<saldo_total>" + saldo_total + "</saldo_total>");
	}
	
	public String listagemContas(Iterator<Conta> iterator) {
		String resultado = gerarCabecalho() + "\n" + gerarListagemContas(iterator) + "\n" +
	                       gerarSumario();
		return resultado;
	}

}
