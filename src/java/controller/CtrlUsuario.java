/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Usuario;
import entity.dao.UsuarioDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cleiton
 */
public class CtrlUsuario implements CtrlBase {

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

    private Usuario requestForm(HttpServletRequest pRequest) {

        Usuario retorno = new Usuario();

        if (pRequest.getParameter("txtId") != null) {
            retorno.setId(Integer.parseInt(pRequest.getParameter("txtId")));
        }

        if (pRequest.getParameter("txtNome") != null) {
            retorno.setNome(pRequest.getParameter("txtNome"));
        }

        if (pRequest.getParameter("txtLogin") != null) {
            retorno.setLogin(pRequest.getParameter("txtLogin"));
        }

        if (pRequest.getParameter("txtSenha") != null) {
            retorno.setSenha(pRequest.getParameter("txtSenha"));
        }

        return retorno;
    }

    @Override
    public void principal(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {

        RequestDispatcher rd = pRequest.getRequestDispatcher("/usuarioIncluir.jsp");
        rd.forward(pRequest, pResponse);

    }

    @Override
    public void salvar(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {

        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario usuario = requestForm(pRequest);

        int retorno = usuarioDao.salvar(usuario);

        if (retorno > 0) {
            mostraAlertMsg(pRequest, pResponse, "OK", "Cadastro de Usuario", "Registro salvo com sucesso!", "usuario", "principal");
        } else {
            mostraAlertMsg(pRequest, pResponse, "ERRO", "Cadastro de Usuario", "Erro ao salvar o registro, por favor, tente novamente!", "usuario", "principal");
        }
    }

    @Override
    public void alterar(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario usuario = requestForm(pRequest);

        boolean retorno = usuarioDao.atualizar(usuario);

        if (retorno) {
            mostraAlertMsg(pRequest, pResponse, "OK", "Alteração de Usuario", "Registro salvo com sucesso!", "usuario", "listar");
        } else {
            mostraAlertMsg(pRequest, pResponse, "ERRO", "Alteração de Usuario", "Erro ao salvar o registro, por favor, tente novamente!", "usuario", "principal");
        }

    }

    @Override
    public void deletar(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {

        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario usuario = requestForm(pRequest);

        boolean retorno = usuarioDao.deletar(usuario.getId());

        if (retorno) {
            mostraAlertMsg(pRequest, pResponse, "OK", "Excluir de Usuario", "Registro excluido com sucesso!", "usuario", "principal");
        } else {
            mostraAlertMsg(pRequest, pResponse, "ERRO", "Excluir de Usuario", "Erro ao excluir o registro, por favor, tente novamente!", "usuario", "principal");
        }
    }

    @Override
    public void listar(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {

        UsuarioDao usuarioDao = new UsuarioDao();
        List<Usuario> usuarios = usuarioDao.listar();

        if (usuarios != null) {

            List<Map> resultado = new ArrayList<Map>();

            for (Usuario usuario : usuarios) {

                HashMap<String, String> map = new HashMap<String, String>();
                map.put("id", Integer.toString(usuario.getId()));
                map.put("nome", usuario.getNome());
                map.put("login", usuario.getLogin());
                map.put("senha", usuario.getSenha());
                resultado.add(map);

            }

            pRequest.setAttribute("usuarios", resultado);

            RequestDispatcher rd = pRequest.getRequestDispatcher("/usuarioListar.jsp");
            rd.forward(pRequest, pResponse);

        } else {
            mostraAlertMsg(pRequest, pResponse, "ERRO", "Lista de Usuario", "Erro ao localizar os registros, por favor, tente novamente!", "usuario", "principal");
        }

    }

    @Override
    public void detalhe(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {

        UsuarioDao usuarioDao = new UsuarioDao();

        Usuario usuario = usuarioDao.detalhe(requestForm(pRequest).getId());

        if (usuario != null) {

            HashMap<String, String> resultado = new HashMap<String, String>();
            resultado.put("id", Integer.toString(usuario.getId()));
            resultado.put("nome", usuario.getNome());
            resultado.put("login", usuario.getLogin());
            resultado.put("senha", usuario.getSenha());

            pRequest.setAttribute("usuario", resultado);

            RequestDispatcher rd = pRequest.getRequestDispatcher("/usuarioAlterar.jsp");
            rd.forward(pRequest, pResponse);

        } else {
            mostraAlertMsg(pRequest, pResponse, "ERRO", "Detalhe de Usuario", "Erro ao localizar o registro, por favor, tente novamente!", "usuario", "principal");
        }

    }

    @Override
    public void combo(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void logon(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {

        UsuarioDao usuarioDao = new UsuarioDao();

        Usuario usuario = usuarioDao.login(requestForm(pRequest).getLogin(), requestForm(pRequest).getSenha());

        if (usuario != null) {

            HttpSession session = pRequest.getSession();
            session.setAttribute("usuarioLogado", usuario);
            session.setAttribute("SessaoLogado", true);

            mostraAlertMsg(pRequest, pResponse, "Ok", "Login", "Login efetuado com sucesso", "", "index");

        } else {
            mostraAlertMsg(pRequest, pResponse, "ERRO", "Login", "usuário e/ou senha inválidos, por favor, tente novamente!", "usuario", "login");
        }

    }

    public void logoff(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
        HttpSession session = pRequest.getSession();
        session.invalidate();

        mostraAlertMsg(pRequest, pResponse, "Ok", "Logoff", "Logoff efetuado com sucesso", "", "index");

    }

    public void login(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
        mostraAlertMsg(pRequest, pResponse, "", "Login requirido", "Para acessar essa funcionalidade você deve estar logado", "", "login");
    }

    @Override
    public void imprimir(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
