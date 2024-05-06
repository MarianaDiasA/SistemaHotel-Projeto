package entities;

public class Camareira extends Thread {
    private EnumCamareiraDisp disponibilidade;
    
    public Camareira() {
    	this.disponibilidade = EnumCamareiraDisp.DISPONIVEL;
    }
    
    // Getters e Setters
	public EnumCamareiraDisp getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(EnumCamareiraDisp disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
	
    // Métodos
    void limparQuarto(Quarto quarto) { 
    	if (quarto.getPosseChave().equals(EnumPosseChave.HOTEL)) {
    		this.setDisponibilidade(EnumCamareiraDisp.OCUPADA);
    		
    		quarto.setPosseChave(EnumPosseChave.CAMAREIRA);
            System.out.println("A Camareira está limpando o quarto");
            
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A Camareira terminou de limpar o quarto");
            quarto.setPosseChave(EnumPosseChave.HOTEL);
            quarto.setLimpo(true);
            this.setDisponibilidade(EnumCamareiraDisp.DISPONIVEL);
    	}
    }
    
	//@Override
    //public void run() {
        //while (true) { //verifica se o quarto está ocupado e executa a limpeza
            //if (quarto != null && quarto.getDisponibilidade() == DisponibilidadeEnum.OCUPADO) {
                //limparQuarto();
            //}
        //}
    //}
}
