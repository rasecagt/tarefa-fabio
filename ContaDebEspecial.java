package apresentacao;


public class ContaDebEspecial extends ContaEspecial{
   
   ContaDebEspecial() {
   	super();
   }
   
   ContaDebEspecial(String n,double s, double l){
   	numero = n;
	saldo = s;
	limite = l;
   }
  
  void debitar (double valor) {
    if ((limite + saldo - valor) >= 0) {
    	saldo = saldo - valor;
    }
  }
  @Override
  public int compareTo(Conta o) {
    // TODO Auto-generated method stub
    return numero.compareTo(o.getNumero());
  }
}

