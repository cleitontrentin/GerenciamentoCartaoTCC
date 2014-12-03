/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controller.CtrlBase;
import controller.CtrlCartao;
import controller.CtrlTransacao;
import controller.CtrlUsuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cleiton
 */
public class servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        boolean vSessaoLogado = false;

        if (session.getAttribute("SessaoLogado") != null) {
            vSessaoLogado = Boolean.parseBoolean(session.getAttribute("SessaoLogado").toString());
        }
        if (!vSessaoLogado) {

            if (request.getParameter("txtMetodo").equals("logon")) {
                CtrlUsuario ctrlUsuario = new CtrlUsuario();
                ctrlUsuario.logon(request, response);
            } else {
                CtrlUsuario ctrlUsuario = new CtrlUsuario();
                ctrlUsuario.login(request, response);
            }

        } else {

            if (request.getParameter("txtMetodo").equals("logoff")) {
                CtrlUsuario ctrlUsuario = new CtrlUsuario();
                ctrlUsuario.logoff(request, response);
            } else {

                CtrlBase vObjeto = null;
                if (request.getParameter("txtObjeto").equals("usuario")) {

                    vObjeto = new CtrlUsuario();

                } else if (request.getParameter("txtObjeto").equals("cartao")) {

                    vObjeto = new CtrlCartao();

                } else if (request.getParameter("txtObjeto").equals("transacao")) {

                    vObjeto = new CtrlTransacao();
                    
                }

                if (request.getParameter("txtMetodo").equals("principal")) {

                    if (vObjeto != null) {
                        vObjeto.principal(request, response);
                    }

                } else if (request.getParameter("txtMetodo").equals("salvar")) {

                    if (vSessaoLogado) {
                        vObjeto.salvar(request, response);
                    }

                } else if (request.getParameter("txtMetodo").equals("alterar")) {

                    if (vSessaoLogado) {
                        vObjeto.alterar(request, response);
                    }

                } else if (request.getParameter("txtMetodo").equals("deletar")) {

                    if (vSessaoLogado) {
                        vObjeto.deletar(request, response);
                    }

                } else if (request.getParameter("txtMetodo").equals("listar")) {

                    if (vSessaoLogado) {
                        vObjeto.listar(request, response);
                    }

                } else if (request.getParameter("txtMetodo").equals("detalhe")) {

                    if (vSessaoLogado) {
                        vObjeto.detalhe(request, response);
                    }

                } else if (request.getParameter("txtMetodo").equals("combo")) {

                    if (vSessaoLogado) {
                        vObjeto.combo(request, response);
                    }

                } else if (request.getParameter("txtMetodo").equals("imprimir")) {

                    if (vSessaoLogado) {
                        vObjeto.imprimir(request, response);
                    }

                }
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

}
