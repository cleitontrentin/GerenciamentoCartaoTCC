/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.dao;

import entity.Transacao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author cleiton
 */
public class TransacaoDao {

    public int salvar(Transacao transacao) {

        int resultado = -1;

        try {
            Connection conn = ConnectionManager.getConnection();

            String QUERY_INSERT = "insert into transacao(idusuario, idCartao, dataVenda, valor)values(?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, transacao.getUsuario().getId());
            stmt.setInt(2, transacao.getCartao().getId());
            stmt.setDate(3, transacao.getDate());
            stmt.setFloat(4, transacao.getValor());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            resultado = rs.getInt(1);

            conn.close();

        } catch (SQLException ex) {

            ex.printStackTrace();

            resultado = -1;
        }

        return resultado;
    }

    public boolean atualizar(Transacao transacao) {

        boolean resultado = false;

        try {
            Connection conn = ConnectionManager.getConnection();

            String QUERY_UPDATE = "update transacao set idCartao = ?, dataVenda = ?, valor = ?, idusuario = ? where idtransacao = ?";

            PreparedStatement stmt = conn.prepareStatement(QUERY_UPDATE);
            System.out.println(transacao.getId()+ " Aqui no atualizar!!");
            
            stmt.setInt(1, transacao.getCartao().getId());
            stmt.setDate(2, transacao.getDate());
            stmt.setFloat(3, transacao.getValor());
            stmt.setInt(4, transacao.getUsuario().getId());
            stmt.setInt(5, transacao.getId());            

            stmt.executeUpdate();
            conn.close();

            resultado = true;

        } catch (SQLException ex) {

            //ex.printStackTrace();
            resultado = false;

        }

        return resultado;

    }

    public boolean deletar(int idtransacao) {

        boolean resultado;

        try {
            Connection conn = ConnectionManager.getConnection();

            String QUERY_DELETE = "delete from transacao where idtransacao = ?";

            PreparedStatement stmt = conn.prepareStatement(QUERY_DELETE);
            stmt.setInt(1, idtransacao);

            stmt.executeUpdate();
            conn.close();

            resultado = true;

        } catch (SQLException ex) {

            //ex.printStackTrace();
            resultado = false;
        }

        return resultado;

    }

    public List<Transacao> listar() {

        List<Transacao> lista = null;

        try {
            String QUERY_DETALHE = "select idtransacao, valor, dataVenda, idcartao, idusuario from transacao ";

            Connection conn = ConnectionManager.getConnection();

            PreparedStatement stmt = conn.prepareStatement(QUERY_DETALHE);
            ResultSet rs = stmt.executeQuery();

            lista = new ArrayList<Transacao>();

            while (rs.next()) {
                Transacao transacao = new Transacao();
                transacao.setId(rs.getInt("idtransacao"));
                transacao.setValor(rs.getFloat("Valor"));
                transacao.setDate(rs.getDate("dataVenda"));
                CartaoDao cartaoDao = new CartaoDao();
                transacao.setCartao(cartaoDao.detalhe(rs.getInt("idcartao")));
                UsuarioDao usuarioDao = new UsuarioDao();
                transacao.setUsuario(usuarioDao.detalhe(rs.getInt("idusuario")));

                lista.add(transacao);
            }

            conn.close();

        } catch (SQLException ex) {

            //  ex.printStackTrace();
        }
        return lista;

    }

    public Transacao detalhe(int idtransacao) {

        Transacao transacao = null;
        System.out.println("ID chegou... " + idtransacao);
        try {

            Connection conn = ConnectionManager.getConnection();

            String QUERY_DETALHE = "select idtransacao, valor, dataVenda, idcartao, idusuario from transacao where idtransacao = ?";

            PreparedStatement stmt = conn.prepareStatement(QUERY_DETALHE);
            stmt.setInt(1, idtransacao);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                transacao = new Transacao();
                transacao.setId(rs.getInt("idtransacao"));
                transacao.setValor(rs.getFloat("valor"));
                transacao.setDate(rs.getDate("dataVenda"));
                CartaoDao cartaoDao = new CartaoDao();
                transacao.setCartao(cartaoDao.detalhe(rs.getInt("idcartao")));
                UsuarioDao usuarioDao = new UsuarioDao();
                transacao.setUsuario(usuarioDao.detalhe(rs.getInt("idusuario")));

            }

            conn.close();
        } catch (SQLException ex) {
            //ex.printStackTrace();
        }
        return transacao;

    }

}
