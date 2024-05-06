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
		System.out.println("Hóspede '" + this.getNome() + "' foi passear.");
		this.getQuarto().setPosseChave(EnumPosseChave.HOTEL);
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Hóspede '" + this.getNome() + "' voltou do passeio");
    }
	
	public void irEmbora() {
		this.getQuarto().limparHospedes();
	}

    @Override
    public void run() {
    	this.saidaPasseio();
    	
    	boolean parar = false;
        while (!parar) {
        	//System.out.println("Posso da chave: " + quarto.getPosseChave().name());
        	if (terminouLimpar()) {
        		this.getQuarto().setPosseChave(EnumPosseChave.HOSPEDE);
        		this.getQuarto().setLimpo(false);
        		parar = true;
        	}
        }
        
        System.out.println("Hóspede '" + this.getNome() + "' voltou para o quarto");
        
        this.irEmbora();
    }
    
    // Métodos auxiliares
    private boolean terminouLimpar() {
    	return (quarto.getPosseChave().equals(EnumPosseChave.HOTEL) && quarto.isLimpo());
    }
}

