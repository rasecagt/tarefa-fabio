package apresentacao;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.LinkedList;
public class Banco {
	
	private ArrayList<Conta> contas;
	//private List<Conta> contas;
	//private TreeSet<Conta> contas; 
	//private HashSet<Conta> contas; 
	//Construtor de Banco
	Banco(){
		contas = contas();
	}
	
	// M�todo para criar contas mantidas em ArrayList
	// Implementa Singleton
	
	private ArrayList<Conta> contas(){
		// Singleton - Gatante que Banco tenha apenas
		// uma única lista de contas.
		if (contas==null) {
			ArrayList<Conta> contas = new ArrayList();
			return contas;
		}
		return contas;
	}

	//M�todo para criar contas mantidas em LinkedList
	// Implementa Singleton
	/*
	private List<Conta> contas(){
		//Singleton - garante que o banco tenha apenas
		//uma �nica lista de contas
		
		if (contas==null) {
			List<Conta> contas = new LinkedList<>();
			return contas;
		}
		return contas;
	}
	*/
	
	//M�todo para criar contas mantidas em HashMap
	/*
	private HashSet<Conta> contas(){
		HashSet<Conta> contas = new HashSet<Conta>();
		return contas;
	}
	*/
	//M�todo para criar contas mantidas em HashMap
	/*
	private HashMap<String,Conta> contas(){
		HashMap<String,Conta> contas = new HashMap<String,Conta>();
		return contas;
	}
	*/
	
	// M�todo para criar contas mantidas em TreeSet	
	// Padr�o Gof Criacional Singleton 
	/*
	private TreeSet<Conta> contas(){
		if (contas==null) {
			TreeSet<Conta> contas = new TreeSet<Conta>();
			return contas;
		}
		return contas;
		
	}
	*/
	private void CriaConta(Conta c) {
		contas.add(c);
		
	}
	
	private void RemoveConta(String numero) {
		Iterator<Conta> iterator = contas.iterator();
		Conta c = null;
	    while(iterator.hasNext()) {
	      c = (Conta)iterator.next();
	      if(c.getNumero().equals(numero)) {
	        iterator.remove();
	      }
	    }
	}
	
	private void CreditaConta(String numero, double valor) {
		Iterator<Conta> iterator = contas.iterator();
		Conta c = null;
	    while(iterator.hasNext()) {
	      c = (Conta)iterator.next();
	      if(c.getNumero().equals(numero)) {
	        c.saldo+=valor;
	        // A conta na posi��o do iterator atual ser� creditada        
	      }
	    }
	}
	
	private void DebitaConta(String numero, double valor) {
		Iterator<Conta> iterator = contas.iterator();
		Conta c = null;
	    while(iterator.hasNext()) {
	      c = (Conta)iterator.next();
	      if(c.getNumero().equals(numero)) {
	        c.saldo-=valor;
	        // A conta na posi��o do iterator atual ser� debitada   
	      }
	    }
	}
	
	private void TransfereConta(String numero_conta_origem, String numero_conta_destino, double valor) {
		// Implementar exce��o limite conta
		DebitaConta(numero_conta_origem, valor);
		CreditaConta(numero_conta_destino, valor);
	}
	
	private void ListaContas() {
		Iterator<Conta> iterator = contas.iterator();
		Conta c = null;
	    while(iterator.hasNext()) {
	      c = (Conta)iterator.next();
	      System.out.printf("Conta %s  %s\n", c.getNumero(), c.getSaldo()); 
	    }
	}
	
	private void ListaContasXML() {
		Iterator<Conta> iterator = contas.iterator();
		ContasXMLBuilder contasXml = new ContasXMLBuilder();
		String resultado = contasXml.listagemContas(iterator);		
		System.out.println(resultado);
	}
	
	private void ListaContasPDF() {
		Iterator<Conta> iterator = contas.iterator();
		//ContasPDFBuilder contasPdf = new ContasPDFBuilder();
		//System.out.println("Imprimindo PDF.");
		//contasPdf.gerarListagemContas(iterator);		

	}
	
	public static void main (String args[]) {
		Banco banco = new Banco();
		AcessoADado a = new AcessoADado();
		String mensagem = new String();
		
		Conta c1;
		c1 = new ContaNormal();
		c1.setNumero(new String("1654-3"));
		c1.setSaldo(500);
		
		mensagem = a.cadastrar_conta(c1.getNumero(), (float) c1.getSaldo());
		mensagem = mensagem + a.cadastrar_conta_normal(c1.getNumero());
		
		
		System.out.println(mensagem);
		
		ContaDebEspecial c2 = new ContaDebEspecial();
		
		ContaDebEspecial c3 = new ContaDebEspecial(new String("6578-9"),2500,5050);
		
		c2.setNumero(new String("4067-6"));
		c2.setSaldo(2500);
		c2.setLimite(1000.67);
		
		System.out.println("A conta n�mero " + c1.getNumero() + " possui saldo " + c1.getSaldo());
		
		c1.creditar(1000);
		
		System.out.println("Ap�s o credito de R$ 1000,00, a conta n�mero " + c1.getNumero() + " passou a ter saldo " + c1.getSaldo());		
		
		c1.debitar(100);
		
		System.out.println("Ap�s o d�bito de R$ 100,00, a conta n�mero " + c1.getNumero() + " passou a ter saldo " + c1.getSaldo());	
		
		System.out.println("");
		
		System.out.println("A conta n�mero " + c2.getNumero() + " possui saldo " + c2.getSaldo());
		
		c2.debitar(500);
		
		System.out.println("A conta n�mero " + c2.getNumero() + " possui saldo " + c2.getSaldo() + " Ap�s d�bito de R$ 500");
		
		//c1.creditar(1000);
		
		System.out.println("A conta n�mero " + c2.getNumero() + " possui saldo " + c2.getSaldo() + " e Limite de " + c2.getLimite());
		
		c2.setLimite(10000);
		
		System.out.println("A conta n�mero " + c2.getNumero() + " possui saldo " + c2.getSaldo() + " e novo Limite de " + c2.getLimite());
		
		banco.CriaConta(c1);
		banco.CriaConta(c2);
		banco.CriaConta(c3);
		
		banco.ListaContas();
		
		banco.CreditaConta("6578-9", 1000);
		
		banco.ListaContas();
		
		banco.DebitaConta("6578-9", 500);
		
		banco.ListaContas();
		
		banco.TransfereConta("6578-9", "1654-3", 500.00);
		
		banco.ListaContas();
		
		//banco.ListaContasPDF();
		
		banco.ListaContasXML();
	
	}
}