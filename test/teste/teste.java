package teste;

import cbpoacontasapagar.CBPoaContasAPagar;

public class teste {

    public static void main(String[] args) {
        int mes = 1;
        int ano = 2020;
        String banco = "Dinheiro";
        String filtroBanco = "Contas;Pagar;Caixinha;.xlsx";
        
        System.out.println(CBPoaContasAPagar.principal(mes, ano, banco, filtroBanco).replaceAll("<br>", "\n"));
    }
    
}
