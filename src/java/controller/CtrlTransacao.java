/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Transacao;
import entity.dao.CartaoDao;
import entity.dao.TransacaoDao;
import entity.dao.UsuarioDao;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cleiton
 */
public class CtrlTransacao implements CtrlBase {

    private void mostraAlertMsg(HttpServletRequest pRequest, HttpServletResponse pResponse, String pTipo, String pTitulo, String pTexto, String pFormulario, String pAcao) throws ServletException, IOException {

        if (pTipo == "OK") {
            pTipo = "alert-success";
        } else if (pTipo == "INFO") {
            pTipo = "alert-info";
        } else if (pTipo == "ERRO") {
            pTipo = "alert-danger";
        } else {
            pTipo = "alert-warning";
        }

        pRequest.setAttribute("tipo", pTipo);
        pRequest.setAttribute("titulo", pTitulo);
        pRequest.setAttribute("texto", pTexto);
        pRequest.setAttribute("objeto", pFormulario);
        pRequest.setAttribute("metodo", pAcao);

        RequestDispatcher rd = pRequest.getRequestDispatcher("/mensagemAlerta.jsp");
        rd.forward(pRequest, pResponse);
    }

    private void mostraMsg(HttpServletRequest pRequest, HttpServletResponse pResponse, String pTipo, String pTitulo, String pTexto) throws ServletException, IOException {
        if (pTipo == "OK") {
            pTipo = "alert-success";
        } else if (pTipo == "INFO") {
            pTipo = "alert-info";
        } else if (pTipo == "ERRO") {
            pTipo = "alert-danger";
        } else {
            pTipo = "alert-warning";
        }

        pRequest.setAttribute("Tipo", pTipo);
        pRequest.setAttribute("titulo", pTitulo);
        pRequest.setAttribute("texto", pTexto);

        RequestDispatcher rd = pRequest.getRequestDispatcher("/mensagem.jsp");
        rd.forward(pRequest, pResponse);
    }

    private Transacao requestForm(HttpServletRequest pRequest) {

        Transacao retorno = new Transacao();

        if (pRequest.getParameter("txtId") != null) {
            retorno.setId(Integer.parseInt(pRequest.getParameter("txtId")));
        }

        if (pRequest.getParameter("txtUsuario") != null) {
            UsuarioDao usuarioDao = new UsuarioDao();
            retorno.setUsuario(usuarioDao.detalhe(Integer.parseInt(pRequest.getParameter("txtUsuario"))));
        }

        if (pRequest.getParameter("txtCartao") != null) {
            CartaoDao cartaoDao = new CartaoDao();
            retorno.setCartao(cartaoDao.detalhe(Integer.parseInt(pRequest.getParameter("txtCartao"))));
        }

        if (pRequest.getParameter("txtValor") != null) {
            retorno.setValor(Float.parseFloat(pRequest.getParameter("txtValor")));
        }

        if (pRequest.getParameter("txtData") != null) {
            try {
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                retorno.setDate(new java.sql.Date(formato.parse(pRequest.getParameter("txtData")).getTime()));
            } catch (Exception e) {
                retorno.setDate(null);
            }
        }

        return retorno;
    }

    @Override
    public void principal(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {

        RequestDispatcher rd = pRequest.getRequestDispatcher("/transacaoIncluir.jsp");
        rd.forward(pRequest, pResponse);

    }

    @Override
    public void salvar(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {

        TransacaoDao transacaoDao = new TransacaoDao();
        Transacao transacao = requestForm(pRequest);

        int retorno = transacaoDao.salvar(transacao);

        if (retorno > 0) {
            mostraAlertMsg(pRequest, pResponse, "OK", "Cadastro de Transacao", "Registro salvo com sucesso!", "transacao", "listar");
        } else {
            mostraAlertMsg(pRequest, pResponse, "ERRO", "Cadastro de Transacao", "05 Erro ao salvar o registro, por favor, tente novamente!", "transacao", "principal");
        }
    }

    @Override
    public void alterar(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
        TransacaoDao transacaoDao = new TransacaoDao();
        Transacao transacao = requestForm(pRequest);

        boolean retorno = transacaoDao.atualizar(transacao);
           
        if (retorno) {
            mostraAlertMsg(pRequest, pResponse, "OK", "Alteração de Transacao", "Registro salvo com sucesso!", "transacao", "listar");
        } else {

            mostraAlertMsg(pRequest, pResponse, "ERRO", "Alteração de Transacao", "04 Erro ao salvar o registro, por favor, tente novamente!", "transacao", "principal");
        }

    }

    @Override
    public void deletar(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {

        TransacaoDao transacaoDao = new TransacaoDao();
        Transacao transacao = requestForm(pRequest);

        boolean retorno = transacaoDao.deletar(transacao.getId());

        if (retorno) {
            mostraAlertMsg(pRequest, pResponse, "OK", "Excluir de Transacao", "Registro excluido com sucesso!", "transacao", "principal");
        } else {
            mostraAlertMsg(pRequest, pResponse, "ERRO", "Excluir de Transacao", "03 Erro ao excluir o registro, por favor, tente novamente!", "transacao", "principal");
        }
    }

    @Override
    public void listar(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {

        TransacaoDao transacaoDao = new TransacaoDao();
        List<Transacao> transacoes = transacaoDao.listar();

        if (transacoes != null) {

            List<Map> resultado = new ArrayList<Map>();

            for (Transacao transacao : transacoes) {

                HashMap<String, String> map = new HashMap<String, String>();
                map.put("id", Integer.toString(transacao.getId()));
                map.put("valor", Float.toString(transacao.getValor()));
                map.put("data", transacao.getDate().toString());
                map.put("cartaoNome", transacao.getCartao().getNome());
                map.put("cartaoId", Integer.toString(transacao.getCartao().getId()));
                map.put("usuarioNome", transacao.getUsuario().getNome());
                map.put("usuarioId", Integer.toString(transacao.getUsuario().getId()));

                resultado.add(map);

            }

            pRequest.setAttribute("transacoes", resultado);

            RequestDispatcher rd = pRequest.getRequestDispatcher("/transacaoListar.jsp");
            rd.forward(pRequest, pResponse);

        } else {
            mostraAlertMsg(pRequest, pResponse, "ERRO", "Lista de Transacao", "02 Erro ao localizar os registros, por favor, tente novamente!", "transacao", "principal");
        }

    }

    @Override
    public void detalhe(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {

        TransacaoDao transacaoDao = new TransacaoDao();

        Transacao transacao = transacaoDao.detalhe(requestForm(pRequest).getId());
        System.out.println(transacao.getId());
        if (transacao != null) {

            HashMap<String, String> resultado = new HashMap<String, String>();
            resultado.put("id", Integer.toString(transacao.getId()));
            
            
            SimpleDateFormat fdate = new SimpleDateFormat("dd/MM/yyyy");            
            resultado.put("data", fdate.format(transacao.getDate()));
            
            resultado.put("valor", Float.toString(transacao.getValor()));
            resultado.put("cartaoId", Integer.toString(transacao.getCartao().getId()));
            resultado.put("cartaoNome", transacao.getCartao().getNome());

            pRequest.setAttribute("transacao", resultado);

            RequestDispatcher rd = pRequest.getRequestDispatcher("/transacaoAlterar.jsp");
            rd.forward(pRequest, pResponse);

        } else {
            mostraAlertMsg(pRequest, pResponse, "ERRO", "Detalhe de Transacao", "01 Erro ao localizar o registro, por favor, tente novamente!", "transacao", "principal");
        }

    }

    @Override
    public void combo(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imprimir(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
