package jogodesenha;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Scanner;

public class JogoDeSenha {
    private int numeroJogadas;
    private int jogada;
    private int senha;
    private int palpite;
    private boolean ganhou;
    public JogoDeSenha(int numeroJogadas) {
        this.numeroJogadas = numeroJogadas;
        this.jogada=0;
        this.ganhou=false;
    }
    public int getJogada() {
        return jogada;
    }
    public void setJogada(int jogada) {
        this.jogada = jogada;
    }
    public int getSenha() {
        return senha;
    }
    public void setSenha(int senha) {
        this.senha = senha;
    }
    public int getPalpite() {
        return palpite;
    }
    public boolean setPalpite(int palpite) {
        this.palpite = palpite;
        this.ganhouJogo();
        if (this.ganhou==true){
            return true;
        }else{
           this.setJogada(this.getJogada()+1);
           return false;
        }
    }
    public boolean isGanhou() {
        return ganhou;
    }
    public void setGanhou(boolean ganhou) {
        this.ganhou = ganhou;
    }
    public int getNumeroJogadas() {
        return numeroJogadas;
    }
    public void setNumeroJogadas(int numeroJogadas) {
        this.numeroJogadas = numeroJogadas;
    }
    public String toString() {
        return "JogoDeSenha{" + "numeroJogadas=" + numeroJogadas + ", jogada=" + jogada + ", senha=" + senha + ", palpite=" + palpite + ", ganhou=" + ganhou + '}';
    }
    public void joganovamente(){
        this.sorteio();
        this.setGanhou(false);
        this.jogada=0;
    }
    public void ganhouJogo(){
        if  (this.getPalpite()==this.getSenha()){
            this.ganhou=true;
            System.out.println("Você acertou!!!");
            
        }else if (this.getPalpite()+1==this.getSenha() || this.getPalpite()-1==this.getSenha()){
            this.ganhou=false;
            System.out.println("Está Quente!");
        }
        else if (this.getPalpite()>this.getSenha()){
            this.ganhou=false;
            System.out.println("A senha é menor");
        }else{
            this.ganhou=false;
            System.out.println("A senha é maior");
        }
    }
    public void sorteio(){
        Random sorte= new Random();
        int sorteado=sorte.nextInt();
        if (sorteado<0){
            sorteado=sorteado*(-1);
        }
        this.setSenha(sorteado%100);
    }
    public static void limpaTela(){
        try {
        Robot robot = new Robot();
        robot.setAutoDelay(5);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_L);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_L);
    } catch (AWTException ex) {
    }
    }
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        JogoDeSenha jogo1=new JogoDeSenha(5);
        boolean jogaDenovo=true, errou;
        int entrada;
        while(jogaDenovo){
            jogo1.sorteio();
            while(jogo1.getNumeroJogadas()>jogo1.getJogada()&& jogo1.ganhou==false){
                entrada=-1;
                errou=false;
                while(entrada<0 || entrada >100){
                    if (errou){
                        System.out.println("Papite incorreta, só são permitidos palpites entre 0 e 100!");
                    }
                    System.out.println("digite um palpite");
                    entrada=input.nextInt();
                    errou=true;

                }
                jogo1.setPalpite(entrada);
                
            }
            if (!jogo1.ganhou){
                System.out.println("Vc Perdeu!");
            }
            System.out.println("Deseja jogar denovo (1=sim/2=não)");
            entrada=input.nextInt();
            if (entrada==1){
                jogaDenovo=true;
                jogo1.joganovamente();
                
            }
            else{
                jogaDenovo=false;
            }
        }
    }
}