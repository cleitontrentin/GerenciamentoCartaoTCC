<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-group">
    <label>Cartao </label>
    <select  class="form-control" placeholder="Selecione um cartão" id="txtCartao" name="txtCartao">
        <c:forEach var="cartao" items="${cartoes}">
            <c:if test="${cartao.id == cartao.cartaoSelecionado}">
                <option value="${cartao.id}" selected="selected" >${cartao.nome} </option>
            </c:if>
            <c:if test="${cartao.id != cartao.cartaoSelecionado}">
                <option value="${cartao.id}">${cartao.nome}</option>
            </c:if>
        </c:forEach>
    </select>
</div>
