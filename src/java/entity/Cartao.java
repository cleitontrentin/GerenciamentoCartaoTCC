/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.sql.Date;

/**
 *
 * @author cleiton
 */
public class Cartao {
    
    private int Id;
    private String nome;
    private int carencia;
    private float taxa;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCarencia() {
        return carencia;
    }

    public void setCarencia(int carencia) {
        this.carencia = carencia;
    }

    public float getTaxa() {
        return taxa;
    }

    public void setTaxa(float taxa) {
        this.taxa = taxa;
    }
    
    

}

