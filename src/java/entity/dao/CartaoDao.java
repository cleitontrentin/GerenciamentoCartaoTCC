/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.dao;

import entity.Cartao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cleiton
 */
public class CartaoDao {
    
    public int salvar(Cartao cartao) {
        
        int resultado = -1;

        try {
            Connection conn = ConnectionManager.getConnection();

            String QUERY_INSERT = "insert into cartao(nome, carencia, taxa)values(?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, cartao.getNome());
            stmt.setInt(2, cartao.getCarencia());
            stmt.setFloat(3, cartao.getTaxa());
                
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            resultado = rs.getInt(1);
                    
            conn.close();

        } catch (SQLException ex) {

            //ex.printStackTrace();
            
            resultado = -1;
        }
        
        return resultado;
    }

    public boolean atualizar(Cartao cartao) {

        boolean resultado = false;

        try {
            Connection conn = ConnectionManager.getConnection();

            String QUERY_UPDATE = "update cartao set nome = ?, carencia = ?, taxa = ? where idcartao = ?";

            PreparedStatement stmt = conn.prepareStatement(QUERY_UPDATE);
            System.out.println(cartao.getId());
            stmt.setString(1, cartao.getNome());
            stmt.setInt(2, cartao.getCarencia());
            stmt.setFloat(3, cartao.getTaxa());
            stmt.setInt(4, cartao.getId());

            stmt.executeUpdate();
            conn.close();

            resultado = true;

        } catch (SQLException ex) {

            //ex.printStackTrace();
            resultado = false;

        }

        return resultado;

    }

    public boolean deletar(int idcartao) {

        boolean resultado;

        try {
            Connection conn = ConnectionManager.getConnection();

            String QUERY_DELETE = "delete from cartao where idcartao = ?";

            PreparedStatement stmt = conn.prepareStatement(QUERY_DELETE);
            stmt.setInt(1, idcartao);

            stmt.executeUpdate();
            conn.close();

            resultado = true;

        } catch (SQLException ex) {

            //ex.printStackTrace();
            resultado = false;
        }
        
        return resultado;

    }

    public List<Cartao> listar() {

        List<Cartao> lista = null;

        try {
            String QUERY_DETALHE = "select idcartao, nome, carencia, taxa from cartao ";

            Connection conn = ConnectionManager.getConnection();

            PreparedStatement stmt = conn.prepareStatement(QUERY_DETALHE);
            ResultSet rs = stmt.executeQuery();
            
            lista = new ArrayList<Cartao>();
            
            while (rs.next()) {
                Cartao cartao = new Cartao();
                cartao.setId(rs.getInt("idcartao"));
                cartao.setNome(rs.getString("nome"));
                cartao.setCarencia(rs.getInt("carencia"));
                cartao.setTaxa(rs.getFloat("taxa"));
                
                lista.add(cartao);
            }
            
            conn.close();
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        return lista;
    
    }

    public Cartao detalhe(int idcartao) {

        Cartao cartao = null;

        try {

            Connection conn = ConnectionManager.getConnection();

            String QUERY_DETALHE = "select idcartao, nome, carencia, taxa from cartao where idcartao = ?";

            PreparedStatement stmt = conn.prepareStatement(QUERY_DETALHE);
            stmt.setInt(1, idcartao);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                cartao = new Cartao();
                cartao.setId(rs.getInt("idcartao"));
                cartao.setNome(rs.getString("nome"));
                cartao.setCarencia(rs.getInt("carencia"));
                cartao.setTaxa(rs.getFloat("taxa"));

            }

            conn.close();
        } catch (SQLException ex) {            
            ex.printStackTrace();
        }   
        return cartao;            

    }

}
