/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callanotherservice;

/**
 *
 * @author richard
 */
public class LojaNaoEncontradaException extends Exception {
    private final static long serialVersionUID = 1;

    private int codigo;

    public LojaNaoEncontradaException(String msg) {
        super(msg);
    }

    public LojaNaoEncontradaException(int codigo, String msg) {
        super(msg);
        this.codigo = codigo;
    }

    public int getCodigoDeErro() {
        return codigo;
    }
}
