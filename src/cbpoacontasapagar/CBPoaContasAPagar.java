package cbpoacontasapagar;

import Entity.Executavel;
import Robo.AppRobo;
import TemplateContabil.Control.ControleTemplates;
import java.util.ArrayList;
import java.util.List;

public class CBPoaContasAPagar {
    private static Integer empresa =  645;
    private static String nomePastaEmpresa = "CB Porto Alegre Comercio de Alimentos Ltda";
    private static String nomeApp = "CB Poa Importação ";

    
    public static void main(String[] args) {
        AppRobo  robo = new AppRobo(nomeApp);
        
        robo.definirParametros();
        int mes = robo.getParametro("mes").getMes();
        int ano = robo.getParametro("ano").getInteger();
        String bancoParametro = robo.getParametro("banco").getString();
        String[] bancoDados = bancoParametro.split("#");
        
        String nomeBanco = bancoDados[0];
        String filtroArquivo = bancoDados[1];
        
        robo.setNome(nomeApp + nomeBanco);
        robo.executar(
                principal(mes, ano, nomeBanco, filtroArquivo)
        );
    }
    
    public static String principal(int mes, int ano, String banco, String filtroArquivo){
        try {
            
            ControleTemplates controle = new ControleTemplates(mes, ano, empresa, nomePastaEmpresa, "Planilhas de Controle;Contas a Pagar");
            controle.definirFilesAndPaths();
            
            List<Executavel> executaveis = new ArrayList<>();
            executaveis.add(controle.new definirFileTemplatePadrao());
            executaveis.add(controle.new importacaoBancoExtratoExcel(banco,"Contas;Pagar;" + filtroArquivo +  ";.xlsx",5,"H","E","B","C;J;L","-F"));
            
            
            return AppRobo.rodarExecutaveis(nomeApp, executaveis);
        } catch (Exception e) {
            return "Ocorreu um erro no Java: " + e;  
        }
    }
    
}
