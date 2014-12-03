/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author cleiton
 */
public class Transacao {
    
    private int id;
    private Date date;
    private float valor;
    private Usuario usuario;
    private Cartao cartao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    public void Format(String data ) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
          
        Date data1 = null;  
        try{  
            data1 = (Date) dateFormat.parse(data.toString());  
            }catch(ParseException e){  
            e.printStackTrace();  
        }  
            
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }
    
    
    

}

