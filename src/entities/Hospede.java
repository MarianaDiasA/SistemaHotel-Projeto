package entities;

public class Hospede extends Thread {

    private Quarto quarto; 
    private String nome;
    
    // Constructors
    public Hospede() {
    	this.nome = "Anônimo";
    }
    
    public Hospede(String nome) {
    	this.nome = nome;
    }
    
    // Getters e Setters
    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }
    
    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	// Métodos 
	public void saidaPasseio() { 
    	/**
    	 * Método criado para definir o comportamente do hóspede quando ele quer sair do quarto
    	 * para passear.
    	 * 
    	 * Por padrão, ele ficará 3 segundos fora.
    	 * 
    	 * O Hóspede não poderá voltar para o quarto até que a chave esteja disponível
    	 */
		this.getQuarto().setPosseChave(EnumPosseChave.HOTEL);
        System.out.println("Hóspede '" + this.getNome() + "' foi passear.");
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Voltou");
        this.start();
        
        try {
			this.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        System.out.println("Entrou");
    }

    @Override
    public void run() {
        while (true) {
        	if (terminouLimpar()) {
        		this.getQuarto().setPosseChave(EnumPosseChave.HOSPEDE);
        		this.getQuarto().setLimpo(false);
        		break;
        	}
        }
    }
    
    // Métodos auxiliares
    private boolean terminouLimpar() {
    	return (quarto.getPosseChave().equals(EnumPosseChave.HOTEL));
    }
}

