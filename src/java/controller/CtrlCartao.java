/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Cartao;
import entity.dao.CartaoDao;
import entity.dao.ConnectionManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author cleiton
 */
public class CtrlCartao implements CtrlBase {

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

    private Cartao requestForm(HttpServletRequest pRequest) {

        Cartao retorno = new Cartao();

        if (pRequest.getParameter("txtId") != null) {
            retorno.setId(Integer.parseInt(pRequest.getParameter("txtId")));
        }

        if (pRequest.getParameter("txtNome") != null) {
            retorno.setNome(pRequest.getParameter("txtNome"));
        }

        if (pRequest.getParameter("txtCarencia") != null) {
            retorno.setCarencia(Integer.parseInt(pRequest.getParameter("txtCarencia")));
        }

        if (pRequest.getParameter("txtTaxa") != null) {
            retorno.setTaxa(Float.parseFloat(pRequest.getParameter("txtTaxa")));
        }

        return retorno;
    }

    @Override
    public void principal(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {

        RequestDispatcher rd = pRequest.getRequestDispatcher("/cartaoIncluir.jsp");
        rd.forward(pRequest, pResponse);

    }

    @Override
    public void salvar(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {

        CartaoDao cartaoDao = new CartaoDao();

        Cartao cartao = requestForm(pRequest);

        int retorno = cartaoDao.salvar(cartao);

        if (retorno > 0) {
            mostraAlertMsg(pRequest, pResponse, "OK", "Cadastro de Cartao", "Registro salvo com sucesso!", "cartao", "listar");
        } else {
            mostraAlertMsg(pRequest, pResponse, "ERRO", "Cadastro de Cartao", "Erro ao salvar o registro, por favor, tente novamente!", "cartao", "principal");
        }
    }

    @Override
    public void alterar(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
        CartaoDao cartaoDao = new CartaoDao();
        Cartao cartao = requestForm(pRequest);

        boolean retorno = cartaoDao.atualizar(cartao);

        if (retorno) {
            mostraAlertMsg(pRequest, pResponse, "OK", "Alteração de Cartao", "Registro salvo com sucesso!", "cartao", "listar");
        } else {
            mostraAlertMsg(pRequest, pResponse, "ERRO", "Alteração de Cartao", "Erro ao salvar o registro, por favor, tente novamente!", "cartao", "principal");
        }

    }

    @Override
    public void deletar(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {

        CartaoDao cartaoDao = new CartaoDao();
        Cartao cartao = requestForm(pRequest);

        boolean retorno = cartaoDao.deletar(cartao.getId());

        if (retorno) {
            mostraAlertMsg(pRequest, pResponse, "OK", "Excluir de Cartao", "Registro excluido com sucesso!", "cartao", "principal");
        } else {
            mostraAlertMsg(pRequest, pResponse, "ERRO", "Excluir de Cartao", "Erro ao excluir o registro, por favor, tente novamente!", "cartao", "principal");
        }
    }

    @Override
    public void listar(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {

        CartaoDao cartaoDao = new CartaoDao();
        List<Cartao> cartoes = cartaoDao.listar();

        if (cartoes != null) {

            List<Map> resultado = new ArrayList<Map>();

            for (Cartao cartao : cartoes) {

                HashMap<String, String> map = new HashMap<String, String>();
                map.put("id", Integer.toString(cartao.getId()));
                map.put("nome", cartao.getNome());
                map.put("carencia", Integer.toString(cartao.getCarencia()));
                map.put("taxa", Float.toString(cartao.getTaxa()));
                resultado.add(map);

            }

            pRequest.setAttribute("cartoes", resultado);

            RequestDispatcher rd = pRequest.getRequestDispatcher("/cartaoListar.jsp");
            rd.forward(pRequest, pResponse);

        } else {
            mostraAlertMsg(pRequest, pResponse, "ERRO", "Lista de Cartao", "Erro ao localizar os registros, por favor, tente novamente!", "cartao", "principal");
        }

    }

    @Override
    public void detalhe(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {

        CartaoDao cartaoDao = new CartaoDao();

        Cartao cartao = cartaoDao.detalhe(requestForm(pRequest).getId());

        if (cartao != null) {

            HashMap<String, String> resultado = new HashMap<String, String>();
            resultado.put("id", Integer.toString(cartao.getId()));
            resultado.put("nome", cartao.getNome());
            resultado.put("carencia", Integer.toString(cartao.getCarencia()));
            resultado.put("taxa", Float.toString(cartao.getTaxa()));

            pRequest.setAttribute("cartao", resultado);

            RequestDispatcher rd = pRequest.getRequestDispatcher("/cartaoAlterar.jsp");
            rd.forward(pRequest, pResponse);

        } else {
            mostraAlertMsg(pRequest, pResponse, "ERRO", "Detalhe de Cartao", "Erro ao localizar o registro, por favor, tente novamente!", "cartao", "principal");
        }

    }

    @Override
    public void combo(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {

        CartaoDao cartaoDao = new CartaoDao();
        List<Cartao> cartoes = cartaoDao.listar();

        Cartao cartaoSelecionado = new Cartao();
        cartaoSelecionado = requestForm(pRequest);

        if (cartoes != null) {

            List<Map> resultado = new ArrayList<Map>();

            for (Cartao cartao : cartoes) {

                HashMap<String, String> map = new HashMap<String, String>();
                map.put("cartaoSelecionado", Integer.toString(cartaoSelecionado.getId()));
                map.put("id", Integer.toString(cartao.getId()));
                map.put("nome", cartao.getNome());
                map.put("carencia", Integer.toString(cartao.getCarencia()));
                map.put("taxa", Float.toString(cartao.getTaxa()));
                resultado.add(map);

            }

            pRequest.setAttribute("cartoes", resultado);

            RequestDispatcher rd = pRequest.getRequestDispatcher("/cartaoCombo.jsp");
            rd.forward(pRequest, pResponse);

        } else {
            mostraAlertMsg(pRequest, pResponse, "ERRO", "Lista de Cartao", "Erro ao localizar os registros, por favor, tente novamente!", "cartao", "principal");
        }

    }

    @Override
    public void imprimir(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {

        try {
            ConnectionManager conn = new ConnectionManager();
                       
            pResponse.setContentType("application/pdf");    
            pResponse.setHeader("Content-disposition","attachment;filename=transacao.pdf");  
            String caminho = "/jasper/";      
            byte[] bytes = JasperRunManager.runReportToPdf(caminho + "TransacaoCartao.jasper", null, ConnectionManager.getConnection());                    
            pResponse.setContentLength(bytes.length);    
            ServletOutputStream ouputStream = pResponse.getOutputStream();    
            ouputStream.write(bytes, 0, bytes.length);    
            ouputStream.flush();    
            ouputStream.close();             
            
        } catch (JRException ex) {
            Logger.getLogger(CtrlCartao.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("ok");
    }

}
