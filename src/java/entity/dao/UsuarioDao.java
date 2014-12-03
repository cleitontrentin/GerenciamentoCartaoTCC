/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.dao;

import entity.Usuario;
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
public class UsuarioDao {
    
    public int salvar(Usuario usuario) {
        
        int resultado = -1;

        try {
            Connection conn = ConnectionManager.getConnection();

            String QUERY_INSERT = "insert into usuario(nome, login, senha)values(?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getLogin());
            stmt.setString(3, usuario.getSenha());
                
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

    public boolean atualizar(Usuario usuario) {

        boolean resultado = false;

        try {
            Connection conn = ConnectionManager.getConnection();

            String QUERY_UPDATE = "update usuario set nome = ?, login = ?, senha = ? where idusuario = ?";
            System.out.println("Aqui me diga o id!!"+ usuario.getId());
            PreparedStatement stmt = conn.prepareStatement(QUERY_UPDATE);
            System.out.println(usuario.getId());
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getLogin());
            stmt.setString(3, usuario.getSenha());

            stmt.setInt(4, usuario.getId());

            stmt.executeUpdate();
            conn.close();

            resultado = true;

        } catch (SQLException ex) {

            //ex.printStackTrace();
            resultado = false;

        }

        return resultado;

    }

    public boolean deletar(int idusuario) {

        boolean resultado;

        try {
            Connection conn = ConnectionManager.getConnection();

            String QUERY_DELETE = "delete from usuario where idusuario = ?";

            PreparedStatement stmt = conn.prepareStatement(QUERY_DELETE);
            stmt.setInt(1, idusuario);

            stmt.executeUpdate();
            conn.close();

            resultado = true;

        } catch (SQLException ex) {

            //ex.printStackTrace();
            resultado = false;
        }
        
        return resultado;

    }

    public List<Usuario> listar() {

        List<Usuario> lista = null;

        try {
            String QUERY_DETALHE = "select idusuario, nome, login, senha from usuario ";

            Connection conn = ConnectionManager.getConnection();

            PreparedStatement stmt = conn.prepareStatement(QUERY_DETALHE);
            ResultSet rs = stmt.executeQuery();
            
            lista = new ArrayList<Usuario>();
            
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("idusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                
                lista.add(usuario);
            }
            
            conn.close();
            
        } catch (SQLException ex) {
            
            //ex.printStackTrace();
        }
        return lista;
    
    }

    public Usuario detalhe(int idusuario) {

        Usuario usuario = null;

        try {

            Connection conn = ConnectionManager.getConnection();

            String QUERY_DETALHE = "select idusuario, nome, login, senha from usuario where idusuario = ?";

            PreparedStatement stmt = conn.prepareStatement(QUERY_DETALHE);
            stmt.setInt(1, idusuario);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                usuario = new Usuario();
                usuario.setId(rs.getInt("idusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));

            }

            conn.close();
        } catch (SQLException ex) {            
            //ex.printStackTrace();
        }   
        return usuario;            

    }

    public Usuario login(String pLogin, String pSenha) {

        Usuario usuario = null;

        try {

            Connection conn = ConnectionManager.getConnection();

            String QUERY_DETALHE = "select idusuario, nome, login, senha from usuario where login = ? and senha = ? ";

            PreparedStatement stmt = conn.prepareStatement(QUERY_DETALHE);
            stmt.setString(1, pLogin);
            stmt.setString(2, pSenha);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                usuario = new Usuario();
                usuario.setId(rs.getInt("idusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));

            }

            conn.close();
        } catch (SQLException ex) {            
            //ex.printStackTrace();
        }   
        return usuario;            

    }

}
