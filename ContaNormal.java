package apresentacao;

 
public class ContaNormal extends Conta {
        
  public void creditar (double valor) {
    saldo = saldo + valor;
  }
  
  public void debitar (double valor) {  // Conta Normal n�o possui limite, logo em uma opera��o de d�bito apenas o saldo est� dispon�vel.
    if ((saldo-valor) >= 0) {
    	saldo = saldo - valor;
    }
	
  }
  @Override
  public int compareTo(Conta o) {
    // TODO Auto-generated method stub
    return numero.compareTo(o.getNumero());
  }
}

