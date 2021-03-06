import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class config extends principal {
	private static principal main = new principal();

	//Configura os valores novamente do zero 
	public static void config(){
		File f = new File("config.txt");
		f.delete();
		primeiraVez();
		System.out.println("Configuracoes prontas, abra novamente o programa para que as modificacoes sejam aplicadas");
		System.exit(0);
	}
	
	//Arquivo config
	public static void primeiraVez(){
		File f = new File("config.txt");
		File m = new File("total.txt");
		if(f.exists()){
			setaConfig("config.txt");
		}else{
			try{
				f.createNewFile();
				m.createNewFile();
				System.out.println("Iniciando pela primeira vez\nDigite o nome do estacionamento");
				String nome = ler.next();
				tools.escreverTexto("config.txt",nome+"\n");
				insereConfig();
			}catch(IOException e){
				e.getStackTrace();
			}
		}
	}
	
	//Insere as configurações no arquivo config.txt pela primeira vez
	public static void insereConfig(){
		System.out.println("CONFIGURAÇAO DE HORAS!\nDigite o valor da hora para MOTO");
		tools.escreverTexto("config.txt",configuraValor()+"\n");
		System.out.println("Digite o valor da hora para CARRO PEQUENO");
		tools.escreverTexto("config.txt",configuraValor()+"\n");
		System.out.println("Digite o valor da hora para CARRO GRANDE");
		tools.escreverTexto("config.txt",configuraValor()+"\n");

		System.out.println("CONFIGURAÇAO DA DIARIA!\nDigite o valor da diaria para MOTO");
		tools.escreverTexto("config.txt",configuraValor()+"\n");
		System.out.println("Digite o valor da diaria para CARRO PEQUENO");
		tools.escreverTexto("config.txt",configuraValor()+"\n");
		System.out.println("Digite o valor da diaria para CARRO GRANDE");
		tools.escreverTexto("config.txt",configuraValor()+"\n");

		System.out.println("DIGITE A QUANTIDADE DE MINUTOS DE TOLERANCIA!\n");
		int tol = ler.nextInt();
		tools.escreverTexto("config.txt",tol+"\n");
	}

    //Seta as configurações presentes no arquivo txt
    public static void setaConfig(String pCaminhoArquivo){
        try(BufferedReader buffRead = new BufferedReader(new FileReader(pCaminhoArquivo));){
            String linha = "";
            for(int i=0;i<8;i++){
				linha = buffRead.readLine();  
                switch(i){
                    case 0: nome = linha; break;
                    case 1: valorMoto = tools.doubleToString(linha); break;
                    case 2: valorCarroP = tools.doubleToString(linha); break;
                    case 3: valorCarroG = tools.doubleToString(linha); break;
                    case 4: valorDiariaM = tools.doubleToString(linha); break;
                    case 5: valorDiariaCP = tools.doubleToString(linha); break;
                    case 6: valorDiariaCG = tools.doubleToString(linha); break;
                    case 7: valorTol = tools.stringToInt(linha); break;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }catch(NullPointerException e){
			System.out.println("Houve algum erro no seu arquivo de configuracao, vamos iniciar do zero");
			File f = new File("config.txt");
			f.delete();
			primeiraVez();
		}
    }

	public static double configuraValor(){
		double val = ler.nextDouble();
		repete:while(true){
			if(val<=0){
				System.out.println("Valor menor ou igual a zero, digite outro valor");
			}else{
				break repete;
			}
		}
		return val;
	}
}
