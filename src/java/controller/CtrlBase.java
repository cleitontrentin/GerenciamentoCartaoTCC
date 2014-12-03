/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cleiton
 */
public interface CtrlBase {
    
    public void principal(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException;

    public void salvar(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException;

    public void alterar(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException;

    public void deletar(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException;

    public void listar(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException;

    public void detalhe(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException;
    
    public void combo(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException;

    public void imprimir(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException;
}
